package cl.inacap.ejercicioplusultra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import cl.inacap.ejercicioplusultra.helpers.DatabaseHelper;
import cl.inacap.ejercicioplusultra.models.Equipo;

public class MainActivity extends AppCompatActivity {
    private ListView lstEquipos;
    private Equipo e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.lstEquipos = (ListView) findViewById(R.id.lstEquipos);

        this.e = new Equipo(getBaseContext());

        ArrayAdapter<Equipo> adapter = new ArrayAdapter<Equipo>(
                this, // Si no funciona, getBaseContext() -- EXTREMO -- getApplicationContext()
                android.R.layout.simple_list_item_1,
                this.e.obtenerEquipos()
        );

        lstEquipos.setAdapter(adapter);


        // Listener para elemento de la lista
        lstEquipos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intento = new Intent(MainActivity.this, EditEquipoActivity.class);

                ArrayList<Equipo> equipos = e.obtenerEquipos();
                Equipo equipoEditar = (Equipo) equipos.get(position);
                intento.putExtra("equipoEditar", equipoEditar);

                startActivity(intento);
            }
        });


        // Objeto temporal de datos (Pregunta Tomás C.)
/*        Equipo equipoActualizar = new Equipo(4,"Bestia","Del","SBL");
        // Objeto que utilizará BD
        Equipo e = new Equipo(getBaseContext());
        e.actualizar(equipoActualizar);*/
    }
}
