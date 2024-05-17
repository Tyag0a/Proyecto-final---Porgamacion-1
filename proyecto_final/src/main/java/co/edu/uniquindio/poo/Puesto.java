package co.edu.uniquindio.poo;

class Puesto {
    private int posicionI;
    public int getPosicionI() {
        return posicionI;
    }

    public void setPosicionI(int posicionI) {
        this.posicionI = posicionI;
    }

    private int posicionJ;
    public int getPosicionJ() {
        return posicionJ;
    }

    public void setPosicionJ(int posicionJ) {
        this.posicionJ = posicionJ;
    }

    private Vehiculo vehiculo;

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Puesto(int i, int j) {
        this.posicionI = i;
        this.posicionJ = j;
        this.vehiculo = null;
    }

    public boolean ocuparPuesto(Vehiculo vehiculo) {
        if (this.vehiculo == null) {
            this.vehiculo = vehiculo;
            return true;
        }
        return false;
    }

    public void liberarPuesto() {
        this.vehiculo = null;
    }

    public boolean estaOcupado() {
        return this.vehiculo != null;
    }
}