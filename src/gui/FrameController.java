package gui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import layers.AntSimulation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FrameController implements Initializable {
    public SplitPane splitpane;
    private CanvasController canvasController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {


            GridPane settingspane = FXMLLoader.load(getClass().getResource("/SettingsControl.fxml"));
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/AntCanvas.fxml"));

            Canvas playground = loader.load();
            canvasController = loader.getController();
            splitpane.getItems().setAll(settingspane, playground);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public CanvasController getCanvasController() {
        return canvasController;
    }
}
