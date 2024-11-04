package es.jeremy.ejee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

/**
 * La clase HelloApplication es la clase principal de la aplicación JavaFX.
 * Esta clase se encarga de iniciar la aplicación y mostrar la interfaz de usuario.
 */
public class HelloApplication extends Application {

    /**
     * El método start se llama cuando la aplicación es iniciada.
     * Este método se encarga de cargar el archivo FXML, crear la escena y configurar el escenario.
     *
     * @param stage El escenario principal de la aplicación donde se muestra la escena.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Cargar el archivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        // Crear la escena con las dimensiones adecuadas
        Scene scene = new Scene(fxmlLoader.load(), 734, 474);

        // Establecer el título de la ventana
        stage.setTitle("Personas");

        // Cargar el logo como imagen
        Image icon = new Image(getClass().getResourceAsStream("/img/agenda.png"));
        stage.getIcons().add(icon); // Establecer el ícono

        // Mostrar la escena en la ventana
        stage.setScene(scene);
        stage.show();
    }

    /**
     * El método main es el punto de entrada de la aplicación.
     * Este método inicia la aplicación JavaFX.
     *
     * @param args Argumentos de línea de comandos que se pueden pasar al programa.
     */
    public static void main(String[] args) {
        // Iniciar la aplicación
        launch();
    }
}
