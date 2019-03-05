package gui;

import com.sun.scenario.Settings;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import layers.*;
import sample.SettingsProperties;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CanvasController implements Initializable {
    public Canvas canvas;

    public Canvas getCanvas() {
        return canvas;
    }

    public void clear() {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.setFill(Color.BLACK);
        graphicsContext2D.fill();

    }

    public void drawLayers(List<Layer> layers) {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        List<Food> foods = layers.stream().filter(layer -> layer instanceof Food).map(layer -> (Food) layer).collect(Collectors.toList());
        List<NestTrail> nestTrails = layers.stream().filter(layer -> layer instanceof NestTrail).map(layer -> (NestTrail) layer).collect(Collectors.toList());
        List<FoodTrail> foodTrails = layers.stream().filter(layer -> layer instanceof FoodTrail).map(layer -> (FoodTrail) layer).collect(Collectors.toList());
        for (int i = 0; i < canvas.getWidth(); i++) {
            for (int j = 0; j < canvas.getHeight(); j++) {
                double red = 0.13, green = 0.11, blue = 0.10;
                for (NestTrail nestTrail : nestTrails) {
                    red += 0.5 * nestTrail.getBuffer(i);
                    green += 0.1 * nestTrail.getBuffer(i);
                }
                for (Food food : foods) {
                    red += 0.65 * food.getBuffer(i);
                    green += 1 * food.getBuffer(i);
                }
                for (FoodTrail foodTrail : foodTrails) {
                    blue += 2.5 * foodTrail.getBuffer(i);
                    green += 1.7 * foodTrail.getBuffer(i);
                }
                graphicsContext2D.getPixelWriter().setColor(i,j,Color.rgb((int)red,(int) green,(int)blue,1d));
            }
        }
    }

    public void drawAnt(Ant ant){
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.save();
        graphicsContext2D.setFill(Color.WHITE);
        graphicsContext2D.beginPath();
        //graphicsContext2D.translate(ant.getPos().x*SettingsProperties.instance.scaling.getValue().doubleValue(),);
        graphicsContext2D.arc(ant.getPos().x, ant.getPos().y,
                SettingsProperties.instance.scaling.getValue().doubleValue(),
                SettingsProperties.instance.scaling.getValue().doubleValue(),
                0d,
                Math.PI*2);
        graphicsContext2D.fill();
        System.out.println(ant.getPos().x+" "+ ant.getPos().y);
        graphicsContext2D.restore();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SettingsProperties.instance.canvasHeight.setValue(canvas.getHeight());
        canvas.heightProperty().bindBidirectional(SettingsProperties.instance.canvasHeight);
        SettingsProperties.instance.canvasWidth.setValue(canvas.getWidth());
        canvas.widthProperty().bindBidirectional(SettingsProperties.instance.canvasWidth);
    }
}
