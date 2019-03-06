package layers;

import gui.CanvasController;
import javafx.application.Platform;
import sample.SettingsProperties;

import java.util.*;
import java.util.stream.Collectors;

public class AntSimulation {

    private CanvasController canvasController;
    private Map<String, Layer> layers = new HashMap<>();
    private List<Ant> ants = new ArrayList<>();
    public static int frame = 0;

    public AntSimulation(CanvasController canvasController) {
        this.canvasController = canvasController;
        createLayers();
    }
    public void start(){
        update();
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        },1000,500);

    }

    public void createLayers() {
        layers.put("Food", new Food());
        layers.put("FoodTrail", new FoodTrail());
        layers.put("NestTrail", new NestTrail());
    }

    public void createandRemoveAnts() {
        if (ants.size() < SettingsProperties.instance.num_ants) {
            for (int i = ants.size(); i < SettingsProperties.instance.num_ants; i++) {

                ants.add(new Ant(this, new Vec(SettingsProperties.instance.canvasWidth/ 2d,SettingsProperties.instance.canvasHeight-1d),System.nanoTime()%(i+1)));
            }
        } else {
            ants = ants.stream().limit(SettingsProperties.instance.num_ants).collect(Collectors.toList());
        }
    }

    public void update() {
        createandRemoveAnts();
        for (int i = 0; i < SettingsProperties.instance.speed; i++) {
            layers.values().forEach(Layer::update);
            ants.forEach(Ant::update);
        }
        Platform.runLater(this::draw);
        frame++;
    }

    public void killAnt(Ant ant) {
        ants.remove(ant);
    }

    public void draw() {
        canvasController.clear();
        canvasController.drawLayers(new ArrayList<>(layers.values()));
        if (SettingsProperties.instance.showAnts == 1)
            ants.forEach(ant -> canvasController.drawAnt(ant));

    }


    public Layer getFoodLayer() {
        return layers.get("Food");
    }

    public Layer getFoodTrailLayer() {
        return layers.get("FoodTrail");
    }

    public Layer getNestTrailLayer() {
        return layers.get("NestTrail");

    }
}
