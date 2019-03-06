package gui;

import com.sun.scenario.Settings;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import sample.SettingsProperties;

import java.io.IOException;
import java.lang.module.Configuration;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class SettingsController implements Initializable
{
    public Slider showAntSlider;
    public Slider numberOfAntSlider;
    public Slider simulationSpeedSlider;
    public Slider antSteeringSpeedSlider;
    public Slider nestTrailFallofRateSlider;
    public Slider nestTrailFadeRateSlider;
    public Slider foodTrailFallofRateSlider;
    public Slider foodTrailFadeRateSlider;
    public Slider jitterAmountSlider;


    public SettingsController(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Properties initconfig=new Properties();
        try {
            initconfig.load(getClass().getResourceAsStream("/configuration.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        showAntSlider.valueProperty().addListener((observableValue, number, t1) -> SettingsProperties.instance.showAnts=observableValue.getValue().intValue());
        //showAntSlider.valueProperty().addListener((observableValue, number, t1) -> {});
        numberOfAntSlider.setMax(Double.valueOf(initconfig.getProperty("num_ants_max")));
        numberOfAntSlider.setMin(Double.valueOf(initconfig.getProperty("num_ants_min")));
        setSliderConfig(numberOfAntSlider);
        numberOfAntSlider.valueProperty().addListener((observableValue, number, t1) -> SettingsProperties.instance.num_ants=observableValue.getValue().intValue());

        simulationSpeedSlider.setMin(Double.valueOf(initconfig.getProperty("stepsPerFrame_min")));
        simulationSpeedSlider.setMax(Double.valueOf(initconfig.getProperty("stepsPerFrame_max")));
        setSliderConfig(simulationSpeedSlider);
        simulationSpeedSlider.valueProperty().addListener((observableValue, number, t1) -> SettingsProperties.instance.speed=observableValue.getValue().doubleValue());

        antSteeringSpeedSlider.setMin(Double.valueOf(initconfig.getProperty("antTurnSpeed_min")));
        antSteeringSpeedSlider.setMax(Double.valueOf(initconfig.getProperty("antTurnSpeed_max")));
        setSliderConfig(antSteeringSpeedSlider);
        antSteeringSpeedSlider.valueProperty().addListener((observableValue, number, t1) -> SettingsProperties.instance.antTurnSpeed=observableValue.getValue().doubleValue());

        nestTrailFallofRateSlider.setMin(Double.valueOf(initconfig.getProperty("nestFalloffRate_min")));
        nestTrailFallofRateSlider.setMax(Double.valueOf(initconfig.getProperty("nestFalloffRate_max")));
        setSliderConfig(nestTrailFallofRateSlider);
        nestTrailFallofRateSlider.valueProperty().addListener((observableValue, number, t1) -> SettingsProperties.instance.nestFalloffRate=observableValue.getValue().doubleValue());

        nestTrailFadeRateSlider.setMin(Double.valueOf(initconfig.getProperty("nestTrailFadeRate_min")));
        nestTrailFadeRateSlider.setMax(Double.valueOf(initconfig.getProperty("nestTrailFadeRate_max")));
        setSliderConfig(nestTrailFadeRateSlider);
        nestTrailFadeRateSlider.valueProperty().addListener((observableValue, number, t1) -> SettingsProperties.instance.nestTrailFadeRate=observableValue.getValue().doubleValue());

        jitterAmountSlider.setMin(Double.valueOf(initconfig.getProperty("jitterMagnitude_min")));
        jitterAmountSlider.setMax(Double.valueOf(initconfig.getProperty("jitterMagnitude_max")));
        setSliderConfig(jitterAmountSlider);
        jitterAmountSlider.valueProperty().addListener((observableValue, number, t1) -> SettingsProperties.instance.jitterMagnitude=observableValue.getValue().doubleValue());

        foodTrailFallofRateSlider.setMin(Double.valueOf(initconfig.getProperty("foodTrailFalloffRate_min")));
        foodTrailFallofRateSlider.setMax(Double.valueOf(initconfig.getProperty("foodTrailFalloffRate_max")));
        setSliderConfig(foodTrailFallofRateSlider);
        foodTrailFallofRateSlider.valueProperty().addListener((observableValue, number, t1) -> SettingsProperties.instance.foodTrailFalloffRate=observableValue.getValue().doubleValue());

        foodTrailFadeRateSlider.setMin(Double.valueOf(initconfig.getProperty("foodTrailFadeRate_min")));
        foodTrailFadeRateSlider.setMax(Double.valueOf(initconfig.getProperty("foodTrailFadeRate_max")));
        setSliderConfig(foodTrailFadeRateSlider);
        foodTrailFadeRateSlider.valueProperty().addListener((observableValue, number, t1) -> SettingsProperties.instance.foodTrailFadeRate=observableValue.getValue().doubleValue());



    }

    private void setSliderConfig(Slider slider) {
        slider.setMajorTickUnit((slider.getMax()-slider.getMin())/4);
        slider.setMinorTickCount(3);
    }
}
