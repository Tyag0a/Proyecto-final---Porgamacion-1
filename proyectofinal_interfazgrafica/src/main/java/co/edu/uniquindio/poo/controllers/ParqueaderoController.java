package co.edu.uniquindio.poo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.models.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;


public class ParqueaderoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button crearParqueaderoButton;

    @FXML
    private TextField nombreAdmin;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField numPuestosTextField;


   //accion del boton toventana2
   
    @FXML
    void toVentana2() throws IOException {
        // Verificar que los campos no estén vacíos
        if (nombreAdmin.getText().isEmpty() || nombreTextField.getText().isEmpty() || numPuestosTextField.getText().isEmpty()) {
            mostrarAlertaError();
        return;
         }

        // Obtener el valor ingresado en numPuestosTextField y guadarlo en el modelo

        String numPuestos = numPuestosTextField.getText();
        Model.getInstance().setNumPuestos(numPuestos);

        // Carga la segunda ventana
        App.setRoot("viewVehiculo");

    }

    @FXML
    void initialize() {
        assert crearParqueaderoButton != null : "fx:id=\"crearParqueaderoButton\" was not injected: check your FXML file 'viewParqueadero.fxml'.";
        assert nombreAdmin != null : "fx:id=\"nombreAdmin\" was not injected: check your FXML file 'viewParqueadero.fxml'.";
        assert nombreTextField != null : "fx:id=\"nombreTextField\" was not injected: check your FXML file 'viewParqueadero.fxml'.";
        assert numPuestosTextField != null : "fx:id=\"numPuestosTextField\" was not injected: check your FXML file 'viewParqueadero.fxml'.";

        // Restricción para permitir solo letras en nombreAdmin y nombreTextField

        UnaryOperator<TextFormatter.Change> lettersOnlyFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-Z]*")) {
                return change;
            } else {
                mostrarAlerta("Ingrese un valor válido. por favor, ingrese una cadena de texto.");
                return null;
            }
        };

        nombreAdmin.setTextFormatter(new TextFormatter<>(lettersOnlyFilter));
        nombreTextField.setTextFormatter(new TextFormatter<>(lettersOnlyFilter));

        // Restricción para permitir solo números enteros en numPuestosTextField

        UnaryOperator<TextFormatter.Change> integerOnlyFilter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("\\d*")) {
            return change;
        } else {
            mostrarAlerta("Ingrese un valor válido. El numero de enteros debe ser un entero positivo.");
            return null;
             }
        };
        
       numPuestosTextField.setTextFormatter(new TextFormatter<>(integerOnlyFilter));



    }

    // Método para mostrar una alerta

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    //Metodo para mostrar una alerta si se ingresan campos vacios

    private void mostrarAlertaError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Error al completar la acción. Por favor, llena los campos indicados para completar.");
        alert.showAndWait();
    
    }



}

