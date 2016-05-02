package br.com.estimaprime.aplicativo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import DAO.Enterprise;

public class Enterprises extends Activity {

    //DATA SECTION
    private Button btn_enterprises_exit;
    public static final int Id_Usuario=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //DEFAULT DECLARATIONS
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprises);
        //CHANGES
        btn_enterprises_exit = (Button) findViewById(R.id.btn_enterprises_exit);
        //METHODS
        // Lista de Empresas
        //ArrayList<String> enterprise = new ArrayList<String>();
            //ArrayList<String> enterpriseList = null;
            ArrayList<String> enterpriseList = new ArrayList<String>();
        final Enterprise enterprise = new Enterprise(this);
        //limpar as empresas teste do banco
//            enterprise.deleteAllEnterprises();
        //adicionar empresas no banco
            //for (int i = 0; i < 5; i++){
            //    enterprise.addEnterprise("Empresa " + i, 1);
            //}
        //
//        for (int i=0; i<1;i++){
//            enterprise.addEnterprise("Empresa " + i,1);
//        }
//        for (int i=0; i<2;i++){
//            enterprise.addEnterprise("Empresa " + i,2);
//        }
//        for (int i=0; i<3;i++){
//            enterprise.addEnterprise("Empresa " + i,3);
//        }
        //Retornar as empresas do banco
            //enterpriseList = enterprise.getAllEnterprises();
            SharedPreferences sharedPreferences = getSharedPreferences("estimaprime", Context.MODE_PRIVATE);
            int UsuarioLogado = sharedPreferences.getInt("GLO_USUARIO",Id_Usuario);
            if (UsuarioLogado <= 0){
                Toast toast = Toast.makeText(Enterprises.this, "Usuário não encontrado! "+UsuarioLogado, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                toast.show();
            } else {
//                  enterpriseList = enterprise.getAllEnterprises();
                enterpriseList = enterprise.getUserEnterprises(UsuarioLogado);
                if (enterpriseList==null){
                    Toast toast = Toast.makeText(Enterprises.this, "Nenhuma empresa encontrada!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    // Adicionar as empresas no list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,enterpriseList);
                    final ListView listView = (ListView) findViewById(R.id.listView_enterprises);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(
                            new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String empresa = (String)listView.getItemAtPosition(position);
                                //Salva a empresa selecionada
                                    SharedPreferences sharedPreferences=getSharedPreferences("estimaprime", Context.MODE_PRIVATE); //salvar em modo privado!
                                    SharedPreferences.Editor editor = sharedPreferences.edit(); //declarar o editor
                                    editor.putInt("GLO_ENTERPRISE",enterprise.getIdEnterprise(Integer.parseInt(empresa.substring(0,1)))); //salvar a enterprise selecionada
                                    editor.commit(); //salvar dados no arquivo
                                //Chama a tela da empresa
                                    Intent intent = new Intent(Enterprises.this, Home.class);
                                    startActivity(intent);
                                }
                            }
                    );
                }
            }

        btn_enterprises_exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Enterprises.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}