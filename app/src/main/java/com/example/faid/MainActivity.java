package com.example.faid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Animation move, fadeIn;
    ImageView logo;
    Snackbar snackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAuth = FirebaseAuth.getInstance();

    }

    public void goToLogIn(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void repeatPassword(View view) {
        Button signup = findViewById(R.id.signupbutton);
        TextInputEditText passwordet = findViewById(R.id.password);
        TextInputEditText repeatpasswordet = findViewById(R.id.repeatpassword);
        String password = passwordet.getText().toString();
        String repeatpassword = repeatpasswordet.getText().toString();
        if (repeatpassword.equals(password)) {
            signUp(view);
        } else {
            Toast.makeText(this, "Passwords should be the same.", Toast.LENGTH_SHORT).show();
        }
    }

    public void signUp(final View view) {
        TextInputEditText loginet = findViewById(R.id.email);
        TextInputEditText passwordet = findViewById(R.id.password);
        String email = loginet.getText().toString();
        String password = passwordet.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("state", "signup");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                            else {
                                // If sign in fails, display a message to the user.
                                Snackbar.make(findViewById(android.R.id.content), "Sign Up failed.", Snackbar.LENGTH_SHORT).show();
                                System.out.println(task.getException());
                            }

                            // ...
                        }
                    });
        }



}