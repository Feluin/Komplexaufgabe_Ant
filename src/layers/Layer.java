package layers;

import sample.SettingsProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static sample.SettingsProperties.instance;

public abstract class Layer {
    private Integer width;
    private Integer height;
    private List<Double> buffer = new ArrayList<>();

    //sim ist die Größe von der UI
    public Layer() {
        double ref;
        int _i, i;
        this.width = instance.canvasWidth.getValue().intValue() / instance.scaling.getValue().intValue();
        this.height = instance.canvasHeight.getValue().intValue() / instance.scaling.getValue().intValue();
        instance.scaling.addListener((observableValue, number, t1) -> {
            this.width = instance.canvasWidth.getValue().intValue() / instance.scaling.getValue().intValue();
            this.height = instance.canvasHeight.getValue().intValue() / instance.scaling.getValue().intValue();
            //update buffer
        });
        instance.canvasHeight.addListener((observableValue, number, t1) -> {
            this.width = instance.canvasWidth.getValue().intValue() / instance.scaling.getValue().intValue();
            this.height = instance.canvasHeight.getValue().intValue() / instance.scaling.getValue().intValue();
            //update buffer
        });
        instance.canvasWidth.addListener((observableValue, number, t1) -> {
            this.width = instance.canvasWidth.getValue().intValue() / instance.scaling.getValue().intValue();
            this.height = instance.canvasHeight.getValue().intValue() / instance.scaling.getValue().intValue();
            //update buffer
        });

        for (i = _i = 0, ref = this.width * this.height; 0 <= ref ? _i < ref : _i > ref; i = 0 <= ref ? ++_i : --_i) {
            this.buffer.add(this.initCell(i % this.width, Math.floor(i / this.height)));
        }
    }

    private Double initCell(double x, double y) {
        return 0.0;
    }

    public List mul(Double n) {
        List<Double> buffer = new ArrayList<>(this.buffer);
        buffer = buffer.stream().map(aDouble -> aDouble * n).collect(Collectors.toList());
        return buffer;
    }

    public List<Double> add(Double n) {
        List<Double> buffer = new ArrayList<>(this.buffer);
        buffer = buffer.stream().map(aDouble -> aDouble + n).collect(Collectors.toList());
        return buffer;
    }

    public void mark(Vec pos, double amount) {
        int index = posToIndex(pos);
        buffer.set(index,getBuffer(index)+amount);
    }
    

    public Integer posToIndex(Vec pos) {
        pos = pos.get().div(SettingsProperties.instance.scaling.getValue().doubleValue());
        return (int) (Math.floor(pos.x) + Math.floor(pos.y) * width);
    }


    public List<Double> blur(double n) {
        List<Double> newBuffer = new ArrayList<>();
        Integer index = 0;

        for (Double bufferdouble : buffer) {
            Double sumNeighbors = 0d;
            Integer x = index % this.width, y = (index - x) / this.height;
            for (int x1 = Math.max(0, x - 1); x1 < Math.min(width - 1, x + 1); x1++) {
                for (int y1 = Math.max(0, y - 1); y1 < Math.min(height - 1, x + 1); y1++) {
                    sumNeighbors += buffer.get(x1 + y1 * width) * n;
                }
            }
            sumNeighbors += bufferdouble * (1 - n);
            index++;

            newBuffer.add(index, ((9 * n + (1 - n))) != 0 ? sumNeighbors / (9 * n + (1 - n)) : 0);
        }
        return this.buffer = newBuffer;
    }

    public double getBuffer(int i) {
        return buffer.get(i);
    }

    public abstract void update();
}