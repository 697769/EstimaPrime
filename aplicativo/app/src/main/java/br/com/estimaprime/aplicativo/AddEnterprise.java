package br.com.estimaprime.aplicativo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import dao.EnterpriseDAO;
import modelo.Enterprise;

public class AddEnterprise extends AppCompatActivity {
    private Button btnAddEnterprise;
    private String AddEnterpriseName;
    private EditText edtAddEnterpriseName;
    public static final int Id_Usuario=0;
    private int UsuarioLogado=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_enterprise);

        edtAddEnterpriseName = (EditText) findViewById(R.id.txtAddEnterpriseName);
        btnAddEnterprise = (Button) findViewById(R.id.btnAddEnterprise);

        if (!GetUsuarioLogado()){
                Deslogar();
            }
        btnAddEnterprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AdicionarEmpresa()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Empresa Adicionada com Sucesso! ", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    toast.show();
                    Voltar();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Erro ao inserir empresa!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    }
    protected boolean GetUsuarioLogado(){

        TextView txtIdUsuarioLogado = (TextView) findViewById(R.id.txtAddEnterpriseIdUsuario);
        SharedPreferences sharedPreferences = getSharedPreferences("estimaprime", Context.MODE_PRIVATE);
        UsuarioLogado = sharedPreferences.getInt("GLO_USUARIO",Id_Usuario);

        if (UsuarioLogado <= 0){
            Toast toast = Toast.makeText(getApplicationContext(), "Usuário não encontrado! " + UsuarioLogado, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
            toast.show();
            return false;
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Código do Usuário: " + UsuarioLogado, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
            toast.show();
            txtIdUsuarioLogado.setText("Criar empresa pro Usuário: " + UsuarioLogado);
        }
        return true;
    }

    protected boolean AdicionarEmpresa(){

        AddEnterpriseName = edtAddEnterpriseName.getText().toString().trim();

        if (AddEnterpriseName.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Informe o Nome da Empresa! ", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
            toast.show();
            return false;
        }else{
            Enterprise emp = new Enterprise();
            emp.setName(AddEnterpriseName);
            emp.setId_usuario(UsuarioLogado);

            EnterpriseDAO empDAO = new EnterpriseDAO(getApplicationContext());
            if (empDAO.insertEnterprise(emp)){
                return true;
            }
            return false;
        }
    }

    protected void Deslogar(){
        Intent intent = new Intent(AddEnterprise.this, Mainactivity.class);
        startActivity(intent);
        finish();
    }

    protected void Voltar(){
        Intent intent = new Intent(AddEnterprise.this, Enterprises.class);
        startActivity(intent);
        finish();
    }
}
