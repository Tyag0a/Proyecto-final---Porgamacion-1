package co.edu.uniquindio.poo;

class Vehiculo {
    
    private String placa;
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    private String modelo;
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    private Propietario propietario;

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Vehiculo(String placa, String modelo, Propietario propietario) {
        this.placa = placa;
        this.modelo = modelo;
        this.propietario = propietario;
    }
}
