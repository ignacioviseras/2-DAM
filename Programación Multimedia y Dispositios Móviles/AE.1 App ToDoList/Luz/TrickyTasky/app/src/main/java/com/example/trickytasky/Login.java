package com.example.trickytasky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    Button botonLogin;
    TextView botonRegistro;
    EditText emailText, passText;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        emailText = findViewById(R.id.cajaCorreo);
        passText = findViewById(R.id.cajaPass);

        botonLogin = findViewById(R.id.button);
        botonLogin.setOnClickListener(view -> {
            String email = emailText.getText().toString();
            String password = passText.getText().toString();

            String regex = "^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(email);
            if (email.isEmpty()) {
                emailText.setError("Empty field");
            } else if (!m.find()) {//si no se cumple el filtro regex del correo avisa
                emailText.setError("Must be an email");
            } else if (password.isEmpty()) {
                passText.setError("Empty field");
            }else if(!(password.length() >= 6)) {
                passText.setError("Need 6 or more characters");
            }else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

        botonRegistro = findViewById(R.id.botonResgistro);
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //crear usuario en firebase
                String email = emailText.getText().toString();
                String password = passText.getText().toString();

                String regex = "^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)$";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(email);
                if (email.isEmpty()) {
                    emailText.setError("Empty field");
                } else if (!m.find()) {//si no se cumple el filtro regex del correo avisa
                    emailText.setError("Must have an email");
                } else if (password.isEmpty()) {
                    passText.setError("Empty field");
                }else if(!(password.length() >= 6)) {
                    passText.setError("Need 6 or more characters");
                }else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(Login.this, "Account created", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Login.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Login.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}

