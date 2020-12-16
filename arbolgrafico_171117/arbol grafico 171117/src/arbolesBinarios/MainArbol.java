package arbolesBinarios;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainArbol extends  Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("view/escena.fxml"));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Arbol Binario");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
