package es.jeremy.ejee;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la ventana de creación de una nueva persona.
 * Maneja la entrada de datos del usuario y la interacción con la interfaz gráfica.
 */
public class NuevaPersonaController {
    @FXML
    private TextField nombreField; // Campo de nombre
    @FXML
    private TextField apellidosField; // Campo de apellidos
    @FXML
    private TextField edadField; // Campo de edad
    @FXML
    private Button guardarButton; // Botón para guardar
    @FXML
    private Button cancelarButton; // Botón para cancelar

    private HelloController parentController; // Controlador padre

    /**
     * Método que se ejecuta al inicializar el controlador.
     * Asigna las acciones a los botones de guardar y cancelar.
     */
    @FXML
    public void initialize() {
        // Asignar acción al botón de guardar
        guardarButton.setOnAction(e -> guardarPersona());
        cancelarButton.setOnAction(e -> cancelar()); // Asignar acción al botón de cancelar
    }

    /**
     * Establece el controlador padre para la ventana.
     *
     * @param parentController El controlador padre.
     */
    public void setParentController(HelloController parentController) {
        this.parentController = parentController;
    }

    /**
     * Método para guardar la nueva persona.
     * Obtiene los datos de los campos, valida la entrada,
     * y añade la persona al controlador padre.
     */
    private void guardarPersona() {
        // Obtener datos de los campos
        String nombre = nombreField.getText().trim();
        String apellidos = apellidosField.getText().trim();
        int edad;

        // Validación de los datos
        if (nombre.isEmpty() || apellidos.isEmpty() || edadField.getText().isEmpty()) {
            mostrarAlerta("Error", "Por favor, completa todos los campos.");
            return;
        }

        // Validar que el nombre y apellidos no contengan números
        if (!validarNombreYApellido(nombre, apellidos)) {
            mostrarAlerta("Error", "El nombre y los apellidos no pueden contener números.");
            return;
        }

        try {
            edad = Integer.parseInt(edadField.getText().trim()); // Convertir a entero

            // Crear la nueva persona y agregarla al controlador padre
            Persona nuevaPersona = new Persona(nombre, apellidos, edad);
            parentController.agregarPersona(nuevaPersona); // Agregar a la lista del controlador padre

            // Cerrar la ventana
            Stage stage = (Stage) guardarButton.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            // Manejar error de conversión a entero
            mostrarAlerta("Error", "La edad debe ser un número válido.");
        }
    }

    /**
     * Cierra la ventana sin realizar ninguna acción.
     */
    private void cancelar() {
        // Cerrar la ventana sin hacer nada
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Muestra una alerta con un mensaje específico.
     *
     * @param titulo  El título de la alerta.
     * @param mensaje El mensaje de la alerta.
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Valida que el nombre y los apellidos no contengan números.
     *
     * @param nombre    El nombre a validar.
     * @param apellidos Los apellidos a validar.
     * @return true si el nombre y los apellidos son válidos, false de lo contrario.
     */
    private boolean validarNombreYApellido(String nombre, String apellidos) {
        return nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+") && apellidos.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");
    }
}
