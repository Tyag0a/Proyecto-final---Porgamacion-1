package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.Scanner;

public class App {

        public static void main(String[] args) {

            //instanciaar un scanner que permita la entrada de datos en consola

            Scanner scanner = new Scanner(System.in);

            System.out.println("\nBienvenido al Programa ;)");

            // solicitar en consola que se ingresen el valor de los atributos del parqueadero y la tarifa
            System.out.println("\nIngrese el nombre del parqueadero:");
            String nombreParqueadero = scanner.nextLine();
    
            System.out.println("\nIngrese el número de puestos del parqueadero:");
            int numeroPuestos = scanner.nextInt();
    
            System.out.println("\nIngrese la tarifa para motos clásicas:");
            double tarifaMtClasica = scanner.nextDouble();
    
            System.out.println("\nIngrese la tarifa para motos híbridas:");
            double tarifaMtHibrida = scanner.nextDouble();
    
            System.out.println("\nIngrese la tarifa para carros:");
            double tarifaCarro = scanner.nextDouble();
    
            // instanciar la tarifa con los atributos ingresados
            TarifaParqueadero tarifa = new TarifaParqueadero(tarifaMtClasica, tarifaMtHibrida, tarifaCarro);
    
            // instanciar parqueadero con los atributos ingresados
            Parqueadero parqueadero = new Parqueadero(nombreParqueadero, numeroPuestos, tarifa);
    
            // crear los puestos del parqueadero con el numero de puestos ingresado
            parqueadero.crearPuestos(numeroPuestos);
    
            // pequeño menu con las acciones que puede hacer el usuariu

            int opcion; //variable que almacena el numero de opcion que se ingrese en consola

            do { //bucle do- while, donde hace que el codigo se repita al menos una vez y se repite mas de una vez mientras que la condicion en el while de abajo se cumpla

                System.out.println("\nMenú de opciones:");
                System.out.println("1. Ubicar un vehículo");
                System.out.println("2. Consultar propietario de un vehículo en un puesto");
                System.out.println("3. Configurar las tarifas");
                System.out.println("4. Generar reporte diario");
                System.out.println("5. Generar reporte mensual");
                System.out.println("6. Retirar un vehículo");
                System.out.println("7. Salir");
                System.out.println("\nIngrese el número de la opción que desea:");

                //Se le asigna a la variable opcion el valor que se ingrese en consola
    
                opcion = scanner.nextInt(); 
    
                switch (opcion) { //esta estructura switch case va a permitir ejecutar un bloque de codigo segun el valor de la variable que definimos,
                                  //tiene la misma funcion que por ejemplo una cadena de  if - else if - else if -...etcetc
                                  //ej: se ingreso el 5 en consola, la variable ahora valdra 5 y se ejecutara en caso 5, donde esta el codigo que se ejecutara respecto al entero ingresado
                    case 1:
                        // opcion 1: ubicar un vehiculo

                        System.out.println("\nIngrese la placa del vehículo:");
                        String placa = scanner.next();
    
                        System.out.println("\nIngrese el modelo del vehículo:");
                        String modelo = scanner.next();
    
                        System.out.println("\nIngrese el nombre del propietario:");
                        String nombrePropietario = scanner.next();
    
                        System.out.println("\nIngrese el apellido del propietario:");
                        String apellidoPropietario = scanner.next();
    
                        // instanciar propietario con los atributos ingresados
                        Propietario propietario = new Propietario(nombrePropietario, apellidoPropietario);

                        // solicitar tipo de vehículo

                        System.out.println("\nIngrese el tipo de vehiculo:");
                        System.out.println("1: Moto Clásica");
                        System.out.println("2: Moto Híbrida");
                        System.out.println("3: Carro");
                        int tipoVehiculo = scanner.nextInt();
    
                        Vehiculo vehiculo = null; //Se declara null, ya que no se sabe que vehiculo es

                        switch (tipoVehiculo) { //switch case para instanciar el vehiculo de acuerdo al numero ingresado

                           case 1:
                             System.out.println("\nIngrese la velocidad maxima de la Moto Clasica:");
                             double velMaximaMtClasica = scanner.nextDouble();

                             vehiculo = new MotoClasica(placa, modelo, propietario,velMaximaMtClasica);
                             break;

                           case 2:
                             System.out.println("\nIngrese la velocidad maxima de la Moto Hibrida:");
                             double velMaximaMtHibrida = scanner.nextDouble();

                             vehiculo = new MotoHibrida(placa, modelo, propietario,velMaximaMtHibrida);
                             break;

                           case 3:
                             vehiculo = new Carro(placa, modelo, propietario);
                             break;

                           default:
                             System.out.println("Tipo de vehículo no válido.");
                             break;
                         }

                        if (vehiculo != null) {
                          // ubicar el vehículo instanciado en un puesto
                          System.out.println("\nIngrese la fila del puesto:");
                          int fila = scanner.nextInt();

                          System.out.println("\nIngrese la columna del puesto:");
                          int columna = scanner.nextInt();

                       // Se llama el método ubicarVehiculo, además se verifica con if si el vehículo ingresado es igual al vehículo ubicado en el puesto
                       if (parqueadero.ubicarVehiculo(vehiculo, parqueadero.getPuestos()[fila][columna])) {
                          System.out.println("\nVehículo ubicado exitosamente.");
                       } else {
                          System.out.println("\nNo se pudo ubicar el vehículo en el puesto indicado. Este no se encuentra disponible.");
                       }
                       }
                      break;
    
                    case 2:
                        // opcion 2: consultar propietario de vehículo en un puesto

                        System.out.println("\nIngrese la fila del puesto:");
                        int filaConsultada = scanner.nextInt();
    
                        System.out.println("\nIngrese la columna del puesto:");
                        int columnaConsultada = scanner.nextInt();

                        //se llama al metodo identificar propietario y se le pasan como argumento las variables ingresadas
    
                        String propietarioVehiculo = parqueadero.identificarPropietario(filaConsultada, columnaConsultada);
                        if (propietarioVehiculo != null) {
                            System.out.println("\nEl propietario del vehículo en el puesto es: " + propietarioVehiculo);
                        } else {
                            System.out.println("\nNo hay un vehículo en el puesto especificado.");
                        }
                        break; //este indica que el case/opcion o este bloque de codigo ya se termino de ejecutar, funciona igual para las 7 opciones

                    case 3:
                        //opcion 3: configurar las tarifas

                        System.out.println("\nIngrese la nueva tarifa para motos clásicas:");
                        double nuevaTarifaMtClasica = scanner.nextDouble();
                        parqueadero.getAdministracion().configurarTarifaMotoClasica(nuevaTarifaMtClasica);
    
                        System.out.println("\nIngrese la nueva tarifa para motos híbridas:");
                        double nuevaTarifaMtHibrida = scanner.nextDouble();
                        parqueadero.getAdministracion().configurarTarifaMotoHibrida(nuevaTarifaMtHibrida);
    
                        System.out.println("\nIngrese la nueva tarifa para carros:");
                        double nuevaTarifaCarro = scanner.nextDouble();
                        parqueadero.getAdministracion().configurarTarifaCarro(nuevaTarifaCarro);

                        //se llaman los metodos de configurar las tarifas para cada tipo de vehiculo, pasandoles como argumento las variables ingrresadas
    
                        System.out.println("\nTarifas actualizadas correctamente.");
                        break;

                    case 4:
                        //opcion 4: generar reporte diario

                        System.out.println("\nIngrese la fecha del reporte diario (Año-Mes-Día):");
                        System.out.print("Año: ");
                        int añoReporteDiario = scanner.nextInt();
                        System.out.print("Mes: ");
                        int mesReporteDiario = scanner.nextInt();
                        System.out.print("Día: ");
                        int diaReporteDiario = scanner.nextInt();

                        //invocar metodo de generar reporte diario y pasarle como argumento los datos ingresados
    
                        double[] reporteDiario = parqueadero.getAdministracion().generarReporteDiario(
                                LocalDateTime.of(añoReporteDiario, mesReporteDiario, diaReporteDiario, 0, 0));

                        //se imprime en pantalla el arreglo creado en el metodo
                        
                        System.out.println("\nReporte diario:");
                        System.out.println("Recaudo por moto clásica: $" + reporteDiario[0]);
                        System.out.println("Recaudo por moto híbrida: $" + reporteDiario[1]);
                        System.out.println("Recaudo por carro: $" + reporteDiario[2]);
                        break;
                        //igual que los demas cases, se llaman los metodos creados en las clases y se les pasa como argumento las variables ingresadas

                    case 5:
                        //opcion 5:  generar reporte mensual

                        System.out.println("\nIngrese el mes y año del reporte mensual (Año-Mes):");
                        System.out.print("\nAño: ");
                        int añoReporteMensual = scanner.nextInt();
                        System.out.print("Mes: ");
                        int mesReporteMensual = scanner.nextInt();

                        //invocar metodo generar reporte mensual 
    
                        double recaudoMensual = parqueadero.getAdministracion().generarReporteMensual(
                                mesReporteMensual, añoReporteMensual);
                        
                        System.out.println("\nRecaudo mensual total: $" + recaudoMensual);
                        break;
                    
                    case 6:

                        //opcion 6: retirar un vehículo del parqueadero

                        System.out.println("\nIngrese la fila del puesto:");
                        int filaDesubicar = scanner.nextInt();

                        System.out.println("\nIngrese la columna del puesto:");
                        int columnaDesubicar = scanner.nextInt();

                        Puesto puestoRetiro = parqueadero.getPuestos()[filaDesubicar][columnaDesubicar];
                        Vehiculo vehiculoRetirado = puestoRetiro.getVehiculo();

                        if (parqueadero.retirarVehiculo(puestoRetiro)) {
                             System.out.println("\nEl vehículo ha salido del parqueadero exitosamente.");

                        //si el metodo retirar vehiculo devuelve un true, se imprime el mensaje y se actualizan los datos

                        // Buscar el registro asociado al vehículo retirado recorriendo la lista de registros
                        RegistroParqueadero registroRetiro = null;
                        for (RegistroParqueadero registro : parqueadero.getAdministracion().getListaRegistros()) {
                            if (registro.getVehiculoRegistrado().equals(vehiculoRetirado)) {
                                 registroRetiro = registro;
                                 break;
                            }
                        }

                        //si se tiene el registro de su salida, se imprime la tarifa total de parqueo
                        //asi asegurando que inmediatamente despues de salir del parqueadero, se tenga su tarifa calculada

                        if (registroRetiro != null) {
                             // Calcular la tarifa total del estacionamiento del vehículo retirado
                             double tarifaTotalRetiro = parqueadero.getTarifa().calcularCostoTotalEstacionamiento(registroRetiro);
                             System.out.println("La tarifa total del estacionamiento es: $" + tarifaTotalRetiro);
                        } else {
                             System.out.println("No se pudo calcular la tarifa. No se encontró el registro asociado al vehículo retirado.");
                        }
                        } else {
                              System.out.println("\nNo se pudo retirar el vehículo del puesto.");
                        }
                        break;



                    case 7:
                        //opcion 7: salir del programa

                        System.out.println("\nSaliendo del programa...");
                        //si se ingresa el numero 6 simplemente se muesta este print
                        break;

                    default: //el default permite indicar que pasa cuando la variable "opcion" no coincide con ninguno de los casos establecidos anteriormente
                        System.out.println("\nOpción no válida. Por favor, ingrese una opción válida.");
                }
    
            } while (opcion != 7); //el bucle while se repite por dos veces o mas mientras el numero de opcion sea diferente de 7, ya que la opcion 7 es "salir del programa" por lo cual cierra el bloque de codigo (corta el bucle por decirlo asi)
            //esta condicion "opcion != 7" se evalua despues de ejecutado el bloque de codigo al menos una vez
    
            //cerrar el scannner
            scanner.close();


    }
}