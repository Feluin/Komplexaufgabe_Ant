package gui;

import com.sun.scenario.Settings;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import layers.Food;
import layers.FoodTrail;
import layers.Layer;
import layers.NestTrail;
import sample.SettingsProperties;

import java.util.List;
import java.util.stream.Collectors;

public class CanvasController {
    public Canvas canvas;


    public Canvas getCanvas() {
        return canvas;
    }

    public void clear() {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
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
                graphicsContext2D.getPixelWriter().setColor(i,j,new Color(red,green,blue,1));
            }
        }
    }
}
