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
        showAntSlider.valueProperty().bindBidirectional(SettingsProperties.instance.showAnts);
        //showAntSlider.valueProperty().addListener((observableValue, number, t1) -> {});
        numberOfAntSlider.setMax(Double.valueOf(initconfig.getProperty("num_ants_max")));
        numberOfAntSlider.setMin(Double.valueOf(initconfig.getProperty("num_ants_min")));
        setSliderConfig(numberOfAntSlider);
        numberOfAntSlider.valueProperty().bindBidirectional(SettingsProperties.instance.num_ants);

        simulationSpeedSlider.setMin(Double.valueOf(initconfig.getProperty("stepsPerFrame_min")));
        simulationSpeedSlider.setMax(Double.valueOf(initconfig.getProperty("stepsPerFrame_max")));
        setSliderConfig(simulationSpeedSlider);
        simulationSpeedSlider.valueProperty().bindBidirectional(SettingsProperties.instance.speed);

        antSteeringSpeedSlider.setMin(Double.valueOf(initconfig.getProperty("antTurnSpeed_min")));
        antSteeringSpeedSlider.setMax(Double.valueOf(initconfig.getProperty("antTurnSpeed_max")));
        setSliderConfig(antSteeringSpeedSlider);
        antSteeringSpeedSlider.valueProperty().bindBidirectional(SettingsProperties.instance.antTurnSpeed);

        nestTrailFallofRateSlider.setMin(Double.valueOf(initconfig.getProperty("nestFalloffRate_min")));
        nestTrailFallofRateSlider.setMax(Double.valueOf(initconfig.getProperty("nestFalloffRate_max")));
        setSliderConfig(nestTrailFallofRateSlider);
        nestTrailFallofRateSlider.valueProperty().bindBidirectional(SettingsProperties.instance.nestFalloffRate);

        nestTrailFadeRateSlider.setMin(Double.valueOf(initconfig.getProperty("nestTrailFadeRate_min")));
        nestTrailFadeRateSlider.setMax(Double.valueOf(initconfig.getProperty("nestTrailFadeRate_max")));
        setSliderConfig(nestTrailFadeRateSlider);
        nestTrailFadeRateSlider.valueProperty().bindBidirectional(SettingsProperties.instance.nestTrailFadeRate);

        jitterAmountSlider.setMin(Double.valueOf(initconfig.getProperty("jitterMagnitude_min")));
        jitterAmountSlider.setMax(Double.valueOf(initconfig.getProperty("jitterMagnitude_max")));
        setSliderConfig(jitterAmountSlider);
        jitterAmountSlider.valueProperty().bindBidirectional(SettingsProperties.instance.jitterMagnitude);

        foodTrailFallofRateSlider.setMin(Double.valueOf(initconfig.getProperty("foodTrailFalloffRate_min")));
        foodTrailFallofRateSlider.setMax(Double.valueOf(initconfig.getProperty("foodTrailFalloffRate_max")));
        setSliderConfig(foodTrailFallofRateSlider);
        foodTrailFallofRateSlider.valueProperty().bindBidirectional(SettingsProperties.instance.foodTrailFalloffRate);

        foodTrailFadeRateSlider.setMin(Double.valueOf(initconfig.getProperty("foodTrailFadeRate_min")));
        foodTrailFadeRateSlider.setMax(Double.valueOf(initconfig.getProperty("foodTrailFadeRate_max")));
        setSliderConfig(foodTrailFadeRateSlider);
        foodTrailFadeRateSlider.valueProperty().bindBidirectional(SettingsProperties.instance.foodTrailFadeRate);



    }

    private void setSliderConfig(Slider slider) {
        slider.setMajorTickUnit((slider.getMax()-slider.getMin())/4);
        slider.setMinorTickCount(3);
    }
}
