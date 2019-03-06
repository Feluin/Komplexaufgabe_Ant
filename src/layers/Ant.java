package layers;

import layers.Layer;
import sample.SettingsProperties;

public class Ant {

    private AntSimulation sim;
    private Vec pos;
    private Double angle;
    private Double speed;
    private Double stomach;
    private int homeRecency;
    private int age;

    public Vec getPos() {
        return pos;
    }

    public Ant(AntSimulation sim, Vec pos) {
        this.sim = sim;
        this.pos = pos != null ? pos : new Vec();
        this.angle = Math.random() * Math.PI * 2;
        this.speed = (Math.random() * 0.2 + 0.8) * SettingsProperties.instance.scaling * 0.4;
        this.stomach = 0D;
        this.homeRecency = 0;
        this.age = 0;
    }

    public Double sniff(Layer layer) {
        Double antennaAngle, antennaDist, leftSample, rightSample;
        Vec antennaLeftPos, antennaRightPos;
        antennaDist = 3d * SettingsProperties.instance.scaling;
        antennaAngle = Math.PI / 4d;
        antennaLeftPos = this.pos.get().add(Vec.fromAngleDist(this.angle + antennaAngle, antennaDist));
        antennaRightPos = this.pos.get().add(Vec.fromAngleDist(this.angle - antennaAngle, antennaDist));
        leftSample = layer.sample(antennaLeftPos);
        rightSample = layer.sample(antennaRightPos);
        if (leftSample < 0.01) {
            leftSample = (double) 0;
        }
        if (rightSample < 0.01) {
            rightSample = (double) 0;
        }
        return leftSample - rightSample;
    }

    public Vec update() {
        Vec boundPos;
        Double jitterAmount, reading, newStomach;

        this.age++;
        this.stomach *= 1 - SettingsProperties.instance.foodTrailFadeRate;
        this.homeRecency *= 1 - SettingsProperties.instance.nestTrailFadeRate;
        if (this.isInNest()) {
            this.stomach = 0D;
            this.homeRecency = 1;

        }
        newStomach = this.stomach + this.sim.getFoodLayer().take(this.pos, 1D);
        stomach = newStomach;
        if (this.isHunting()) {
            reading = this.sniff(this.sim.getFoodLayer());
            if (reading == 0) {
                reading = this.sniff(this.sim.getFoodTrailLayer());
            }
        } else {
            reading = this.sniff(this.sim.getNestTrailLayer());
        }
        this.sim.getFoodTrailLayer().mark(this.pos, this.stomach * 0.01);
        this.sim.getNestTrailLayer().mark(this.pos, this.homeRecency * 0.1);
        System.out.println(pos.x+" "+pos.y+ homeRecency*0.1);
        if (reading > 0) {
            this.angle += SettingsProperties.instance.antTurnSpeed;
        }
        if (reading < 0) {
            this.angle -= sample.SettingsProperties.instance.antTurnSpeed;
        }
        jitterAmount = Math.max(0, 1 - this.sim.getFoodTrailLayer().sample(this.pos));
        this.angle += (Math.random() - 0.5) * 2 * jitterAmount * SettingsProperties.instance.jitterMagnitude;
        this.pos.add(Vec.fromAngleDist(this.angle, this.speed));
        boundPos = this.pos.get().bound(0D, 0d, 0d, SettingsProperties.instance.canvasHeight-1D, SettingsProperties.instance.canvasWidth-1D, 0d);
        if (!boundPos.eq(this.pos)) {
            this.angle = Math.random() * Math.PI * 2;
            return this.pos = boundPos;
        }

        return boundPos;
    }

    public boolean isInNest() {
        return new Vec(SettingsProperties.instance.canvasWidth / 2D, SettingsProperties.instance.canvasHeight.doubleValue()).sub(this.pos).mag() < 10;
    }

    public boolean isHunting() {
        return this.stomach < 0.1 && this.homeRecency > 0.01;
    }


    public void draw() {

    }
}

