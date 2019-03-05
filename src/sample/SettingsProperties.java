package sample;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public enum SettingsProperties {
    instance;

    public Property<Number> num_ants=new SimpleObjectProperty<>();
    public Property<Number> speed=new SimpleObjectProperty<>();
    public Property<Number> antTurnSpeed=new SimpleObjectProperty<>();
    public Property<Number> showAnts=new SimpleObjectProperty<>();
    public Property<Number> jitterMagnitude=new SimpleObjectProperty<>();
    public Property<Number> nestFalloffRate=new SimpleObjectProperty<>();
    public Property<Number> foodTrailFalloffRate=new SimpleObjectProperty<>();
    public Property<Number> nestTrailFadeRate=new SimpleObjectProperty<>();
    public Property<Number> foodTrailFadeRate=new SimpleObjectProperty<>();
    public Property<Number> scaling=new SimpleObjectProperty<>();
    public Property<Number> canvasHeight=new SimpleObjectProperty<>();
    public Property<Number> canvasWidth=new SimpleObjectProperty<>();
}
