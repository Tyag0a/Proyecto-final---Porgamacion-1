package co.edu.uniquindio.poo.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import co.edu.uniquindio.poo.models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.scene.Node;

public class VehiculoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnRetirarVehiculo;

    @FXML
    private Button establecerTarifasButton;

    @FXML
    private TextField modeloTextField;

    @FXML
    private Label numeroPuestosIngresado;

    @FXML
    private TextField placaTextField;

    @FXML
    private TextField propietarioTextField;

    @FXML
    private TextField puestoTextField;

    @FXML
    private TextField reiroTextField; 

    @FXML
    private TextField tarifaCarroTextField;

    @FXML
    private TextField tarifaMotoTextFiel;

    @FXML
    private TextField tarifamotodosTextField;

    @FXML
    private ComboBox<String> tipovehiculoComboBox;

    @FXML
    private Button ubicarVehiculoButton;

    @FXML
    private Label tarifaCarroLabel;

    @FXML
    private Label tarifaMotoLabel;

    @FXML
    private Label tarifamotodosLabel;

    //accion del boton cerrar parqueadero

    @FXML
    void cerrarVentana(ActionEvent event) {

      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.close();

    }

    //accion del boton establecer tarifas

    @FXML
    void establecerTarifas(ActionEvent event) {

        // Verificar que los campos no estén vacíos

        if (tarifaCarroTextField.getText().isEmpty() || tarifaMotoTextFiel.getText().isEmpty() || tarifamotodosTextField.getText().isEmpty()) {
            mostrarAlertaError();
            return;
        }
         // establecer las tarifas

         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Tarifas Establecidas");
         alert.setHeaderText(null);
         alert.setContentText("Tarifas establecidas correctamente.");
         alert.showAndWait();

         //establecer tarifas en los label creados
         
        tarifaCarroLabel.setText("Tarifa Carro: " + tarifaCarroTextField.getText());
        tarifaMotoLabel.setText("Tarifa Moto clasica: " + tarifaMotoTextFiel.getText());
        tarifamotodosLabel.setText("Tarifa Moto hibrida: " + tarifamotodosTextField.getText());

    }

    @FXML
    void mostrarMensajeRetirado(ActionEvent event) {

         // Verificar que los campos no estén vacios

        if (placaTextField.getText().isEmpty() || reiroTextField.getText().isEmpty()) {
            mostrarAlertaError();
            return;
         }

         // Obtener los valores de los textfields

         String placaIngresada = placaTextField.getText();
         String placaRetirar = reiroTextField.getText();
        
         // Comparar las placas

         if (!placaIngresada.equals(placaRetirar)) {

            // Mostrar mensaje de error si las placas no coinciden

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("El vehículo que intenta retirar no existe en el parqueadero. Por favor, verifique el valor ingresado.");
            alert.showAndWait();

         } else {

            // Mostrar mensaje de vehículo retirado

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vehículo Retirado");
            alert.setHeaderText(null);
            alert.setContentText("El vehículo se ha retirado del parqueadero.");
            alert.showAndWait();
        }


    }

    @FXML
    void registrarVehiculo(ActionEvent event) {

        // Verificar que los campos no estén vacíos

        if (modeloTextField.getText().isEmpty() || placaTextField.getText().isEmpty() || propietarioTextField.getText().isEmpty() || puestoTextField.getText().isEmpty()) {
            mostrarAlertaError();
            return;
        }
        // Obtener el valor del textfield de la placa

        String placa = placaTextField.getText();

        // Mostrar mensaje de confirmación

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registro de Vehículo");
        alert.setHeaderText(null);
        alert.setContentText("Vehículo de placa " + placa + " registrado correctamente.");
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        assert btnCerrar != null : "fx:id=\"btnCerrar\" was not injected: check your FXML file 'viewVehiculo.fxml'.";
        assert btnRetirarVehiculo != null : "fx:id=\"btnRetirarVehiculo\" was not injected: check your FXML file 'viewVehiculo.fxml'.";
        assert establecerTarifasButton != null : "fx:id=\"establecerTarifasButton\" was not injected: check your FXML file 'viewVehiculo.fxml'.";
        assert modeloTextField != null : "fx:id=\"modeloTextField\" was not injected: check your FXML file 'viewVehiculo.fxml'.";
        assert placaTextField != null : "fx:id=\"placaTextField\" was not injected: check your FXML file 'viewVehiculo.fxml'.";
        assert propietarioTextField != null : "fx:id=\"propietarioTextField\" was not injected: check your FXML file 'viewVehiculo.fxml'.";
        assert puestoTextField != null : "fx:id=\"puestoTextField\" was not injected: check your FXML file 'viewVehiculo.fxml'.";
        assert tarifaCarroTextField != null : "fx:id=\"tarifaCarroTextField\" was not injected: check your FXML file 'viewVehiculo.fxml'.";
        assert tarifaMotoTextFiel != null : "fx:id=\"tarifaMotoTextFiel\" was not injected: check your FXML file 'viewVehiculo.fxml'.";
        assert tarifamotodosTextField != null : "fx:id=\"tarifamotodosTextField\" was not injected: check your FXML file 'viewVehiculo.fxml'.";
        assert tipovehiculoComboBox != null : "fx:id=\"tipovehiculoComboBox\" was not injected: check your FXML file 'viewVehiculo.fxml'.";
        assert ubicarVehiculoButton != null : "fx:id=\"ubicarVehiculoButton\" was not injected: check your FXML file 'viewVehiculo.fxml'.";

        // Añadir opciones al ComboBox (los items)

        tipovehiculoComboBox.getItems().addAll("Moto Híbrida", "Moto Clásica", "Carro");

        // Restricción para permitir solo letras en modelo y propietario

        modeloTextField.setTextFormatter(new TextFormatter<>(change ->
        change.getControlNewText().matches("^[a-zA-Z]*$") ? change : null));

        propietarioTextField.setTextFormatter(new TextFormatter<>(change ->
        change.getControlNewText().matches("^[a-zA-Z]*$") ? change : null));

        // Restricción para no permitir números negativos en puesto

        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("-?\\d*")) {
            return change;
        }

            return null;

        };

        // Restricciones para permitir solo números reales positivos en tarifas
        UnaryOperator<TextFormatter.Change> positiveDoubleFilter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("\\d*\\.?\\d*")) {
            try {
                if (newText.isEmpty() || Double.parseDouble(newText) >= 0) {
                    return change;
                }
            } catch (NumberFormatException e) {
                            //
            }
        }
         mostrarAlerta("Ingrese un valor válido (número positivo).");
         return null;
        };

        //Restricciones del formato de los textfield de las tarifas
        
        tarifaCarroTextField.setTextFormatter(new TextFormatter<>(positiveDoubleFilter));
        tarifaMotoTextFiel.setTextFormatter(new TextFormatter<>(positiveDoubleFilter));
        tarifamotodosTextField.setTextFormatter(new TextFormatter<>(positiveDoubleFilter));

        puestoTextField.setTextFormatter(new TextFormatter<>(integerFilter));

        // Evento de validación para mostrar mensaje de advertencia

        modeloTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[a-zA-Z]*$")) {
                modeloTextField.setText(oldValue);
                mostrarAlerta("Ingrese un valor válido para Modelo.");
            }
        });

        propietarioTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[a-zA-Z]*$")) {
                propietarioTextField.setText(oldValue);
                mostrarAlerta("Ingrese un valor válido para Propietario.");
            }
        });

        // Obtener el valor del modelo y actualiza el label

        String numPuestos = Model.getInstance().getNumPuestos();
        numeroPuestosIngresado.setText("Número de puestos disponibles: " + numPuestos);

    }

    // Método para mostrar una alerta

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();

    }

    //metodo para mostrar una alerta cuando se ingresan campos vacios

    private void mostrarAlertaError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Error al completar la acción. Por favor, llena los campos indicados para completar.");
        alert.showAndWait();
        
        }

}



