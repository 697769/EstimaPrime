package br.com.estimaprime.aplicativo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Enterprises extends Activity {

    //DATA SECTION
    private Button btn_enteprises_select_enterprise,btn_enterprises_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //DEFAULT DECLARATIONS
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprises);
        //CHANGES
        btn_enteprises_select_enterprise = (Button) findViewById(R.id.btn_enteprises_select_enterprise);
        btn_enterprises_exit = (Button) findViewById(R.id.btn_enterprises_exit);
        //METHODS
        btn_enteprises_select_enterprise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Enterprises.this, Home.class);
                startActivity(intent);
            }
        });
        btn_enterprises_exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Enterprises.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
