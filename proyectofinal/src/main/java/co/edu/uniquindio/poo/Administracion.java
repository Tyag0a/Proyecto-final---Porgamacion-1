package co.edu.uniquindio.poo;

import java.util.Collection;
import java.util.LinkedList;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Administracion {

    public Administracion(String administrador, Parqueadero parqueadero) {
        this.administrador = administrador;
        this.listaRegistros = new LinkedList<>();
        this.parqueadero = parqueadero;
    }
    
    private final String administrador;
    Collection<RegistroParqueadero> listaRegistros; //Esta coleccion incluye el historico de vehiculos parqueados, por lo cual no se le pueden eliminar elementos
    public final Parqueadero parqueadero;

    
    public String getAdministrador() {
        return administrador;
    }


    public Collection<RegistroParqueadero> getListaRegistros() {
        return listaRegistros;
    }


    public void setListaRegistros(Collection<RegistroParqueadero> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }


    public Parqueadero getParqueadero() {
        return parqueadero;
    }


    //Metodos para que el administrador pueda configurar las tarifas por hora para cada tipo de vehiculo

    public void configurarTarifaMotoClasica(double tarifaMtClasica) {
        parqueadero.getTarifa().setTarifaMtClasica(tarifaMtClasica);
        
    }

    public void configurarTarifaMotoHibrida(double tarifaMtHibrida) {
        parqueadero.getTarifa().setTarifaMtHibrida(tarifaMtHibrida);

    }

    public void configurarTarifaCarro(double tarifaCarro) {
        parqueadero.getTarifa().setTarifaCarro(tarifaCarro);

    }

    //Metodo para generar un reporte diario del recaudo por el tipo de vehiculo

    public double[] generarReporteDiario(LocalDateTime fecha) {
        double[] reporteDiario = new double[3]; // organizacion de datos en el arreglo: 0 - MotoClasica, 1 - MotoHibrida, 2 - Carro
        TarifaParqueadero tarifa = parqueadero.getTarifa();

        for (RegistroParqueadero registro : listaRegistros) {
            // Verificar si el registro corresponde a la fecha especificada
            LocalDate fechaRegistro = registro.getRegistroIngreso().toLocalDate();
            if (fechaRegistro.equals(fecha.toLocalDate())) {
                // Calcular costo de estacionamiento para este registro
                if (registro.getVehiculoRegistrado() instanceof MotoClasica) {
                    reporteDiario[0] += tarifa.calcularCostoTotalEstacionamiento(registro);
                } else if (registro.getVehiculoRegistrado() instanceof MotoHibrida) {
                    reporteDiario[1] += tarifa.calcularCostoTotalEstacionamiento(registro);
                } else if (registro.getVehiculoRegistrado() instanceof Carro) {
                    reporteDiario[2] += tarifa.calcularCostoTotalEstacionamiento(registro);
                }
            }
        }

        // Devolver el array con el reporte por tipo de vehículo
        return reporteDiario;
    }

    //Metodo para generar un reporte del recaudo del mes por tipo de vehiculo
    //Al no necesitar generar un reporte por tipo de vehiculo, se puede retornar una sola variable double

    public double generarReporteMensual(int mes, int año) {
        double RecaudoMensual = 0.0;

        for (RegistroParqueadero registro : listaRegistros) {
            LocalDate fechaRegistro = registro.getRegistroIngreso().toLocalDate();
            if (fechaRegistro.getMonthValue() == mes && fechaRegistro.getYear() == año) {
                if (registro.getVehiculoRegistrado() instanceof MotoClasica) {
                    RecaudoMensual += parqueadero.tarifa.calcularCostoTotalEstacionamiento(registro);
                } else if (registro.getVehiculoRegistrado() instanceof MotoHibrida) {
                    RecaudoMensual += parqueadero.tarifa.calcularCostoTotalEstacionamiento(registro);
                } else if (registro.getVehiculoRegistrado() instanceof Carro) {
                    RecaudoMensual += parqueadero.tarifa.calcularCostoTotalEstacionamiento(registro);
                }
            }
        }

        return RecaudoMensual;
    }

    public void agregarRegistro(RegistroParqueadero registro) {
        listaRegistros.add(registro);

    }


    
}
