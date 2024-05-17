package co.edu.uniquindio.poo;

import java.time.LocalDateTime;

public class Registro {
    private LocalDateTime momentoIngreso;
    public LocalDateTime getMomentoIngreso() {
        return momentoIngreso;
    }

    public void setMomentoIngreso(LocalDateTime momentoIngreso) {
        this.momentoIngreso = momentoIngreso;
    }

    private Puesto puesto;
    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    private Vehiculo vehiculo;

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Registro(LocalDateTime momentoIngreso, Puesto puesto, Vehiculo vehiculo) {
        this.momentoIngreso = momentoIngreso;
        this.puesto = puesto;
        this.vehiculo = vehiculo;
    }
}