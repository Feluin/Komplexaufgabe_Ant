package sample;

import gui.FrameController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import layers.AntSimulation;

import java.util.ResourceBundle;

public class AntSimulationApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader =new  FXMLLoader(getClass().getResource("/ApplicationFrame.fxml"));
        Parent parent=loader.load();
        primaryStage.setTitle("layers.Ant Pheromone Simulation by Team 16");
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
        AntSimulation antSimulation=new AntSimulation(((FrameController)loader.getController()).getCanvasController());
        Platform.runLater(antSimulation::start);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
