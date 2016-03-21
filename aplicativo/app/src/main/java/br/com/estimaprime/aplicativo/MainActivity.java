package br.com.estimaprime.aplicativo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    //DAL
    DBManager dbManager = new DBManager(this);
    //DATA SECTION
    private Button btn_entrar,btn_sigup,btn_forget;
    //METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Definições Default
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Criar usuario ADMIN
        dbManager.addLogin("admin@admin","123");
        //CHANGES
        btn_entrar = (Button) findViewById(R.id.btn_entrar);
        btn_sigup = (Button) findViewById(R.id.btn_sigup);
        btn_forget = (Button) findViewById(R.id.btn_forget);
        // Lista de Empresas
        ArrayList<String> enterprise = new ArrayList<String>();

        btn_entrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                entrar(view);
            }
            });

        btn_sigup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
            });

        btn_forget.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, Forget.class);
                startActivity(intent);
            }
        });
    }

    public void entrar(View view){ //TODO: Metodo Login/Sigin
        String button_text = ((Button) view).getText().toString();
        String user_password;
        Boolean user_email;

        EditText editText = (EditText) findViewById(R.id.Login_email);
        String login_email = editText.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.Login_senha);
        String login_senha = editText2.getText().toString();

        //VALIDA EMAIL
        if (login_email.isEmpty()) {
            Toast toast = Toast.makeText(this, "Informe um E-mail", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }else{
            user_email = dbManager.getEmail(login_email);
            if(!user_email){
                Toast toast = Toast.makeText(this, "É necessario cadastro!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                toast.show();
                return;
            }
        }

        //VALIDA SENHA
        if (login_senha.isEmpty()) {
            Toast toast = Toast.makeText(this, "Informe uma Senha", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
            toast.show();
            return;
        } else {
            user_password = dbManager.getPassword(login_email);
            if (!login_senha.equals(user_password)) {
                Toast toast = Toast.makeText(this, "Senha invalida!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                toast.show();
                return;
            }
        }
        Intent intent = new Intent(MainActivity.this,Enterprises.class);
        startActivity(intent);
    }
}
