package edu.hackeru.edu.firebaseauth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {
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
    }

    @OnClick(R.id.btnRegister)
    public void onBtnRegisterClicked() {
        //client side validation
        if (!isEmailValid() | !isPasswordValid()) return;
        String email = getEmail();
        String password = getPassword();
    }
}
