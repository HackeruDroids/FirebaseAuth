package edu.hackeru.edu.firebaseauth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnLogin)
    BootstrapButton btnLogin;
    @BindView(R.id.btnRegister)
    BootstrapButton btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
    }

    private String getEmail() {
        return etEmail.getText().toString();
    }

    private String getPassword() {
        return etPassword.getText().toString();
    }

    private boolean isEmailValid() {
        String email = getEmail();
        boolean valid = email.length() > 6 && email.contains("@");//...
        //boolean valid = Patterns.EMAIL_ADDRESS.matcher(email).matches();

        if (!valid)
            etEmail.setError("Invalid email address.");

        return valid;
    }

    private boolean isPasswordValid() {
        boolean valid = getPassword().length() > 5;

        if (!valid) etPassword.setError("Must contain at least 6 Characters");

        return valid;
    }

    @OnClick(R.id.btnLogin)
    public void onBtnLoginClicked() {
        //client side validation
        if (!isEmailValid() | !isPasswordValid()) return;

        showProgress(true);
        String email = getEmail();
        String password = getPassword();
        //INTERNET PERMISSION
        mAuth.signInWithEmailAndPassword(email, password).
                addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        showProgress(false);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }).
                addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showProgress(false);
                        Snackbar.make(btnLogin, e.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
                    }
                });
    }

    ProgressDialog dialog;
    private void showProgress(boolean show){
        if (dialog == null){
            dialog = new ProgressDialog(this/*Context*/);
            //title
            dialog.setTitle("Connecting to server");
            //message
            dialog.setMessage("Please Wait");
            //cancelable
            dialog.setCancelable(false);
        }
        if (show)
            dialog.show();
        else
            dialog.dismiss();
    }

    @OnClick(R.id.btnRegister)
    public void onBtnRegisterClicked() {
        //client side validation
        if (!isEmailValid() | !isPasswordValid()) return;

        showProgress(true);
        String email = getEmail();
        String password = getPassword();
        //INTERNET PERMISSION
        mAuth.createUserWithEmailAndPassword(email, password).
                addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        showProgress(false);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }).
                addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showProgress(false);
                        Snackbar.make(btnLogin, e.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
                    }
                });
    }
}
