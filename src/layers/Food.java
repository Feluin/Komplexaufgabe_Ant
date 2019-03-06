package layers;

import sample.MersenneTwister;
import sample.SettingsProperties;


public class Food extends Layer {

    @Override
    public void update() {
        if (AntSimulation.frame % 10 == 0) {
            super.blur(0.02);
        }
        if (new MersenneTwister().nextDouble() < 0.01) {
            mark(new Vec(new MersenneTwister().nextDouble() * SettingsProperties.instance.canvasWidth ,
                    new MersenneTwister().nextDouble() * SettingsProperties.instance.canvasHeight), 100);

        }
    }

    @Override
    public Double initCell(double x, double y) {
        return new MersenneTwister().nextDouble() < 0.002 ? 100d : 0d;
    }
}
