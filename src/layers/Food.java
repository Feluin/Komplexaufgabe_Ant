package layers;

import sample.SettingsProperties;


public class Food extends Layer {
    @Override
    public void update() {
        if (AntSimulation.frame % 100 == 0)
            super.blur(0.002);
        if (Math.random() < 0.01) {
            mark(new Vec(Math.random() * SettingsProperties.instance.canvasWidth.getValue().doubleValue() / SettingsProperties.instance.scaling.getValue().doubleValue(),
                    Math.random() * SettingsProperties.instance.canvasHeight.getValue().doubleValue() /SettingsProperties.instance.scaling.getValue().doubleValue()), 100);

        }
    }

    @Override
    public Double initCell(double x, double y) {
        return Math.random() < 0.002 ? 100d : 0d;
    }
}
