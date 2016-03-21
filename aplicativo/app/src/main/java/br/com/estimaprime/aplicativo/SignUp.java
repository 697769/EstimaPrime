package br.com.estimaprime.aplicativo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends Activity {
    //DATA SECTION
    private Button btn_sigup,btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //DEFAULT DECLARATIONS
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //CHANGES
        btn_sigup = (Button) findViewById(R.id.btn_sigup);
        btn_back = (Button) findViewById(R.id.btn_back);
        //METHODS
        btn_sigup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //SIGNUP USER
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
