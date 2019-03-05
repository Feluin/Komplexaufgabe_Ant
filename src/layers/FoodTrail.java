package layers;

import sample.SettingsProperties;

public class FoodTrail extends Layer {
    @Override
    public void update() {
        super.mul(1 - SettingsProperties.instance.foodTrailFadeRate.getValue().doubleValue());
    }
}
