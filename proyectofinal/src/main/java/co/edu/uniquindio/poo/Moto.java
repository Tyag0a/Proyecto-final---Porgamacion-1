package co.edu.uniquindio.poo;

public class Moto extends Vehiculo {

    public double velocidadMaxima;

    public Moto(String placa, String modelo, Propietario propietario, double velocidadMaxima) {
        super(placa, modelo, propietario);
        this.velocidadMaxima = velocidadMaxima;
    }

    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(double velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }
    
}
