package co.edu.uniquindio.poo;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
//comando para ejecutar un test: mvn -Dtest=NombredeclaseTest#nombredemetodoTest test
//comando para ejecutar todos los test de todas las clases: mvn test


public class AdministracionTest {
     private static final Logger LOG = Logger.getLogger(AppTest.class.getName());

    //Test unitario para probar el funcionamiento del metodo que genera un reporte diario del recaudo

    @Test
    public void testGenerarReporteDiario() {
        LOG.info("Iniciado test");

 
            // Configuración de prueba
            TarifaParqueadero tarifa = new TarifaParqueadero(3500, 2000, 3000);
            Parqueadero parqueadero = new Parqueadero("Parqueadero Central", 10, tarifa);
            Administracion administracion = new Administracion("juan",parqueadero);
            
            // Agregar la lista de registros a la administración
            parqueadero.setAdministracion(administracion);
    
            // Creación de una fecha específica
            LocalDateTime fechaIngreso = LocalDateTime.of(2024, 5, 1, 8, 0);
            LocalDateTime fechaSalida = LocalDateTime.of(2024, 5, 1, 12, 0);
    
            // Creación de un propietario y un vehículo
            Propietario propietario = new Propietario("Juan", "Perez");
            Vehiculo vehiculo = new MotoClasica("ABC123", "ModeloX", propietario,100.4);
    
            // Creación de un registro y agregado a la administración
            RegistroParqueadero registro = new RegistroParqueadero(fechaIngreso, fechaSalida, vehiculo);
            administracion.agregarRegistro(registro);
    
            // Ejecución de la prueba
            double[] reporteDiario = administracion.generarReporteDiario(LocalDateTime.of(2024, 5, 1,0,0));
    
            // Verificación
            assertEquals(14000, reporteDiario[0], 0.01);
            assertEquals(0.0, reporteDiario[1], 0.01);
            assertEquals(0.0, reporteDiario[2], 0.01);
        


        LOG.info("Finalizando test");
    }

    //Test unitario para probar el funcionamiento del metodo que genera un reporte mensual del recaudo

    @Test
    public void testGenerarReporteMensual() {
        LOG.info("Iniciado test");

        TarifaParqueadero tarifa = new TarifaParqueadero(5.0, 7.0, 10.0);
        Parqueadero parqueadero = new Parqueadero("Parqueadero Central", 10, tarifa);
        Administracion administracion = new Administracion("Admin", parqueadero);
        parqueadero.setAdministracion(administracion);

        // Crear propietarios y vehículos
        Propietario propietario1 = new Propietario("Juan", "Perez");
        Propietario propietario2 = new Propietario("Ana", "Gomez");
        Propietario propietario3 = new Propietario("Luis", "Martinez");

        Vehiculo motoClasica = new MotoClasica("ABC123", "ModeloX", propietario1,99.9);
        Vehiculo motoHibrida = new MotoHibrida("DEF456", "ModeloY", propietario2,98.66);
        Vehiculo carro = new Carro("GHI789", "ModeloZ", propietario3);

        // Crear registros de ingreso
        LocalDateTime inicioMes = LocalDateTime.of(2024, 5, 1, 8, 0);
        LocalDateTime finMes = LocalDateTime.of(2024, 5, 31, 18, 0);

        RegistroParqueadero registroMotoClasica = new RegistroParqueadero(inicioMes, finMes, motoClasica);
        RegistroParqueadero registroMotoHibrida = new RegistroParqueadero(inicioMes, finMes, motoHibrida);
        RegistroParqueadero registroCarro = new RegistroParqueadero(inicioMes, finMes, carro);

        // Agregar registros a la administración
        administracion.agregarRegistro(registroMotoClasica);
        administracion.agregarRegistro(registroMotoHibrida);
        administracion.agregarRegistro(registroCarro);

        // Calcular el recaudo mensual
        double recaudoMensual = administracion.generarReporteMensual(5, 2024);

        // Calcular el recaudo esperado
        double expectedRecaudo = (tarifa.calcularCostoTotalEstacionamiento(registroMotoClasica) + 
                                   tarifa.calcularCostoTotalEstacionamiento(registroMotoHibrida) + 
                                   tarifa.calcularCostoTotalEstacionamiento(registroCarro));

        // Verificar que el recaudo mensual sea el esperado
        assertEquals(expectedRecaudo, recaudoMensual, 0.001);



        LOG.info("Finalizando test");
    }
    
}
