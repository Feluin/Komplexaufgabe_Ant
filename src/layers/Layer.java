package layers;

import sample.SettingsProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static sample.SettingsProperties.instance;

public abstract class Layer {
    Integer width;
    Integer height;
    List<List<Double>> buffer = new ArrayList<>();

    //sim ist die Größe von der UI
    public Layer() {

        this.width = instance.getCanvasWidthInt() / instance.scaling.getValue().intValue();
        this.height = instance.getCanvasHeightInt() / instance.scaling.getValue().intValue();
        initCells();
        instance.scaling.addListener((observableValue, number, t1) -> {
            this.width = instance.getCanvasWidthInt() / instance.scaling.getValue().intValue();
            this.height = instance.getCanvasHeightInt() / instance.scaling.getValue().intValue();
            initCells();
        });
        instance.canvasHeight.addListener((observableValue, number, t1) -> {
            this.width = instance.getCanvasWidthInt() / instance.scaling.getValue().intValue();
            this.height = instance.getCanvasHeightInt() / instance.scaling.getValue().intValue();
            initCells();
        });
        instance.canvasWidth.addListener((observableValue, number, t1) -> {
            this.width = instance.getCanvasWidthInt() / instance.scaling.getValue().intValue();
            this.height = instance.getCanvasHeightInt() / instance.scaling.getValue().intValue();

            initCells();
        });

    }

    private void initCells() {
        for (int i = buffer.size() / this.width; i <= this.width; i++) {
            buffer.add(new ArrayList<>());
        }
        for (int i = 0; i <= this.width; i++) {

            for (int j = buffer.get(i).size() / this.width; j <= this.height; j++) {
                buffer.get(i).add(initCell(i,j));
            }
        }

    }

    public Double initCell(double x, double y) {
        return 0.0;
    }

    public List<List<Double>> mul(Double n) {
        List<List<Double>> buffer = new ArrayList<>(this.buffer);
        buffer.forEach(doubles -> doubles = doubles.stream().map(aDouble -> aDouble * n).collect(Collectors.toList()));
        return buffer;
    }

    public List<List<Double>> add(Double n) {
        List<List<Double>> buffer = new ArrayList<>(this.buffer);
        buffer.forEach(doubles -> doubles = doubles.stream().map(aDouble -> aDouble + n).collect(Collectors.toList()));
        return buffer;
    }

    public void mark(Vec pos, double amount) {
        int index = posToIndex(pos);
        buffer.get(index / height).set(index % height, getBuffer(index) + amount);
    }

    public Double sample(Vec pos) {
        int index = posToIndex(pos);
        return getBuffer(index) != null ? getBuffer(index) : 0d;
    }

    public Double take(Vec pos, double amount) {
        int index = posToIndex(pos);
        Double takeamount = Math.min(getBuffer(index) != null ? getBuffer(index) : 0d, amount);
        buffer.get(index / height).set(index % height, getBuffer(index) - takeamount);
        return takeamount;
    }


    public Integer posToIndex(Vec pos) {
        pos = pos.get().div(SettingsProperties.instance.scaling.getValue().doubleValue());
        return (int) (Math.floor(pos.x) + Math.floor(pos.y) * width);
    }


    public List<List<Double>> blur(double n) {
        List<List<Double>> newBuffer = new ArrayList<>();
        Integer index = 0;
        for (int x = 0; x < buffer.size(); x++) {

            List<Double> doubles = buffer.get(x);
            List<Double> newdoubles = new ArrayList<>();
            for (int y = 0; y < doubles.size(); y++) {
                Double sumNeighbors = 0d;
                Double aDouble = doubles.get(y);
                for (int x1 = Math.max(0, x - 1); x1 < Math.min(width - 1, x + 1); x1++) {
                    for (int y1 = Math.max(0, y - 1); y1 < Math.min(height - 1, x + 1); y1++) {
                        sumNeighbors += aDouble * n;
                    }
                }
                sumNeighbors += aDouble * (1 - n);
                newdoubles.add(((9 * n + (1 - n))) != 0 ? sumNeighbors / (9 * n + (1 - n)) : 0);
            }
            newBuffer.add(newdoubles);

        }
        return this.buffer = newBuffer;
    }

    public Double getBuffer(int i) {
        int x = i / width;
        int y = i % width;
        return buffer.size()>x&& buffer.get(x).size()>y?buffer.get(x).get(y):0D;
    }

    public abstract void update();
}