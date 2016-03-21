package br.com.estimaprime.aplicativo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends Activity {
    //DATA SECTION
    private Button btn_select_enterprise,btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //DEFAULT DECLARATIONS
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //CHANGES
        btn_select_enterprise = (Button) findViewById(R.id.btn_select_enterprise);
        btn_back = (Button) findViewById(R.id.btn_back);
        //METHODS
        btn_select_enterprise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Home.this, Enterprises.class);
                startActivity(intent);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
