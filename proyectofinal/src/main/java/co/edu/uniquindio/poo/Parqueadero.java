package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.awt.Point;

public class Parqueadero {

    private final String nombre;
    public int numeroPuestos;
    static Puesto[][] puestos;
    public Collection<Vehiculo> listaVehiculos;
    public Tarifa tarifa;
    private Administracion administracion;


    public Parqueadero(String nombre, int numeroPuestos, Tarifa tarifa) {
        this.nombre = nombre;
        this.numeroPuestos = numeroPuestos;
        this.listaVehiculos = new LinkedList<>();
        this.administracion = new Administracion("",this);
    
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

    public boolean ubicarVehiculo(Vehiculo vehiculo, Puesto puesto) {
        if (verificarDisponibilidadPuesto(puesto) && !puesto.estaOcupado()) {

            puesto.ocuparPuesto(vehiculo);
            puesto.setOcupado(true);
            listaVehiculos.add(vehiculo);

            actualizarPuestoEnMatriz(puesto);

            Registro registro = new Registro(LocalDateTime.now(), null, vehiculo);
            
            administracion.agregarRegistro(registro);

            return true;
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

    //Metodo para verificar que la posicion en i,j existe

    private boolean verificarPuestoExiste(int i, int j) {
            return i >= 0 && i < puestos.length && j >= 0 && j < puestos[0].length;
        }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public Administracion getAdministracion() {
        return administracion;
    }

    public void setAdministracion(Administracion administracion) {
        this.administracion = administracion;
    }

    //Metodo para actualizar el atributo puestos con el puesto que se instancie

    public static void actualizarPuestoEnMatriz(Puesto puesto) {
        Point posicion = puesto.getPosicion();
        int i = (int) posicion.getX();
        int j = (int) posicion.getY();
        puestos[i][j] = puesto;
    }





}
