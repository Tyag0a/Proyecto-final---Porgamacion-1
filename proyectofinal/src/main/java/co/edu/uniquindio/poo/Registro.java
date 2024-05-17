package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.time.Duration;

public class Registro {

    public LocalDateTime registroIngreso;
    public LocalDateTime registroSalida;
    public Vehiculo vehiculoRegistrado;
    
    public Registro(LocalDateTime registroIngreso, LocalDateTime registroSalida, Vehiculo vehiculoRegistrado) {
        this.registroIngreso = registroIngreso;
        this.registroSalida = registroSalida;
        this.vehiculoRegistrado = vehiculoRegistrado;
    }

    public LocalDateTime getRegistroIngreso() {
        return registroIngreso;
    }
 
    public void setRegistroIngreso(LocalDateTime registroIngreso) {
        this.registroIngreso = registroIngreso;
    }

    public LocalDateTime getRegistroSalida() {
        return registroSalida;
    }

    public void setRegistroSalida(LocalDateTime registroSalida) {
        this.registroSalida = registroSalida;
    }

    public Vehiculo getVehiculoRegistrado() {
        return vehiculoRegistrado;
    }

    public void setVehiculoRegistrado(Vehiculo vehiculoRegistrado) {
        this.vehiculoRegistrado = vehiculoRegistrado;
    }

    //Metodo para registrar el tiempo total del estacionamiento de un vehiculo.
    //El método Duration.between(registroIngreso, regisroSalida) se utiliza para
    // calcular la diferencia de tiempo entre dos instancias de LocalDateTime

    public Duration calcularDuracionEstacionamiento() {
        if (registroIngreso != null && registroSalida != null) {
            return Duration.between(registroIngreso, registroSalida);
        } else {
            // Si no hay hora de entrada o salida registrada, retornamos Duration.ZERO
            return Duration.ZERO;
        }
    }

    public void registrarHoraSalida(){

       setRegistroSalida(LocalDateTime.now());
   }

    
}
