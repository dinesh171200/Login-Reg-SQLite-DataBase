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

public class Register extends AppCompatActivity {

    EditText etUsername, etPassword, etConfirm;
    TextView tvLogin;
    Button btnRegister;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirm = findViewById(R.id.etConfirm);
        tvLogin = findViewById(R.id.tvLogin);
        btnRegister = findViewById(R.id.btnRegister);

        myDB = new DBHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String pass = etPassword.getText().toString();
                String conf = etConfirm.getText().toString();
                if(username.equals("") || pass.equals("") || conf.equals("") ){
                    Toast.makeText(Register.this, "Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(conf)){
                       boolean usercheckresult = myDB.checkusename(username);
                       if(usercheckresult == false){
                           boolean regResult =  myDB.insertData(username,pass);
                           if(regResult == true){
                               Toast.makeText(Register.this, "Registeration Successful", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(Register.this, MainActivity.class);
                               startActivity(intent);
                           }
                           else{
                               Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                           }
                       }
                       else {
                           Toast.makeText(Register.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                       }
                    }
                    else{
                        Toast.makeText(Register.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}