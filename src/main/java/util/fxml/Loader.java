package util.fxml;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Loader {
    private static final String sPathControl = "fxml/";

    public Loader() {
        super();
    }

    public static Stage loader(Class<?> Class, StageStyle style, String path, String title) throws IOException {
        Stage stage = createStageInstance(sPathControl + path, Class);
        stage.initStyle(style);
        stage.setTitle(title);
        return stage;
    }

    public static Stage createStageInstance(String path, Class<?> Class) throws IOException {
        Parent root = FXMLLoader.load(Class.getClassLoader().getResource(path));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        return stage;
    }
}
