package buskinggo.seoul.com.buskinggo.configure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import buskinggo.seoul.com.buskinggo.R;

public class ConfigureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        Button changeNicknameButton = (Button) findViewById(R.id.changeNicknameButton);
        changeNicknameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NicknameIntent = new Intent(ConfigureActivity.this, ChangeNicknameActivity.class);
                startActivity(NicknameIntent);
            }
        });

        Button changePasswordButton = (Button) findViewById(R.id.changePasswordButton);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PasswordIntent = new Intent(ConfigureActivity.this, ChangePasswordActivity.class);
                startActivity(PasswordIntent);
            }
        });


    }

}
