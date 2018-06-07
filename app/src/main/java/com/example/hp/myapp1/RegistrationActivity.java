package com.example.hp.myapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class RegistrationActivity extends AppCompatActivity {
    private EditText userName,userEmail,userPassword;
    private Button regButton;
    private TextView userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        userName = (EditText)findViewById(R.id.etUserName);
        userPassword = (EditText)findViewById(R.id.etPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        userLogin = (TextView) findViewById(R.id.tvUserLogin);
        regButton = (Button)findViewById(R.id.btnRegister);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {


                    String name = userName.getText().toString().trim();
                    String email = userEmail.getText().toString().trim();
                    String password = userPassword.getText().toString().trim();
                    try
                    {
                        ContactsDB db = new ContactsDB(RegistrationActivity.this);
                        db.open();
                        db.createEntry(name, email, password);
                        db.close();
                        Toast.makeText(RegistrationActivity.this, "Successgully saved", Toast.LENGTH_SHORT).show();
                        userName.setText("");
                        userEmail.setText("");
                        userPassword.setText("");
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(RegistrationActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    }
                }
                });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });
    }




    private Boolean validate(){
        Boolean result=false;
        String name= userName.getText().toString();
        String password=userPassword.getText().toString();
        String email=userEmail.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(RegistrationActivity.this, "please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result=true;
        }
        return result;
    }
}
