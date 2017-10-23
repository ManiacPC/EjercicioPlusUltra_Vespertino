package cl.inacap.ejercicioplusultra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cl.inacap.ejercicioplusultra.helpers.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DatabaseHelper helper = new DatabaseHelper(this);
    }
}
