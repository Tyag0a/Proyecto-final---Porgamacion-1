package co.edu.uniquindio.poo;

import java.time.Duration;

public class Tarifa {

    public Tarifa(double tarifaMtClasica, double tarifaMtHibrida, double tarifaCarro) {
        this.tarifaMtClasica = tarifaMtClasica;
        this.tarifaMtHibrida = tarifaMtHibrida;
        this.tarifaCarro = tarifaCarro;
    }
    public double tarifaMtClasica;
    public double tarifaMtHibrida;
    public double tarifaCarro;

    
    public double getTarifaMtClasica() {
        return tarifaMtClasica;
    }
    public void setTarifaMtClasica(double tarifaMtClasica) {
        this.tarifaMtClasica = tarifaMtClasica;
    }
    public double getTarifaMtHibrida() {
        return tarifaMtHibrida;
    }
    public void setTarifaMtHibrida(double tarifaMtHibrida) {
        this.tarifaMtHibrida = tarifaMtHibrida;
    }
    public double getTarifaCarro() {
        return tarifaCarro;
    }
    public void setTarifaCarro(double tarifaCarro) {
        this.tarifaCarro = tarifaCarro;
    }

    //Metodo para calcular el costo total del estacionamiento de una moto clasica, segun sus horas de parqueo
    //y valor de tarifa por hora de su tipo
    //Utilizando el metodo de calcular duracion de estacionamiento

    public double calcularCostoEstacionamientoMotoClasica(Registro registro) {
        Duration duracion = registro.calcularDuracionEstacionamiento();
        long horas = duracion.toHours(); // Obtener la diferencia en horas
        return getTarifaMtClasica() * horas;
    }

    //Metodo para calcular el costo total del estacionamiento de una moto hibrida, segun sus horas de parqueo
    //y valor de tarifa por hora de su tipo
    //Utilizando el metodo de calcular duracion de estacionamiento

    public double calcularCostoEstacionamientoMotoHibrida(Registro registro) {
        Duration duracion = registro.calcularDuracionEstacionamiento();
        long horas = duracion.toHours(); // Obtener la diferencia en horas
        return getTarifaMtHibrida() * horas;
    }

     //Metodo para calcular el costo total del estacionamiento de un carro, segun sus horas de parqueo 
     //y valor de tarifa por hora de su tipo
     //Utilizando el metodo de calcular duracion de estacionamiento

     public double calcularCostoEstacionamientoCarro(Registro registro) {
        Duration duracion = registro.calcularDuracionEstacionamiento();
        long horas = duracion.toHours(); // Obtener la diferencia en horas
        return getTarifaCarro() * horas;
    }
    
}
