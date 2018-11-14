package com.example.project.evote;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private TextView register;
    private EditText email, password;
    private DBHandler dbHandler;
    private User user;
    private String value_email, value_password;
    private List<User> listUser;
    private List<Candidate> listCandidate;
    private Candidate candidate1, candidate2, candidate3, candidate4;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.login_login);
        register = (TextView) findViewById(R.id.login_register);
        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);

        dbHandler = new DBHandler(this);
        candidate1 = new Candidate("Yatharth", "Rathore", 1, 0);
        candidate2 = new Candidate("Shobhit", "Shrivastava", 2, 0);
        candidate3 = new Candidate("Praveen", "Bhanodiya", 3, 0);
        candidate4 = new Candidate("Narendra", "Modi", 4, 0);

        dbHandler.insertCandidate(candidate1);
        dbHandler.insertCandidate(candidate2);
        dbHandler.insertCandidate(candidate3);
        dbHandler.insertCandidate(candidate4);

        // Action
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = dbHandler.getUser(email.getText().toString());
                value_email = email.getText().toString();
                value_password = password.getText().toString();
                if ((user.getEmail().equals(value_email)) && (user.getPassword().equals(value_password))) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("email", user.getEmail());
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Incorrect username/password please try again", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setText("");
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.setText("");
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
