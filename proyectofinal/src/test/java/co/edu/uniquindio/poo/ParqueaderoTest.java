package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

public class ParqueaderoTest {

    private static final Logger LOG = Logger.getLogger(AppTest.class.getName());

    //Test unitario para probar el funcionamiento del metodo CrearPuestos

    @Test
    public void crearPuestosTest() {

        LOG.info("Iniciado test ");
        Tarifa tarifa = new Tarifa(12.000, 11.000, 14.000);
        Parqueadero parqueadero = new Parqueadero("parqueadero x", 13, tarifa);

        parqueadero.crearPuestos(parqueadero.getNumeroPuestos());

         // Verificar que se crearon el número correcto de puestos
        assertEquals(parqueadero.getNumeroPuestos(), parqueadero.getPuestos().length);
        assertEquals(parqueadero.getNumeroPuestos(), parqueadero.getPuestos()[0].length);

        // Verificar que todos los puestos se crearon correctamente
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

        Tarifa tarifa = new Tarifa(3.000, 3.009, 5.000);
        Parqueadero parqueadero = new Parqueadero("parqueadero x", 13, tarifa);
        Propietario propietario = new Propietario("Lorenzo", "Daniel");
        Vehiculo vehiculo = new Carro("XYZ59", "Logan 6", propietario);
        Puesto puesto = new Puesto(0, 0, false, vehiculo);

        parqueadero.crearPuestos(parqueadero.getNumeroPuestos());

        // Intentar ubicar el vehículo en un puesto valido
        assertTrue(parqueadero.ubicarVehiculo(vehiculo,puesto));

        // Verificar que el vehículo se ubicó correctamente en el puesto especificado
        assertEquals(vehiculo, parqueadero.getPuestos()[0][0].getVehiculo());

        // Intentar ubicar otro vehículo en el mismo puesto asi que debería fallar
        assertFalse(parqueadero.ubicarVehiculo(new MotoClasica("XYZ456", "Honda", new Propietario("Maria", "987654321"), 100),puesto));

        LOG.info("Finalizando test");
    }

    //Test unitario para crear puestos con un numero de puestos negativo, para que genere una excepcion y pase la prueba

    @Test
    public void testCrearNumeroPuestosNegativos() {

        LOG.info("Iniciado test");

        Tarifa tarifa = new Tarifa(3.000, 3.009, 5.000);
        Parqueadero parqueadero = new Parqueadero("parqueadero x", -13, tarifa);

        assertThrows(Throwable.class, () -> parqueadero.crearPuestos(parqueadero.getNumeroPuestos()));


        LOG.info("Finalizando test");
    }

    //Test unitario para el metodo de identificar un propietario

    @Test
    public void testIdentificarPropietario() {

        LOG.info("Iniciado test");

        Tarifa tarifa = new Tarifa(3.000, 3.009, 5.000);
        Parqueadero parqueadero = new Parqueadero("parqueadero x", 13, tarifa);
        Propietario propietario = new Propietario("Juan", "Daniel");
        Vehiculo vehiculo = new Vehiculo("JEY07", "Twingo", propietario);
        Puesto puesto = new Puesto(0, 0, false, vehiculo);

        parqueadero.getPuestos()[0][0] = puesto;
        parqueadero.crearPuestos(parqueadero.getNumeroPuestos());
        
        // Llamamos al método identificarPropietario y verificamos el resultado
        String nombrePropietario = parqueadero.identificarPropietario(0, 0);
        assertEquals("Juan", nombrePropietario);

        LOG.info("Finalizando test");
    }

    //Test unitario para el metodo verificar la disponibilidad de un puesto

    @Test
    public void testVerificarDisponibilidadPuesto() {

        LOG.info("Iniciado test");

        Tarifa tarifa = new Tarifa(3.000, 3.009, 5.000);
        Parqueadero parqueadero = new Parqueadero("parqueadero x", 13, tarifa);
        Propietario propietario = new Propietario("Juan", "heredia");
        Vehiculo vehiculo = new Vehiculo("GHJ375", "Tesla", propietario);
        Puesto puestoVacio = new Puesto(0, 0, false, null);
        Puesto puestoOcupado = new Puesto(1, 1, false, null);

        parqueadero.crearPuestos(parqueadero.numeroPuestos);
        parqueadero.ubicarVehiculo(vehiculo,puestoOcupado);

          // Verificación del resultado para un puesto vacío
          assertTrue(parqueadero.verificarDisponibilidadPuesto(puestoVacio));

          // Verificación del resultado para un puesto ocupado
          assertFalse(parqueadero.verificarDisponibilidadPuesto(puestoOcupado));


        LOG.info("Finalizando test");
    }

    @Test
    public void testDesubicarVehiculo_PuestoVacio() {

        LOG.info("Iniciado test");

        //  configuración inicial
        Tarifa tarifa = new Tarifa(5.0, 7.0, 10.0);
        Parqueadero parqueadero = new Parqueadero("Parqueadero Central", 10, tarifa);
        parqueadero.crearPuestos(10);
        Administracion administracion = new Administracion("Admin", parqueadero);
        parqueadero.setAdministracion(administracion);

        //  rear un puesto vacío
        Puesto puestoVacio = parqueadero.getPuestos()[1][1];

        // desubicar un vehículo de un puesto vacío
        boolean desubicado = parqueadero.desubicarVehiculo(puestoVacio);

        // verificar que el método devuelve false
        assertFalse(desubicado);

        LOG.info("Finalizando test");
    }
}
