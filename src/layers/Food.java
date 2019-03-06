package layers;

import sample.MersenneTwister;
import sample.SettingsProperties;

import java.util.Random;

public class Food extends Layer {

    private Random random=new Random();
    @Override
    public void update() {
        if (AntSimulation.frame % 10 == 0) {
            super.blur(0.002);
        }
        if (random.nextDouble() < 0.01) {
            Vec data=new Vec(random.nextDouble() * SettingsProperties.instance.canvasWidth ,
                random.nextDouble() * SettingsProperties.instance.canvasHeight);
            mark(data, 100);
            System.out.println(data.x+" "+data.y);

        }
    }

    @Override
    public Double initCell(double x, double y) {
        if(random==null)random=new Random();
        return random.nextDouble() < 0.002 ? 100d : 0d;
    }
}
