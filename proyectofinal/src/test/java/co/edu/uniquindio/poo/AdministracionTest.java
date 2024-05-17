package co.edu.uniquindio.poo;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.util.*;

public class AdministracionTest {
     private static final Logger LOG = Logger.getLogger(AppTest.class.getName());

    //Test unitario para probar el funcionamiento del metodo que genera un reporte diario del recaudo

    @Test
    public void testGenerarReporteDiario() {
        LOG.info("Iniciado test");

            // Crear instancia de Parqueadero y agregar registros 

            Propietario propietario1 = new Propietario("Juan", "Perez");
            Vehiculo vehiculo1 =  new MotoClasica("ABC123", "Honda", propietario1,120.5);
            Vehiculo vehiculo2 =  new MotoHibrida("XYZ456", "Yamaha", new Propietario("Maria", "Lopez"),77.89);
            Vehiculo vehiculo3 =  new Carro("DEF789", "Toyota", new Propietario("Pedro", "Gomez"));
            Vehiculo vehiculo4 = new MotoClasica("JKL321", "Suzuki", new Propietario("Ana", "Martinez"),46.67);

            Parqueadero parqueadero = new Parqueadero("Parqueadero Ejemplo", 20, new Tarifa(100, 150, 200));

            Registro registro1 = new Registro(LocalDateTime.of(2024, 5, 10, 8, 0), null,vehiculo1);
            Registro registro2 = new Registro(LocalDateTime.of(2024, 5, 10, 9, 0), null, vehiculo2);
            Registro registro3 = new Registro(LocalDateTime.of(2024, 5, 10, 10, 0), null, vehiculo3);
            Registro registro4 = new Registro(LocalDateTime.of(2024, 5, 11, 8, 0), null, vehiculo4);

            Collection<Registro> listaRegistros = new ArrayList<>();
            
            listaRegistros.add(registro1);
            listaRegistros.add(registro2);
            listaRegistros.add(registro3);
            listaRegistros.add(registro4);
            parqueadero.getAdministracion().setListaRegistros(listaRegistros);
    
            // Calcular el reporte para la fecha 2024-05-10
            LocalDateTime fecha = LocalDateTime.of(2024, 5, 10, 0, 0);
            double[] reporteEsperado = {100, 150, 200}; // El costo de estacionamiento esperado para MotoClasica, MotoHibrida, y Carro
            double[] reporteDiario = parqueadero.getAdministracion().generarReporteDiario(fecha);
    
            // Verificar que el reporte generado coincida con el esperado
            assertEquals(reporteEsperado[0], reporteDiario[0]); // MotoClasica
            assertEquals(reporteEsperado[1], reporteDiario[1]); // MotoHibrida
            assertEquals(reporteEsperado[2], reporteDiario[2]); // Carro


        LOG.info("Finalizando test");
    }

    //Test unitario para probar el funcionamiento del metodo que genera un reporte mensual del recaudo

    @Test
    public void testGenerarReporteMensual() {
        LOG.info("Iniciado test");



        LOG.info("Finalizando test");
    }
    
}
