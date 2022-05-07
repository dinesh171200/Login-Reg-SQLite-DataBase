package com.example.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvSignUp;
    EditText etLUsername, etLPassword;
    Button btnLogin;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        tvSignUp = findViewById(R.id.tvSignUp);
        etLPassword = findViewById(R.id.etLPassword);
        etLUsername = findViewById(R.id.etLUsername);
        btnLogin = findViewById(R.id.btnLogin);

        myDB = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Luser = etLUsername.getText().toString();
                String Lpass = etLPassword.getText().toString();
                if (Luser.equals("") || Lpass.equals("")){
                    Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean result = myDB.checkusernamePassword(Luser, Lpass);
                    if(result == true){
                        Intent intent = new Intent(MainActivity.this, Open.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "NO User Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
    }
}