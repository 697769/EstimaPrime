package br.com.estimaprime.aplicativo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import Controle.User;
import Modelo.UserM;

public class Signup extends Activity {

    Controle.User User = new User(this);

    private Button btnSigup;
    private String signupEmail,signupSenha;
    private EditText editTextEmail,editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSigup = (Button) findViewById(R.id.btn_sigup);
        editTextEmail = (EditText) findViewById(R.id.SigUpEmail);
        editTextSenha = (EditText) findViewById(R.id.SignUpPassword);

        btnSigup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (Cadastrar()==true){
                    Voltar();
                }else { return; }
            }
        });
    }
    private boolean Cadastrar(){
        atribuirEmailSenha();
        if (verificaEmailSenhaVazios()==false){
            Toast toast = Toast.makeText(Signup.this, "Informe um E-mail e uma Senha válidos!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
            toast.show();
        }else
            if (User.verifyEmail(signupEmail)==false){
                Toast toast = Toast.makeText(Signup.this, "Opção de E-mail indisponível!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                toast.show();
            }else{
                UserM usuario = new UserM();
                usuario.setEmail(signupEmail);
                usuario.setPassword(signupSenha);
                User.insertUser(usuario);
                return true;
            }
        return false;
    }
    private void atribuirEmailSenha(){
        signupEmail = editTextEmail.getText().toString().trim();
        signupSenha = editTextSenha.getText().toString().trim();
    }
    private boolean verificaEmailSenhaVazios(){
        if (signupEmail.isEmpty() || signupSenha.isEmpty()) {
            return false;
        }else
            return true;
    }
    private void Voltar(){
        Intent intent = new Intent(Signup.this, Mainactivity.class);
        startActivity(intent);
        kill_activity();
    }
    private void kill_activity() { finish(); }
}