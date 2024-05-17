package co.edu.uniquindio.poo;

public class Vehiculo {

    public final String placa;
    public final String modelo;
    public final Propietario propietario;
    public Puesto puesto;

    
    public Vehiculo(String placa, String modelo, Propietario propietario) {
        this.placa = placa;
        this.modelo = modelo;
        this.propietario = propietario;
    }


    public String getPlaca() {
        return placa;
    }


    public String getModelo() {
        return modelo;
    }


    public Propietario getPropietario() {
        return propietario;
    }

    //Metodo para identificar el propietario del vehiculo, con un get simple

    public Propietario identificarPropietario () {
        return getPropietario();
    }

    
}
