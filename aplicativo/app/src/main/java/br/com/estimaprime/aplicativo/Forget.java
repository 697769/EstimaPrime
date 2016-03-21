package br.com.estimaprime.aplicativo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Forget extends Activity {
    //DATA SECTION
    private Button btn_forget_send,btn_forget_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //DEFAULT DECLARATIONS
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        //CHANGES
        btn_forget_send = (Button) findViewById(R.id.btn_forget_send);
        btn_forget_back = (Button) findViewById(R.id.btn_forget_back);
        //METHODS
        btn_forget_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //PASSWORD RECOVERY
            }
        });
        btn_forget_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Forget.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
