package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

public class VehiculoTest {

    private static final Logger LOG = Logger.getLogger(AppTest.class.getName());

    @Test
    public void testParquear() {
        LOG.info("Iniciado test ");

        // crear la tarifa del parqueadero
        TarifaParqueadero tarifa = new TarifaParqueadero(3.000, 3.009, 5.000);
        
        // crear el parqueadero con la tarifa y número de puestos
        Parqueadero parqueadero = new Parqueadero("parqueadero x", 13, tarifa);
        
        // crear los puestos en el parqueadero
        parqueadero.crearPuestos(parqueadero.getNumeroPuestos());
        
        // crear el propietario y el vehículo
        Propietario propietario = new Propietario("Juan", "Daniel");
        Vehiculo vehiculo = new Carro("JEY07", "Twingo", propietario);
        
        // crear el puesto donde se ubicará el vehículo
        Puesto puesto = parqueadero.getPuestos()[0][0];
        
        // llamar al método parquear del vehículo
        boolean resultado = vehiculo.parquear(parqueadero, puesto);
        
        // verificar que el método parquear devolvió true
        assertTrue(resultado);
        
        // verificar que el puesto está ocupado
        assertTrue(puesto.estaOcupado());
        
        // verificar que el vehículo en el puesto es el mismo que se ha parqueado
        assertEquals(vehiculo, puesto.getVehiculo());

        LOG.info("Finalizando test ");
    }
    
}
