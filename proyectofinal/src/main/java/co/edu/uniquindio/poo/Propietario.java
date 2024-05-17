package co.edu.uniquindio.poo;

public class Propietario {

    public final String nombre;
    public final String apellido;
    
    public Propietario(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    
}
