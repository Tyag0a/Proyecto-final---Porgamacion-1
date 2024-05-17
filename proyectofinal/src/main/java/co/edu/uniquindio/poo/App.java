package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.Scanner;

public class App {

        public static void main(String[] args) {

            //instanciaar un scanner que permita la entrada de datos en consola

            Scanner scanner = new Scanner(System.in);

            // solicitar en consola que se ingresen el valor de los atributos del parqueadero y la tarifa
            System.out.println("Ingrese el nombre del parqueadero:");
            String nombreParqueadero = scanner.nextLine();
    
            System.out.println("Ingrese el número de puestos del parqueadero:");
            int numeroPuestos = scanner.nextInt();
    
            System.out.println("Ingrese la tarifa para motos clásicas:");
            double tarifaMtClasica = scanner.nextDouble();
    
            System.out.println("Ingrese la tarifa para motos híbridas:");
            double tarifaMtHibrida = scanner.nextDouble();
    
            System.out.println("Ingrese la tarifa para carros:");
            double tarifaCarro = scanner.nextDouble();
    
            // instanciar la tarifa con los atributos ingresados
            Tarifa tarifa = new Tarifa(tarifaMtClasica, tarifaMtHibrida, tarifaCarro);
    
            // instanciar parqueadero con los atributos ingresados
            Parqueadero parqueadero = new Parqueadero(nombreParqueadero, numeroPuestos, tarifa);
    
            // crear los puestos del parqueadero con el numero de puestos ingresado
            parqueadero.crearPuestos(numeroPuestos);
    
            // pequeño menu con las acciones que puede hacer el usuariu

            int opcion; //variable que almacena el numero de opcion que se ingrese en consola

            do { //bucle do- while, donde hace que el codigo se repita mientras que la condicion en el while de abajo se cumpla

                System.out.println("\nMenú de opciones:");
                System.out.println("1. Ubicar vehículo");
                System.out.println("2. Consultar propietario de vehículo en un puesto");
                System.out.println("3. Configurar tarifas");
                System.out.println("4. Generar reporte diario");
                System.out.println("5. Generar reporte mensual");
                System.out.println("6. Salir");
                System.out.println("Ingrese el número de la opción que desea:");
    
                opcion = scanner.nextInt(); 
    
                switch (opcion) { //esta estructura switch case va a permitir ejecutar un bloque de codigo segun el valor de la variable que definimos,
                                  //tiene la misma funcion que por ejemplo una cadena de  if - else if - else if -...etcetc
                                  //ej: se ingreso el 5 en consola, la variable ahora valdra 5 y se ejecutara en caso 5, donde esta el codigo que se ejecutara respecto a la opcion 5
                    case 1:
                        // ubicar un vehiculo
                        System.out.println("Ingrese la placa del vehículo:");
                        String placa = scanner.next();
    
                        System.out.println("Ingrese el modelo del vehículo:");
                        String modelo = scanner.next();
    
                        System.out.println("Ingrese el nombre del propietario:");
                        String nombrePropietario = scanner.next();
    
                        System.out.println("Ingrese el apellido del propietario:");
                        String apellidoPropietario = scanner.next();
    
                        // instanciar propietario con los atributos ingresados
                        Propietario propietario = new Propietario(nombrePropietario, apellidoPropietario);
    
                        // instanciar vehiculo con los atributos ingresados
                        Vehiculo vehiculo = new Vehiculo(placa, modelo, propietario);
    
                        // ubicar un vehiculo en un puesto
                        System.out.println("Ingrese la fila del puesto:");
                        int fila = scanner.nextInt();
    
                        System.out.println("Ingrese la columna del puesto:");
                        int columna = scanner.nextInt();

                        //se llama el metodo ubicar vehiculo, ademas se verifica con if si el vehiculo ingresado es igual al vehiculo ubicado en el puesto
    
                        if (parqueadero.ubicarVehiculo(vehiculo, parqueadero.getPuestos()[fila][columna])) {
                            System.out.println("Vehículo ubicado exitosamente.");
                        } else {
                            System.out.println("No se pudo ubicar el vehículo en el puesto.");
                        }
                        break;
                    case 2:
                        // consultar propietario de vehículo en un puesto
                        System.out.println("Ingrese la fila del puesto:");
                        int filaConsultada = scanner.nextInt();
    
                        System.out.println("Ingrese la columna del puesto:");
                        int columnaConsultada = scanner.nextInt();

                        //se llama al metodo identificar propietario y se le pasan como argumento las variables ingresadas
    
                        String propietarioVehiculo = parqueadero.identificarPropietario(filaConsultada, columnaConsultada);
                        if (propietarioVehiculo != null) {
                            System.out.println("El propietario del vehículo en el puesto es: " + propietarioVehiculo);
                        } else {
                            System.out.println("No hay vehículo en el puesto especificado.");
                        }
                        break; //este indica que el case/opcion o este bloque de codigo ya se termino de ejecutar, funciona igual para las 6 opciones
                    case 3:
                        // configurar tarifas
                        System.out.println("Ingrese la nueva tarifa para motos clásicas:");
                        double nuevaTarifaMtClasica = scanner.nextDouble();
                        parqueadero.getAdministracion().configurarTarifaMotoClasica(nuevaTarifaMtClasica);
    
                        System.out.println("Ingrese la nueva tarifa para motos híbridas:");
                        double nuevaTarifaMtHibrida = scanner.nextDouble();
                        parqueadero.getAdministracion().configurarTarifaMotoHibrida(nuevaTarifaMtHibrida);
    
                        System.out.println("Ingrese la nueva tarifa para carros:");
                        double nuevaTarifaCarro = scanner.nextDouble();
                        parqueadero.getAdministracion().configurarTarifaCarro(nuevaTarifaCarro);

                        //se llaman los metodos de configurar las tarifas para cada tipo de vehiculo, pasandoles como argumento las variables ingrresadas
    
                        System.out.println("Tarifas actualizadas correctamente.");
                        break;
                    case 4:
                        // generar reporte diario
                        System.out.println("Ingrese la fecha del reporte diario (Año-Mes-Día):");
                        System.out.print("Año: ");
                        int añoReporteDiario = scanner.nextInt();
                        System.out.print("Mes: ");
                        int mesReporteDiario = scanner.nextInt();
                        System.out.print("Día: ");
                        int diaReporteDiario = scanner.nextInt();
    
                        double[] reporteDiario = parqueadero.getAdministracion().generarReporteDiario(
                                LocalDateTime.of(añoReporteDiario, mesReporteDiario, diaReporteDiario, 0, 0));
                        
                        System.out.println("Reporte diario:");
                        System.out.println("Recaudo por moto clásica: $" + reporteDiario[0]);
                        System.out.println("Recaudo por moto híbrida: $" + reporteDiario[1]);
                        System.out.println("Recaudo por carro: $" + reporteDiario[2]);
                        break;
                        //igual que los demas cases, se llaman los metodos creados en las clases y se les pasa como argumento las variables ingresadas
                    case 5:
                        // generar reporte mensual
                        System.out.println("Ingrese el mes y año del reporte mensual (Año-Mes):");
                        System.out.print("Año: ");
                        int añoReporteMensual = scanner.nextInt();
                        System.out.print("Mes: ");
                        int mesReporteMensual = scanner.nextInt();
    
                        double recaudoMensual = parqueadero.getAdministracion().generarReporteMensual(
                                mesReporteMensual, añoReporteMensual);
                        
                        System.out.println("Recaudo mensual total: $" + recaudoMensual);
                        break;
                    case 6:
                        // salir del programa
                        System.out.println("Saliendo del programa...");
                        //si se ingresa el numero 6 simplemente se muesta este print
                        break;
                    default: //el default permite indicar que pasa cuando la variable "opcion" no coincide con ninguno de los casos establecidos anteriormente
                        System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                }
    
            } while (opcion != 6); //el bucle while se repite mientras el numero de opcion sea diferente de 6, porque 6 cierra el bloque de codigo (corta el bucle por decirlo asi)
            //esta condicion "opcion != 6" se evalua despues de ejecutado el bloque de codigo
    
            //cerrar el scannner
            scanner.close();


    }
}

