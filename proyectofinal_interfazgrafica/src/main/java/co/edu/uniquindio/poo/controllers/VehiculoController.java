package co.edu.uniquindio.poo.controllers;

import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.TextInputDialog;
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

    /*
    *   Variable para mantener el estado de los puestos disponibles
    */
    private int puestosDisponibles;
   

    /*
     *  Set para mantener el estado de los puestos ocupado
     */
    private Set<Integer> puestosOcupados;


    /*
     *  Acción del botón cerrar parqueadero
     */
    @FXML
    void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    /*
     *  Acción del botón establecer tarifas
     */
    @FXML
    void establecerTarifas(ActionEvent event) {
        // Verificar que los campos no estén vacíos
        if (tarifaCarroTextField.getText().isEmpty() || tarifaMotoTextFiel.getText().isEmpty() || tarifamotodosTextField.getText().isEmpty()) {
            mostrarAlertaError();
            return;
        }

        /*
         *   establecer tarifas
         */
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tarifas Establecidas");
        alert.setHeaderText(null);
        alert.setContentText("Tarifas establecidas correctamente.");
        alert.showAndWait();

        /*
         *   establecer tarifas en labels creados
         */
        tarifaCarroLabel.setText("Tarifa Carro: " + tarifaCarroTextField.getText());
        tarifaMotoLabel.setText("Tarifa Moto clásica: " + tarifaMotoTextFiel.getText());
        tarifamotodosLabel.setText("Tarifa Moto híbrida: " + tarifamotodosTextField.getText());
    }

    @FXML
    void mostrarMensajeRetirado(ActionEvent event) {
        /*
         *  Verificar que los campos no estén vacíos
         */
        if (placaTextField.getText().isEmpty() || reiroTextField.getText().isEmpty()) {
            mostrarAlertaError();
            return;
        }
        
        /*
         *  Obtener los valores de los textfields
         */
        String placaIngresada = placaTextField.getText();
        String placaRetirar = reiroTextField.getText();

        /*
         * Comparar las placas
         */
        if (!placaIngresada.equals(placaRetirar)) {
            // Mostrar mensaje de error si las placas no coinciden
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("El vehículo que intenta retirar no existe en el parqueadero. Por favor, verifique el valor ingresado.");
            alert.showAndWait();
        } else {
        /*
         *  Pedir al usuario la cantidad de horas que estuvo el vehículo en el parqueadero
         */
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Horas en el parqueadero");
        dialog.setHeaderText(null);
        dialog.setContentText("Por favor, ingrese la cantidad de horas que estuvo el vehículo en el parqueadero:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(horasStr -> {
            try {
                int horas = Integer.parseInt(horasStr);

                // Calcular el precio basado en las horas y la tarifa del tipo de vehículo
                double precio = calcularPrecio(horas);

                // Mostrar el precio al usuario
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Precio a pagar");
                alert.setHeaderText(null);
                alert.setContentText("El precio a pagar por el tiempo en el parqueadero es: $" + precio);
                alert.showAndWait();

                // Incrementar el número de puestos disponibles
                puestosDisponibles++;
                numeroPuestosIngresado.setText("Número de puestos disponibles: " + puestosDisponibles);

                // Marcar el puesto como desocupado
                int puesto = Integer.parseInt(puestoTextField.getText());
                puestosOcupados.remove(puesto);

                // Mostrar mensaje de vehículo retirado
                Alert retiradoAlert = new Alert(Alert.AlertType.INFORMATION);
                retiradoAlert.setTitle("Vehículo Retirado");
                retiradoAlert.setHeaderText(null);
                retiradoAlert.setContentText("El vehículo se ha retirado del parqueadero.");
                retiradoAlert.showAndWait();
            } catch (NumberFormatException e) {
                mostrarAlerta("Por favor, ingrese un número válido de horas.");
            }
        });
    }
}   
    /*
    *  Método para calcular el precio basado en las horas y la tarifa del tipo de vehículo
    */
    private double calcularPrecio(int horas) {
        String tipoVehiculo = tipovehiculoComboBox.getValue();
    double tarifa = 0;

    // Obtener la tarifa correspondiente al tipo de vehículo
    switch (tipoVehiculo) {
        case "Moto Híbrida":
            tarifa = Double.parseDouble(tarifamotodosTextField.getText());
            break;
        case "Moto Clásica":
            tarifa = Double.parseDouble(tarifaMotoTextFiel.getText());
            break;
        case "Carro":
            tarifa = Double.parseDouble(tarifaCarroTextField.getText());
            break;
        }

    // Calcular el precio
    return horas * tarifa;

}

    @FXML
    void registrarVehiculo(ActionEvent event) {
        // Verificar que los campos no estén vacíos
        if (modeloTextField.getText().isEmpty() || placaTextField.getText().isEmpty() || propietarioTextField.getText().isEmpty() || puestoTextField.getText().isEmpty()) {
            mostrarAlertaError();
            return;
        }

        // Obtener el valor del puesto
        int puesto = Integer.parseInt(puestoTextField.getText());

        // Verificar si el puesto ya está ocupado
        if (puestosOcupados.contains(puesto)) {
            mostrarAlerta("El puesto " + puesto + " ya está ocupado. Por favor, elija otro puesto.");
            return;
        }

        // Decrementar el número de puestos disponibles
        if (puestosDisponibles > 0) {
            puestosDisponibles--;
            numeroPuestosIngresado.setText("Número de puestos disponibles: " + puestosDisponibles);
            puestosOcupados.add(puesto);
        } else {
            mostrarAlertaError();
            return;
        }

        // Mostrar mensaje de confirmación
        String placa = placaTextField.getText();
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

        // Inicializar el número de puestos disponibles
        puestosDisponibles = Integer.parseInt(Model.getInstance().getNumPuestos());
        numeroPuestosIngresado.setText("Número de puestos disponibles: " + puestosDisponibles);

        // Inicializar el set de puestos ocupados
        puestosOcupados = new HashSet<>();

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

        /*
         *  Restricciones de los textfields de las tarifa
         */
        tarifaCarroTextField.setTextFormatter(new TextFormatter<>(positiveDoubleFilter));
        tarifaMotoTextFiel.setTextFormatter(new TextFormatter<>(positiveDoubleFilter));
        tarifamotodosTextField.setTextFormatter(new TextFormatter<>(positiveDoubleFilter));

        puestoTextField.setTextFormatter(new TextFormatter<>(integerFilter));

        /*
         *  Evento para mostrar mensaje de advertencia
         */
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
    }


    /*
     *    Método para mostrar una alerta
     */
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    /*
     *  Método para mostrar una alerta cuando se ingresan campos vacíos
     */
    private void mostrarAlertaError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Error al completar la acción. Por favor, llena los campos indicados para completar.");
        alert.showAndWait();
    }
}