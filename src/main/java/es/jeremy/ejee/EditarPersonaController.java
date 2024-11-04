package es.jeremy.ejee;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la ventana de edición de una persona.
 * Permite modificar los datos de una persona seleccionada.
 */
public class EditarPersonaController {
    @FXML
    private TextField nombreField; // Campo para nombre
    @FXML
    private TextField apellidosField; // Campo para apellidos
    @FXML
    private TextField edadField; // Campo para edad
    @FXML
    private Button guardarButton; // Botón para guardar
    @FXML
    private Button cancelarButton; // Botón para cancelar

    private HelloController parentController; // Controlador padre
    private Persona personaEdicion; // Persona que se está editando

    /**
     * Método que se ejecuta al inicializar el controlador.
     * Asigna las acciones a los botones de guardar y cancelar.
     */
    @FXML
    public void initialize() {
        // Asignar acción al botón de guardar
        guardarButton.setOnAction(e -> guardarPersona());
        cancelarButton.setOnAction(e -> cancelar());
    }

    /**
     * Pre-carga los datos de la persona seleccionada en los campos de texto.
     *
     * @param persona La persona a editar.
     */
    public void cargarDatos(Persona persona) {
        this.personaEdicion = persona; // Guardar la referencia de la persona
        nombreField.setText(persona.getNombre());
        apellidosField.setText(persona.getApellidos());
        edadField.setText(String.valueOf(persona.getEdad()));
    }

    /**
     * Guarda los cambios realizados en los campos de texto.
     * Valida la entrada y actualiza la persona editada.
     */
    private void guardarPersona() {
        String nombre = nombreField.getText();
        String apellidos = apellidosField.getText();
        String edadStr = edadField.getText();

        // Validación de datos
        if (nombre.isEmpty() || apellidos.isEmpty() || edadStr.isEmpty()) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return;
        }

        try {
            int edad = Integer.parseInt(edadStr); // Convertir la edad a entero
            // Actualizar la persona
            personaEdicion.setNombre(nombre);
            personaEdicion.setApellidos(apellidos);
            personaEdicion.setEdad(edad);

            // Notificar al controlador padre que los datos han cambiado
            parentController.actualizarTabla(); // Asegúrate de implementar este método

            // Cerrar la ventana
            cerrarVentana();
        } catch (NumberFormatException e) {
            mostrarAlerta("La edad debe ser un número válido.");
        }
    }

    /**
     * Cancela la edición y cierra la ventana.
     */
    private void cancelar() {
        cerrarVentana();
    }

    /**
     * Cierra la ventana actual.
     */
    private void cerrarVentana() {
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Muestra una alerta al usuario con un mensaje específico.
     *
     * @param mensaje El mensaje de alerta a mostrar.
     */
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Establece el controlador padre para la ventana de edición.
     *
     * @param parentController El controlador padre.
     */
    public void setParentController(HelloController parentController) {
        this.parentController = parentController;
    }
}
