package com.example.hp.myapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  private EditText Name;
  private EditText Password;
  private TextView Info;
  private Button Login;
  private Button Register;

private int counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvinfo);
        Login = (Button)findViewById(R.id.btnLogin);
        Register = (Button)findViewById(R.id.btregister);

        Info.setText("No of attempts remaining:5");

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });
    }

    private void validate(String userName,String userPassword) {
        if ((userName.equals("Amisha")) && (userPassword.equals("234"))) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            Log.d("myTag", "success");
            startActivity(intent);


        } else {
            counter--;
            Info.setText("No of attempts remaining; " + String.valueOf(counter));
            if (counter == 0) {
                Login.setEnabled(false);
            }

        }
    }}
