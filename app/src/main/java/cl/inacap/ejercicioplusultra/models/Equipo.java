package cl.inacap.ejercicioplusultra.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

import cl.inacap.ejercicioplusultra.helpers.DatabaseHelper;

                    // Implementar interfaz para poder enviar
                    // objetos de Equipo a otras actividades
public class Equipo implements Serializable {
    // Atributos
    private DatabaseHelper helper;

    private int serie;
    private String modelo;
    private String marca;
    private String planta;

    // Constructores
    public Equipo() { } // Objeto que sirva temporalmente para datos

    // Constructor para objeto que utilizará CRUD con la BD
    public Equipo(Context context) {
        this.helper = new DatabaseHelper(context);
    }

    // Constructor que sirva temporalmente para datos (con datos en el constructor)
    public Equipo(int serie, String modelo, String marca, String planta) {
        this.serie = serie;
        this.modelo = modelo;
        this.marca = marca;
        this.planta = planta;
    }

    public Equipo(Context context, int serie, String modelo, String marca, String planta) {
        this.serie = serie;
        this.modelo = modelo;
        this.marca = marca;
        this.planta = planta;
        this.helper = new DatabaseHelper(context);
    }


    /*
     * CRUD
     */
    // CREATE (CRUD)
    public boolean insertar()
    {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("MODELO", this.modelo);
        c.put("MARCA", this.marca);
        c.put("PLANTA", this.planta);

        try {
            db.insert("equipo",null,c);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.close();
        }
    }

    public boolean insertar(Equipo equipo) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("MODELO", equipo.getModelo());
        c.put("MARCA", equipo.getMarca());
        c.put("PLANTA", equipo.getPlanta());

        try {
            db.insert("equipo",null,c);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.close();
        }
    }

    // READ (CRUD)
    public ArrayList<Equipo> obtenerEquipos()
    {
        ArrayList<Equipo> equipos = new ArrayList<Equipo>();
        SQLiteDatabase db = helper.getReadableDatabase();
        try {
            Cursor consulta = db.rawQuery("SELECT * FROM equipo", null);
            if(consulta.moveToFirst()) {
                do {
                    int serie = consulta.getInt(0); // Column[0] = serie
                    String modelo = consulta.getString(1); // Column[1] = modelo
                    String marca = consulta.getString(2); // Column[2] = marca
                    String planta = consulta.getString(3); // Column[3] = planta
                    equipos.add(new Equipo(serie,modelo,marca,planta));
                } while (consulta.moveToNext());
                return equipos;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            db.close();
        }
    }

    // Obtener equipos condicionados por el tipo de planta donde se encuentran
    public ArrayList<Equipo> obtenerEquipos(String nombrePlanta)
    {
        ArrayList<Equipo> equipos = new ArrayList<Equipo>();
        SQLiteDatabase db = helper.getReadableDatabase();
        try {
            Cursor consulta = db.rawQuery("SELECT * FROM equipo WHERE planta = ?", new String[] { nombrePlanta });
            if(consulta.moveToFirst()) {
                do {
                    int serie = consulta.getInt(0); // Column[0] = serie
                    String modelo = consulta.getString(1); // Column[1] = modelo
                    String marca = consulta.getString(2); // Column[2] = marca
                    String planta = consulta.getString(3); // Column[3] = planta
                    equipos.add(new Equipo(serie,modelo,marca,planta));
                } while (consulta.moveToNext());
                return equipos;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            db.close();
        }
    }


    /*
     * UPDATE (CRUD)
     */
    public boolean actualizar(Equipo equipo) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        int filasActualizadas;
        ContentValues c = new ContentValues();
        c.put("MODELO", equipo.getModelo());
        c.put("MARCA", equipo.getMarca());
        c.put("PLANTA", equipo.getPlanta()); // TYPO
        try {
            filasActualizadas = db.update("equipo",c,"serie = ?", new String[]{ String.valueOf(equipo.getSerie()) });
            return (filasActualizadas > 0 ? true : false);
        } catch (Exception e) {
            return false;
        } finally {
            db.close();
        }
    }

    /*
     * DELETE (CRUD)
     */
    public boolean eliminar(int serie) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        int filasAfectadas;
        try {
            filasAfectadas = db.delete("equipo","serie = ?", new String[]{ String.valueOf(serie) });
            return (filasAfectadas > 0 ? true : false);
        } catch (Exception e) {
            return false;
        } finally {
            db.close();
        }
    }

    /*
     * GETTERS Y SETTERS
     */
    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    @Override
    public String toString(){
        return (this.serie + ":" + this.modelo + " (" + this.marca + ") - Planta: " + this.planta);
    }
}
