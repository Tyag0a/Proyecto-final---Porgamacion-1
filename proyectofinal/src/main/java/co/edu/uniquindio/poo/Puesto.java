package co.edu.uniquindio.poo;
import java.awt.Point;

public class Puesto {   

    public Point posicion;
    public boolean ocupado;
    public Vehiculo vehiculo;

    public Puesto(int i, int j, boolean ocupado, Vehiculo vehiculo) {
        this.posicion = new Point(i,j); //point es un tipo de dato para puntos bidimensionales, de dos coordenadas
        this.ocupado = ocupado;
        this.vehiculo = vehiculo;
    }

    public Point getPosicion() {
        return posicion;
    }

    public void setPosicion(Point posicion) {
        this.posicion = posicion;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;

         // Si el puesto se ha ocupado, actualizamos la matriz de puestos
        if (this.ocupado) {
          Parqueadero.actualizarPuestoEnMatriz(this);

         }
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    //Metodo para saber si un puesto se encuentra ocupado o vacio

    public boolean estaOcupado() {
        return vehiculo != null;
    }

    //Metodo para liberar un puesto y desocuparlo

    public void desocuparPuesto () {
        setVehiculo(null);
    }

    //Metodo para ocupar el puesto

    public void ocuparPuesto ( Vehiculo vehiculo) {
        if (this.vehiculo == null) { //se verifica que el puesto no tenga un vehiculo ubicado en el
            this.vehiculo = vehiculo;
            setVehiculo(vehiculo);
        }
    }
    
}
