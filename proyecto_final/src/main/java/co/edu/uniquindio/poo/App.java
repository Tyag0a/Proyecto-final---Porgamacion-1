package co.edu.uniquindio.poo;

import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero(5, 5);

        // Crear propietarios
        Propietario propietario1 = new Propietario("Juan Pérez", "123456789");
        Propietario propietario2 = new Propietario("María Gómez", "987654321");

        // Crear vehículos
        Vehiculo vehiculo1 = new Carro("ABC123", "Toyota", propietario1);
        Vehiculo vehiculo2 = new Moto("XYZ987", "Honda", propietario2, 120);

        // Ingresar vehículos al parqueadero
        boolean ingresoExitoso1 = parqueadero.ingresarVehiculo(vehiculo1, 0, 0);
        boolean ingresoExitoso2 = parqueadero.ingresarVehiculo(vehiculo2, 1, 1);

        if (ingresoExitoso1) {
            System.out.println("El vehículo 1 fue ingresado exitosamente al parqueadero.");
        } else {
            System.out.println("Error al ingresar el vehículo 1 al parqueadero.");
        }

        if (ingresoExitoso2) {
            System.out.println("El vehículo 2 fue ingresado exitosamente al parqueadero.");
        } else {
            System.out.println("Error al ingresar el vehículo 2 al parqueadero.");
        }

        // Obtener propietarios
        Propietario propietarioVehiculo1 = parqueadero.obtenerPropietario(0, 0);
        Propietario propietarioVehiculo2 = parqueadero.obtenerPropietario(1, 1);

        if (propietarioVehiculo1 != null) {
            System.out.println("El propietario del vehículo 1 es: " + propietarioVehiculo1.getNombre());
        } else {
            System.out.println("No se encontró el vehículo 1 en el parqueadero.");
        }

        if (propietarioVehiculo2 != null) {
            System.out.println("El propietario del vehículo 2 es: " + propietarioVehiculo2.getNombre());
        } else {
            System.out.println("No se encontró el vehículo 2 en el parqueadero.");
        }

        // Configurar tarifas
        parqueadero.configurarTarifa(TipoVehiculo.CARRO, 10.0);
        parqueadero.configurarTarifa(TipoVehiculo.MOTO_CLASICA, 5.0);
        parqueadero.configurarTarifa(TipoVehiculo.MOTO_HIBRIDA, 7.5);

        // Calcular costo de estacionamiento
        LocalDateTime momentoIngreso = LocalDateTime.of(2024, 5, 15, 10, 0);
        LocalDateTime momentoSalida = LocalDateTime.of(2024, 5, 15, 13, 30);
        double costoEstacionamiento1 = parqueadero.calcularCostoEstacionamiento(vehiculo1, momentoIngreso, momentoSalida);
        double costoEstacionamiento2 = parqueadero.calcularCostoEstacionamiento(vehiculo2, momentoIngreso, momentoSalida);

        System.out.println("El costo de estacionamiento del vehículo 1 es: " + costoEstacionamiento1);
        System.out.println("El costo de estacionamiento del vehículo 2 es: " + costoEstacionamiento2);
    }
}
