package gui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FrameController implements Initializable {
    public SplitPane splitpane;
    private GridPane settingspane;
    private Canvas playground;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            settingspane = FXMLLoader.load(getClass().getResource("/SettingsControl.fxml"));
            playground = FXMLLoader.load(getClass().getResource("/AntCanvas.fxml"));
            splitpane.getItems().setAll(settingspane, playground);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
