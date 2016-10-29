package br.com.estimaprime.aplicativo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dao.UserDAO;
import modelo.User;

public class Signup extends Activity {
    private Button btnSigup2;
    private String signupEmail,signupSenha;
    private EditText editTextEmail,editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSigup2 = (Button) findViewById(R.id.btnSigup2);
        editTextEmail = (EditText) findViewById(R.id.SigUpEmail);
        editTextSenha = (EditText) findViewById(R.id.SignUpPassword);

        btnSigup2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (Cadastrar()){ Voltar(); }
            }
        });
    }
    protected boolean Cadastrar(){
        signupEmail = editTextEmail.getText().toString().trim();
        signupSenha = editTextSenha.getText().toString().trim();

        UserDAO userDAO = new UserDAO(getApplicationContext());

        if (signupEmail.equals("") || signupSenha.equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Informe um E-mail e uma Senha válidos!", Toast.LENGTH_LONG);
            toast.setGravity(android.view.Gravity.CENTER | android.view.Gravity.CENTER, 0, 0);
            toast.show();
            return false;
        }else if (!userDAO.verifyEmail(signupEmail)){
                Toast toast = Toast.makeText(getApplicationContext(), "Opção de E-mail indisponível!", Toast.LENGTH_LONG);
                toast.setGravity(android.view.Gravity.CENTER | android.view.Gravity.CENTER, 0, 0);
                toast.show();
                return false;
            }else{
                User usuario = new User();
                usuario.setEmail(signupEmail);
                usuario.setPassword(signupSenha);
                userDAO.insertUser(usuario);
                Toast toast = Toast.makeText(getApplicationContext(), "Cadastro Efetuado com Sucesso!", Toast.LENGTH_LONG);
                toast.setGravity(android.view.Gravity.CENTER | android.view.Gravity.CENTER, 0, 0);
                toast.show();
                return true;
        }
    }

    protected void Voltar(){
        Intent intent = new Intent(Signup.this, Mainactivity.class);
        startActivity(intent);
    }
}