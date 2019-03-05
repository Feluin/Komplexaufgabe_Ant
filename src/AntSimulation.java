import gui.CanvasController;
import layers.*;
import sample.SettingsProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AntSimulation {

    CanvasController canvasController=new CanvasController();
    private Map<String , Layer> layers=new HashMap<>();
    private List<Ant> ants=new ArrayList<>();
    private int frame=0;

    public void createLayers(){
       layers.put("Food",new Food());
       layers.put("FoodTrail",new FoodTrail());
       layers.put("NestTrail",new NestTrail());
    }
    public void createandRemoveAnts(){
        if(ants.size()<SettingsProperties.instance.num_ants.getValue().intValue()){
            for (int i = ants.size(); i < SettingsProperties.instance.num_ants.getValue().intValue(); i++) {
               // TODO ants.add(new layers.Ant());
            }
        }else {
            ants=ants.stream().limit( SettingsProperties.instance.num_ants.getValue().intValue()).collect(Collectors.toList());
        }
    }

    public void update(){
        createandRemoveAnts();
        for (int i = 0; i < SettingsProperties.instance.speed.getValue().intValue(); i++) {
        layers.values().forEach(Layer::update);
        ants.forEach(Ant::update);
        }
        draw();
        frame++;
    }
    public void killAnt(Ant ant){
        ants.remove(ant);
    }

    public void draw(){
        canvasController.clear();
        drawLayers();
        if(SettingsProperties.instance.showAnts.getValue().intValue()==1) ants.forEach(Ant::draw);
        //update oder so
    }
    public void drawLayers(){

    }
}
