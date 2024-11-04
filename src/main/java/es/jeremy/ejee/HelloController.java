package es.jeremy.ejee;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La clase HelloController es responsable de manejar la lógica de la interfaz de usuario
 * para la gestión de personas. Permite agregar, modificar y eliminar personas de una tabla.
 */
public class HelloController {

    @FXML
    private TableView<Persona> tableView;  // Tabla de personas
    @FXML
    private TableColumn<Persona, String> nombreColumn;  // Columna Nombre
    @FXML
    private TableColumn<Persona, String> apellidosColumn;  // Columna Apellidos
    @FXML
    private TableColumn<Persona, Integer> edadColumn;  // Columna Edad
    @FXML
    private Button agregarButton;  // Botón para agregar personas
    @FXML
    private Button modificarButton;  // Botón para modificar personas
    @FXML
    private Button eliminarButton;  // Botón para eliminar personas

    private ObservableList<Persona> personas; // Lista observable de personas

    /**
     * Este método es llamado al inicializar la interfaz. Configura la tabla de personas y
     * asigna las acciones a los botones de agregar, modificar y eliminar.
     */
    @FXML
    public void initialize() {
        personas = FXCollections.observableArrayList();
        tableView.setItems(personas);

        // Configuración de las columnas de la tabla
        nombreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        apellidosColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellidos()));
        edadColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEdad()).asObject());

        // Asignar la acción al botón agregar
        agregarButton.setOnAction(e -> agregarPersona());

        // Asignar la acción al botón modificar
        modificarButton.setOnAction(e -> modificarPersona());

        // Asignar la acción al botón eliminar
        eliminarButton.setOnAction(e -> eliminarPersona());
    }

    /**
     * Método que se encarga de agregar una nueva persona.
     * Abre una ventana modal para ingresar los datos de la persona.
     */
    private void agregarPersona() {
        try {
            // Cargar el FXML de la ventana modal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ventana.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la ventana modal
            NuevaPersonaController controller = loader.getController();
            controller.setParentController(this); // Establecer el controlador padre

            // Crear la nueva ventana
            Stage stage = new Stage();
            stage.setTitle("Nueva Persona");
            stage.setScene(new Scene(root));
            stage.setResizable(false); // No se puede cambiar el tamaño
            stage.initModality(Modality.APPLICATION_MODAL); // Modalidad
            stage.showAndWait(); // Esperar a que se cierre la ventana

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la ventana de agregar persona.");
        }
    }

    /**
     * Método que se encarga de modificar la información de una persona seleccionada.
     * Abre una ventana modal con los datos de la persona para ser editados.
     */
    private void modificarPersona() {
        // Obtener la persona seleccionada de la tabla
        Persona personaSeleccionada = tableView.getSelectionModel().getSelectedItem();
        if (personaSeleccionada != null) {
            try {
                // Cargar el FXML de la ventana modal
                FXMLLoader loader = new FXMLLoader(getClass().getResource("editarventana.fxml"));
                Parent root = loader.load();

                // Obtener el controlador de la ventana modal
                EditarPersonaController controller = loader.getController();
                controller.setParentController(this); // Establecer el controlador padre
                controller.cargarDatos(personaSeleccionada); // Cargar datos de la persona seleccionada

                // Crear la nueva ventana
                Stage stage = new Stage();
                stage.setTitle("Modificar Persona");
                stage.setScene(new Scene(root));
                stage.setResizable(false); // No se puede cambiar el tamaño
                stage.initModality(Modality.APPLICATION_MODAL); // Modalidad
                stage.showAndWait(); // Esperar a que se cierre la ventana

            } catch (IOException e) {
                e.printStackTrace();
                mostrarAlerta("Error", "No se pudo abrir la ventana de editar persona.");
            }
        } else {
            mostrarAlerta("Advertencia", "Por favor, selecciona una persona para modificar.");
        }
    }

    /**
     * Método que se encarga de eliminar una persona seleccionada de la tabla.
     */
    private void eliminarPersona() {
        // Obtener la persona seleccionada de la tabla
        Persona personaSeleccionada = tableView.getSelectionModel().getSelectedItem();
        if (personaSeleccionada != null) {
            personas.remove(personaSeleccionada);
            mostrarAlerta("Éxito", "Persona eliminada con éxito.");
        } else {
            mostrarAlerta("Advertencia", "Por favor, selecciona una persona para eliminar.");
        }
    }

    /**
     * Método para agregar una nueva persona a la lista.
     *
     * @param nuevaPersona La nueva persona a agregar.
     */
    public void agregarPersona(Persona nuevaPersona) {
        if (!personas.contains(nuevaPersona)) {
            personas.add(nuevaPersona);
            mostrarAlerta("Éxito", "Persona agregada con éxito.");
        } else {
            mostrarAlerta("Error", "Esta persona ya existe en la lista.");
        }
    }

    /**
     * Método para mostrar alertas al usuario.
     *
     * @param titulo   Título de la alerta.
     * @param mensaje  Mensaje de la alerta.
     */
    public void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Método para actualizar la tabla de personas.
     * Se debe llamar después de modificar o eliminar una persona.
     */
    public void actualizarTabla() {
        tableView.refresh();
    }
}
