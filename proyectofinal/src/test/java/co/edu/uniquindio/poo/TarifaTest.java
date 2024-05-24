package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Duration;

//comando para ejecutar un test: mvn -Dtest=NombredeclaseTest#nombredemetodoTest test
//comando para ejecutar todos los test de todas las clases: mvn test

public class TarifaTest {

    private static final Logger LOG = Logger.getLogger(AppTest.class.getName());

    //Test unitario para probar el funcionamiento de los tres metodos que calculan el costo total de un estacionamiento
    //Solo hace falta un test, ya que los tres metodos son iguales, solo cambia el valor del costo por hora
    //segun el tipo

    @Test
    public void testCalcularCostoEstacionamiento() {
        LOG.info("Iniciado test ");

        Propietario propietario = new Propietario("Lorenzo", "padilla");
        Vehiculo vehiculo = new MotoHibrida("STG57","Yamaha",propietario,120.5);
        RegistroParqueadero registro = new RegistroParqueadero(LocalDateTime.of(2024, 5, 10, 13, 30), LocalDateTime.of(2024, 5, 10, 15, 30), vehiculo);

         // Calcular la duración del estacionamiento (en este caso  2 horas)
         Duration duracion = Duration.between(registro.getRegistroIngreso(), registro.getRegistroSalida());

         // Calcular el costo del estacionamiento de acuerdo a la tarifa por hora de la moto híbrida (supongamos 2.5 por hora)
         TarifaParqueadero tarifa = new TarifaParqueadero(2.4, 2.5, 5.2);
         long horas = duracion.toHours();
         double costoEsperado = tarifa.getTarifaMtHibrida() * horas;

         double costoCalculado = tarifa.calcularCostoTotalEstacionamiento(registro);

         // Verificar si el costo calculado es igual al costo esperado
        assertEquals(costoEsperado, costoCalculado, 0.001);

        LOG.info("Finalizando test ");
    }


    
}
