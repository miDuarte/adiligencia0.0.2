package com.example.adiligencia01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cadastrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        Button botao = (Button) findViewById(R.id.botaocad);
        final Intent intent = new Intent(this, login.class);

        botao.setOnClickListener(new View.OnClickListener()

        {


            @Override
            public void onClick(View v){
                BancoController crud = new BancoController(getBaseContext());
                EditText nomecadast = (EditText) findViewById(R.id.nomecadast);
                EditText emailcadast = (EditText) findViewById(R.id.emailcadast);
                EditText senhacadast = (EditText) findViewById(R.id.senhacadast);
                EditText repetirsecadast = (EditText) findViewById(R.id.repetirsecadast);

                String nomecadastString = nomecadast.getText().toString();
                String emailcadstString = emailcadast.getText().toString();
                String senhacadastString = senhacadast.getText().toString();
                String repetirsecadastString = repetirsecadast.getText().toString();



                if (nomecadastString.isEmpty() || emailcadstString.isEmpty() || senhacadastString.isEmpty() || repetirsecadastString.isEmpty()) {
                    String resultado = "preencha todos os campos";
                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                } else {
                    if (senhacadastString.equals(repetirsecadastString)) {


                       // String resultado = "cadastrou";
                        String resultado = crud.insereDado(nomecadastString,emailcadstString,senhacadastString);
                        Toast toast = Toast.makeText(getApplicationContext(),
                                resultado, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        startActivity(intent);

                    } else {
                        String resultado = "as senhas não conferem";


                        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                    }


                }
            }
        });
    }
}
