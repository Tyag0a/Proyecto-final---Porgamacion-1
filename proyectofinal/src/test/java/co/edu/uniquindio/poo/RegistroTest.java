package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

public class RegistroTest {
    private static final Logger LOG = Logger.getLogger(AppTest.class.getName());

    //Test para probar el funcionamiento del metodo que calcula la duracion de un estacionamiento

    @Test
    public void testCalcularDuracionEstacionamiento() {
        LOG.info("Iniciado test ");

        Propietario propietario = new Propietario("Lorenzo", "padilla");
        Vehiculo vehiculo = new MotoHibrida("STG57","Yamaha",propietario,120.5);
        Registro registro = new Registro(LocalDateTime.of(2024, 5, 10, 10, 00), LocalDateTime.of(2024, 5, 10, 13, 30), vehiculo);

        // Calcular la duración del estacionamiento (en este caso, 3.5 horas)
        Duration duracionEsperada = Duration.ofHours(3).plusMinutes(30);
        Duration duracionCalculada = registro.calcularDuracionEstacionamiento();

        // Verificar si la duración calculada es igual a la duración esperada
        assertEquals(duracionEsperada, duracionCalculada);


        LOG.info("Finalizando test");
    }
    
}
