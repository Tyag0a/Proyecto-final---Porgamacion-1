package co.edu.uniquindio.poo;

class Moto extends Vehiculo {
    private int velocidadMaxima;

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(int velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public Moto(String placa, String modelo, Propietario propietario, int velocidadMaxima) {
        super(placa, modelo, propietario);
        this.velocidadMaxima = velocidadMaxima;
    }
}
