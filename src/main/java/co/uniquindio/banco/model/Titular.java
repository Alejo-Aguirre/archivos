package co.uniquindio.banco.model;

import java.io.Serializable;
import java.util.Objects;

public class Titular implements Serializable {
    private String id;
    private String nombre;

    public Titular(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }


    @Override
    public String toString() {
        return "Titular{" +
                "id='" + id + '\'' +
                '}';
    }
}
