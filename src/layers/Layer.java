package layers;

import com.sun.scenario.Settings;
import sample.SettingsProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static sample.SettingsProperties.instance;

public abstract class Layer {
    Integer width;
    Integer height;
    Double[][]  buffer= new Double[0][0];

    //sim ist die Größe von der UI
    public Layer() {

        this.width = instance.canvasWidth/ instance.scaling;
        this.height = instance.canvasHeight/ instance.scaling;
        //updates
        initCells();
    }

    private void initCells() {
        Double[][] newbuffer= new Double[height][width];
        for (int y = 0; y < newbuffer.length; y++) {
            Double[] row = newbuffer[y];
            for (int x = 0; x < row.length; x++) {
                if (y<buffer.length&&x<buffer[y].length){
                    newbuffer[y][x]=buffer[y][x];
                }else {
                    newbuffer[y][x]=initCell(x,y);
                }
            }

        }
        buffer=newbuffer;
    }

    public Double initCell(double x, double y) {
        return 0.0;
    }

    public Double[][] mul(Double n) {

        for (int y = 0; y < buffer.length; y++) {
            Double[] row = buffer[y];
            for (int x = 0; x < row.length; x++) {
               buffer[y][x]= buffer[y][x]*n;
            }

        }
        return buffer;
    }

    public Double[][] add(Double n) {
        for (int y = 0; y < buffer.length; y++) {
            Double[] row = buffer[y];
            for (int x = 0; x < row.length; x++) {
                buffer[y][x]= buffer[y][x]+n;
            }
        }
        return buffer;
    }

    public void mark(Vec pos, double amount) {
        getBuffer(pos.x.intValue(),pos.y.intValue());
        buffer[Math.min(height,Math.max(pos.y.intValue(),0)) / SettingsProperties.instance.scaling]
                [Math.min(width,Math.max(pos.x.intValue(),0))/ SettingsProperties.instance.scaling]=getBuffer(pos.x.intValue(),pos.y.intValue())+amount;

    }

    public Double sample(Vec pos) {
        return getBuffer(pos.x.intValue(),pos.y.intValue())!=null ? getBuffer(pos.x.intValue(),pos.y.intValue()) : 0d;
    }

    public Double take(Vec pos, double amount) {

        Double takeamount = Math.min(getBuffer(pos.x.intValue(),pos.y.intValue()) != null ? getBuffer(pos.x.intValue(),pos.y.intValue()) : 0d, amount);
        buffer[Math.min(height,Math.max(pos.y.intValue(),0)) / SettingsProperties.instance.scaling]
                [Math.min(width,Math.max(pos.x.intValue(),0))/ SettingsProperties.instance.scaling]=getBuffer(pos.x.intValue(),pos.y.intValue())-amount;

        return takeamount;
    }

    public Double[][] blur(double n) {


        for (int y = 0; y < buffer.length; y++) {
            Double[] row = buffer[y];
            for (int x = 0; x < row.length; x++) {
                Double sumNeighbors = 0d;
                Double aDouble = row[x];
                for (int x1 = Math.max(0, x - 1); x1 < Math.min(width - 1, x + 1); x1++) {
                    for (int y1 = Math.max(0, y - 1); y1 < Math.min(height - 1, x + 1); y1++) {
                        sumNeighbors += aDouble * n;
                    }
                }
                sumNeighbors += aDouble * (1 - n);
                buffer[y][x]=(((9 * n + (1 - n))) != 0 ? sumNeighbors / (9 * n + (1 - n)) : 0);
            }

        }

        return this.buffer;
    }
    public void printlayer(){
        for (int y = 0; y < buffer.length; y++) {
            Double[] row = buffer[y];
            for (int x = 0; x < row.length; x++) {
               System.out.print(buffer[y][x].intValue()+" ");
            }
            System.out.println();

        }
    }


    public abstract void update();

    public Double getBuffer(int x, int y) {
        x=Math.min(SettingsProperties.instance.canvasWidth-1,Math.max(x,0))/ SettingsProperties.instance.scaling;
        y=Math.min(SettingsProperties.instance.canvasHeight-1,Math.max(y,0))/ SettingsProperties.instance.scaling;
        return buffer[y][x];
    }
}