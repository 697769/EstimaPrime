package br.com.estimaprime.aplicativo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Mainactivity extends Activity {
    private String loginSenha,loginEmail;
    private EditText editText,editText2;
    private Button btnEntrar,btnSignUp,btnForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.Login_email);
        editText2 = (EditText) findViewById(R.id.Login_senha);
        btnEntrar = (Button) findViewById(R.id.btn_entrar);
        btnSignUp = (Button) findViewById(R.id.btn_sigup);
        btnForget = (Button) findViewById(R.id.btn_forget);

        btnEntrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){ entrar(view); }});
        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){ Cadastrar(); }});
        btnForget.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Forget.class);
                startActivity(intent);
            }
        });

//        rest.RestHelper.retrofit().ping(new retrofit2.Callback<String>() {
//            @Override
//            public void onResponse(retrofit2.Call<String> call, retrofit2.Response<String> response) {
//                try {
//                    android.util.Log.i("Resposta da Requisição do Ping",call.execute().body());
//                } catch (java.io.IOException e) {
//                    e.printStackTrace();
//                    android.util.Log.e("Falha na Requisição do Ping",e.getMessage());
//                }
//            }
//            @Override
//            public void onFailure(retrofit2.Call<String> call, Throwable t) {
//
//            }
//        });

        retrofit2.Call<okhttp3.ResponseBody> call = rest.RestHelper.retrofit().ping2();
        call.enqueue(new retrofit2.Callback<okhttp3.ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<okhttp3.ResponseBody> call, retrofit2.Response<okhttp3.ResponseBody> response) {
                android.util.Log.i("Requisicao de ping", response.body().toString());
            }

            @Override
            public void onFailure(retrofit2.Call<okhttp3.ResponseBody> call, Throwable t) {
                android.util.Log.i("Falha ping", t.getMessage());
            }
        });
    }

    protected void entrar(View view){ //TODO: Metodo Login/Sigin

        loginEmail = editText.getText().toString().trim();
        loginSenha = editText2.getText().toString().trim();

//        rest.RestHelper.retrofit().getEmpresas(loginEmail, loginSenha, new retrofit2.Callback<modelo.Enterprise>() {
//            @Override
//            public void onResponse(retrofit2.Call<modelo.Enterprise> call, retrofit2.Response<modelo.Enterprise> response) {
//
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<modelo.Enterprise> call, Throwable t) {
//
//            }
//        });

        dao.UserDAO userDAO1 = new dao.UserDAO(getApplicationContext());

        if (loginEmail.isEmpty() || loginSenha.isEmpty()){
            android.widget.Toast toast = android.widget.Toast.makeText(getApplicationContext(), "Informe E-mail e Senha!", android.widget.Toast.LENGTH_LONG);
            toast.setGravity(android.view.Gravity.CENTER | android.view.Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }else{
            if (userDAO1.checkLogin(loginEmail,loginSenha)){
                android.widget.Toast toast = android.widget.Toast.makeText(getApplicationContext(), "Logado com sucesso!", android.widget.Toast.LENGTH_LONG);
                toast.setGravity(android.view.Gravity.CENTER | android.view.Gravity.CENTER, 0, 0);
                toast.show();
                salvarLogin();
                Logar();
            }else{
                android.widget.Toast toast = android.widget.Toast.makeText(getApplicationContext(), "Login incorreto!", android.widget.Toast.LENGTH_LONG);
                toast.setGravity(android.view.Gravity.CENTER | android.view.Gravity.CENTER, 0, 0);
                toast.show();
                return;
            }
        }
    }

    protected void salvarLogin(){
        dao.UserDAO userDAO2 = new dao.UserDAO(getApplicationContext());
        SharedPreferences sharedPreferences = getSharedPreferences("estimaprime", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("GLO_USUARIO", userDAO2.getIdUser(loginEmail));
        editor.commit();
    }

    protected void Logar(){
        Intent intent = new Intent(Mainactivity.this,Enterprises.class);
        startActivity(intent);
    }

    protected void Cadastrar(){
        Intent intent = new Intent(getApplicationContext(), Signup.class);
        startActivity(intent);
    }
}
