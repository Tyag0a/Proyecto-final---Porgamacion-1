package co.edu.uniquindio.poo;

import java.time.Duration;

public class TarifaParqueadero {

    public TarifaParqueadero(double tarifaMtClasica, double tarifaMtHibrida, double tarifaCarro) {
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

    //Metodo para calcular el costo total del estacionamiento de un vehiculo
    //dependiendo de que vehiculo se instancia, se le da un valor diferente a tarifaTotal

    //la tarifa se calcula en base a las horas de parqueo y la tarifa configurada por hora para
    //cada tipo de vehiculo

    public double calcularCostoTotalEstacionamiento (RegistroParqueadero registro) {
        double tarifaTotal = 0.0;
        if (registro.getVehiculoRegistrado() instanceof MotoClasica) {
            Duration duracion = registro.calcularDuracionEstacionamiento();
            long horas = duracion.toHours(); // Obtener la diferencia en horas
            tarifaTotal = getTarifaMtClasica() * horas;
        }
        else if (registro.getVehiculoRegistrado() instanceof MotoHibrida) {
            Duration duracion = registro.calcularDuracionEstacionamiento();
            long horas = duracion.toHours(); // Obtener la diferencia en horas
            tarifaTotal = getTarifaMtHibrida() * horas;
        }
        else if (registro.getVehiculoRegistrado() instanceof Carro) {
            Duration duracion = registro.calcularDuracionEstacionamiento();
            long horas = duracion.toHours(); // Obtener la diferencia en horas
            tarifaTotal = getTarifaCarro() * horas;
        }

     return tarifaTotal;
    }
    
}
