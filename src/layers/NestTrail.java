package layers;

import com.sun.scenario.Settings;
import sample.SettingsProperties;

public class NestTrail extends Layer{
    @Override
    public void update() {
        super.mul(1 - SettingsProperties.instance.nestTrailFadeRate);
        if(buffer.length>0)
            buffer[height-1][width/2]=1000D;

    }
}
