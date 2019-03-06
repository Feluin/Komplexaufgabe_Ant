package sample;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public enum SettingsProperties {
    instance;

    public Integer num_ants=0;
    public Double speed=1.0;
    public Double antTurnSpeed=0.7;
    public Integer showAnts=1;
    public Double jitterMagnitude=0.5;
    public Double nestFalloffRate=0.01;
    public Double foodTrailFalloffRate=0.01;
    public Double nestTrailFadeRate=0.01;
    public Double foodTrailFadeRate=0.005;
    public Integer scaling=1;
    public Integer canvasHeight=1;
    public Integer canvasWidth=1;

}
