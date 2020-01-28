import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adiligencia01.R;

import java.util.concurrent.ExecutionException;

public class consultaCep extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cep);

        Button btnBuscarCep = findViewById(R.id.btnMain_buscarCep);

        final EditText cep = findViewById(R.id.etMain_cep);
        final TextView resposta = findViewById(R.id.etMain_resposta);

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CEP retorno = new HttpService(cep.getText().toString()).execute().get();
                    resposta.setText(retorno.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}