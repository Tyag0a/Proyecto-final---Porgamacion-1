package co.edu.uniquindio.poo;

//ya que es posible tener una clase abstracta aunque no tenga metodos abstractos,
//pponemos vehiculo abstracta para que no se instancie un vehiculo, sino una moto hibrida, clasica o un carro.

public abstract class Vehiculo {

    public final String placa;
    public final String modelo;
    public final Propietario propietario;

    
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
    //este solo en caso de que simplemente se necesite saber el propietario de determinado vehiculo sin ningun parametro

    public Propietario identificarPropietario () {
        return getPropietario();
    }

    //Metodo de parquear un vehiculo
    //se implementa el metodo ubicar vehiculo, el cual es el que actualiza los datos del parqueo

    public boolean parquear(Parqueadero parqueadero, Puesto puesto) {
        return parqueadero.ubicarVehiculo(this, puesto);
    }
    
}
