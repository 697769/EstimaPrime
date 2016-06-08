package br.com.estimaprime.aplicativo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import Controle.User;

public class Mainactivity extends Activity {
    private String loginSenha,loginEmail;
    private EditText editText,editText2;
    private Button btnEntrar,btnForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.Login_email);
        editText2 = (EditText) findViewById(R.id.Login_senha);
        btnEntrar = (Button) findViewById(R.id.btn_entrar);
        btnForget = (Button) findViewById(R.id.btn_forget);

        btnEntrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){ entrar(view); }
            });

        btnForget.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Mainactivity.this, Forget.class);
                startActivity(intent);
            }
        });
    }

    public void onClickSigup(View v){
        startActivityForResult(new Intent(this, Signup.class),1);
    }

    public void entrar(View view){ //TODO: Metodo Login/Sigin
        atribuirEmailPassword();
        if (verificaEmailSenhaVazios()==true){
            Toast toast = Toast.makeText(this, "Informe E-mail e Senha!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }else{
            Controle.User User = new User(getApplicationContext());
            if (User.checkLogin(loginEmail,loginSenha)==true){
                salvarLogin();
                Logar();
            }else{
                Toast toast = Toast.makeText(this, "Login incorreto!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                toast.show();
                return;
            }

        }
    }
    private void atribuirEmailPassword(){
        loginEmail = editText.getText().toString().trim();
        loginSenha = editText2.getText().toString().trim();
    }
    public boolean verificaEmailSenhaVazios(){
        if (loginEmail.isEmpty() || loginSenha.isEmpty())
            return true;
        return false;
    }
    private void salvarLogin(){
        Controle.User User = new User(getApplicationContext());

        SharedPreferences sharedPreferences=getSharedPreferences("estimaprime", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("GLO_USUARIO", User.getIdUser(loginEmail));
        editor.commit();
    }
    private void Logar(){
        Intent intent = new Intent(Mainactivity.this,Enterprises.class);
        startActivity(intent);
    }
}
