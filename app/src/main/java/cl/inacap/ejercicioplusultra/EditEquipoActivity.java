package cl.inacap.ejercicioplusultra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cl.inacap.ejercicioplusultra.models.Equipo;

public class EditEquipoActivity extends AppCompatActivity {
    private TextView txtSerie;
    private EditText editModelo;
    private EditText editMarca;
    private EditText editPlanta;
    private Button btnGuardar;
    private Equipo e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_equipo);

        this.txtSerie = (TextView) findViewById(R.id.txtSerie);
        this.editModelo = (EditText) findViewById(R.id.editModelo);
        this.editMarca = (EditText) findViewById(R.id.editMarca);
        this.editPlanta = (EditText) findViewById(R.id.editPlanta);
        this.btnGuardar = (Button) findViewById(R.id.btnGuardar);

        this.e = (Equipo) getIntent().getSerializableExtra("equipoEditar");
        this.txtSerie.setText(String.valueOf(e.getSerie()));
        this.editModelo.setText(e.getModelo());
        this.editMarca.setText(e.getMarca());
        this.editPlanta.setText(e.getPlanta());

        this.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Equipo equipoActualizar = new Equipo(getBaseContext());
                int serie = e.getSerie();

                String modelo = editModelo.getText().toString();
                String marca = editMarca.getText().toString();
                String planta = editPlanta.getText().toString();

                Equipo equipo = new Equipo(serie, modelo, marca, planta);

                if (equipoActualizar.actualizar(equipo)) {
                    Toast.makeText(getBaseContext(), "El equipo " + String.valueOf(serie) + " ha sido actualizado exitosamente", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(), "El equipo " + String.valueOf(serie) + " no se ha podido actualizar", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
