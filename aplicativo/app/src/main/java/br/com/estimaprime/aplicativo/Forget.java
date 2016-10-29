package br.com.estimaprime.aplicativo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Forget extends Activity {

    //DATA SECTION
    private Button btn_forget_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //DEFAULT DECLARATIONS
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        //CHANGES
        btn_forget_send = (Button) findViewById(R.id.btn_forget_send);

        //METHODS
        btn_forget_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //PASSWORD RECOVERY
            }
        });
    }
}
