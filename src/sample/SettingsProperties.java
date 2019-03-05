package sample;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public enum SettingsProperties {
    instance;

    public Property<Number> num_ants=new SimpleObjectProperty<>(1);
    public Property<Number> speed=new SimpleObjectProperty<>(1);
    public Property<Number> antTurnSpeed=new SimpleObjectProperty<>(0.7);
    public Property<Number> showAnts=new SimpleObjectProperty<>(1);
    public Property<Number> jitterMagnitude=new SimpleObjectProperty<>(0.5);
    public Property<Number> nestFalloffRate=new SimpleObjectProperty<>(0.01);
    public Property<Number> foodTrailFalloffRate=new SimpleObjectProperty<>(0.01);
    public Property<Number> nestTrailFadeRate=new SimpleObjectProperty<>(0.01);
    public Property<Number> foodTrailFadeRate=new SimpleObjectProperty<>(0.005);
    public Property<Number> scaling=new SimpleObjectProperty<>(4);
    public Property<Number> canvasHeight=new SimpleObjectProperty<>(1);
    public Property<Number> canvasWidth=new SimpleObjectProperty<>(1);

    public Integer getCanvasHeightInt() {
        return canvasHeight.getValue().intValue();
    }

    public Integer getCanvasWidthInt() {
        return canvasWidth.getValue().intValue();
    }
}
