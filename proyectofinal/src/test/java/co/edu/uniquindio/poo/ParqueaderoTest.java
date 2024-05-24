package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

//comando para ejecutar un test: mvn -Dtest=NombredeclaseTest#nombredemetodoTest test
//comando para ejecutar todos los test de todas las clases: mvn test

public class ParqueaderoTest {

    private static final Logger LOG = Logger.getLogger(AppTest.class.getName());

    //Test unitario para probar el funcionamiento del metodo CrearPuestos

    @Test
    public void crearPuestosTest() {

        LOG.info("Iniciado test ");

        //se crea una tarifa y un parqueadero

        TarifaParqueadero tarifa = new TarifaParqueadero(12.000, 11.000, 14.000);
        Parqueadero parqueadero = new Parqueadero("parqueadero x", 13, tarifa);

        parqueadero.crearPuestos(parqueadero.getNumeroPuestos());

         // verificar que se crearon el número correcto de puestos

        assertEquals(parqueadero.getNumeroPuestos(), parqueadero.getPuestos().length);
        assertEquals(parqueadero.getNumeroPuestos(), parqueadero.getPuestos()[0].length);

        // verificar que todos los puestos se crearon correctamente

        for (int i = 0; i < parqueadero.getNumeroPuestos(); i++) {
            for (int j = 0; j < parqueadero.getNumeroPuestos(); j++) {
                Puesto puesto = parqueadero.getPuestos()[i][j];
                assertEquals(i, puesto.getPosicion().getX());
                assertEquals(j, puesto.getPosicion().getY());
                assertEquals(false, puesto.isOcupado());
                assertEquals(null, puesto.getVehiculo());
            }
        }
        
        LOG.info("Finalizando test ");
    }

    //Test unitario para verificar la funcionalidad del metodo ubicarVehiculo

    @Test
    public void testUbicarVehiculo() {
        LOG.info("Iniciado test");

        TarifaParqueadero tarifa = new TarifaParqueadero(3.000, 3.009, 5.000);
        Parqueadero parqueadero = new Parqueadero("parqueadero x", 13, tarifa);
        Propietario propietario = new Propietario("Lorenzo", "Daniel");
        Vehiculo vehiculo = new Carro("XYZ59", "Logan 6", propietario);
        
        // Crear los puestos del parqueadero
        parqueadero.crearPuestos(parqueadero.getNumeroPuestos());

        // Obtener el puesto especifico para ubicar el vehiculo
        Puesto puesto = parqueadero.getPuestos()[0][0];

        // Intentar ubicar el vehículo en un puesto valido
        assertTrue(parqueadero.ubicarVehiculo(vehiculo, puesto));

        // Verificar que el vehículo se ubicó correctamente en el puesto especificado
        assertEquals(vehiculo, parqueadero.getPuestos()[0][0].getVehiculo());

        LOG.info("Finalizando test");
    }



    //Test unitario para crear puestos con un numero de puestos negativo, para que genere una excepcion y pase la prueba

    @Test
    public void testCrearNumeroPuestosNegativos() {

        LOG.info("Iniciado test");

        //instanciar tarifa y parqueadero

        TarifaParqueadero tarifa = new TarifaParqueadero(3.000, 3.009, 5.000);
        Parqueadero parqueadero = new Parqueadero("parqueadero x", -13, tarifa);

        //se debe generar una excepcion al poner un numero de puestos negativo

        assertThrows(Throwable.class, () -> parqueadero.crearPuestos(parqueadero.getNumeroPuestos()));


        LOG.info("Finalizando test");
    }

    //Test unitario para el metodo de identificar un propietario

    @Test
    public void testIdentificarPropietario() {

        LOG.info("Iniciado test");

        // crear la tarifa para el parqueadero
        TarifaParqueadero tarifa = new TarifaParqueadero(3.000, 3.009, 5.000);
        
        // crear el parqueadero con la tarifa y número de puestos
        Parqueadero parqueadero = new Parqueadero("parqueadero x", 13, tarifa);
        
        // crear los puestos en el parqueadero
        parqueadero.crearPuestos(parqueadero.getNumeroPuestos());
        
        // crear el propietario y el vehículo
        Propietario propietario = new Propietario("Juan", "Daniel");
        Vehiculo vehiculo = new Carro("JEY07", "Twingo", propietario);
        
        // crear el puesto, debe de estar vacio para que el metodo ubicar pueda identificarlo como vacio
        Puesto puesto = new Puesto(0, 0, false, null);
         
        // ubicar el vehículo en el parqueadero en el puesto especificado
        parqueadero.ubicarVehiculo(vehiculo, puesto);
        
        // llamar al método identificarPropietario y verificar el resultado con assertEquals
        String nombrePropietario = parqueadero.identificarPropietario(0, 0);
        assertEquals("Juan", nombrePropietario);
    
        LOG.info("Finalizando test");


    }

    //Test unitario para el metodo verificar la disponibilidad de un puesto

    @Test
    public void testVerificarDisponibilidadPuesto() {

        LOG.info("Iniciado test");

        //instanciar objetos necesarios

        TarifaParqueadero tarifa = new TarifaParqueadero(3.000, 3.009, 5.000);
        Parqueadero parqueadero = new Parqueadero("parqueadero x", 13, tarifa);
        Propietario propietario = new Propietario("Juan", "heredia");
        Vehiculo vehiculo = new Carro("GHJ375", "Tesla", propietario);
        Puesto puestoVacio = new Puesto(0, 0, false, null);
        Puesto puestoOcupado = new Puesto(1, 1, false, null);

        //se crean los puestos y se ubica un vehiculo

        parqueadero.crearPuestos(parqueadero.numeroPuestos);
        parqueadero.ubicarVehiculo(vehiculo,puestoOcupado);

          // Verificación del resultado para un puesto vacío, el metodo debe retornar true
          assertTrue(parqueadero.verificarDisponibilidadPuesto(puestoVacio));

          // Verificación del resultado para un puesto ocupado, el metodo debe retornar false
          assertFalse(parqueadero.verificarDisponibilidadPuesto(puestoOcupado));


        LOG.info("Finalizando test");
    }

    //prueba unitaria para probar el funcionamiento del metodo retirar vehiculo

    @Test
    public void testRetirarVehiculo() {

        LOG.info("Iniciado test");

        //  configuración inicial
        TarifaParqueadero tarifa = new TarifaParqueadero(5.0, 7.0, 10.0);
        Parqueadero parqueadero = new Parqueadero("Parqueadero Central", 10, tarifa);
        parqueadero.crearPuestos(10);
        Administracion administracion = new Administracion("Admin", parqueadero);
        parqueadero.setAdministracion(administracion);

        //  rear un puesto vacío
        Puesto puestoVacio = parqueadero.getPuestos()[1][1];

        // retirar un vehículo de un puesto vacío
        boolean desubicado = parqueadero.retirarVehiculo(puestoVacio);

        // verificar que el método devuelve false
        assertFalse(desubicado);

        LOG.info("Finalizando test");
    }
}
