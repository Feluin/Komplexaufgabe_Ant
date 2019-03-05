package layers;

import com.sun.scenario.Settings;
import sample.SettingsProperties;

public class NestTrail extends Layer{
    @Override
    public void update() {
        super.mul(1 - SettingsProperties.instance.nestTrailFadeRate.getValue().doubleValue());
        if(buffer.size()>0)
            buffer.get(height-1).set(width/2,1000D);

    }
}
