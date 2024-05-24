package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.awt.Point;

public class Parqueadero {

    private final String nombre;
    public int numeroPuestos;
    static Puesto[][] puestos;
    public Collection<Vehiculo> listaVehiculos; //Esta coleccion solo incluye los vehiculos parqueados ACTUALMENTE
    public TarifaParqueadero tarifa;
    private Administracion administracion;


    public Parqueadero(String nombre, int numeroPuestos, TarifaParqueadero tarifa) {
        this.nombre = nombre;
        this.numeroPuestos = numeroPuestos;
        this.listaVehiculos = new LinkedList<>();
        this.administracion = new Administracion("",this);
        this.tarifa = tarifa;
    
    }

    public String getNombre() {
        return nombre;
    }


    public int getNumeroPuestos() {
        return numeroPuestos;
    }


    public Collection<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }


    public void setNumeroPuestos(int numeroPuestos) {
        this.numeroPuestos = numeroPuestos;
    }

    public void setListaVehiculos(Collection<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public Puesto[][] getPuestos() {
        return puestos;
    }

    //Metodo para verificar si un vehiculo ya existe en la lista de vehiculos, es decir si ya se 
    //encuentra parqueado o no existe en el parqueadero

    public boolean verificarVehiculoExiste(Vehiculo vehiculo) {
        for (Vehiculo v : listaVehiculos) {
            if (v.equals(vehiculo)) {
                return true;
            }
        }
        return false; //true si el vehiculo existe en la lista, false en caso contrario
    }

    //Metodo para agregar un vehiculo a la lista de vehiculos parqueados actualmente,
    //verificando si ya existe en la lista

    public boolean agregarVehiculo(Vehiculo vehiculo) {
        if (!verificarVehiculoExiste(vehiculo)) {
            listaVehiculos.add(vehiculo);
            return true;
        }
        return false; //true si el vehiculo se agrega correctamente, false en caso contrario
    }

    //Metodo para eliminar un vehiculo de la lista actual de vehiculos parqueados
    //verificando si existe en la lista

    public boolean eliminarVehiculo (Vehiculo vehiculo){
        if (!verificarVehiculoExiste(vehiculo)) {
            listaVehiculos.remove(vehiculo);
            return true;
        }
        return false; //true si el vehiculo se elimina correctamente, false en caso contrario
    }
    //Metodo para crear el numero de puestos, vacios y con su estado de ocupado false

    public void crearPuestos(int numeroPuestos) {
        assert numeroPuestos > 0;
        puestos = new Puesto[numeroPuestos][numeroPuestos];
        for (int i = 0; i < numeroPuestos; i++) {
            for (int j = 0; j < numeroPuestos; j++) {
                puestos[i][j] = new Puesto(i,j,false,null);
                //Cuando se crea la matriz con los puestso, estos se encuentran vacios y en estado false
            }
        }
    }

    //Metodo para verificar que un puesto se encuentra disponible y sin ningun vehiculo

    public boolean verificarDisponibilidadPuesto(Puesto puesto) {

        return puesto.getVehiculo() == null; //si el vehiculo es null, el puesto ta vacio

    }

    //Metodo para ubicar un vehiculo en un puesto verificando si este esta disponible, 
    //asi dando las referencias del puesto y vehiculo en cada uno de ellos
    //resumen: metodo para parquear

    public boolean ubicarVehiculo(Vehiculo vehiculo, Puesto puesto) {
        if (verificarDisponibilidadPuesto(puesto) && !puesto.estaOcupado() && !verificarVehiculoExiste(vehiculo)) {

            puesto.ocuparPuesto(vehiculo);
            puesto.setOcupado(true);
            agregarVehiculo(vehiculo);

            actualizarPuestoEnMatriz(puesto);

            RegistroParqueadero registro = new RegistroParqueadero(LocalDateTime.now(), null, vehiculo);
            
            administracion.agregarRegistro(registro);

            return true;
        }
        return false; // true si el vehiculo cumple las condiciones y se ubica correctamente, false en caso contrario
    }

    //Metodo para retirar un vehiculo, asegurando que el vehiculo salga del parqueadero y se actualicen los datos
    //Registrando la hora de salida, actualizando el estado del puesto

    public boolean retirarVehiculo(Puesto puesto) {
        if (puesto.estaOcupado()) {
            
            Vehiculo vehiculo = puesto.getVehiculo();
    
            puesto.desocuparPuesto();
            puesto.setOcupado(false);
            eliminarVehiculo(vehiculo);
    
            // buscar el registro correspondiente al vehículo para actualizar la hora de salida
            for (RegistroParqueadero registro : administracion.getListaRegistros()) {
                if (registro.getVehiculoRegistrado().equals(vehiculo) && registro.getRegistroSalida() == null) {
                    registro.registrarHoraSalida();
                    actualizarPuestoEnMatriz(puesto);
                    return true;
                    
                }
            }
    
        }
        return false;

    }

    //Metodo para obtener el propietario de un vehiculo, segun un determinado puesto donde se encuentra ubicado

    public String identificarPropietario(int i, int j) {
        if (verificarPuestoExiste(i, j)) {
            Puesto puesto = puestos[i][j];
            if (puesto != null && puesto.getVehiculo() != null) {
                Propietario propietario = puesto.getVehiculo().getPropietario();
                if (propietario != null) {
                    return propietario.getNombre(); 
                }
            }
        }
        return null;
    }

    //Metodo para verificar que la posicion en i,j existe, verificar si este existe en el arreglo

    private boolean verificarPuestoExiste(int i, int j) {
            return i >= 0 && i < puestos.length && j >= 0 && j < puestos[0].length;
        }

    public TarifaParqueadero getTarifa() {
        return tarifa;
    }

    public void setTarifa(TarifaParqueadero tarifa) {
        this.tarifa = tarifa;
    }

    public Administracion getAdministracion() {
        return administracion;
    }

    public void setAdministracion(Administracion administracion) {
        this.administracion = administracion;
    }

    //Metodo para actualizar el atributo puestos con el puesto que se instancie
    //ya sea para desocupar u ocupar esa posicion en la matriz

    public static void actualizarPuestoEnMatriz(Puesto puesto) {
        Point posicion = puesto.getPosicion();
        int i = (int) posicion.getX();
        int j = (int) posicion.getY();
        puestos[i][j] = puesto;
    }





}
