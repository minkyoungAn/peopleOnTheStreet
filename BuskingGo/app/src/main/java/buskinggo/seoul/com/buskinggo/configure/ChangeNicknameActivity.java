package buskinggo.seoul.com.buskinggo.configure;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import buskinggo.seoul.com.buskinggo.MyApplication;
import buskinggo.seoul.com.buskinggo.R;

public class ChangeNicknameActivity extends AppCompatActivity {
    String userNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_nickname);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        userNickname = jsonResponse.getString("userNickname");
                        TextView nicknameText = findViewById(R.id.changeNicknameText);
                        nicknameText.setText(userNickname);
                    } else {
                        Toast.makeText(getApplicationContext(), "정보를 가져오지 못했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        NicknameRequest nicknameChangeRequest = new NicknameRequest(MyApplication.userEmail, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ChangeNicknameActivity.this);
        queue.add(nicknameChangeRequest);
    }
}
