package co.edu.uniquindio.poo.models;

//esta clase se utilizara como un singlenton, para almacenar el numero de puestos ingresado en la ventana 1
//y permitir acceder a este valor desde otras clases. resume: contenedor de datos

public class Model {
    
    private static Model instance = new Model();
    private String numPuestos;

    private Model() {}

    public static Model getInstance() {
        return instance;
    }

    public String getNumPuestos() {
        return numPuestos;
    }

    public void setNumPuestos(String numPuestos) {
        this.numPuestos = numPuestos;
    }
}