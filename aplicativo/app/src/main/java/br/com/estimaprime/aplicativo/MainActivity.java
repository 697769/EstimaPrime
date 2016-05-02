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

import DAO.User;

public class MainActivity extends Activity {
    //DAO
    User user = new User(this);
    //DATA SECTION
    private Button btn_entrar,btn_sigup,btn_forget;
    //METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Definições Default
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //user.deleteAllUsers();
        //Criar usuario ADMIN
        //user.addUser("1@admin", "123");
        //user.addUser("2@admin","123");
        //user.addUser("3@admin","123");

        btn_entrar = (Button) findViewById(R.id.btn_entrar);
        btn_sigup = (Button) findViewById(R.id.btn_sigup);
        btn_forget = (Button) findViewById(R.id.btn_forget);

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

        if (login_email.isEmpty()) {
            Toast toast = Toast.makeText(this, "Informe um E-mail", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }else{
            user_email = user.getEmail(login_email);
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
            user_password = user.getPassword(login_email);
            if (!login_senha.equals(user_password)) {
                Toast toast = Toast.makeText(this, "Senha invalida!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                toast.show();
                return;
            }
        }
        //Salvar usuário logado
        SharedPreferences sharedPreferences=getSharedPreferences("estimaprime", Context.MODE_PRIVATE); //salvar em modo privado!
        SharedPreferences.Editor editor = sharedPreferences.edit(); //declarar o editor
        editor.putInt("GLO_USUARIO",user.getIdUser(login_email)); //salvar o email logado
        editor.commit(); //salvar dados no arquivo
        //-------------------------------
        Intent intent = new Intent(MainActivity.this,Enterprises.class);
        startActivity(intent);
    }
}
