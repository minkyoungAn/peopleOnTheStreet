package buskinggo.seoul.com.buskinggo.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import buskinggo.seoul.com.buskinggo.MainActivity;
import buskinggo.seoul.com.buskinggo.MyApplication;
import buskinggo.seoul.com.buskinggo.R;
import buskinggo.seoul.com.buskinggo.register.RegisterActivity;
import buskinggo.seoul.com.buskinggo.dto.UserDTO;

public class LoginActivity extends AppCompatActivity {

    private AlertDialog dialog;
    EditText emailText;
    EditText passwordText;
    UserDTO userDTO;


    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        final Button loginButton = findViewById(R.id.loginButton);
        RelativeLayout rl = findViewById(R.id.rl_login);

        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(emailText.getWindowToken(),0);
                    imm.hideSoftInputFromWindow(passwordText.getWindowToken(),0);
                }

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 final String userEmail = emailText.getText().toString();
                 final String userPassword = passwordText.getText().toString();

                 Response.Listener<String> responseListener = new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         try{
                             JSONObject jsonResponse = new JSONObject(response);
                             boolean success = jsonResponse.getBoolean("success");

                             if(success){
                                 String userNo = jsonResponse.getString("userNo");
                                 String nickname = jsonResponse.getString("Nickname");
                                 String mainPlace = jsonResponse.getString("MainPlace");
                                 String likeGenre = jsonResponse.getString("LikeGenre");
                                 String checkBusker = jsonResponse.getString("checkBusker");
                                 userDTO = new UserDTO(Integer.parseInt(userNo), nickname, mainPlace, likeGenre, Integer.parseInt(checkBusker));

                                 AsyncLogin asyncLogin = new AsyncLogin();
                                 asyncLogin.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userEmail, userPassword);

                                 AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                 dialog = builder.setMessage("로그인에 성공하셨습니다.")
                                         .setPositiveButton("확인", null)
                                         .create();
                                 dialog.show();
                                 // 화면 전환
                                 Bundle bundle = new Bundle();
                                 bundle.putSerializable("userDTO", userDTO);
                                 Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                                 loginIntent.putExtras(bundle);
                                 LoginActivity.this.startActivity(loginIntent);
                                 finish();
                             }else{
                                 // 로그인 실패
                                 AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                 dialog = builder.setMessage("아이디와 패스워드를 다시 확인하세요.")
                                         .setNegativeButton("다시 시도", null)
                                         .create();
                                 dialog.show();
                             }
                         }catch(Exception e){
                             e.printStackTrace();
                         }
                     }
                 };
                LoginRequest loginRequest = new LoginRequest(userEmail, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }


    class AsyncLogin extends AsyncTask<String,Void,Void>{
        @Override
        protected Void doInBackground(String... params) {
            // 로그인 정보 어플리케이션에 저장
            MyApplication.userDTO = userDTO;
            MyApplication.userEmail = params[0];
            MyApplication.password = params[1];
            return null;
        }
    }
}
