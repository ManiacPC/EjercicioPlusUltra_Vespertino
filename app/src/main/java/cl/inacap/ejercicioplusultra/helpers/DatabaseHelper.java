package cl.inacap.ejercicioplusultra.helpers;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbplusultra.db";
    public static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE equipo(serie INTEGER PRIMARY KEY, modelo TEXT NOT NULL, marca TEXT NOT NULL);");
        db.execSQL("INSERT INTO equipo(modelo,marca) VALUES('Avilon', 'Achepe');");
        db.execSQL("INSERT INTO equipo(modelo,marca) VALUES('Noliva', 'Lemovo');");
        db.execSQL("INSERT INTO equipo(modelo,marca) VALUES('Titan', 'Compak');");
        db.execSQL("INSERT INTO equipo(modelo,marca) VALUES('Bestia', 'LosDel');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == 2) {
            db.execSQL("DROP TABLE equipo;");
            db.execSQL("CREATE TABLE equipo(serie INTEGER PRIMARY KEY, modelo TEXT NOT NULL, marca TEXT NOT NULL, planta TEXT NOT NULL);");
            db.execSQL("INSERT INTO equipo(modelo,marca,planta) VALUES('Avilon', 'Achepe', 'SBL');");
            db.execSQL("INSERT INTO equipo(modelo,marca,planta) VALUES('Noliva', 'Lemovo', 'PA');");
            db.execSQL("INSERT INTO equipo(modelo,marca,planta) VALUES('Titan', 'Compak', 'HF');");
            db.execSQL("INSERT INTO equipo(modelo,marca,planta) VALUES('Bestia', 'LosDel', 'SBL');");
        }
    }
}
