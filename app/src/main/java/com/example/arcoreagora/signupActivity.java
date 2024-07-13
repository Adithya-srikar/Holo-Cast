package com.example.arcoreagora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signupActivity extends AppCompatActivity {
    private EditText nameEditText, emailEditText, usernameEditText, passwordEditText, reEnterPasswordEditText;
    private RadioGroup roleRadioGroup;
    private RadioButton teacherRadioButton, studentRadioButton;
    private Button signUpButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        reEnterPasswordEditText = findViewById(R.id.reEnterPasswordEditText);
        roleRadioGroup = findViewById(R.id.roleRadioGroup);
        teacherRadioButton = findViewById(R.id.teacherRadioButton);
        studentRadioButton = findViewById(R.id.studentRadioButton);
        signUpButton = findViewById(R.id.signUpButton);

        mAuth = FirebaseAuth.getInstance();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String reEnterPassword = reEnterPasswordEditText.getText().toString();
                int selectedRoleId = roleRadioGroup.getCheckedRadioButtonId();
                String role = (selectedRoleId == R.id.teacherRadioButton) ? "Teacher" : "Student";

                if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || reEnterPassword.isEmpty() || selectedRoleId == -1) {
                    Toast.makeText(signupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(reEnterPassword)) {
                    Toast.makeText(signupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(signupActivity.this, task -> {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null) {
                                        // Sign up success, update UI with the signed-in user's information
                                        Toast.makeText(signupActivity.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(signupActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(signupActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
}
