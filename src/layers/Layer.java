package layers;

import static sample.SettingsProperties.*;

import sample.SettingsProperties;

public abstract class Layer
{
    Integer width;
    Integer height;
    Double[][] buffer = new Double[0][0];

    //sim ist die Größe von der UI
    public Layer()
    {

        this.width = instance.canvasWidth / instance.scaling;
        this.height = instance.canvasHeight / instance.scaling;
        //updates
        initCells();
    }

    private void initCells()
    {
        Double[][] newbuffer = new Double[height][width];
        for (int y = 0; y < newbuffer.length; y++)
        {
            Double[] row = newbuffer[y];
            for (int x = 0; x < row.length; x++)
            {
                if (y < buffer.length && x < buffer[y].length)
                {
                    newbuffer[y][x] = buffer[y][x];
                } else
                {
                    newbuffer[y][x] = initCell(x, y);
                }
            }

        }
        buffer = newbuffer;
    }

    public Double initCell(double x,
        double y)
    {
        return 0.0;
    }

    public Double[][] mul(Double n)
    {

        for (int y = 0; y < buffer.length; y++)
        {
            Double[] row = buffer[y];
            for (int x = 0; x < row.length; x++)
            {
                buffer[y][x] = buffer[y][x] * n;
            }

        }
        return buffer;
    }

    public Double[][] add(Double n)
    {
        for (int y = 0; y < buffer.length; y++)
        {
            Double[] row = buffer[y];
            for (int x = 0; x < row.length; x++)
            {
                buffer[y][x] = buffer[y][x] + n;
            }
        }
        return buffer;
    }

    public void mark(Vec pos,
        double amount)
    {
        Coords coords = convertVecToCoords(pos);
        if (amount > 0)
        {
            buffer[coords.y][coords.x] = buffer[coords.y][coords.x] + amount;
        }

    }

    public Double sample(Vec pos)
    {
        Coords coords = convertVecToCoords(pos);
        return buffer[coords.y][coords.x] != null ? buffer[coords.y][coords.x] : 0d;
    }

    public Double take(Vec pos,
        double amount)
    {
        Coords coords = convertVecToCoords(pos);
        Double takeamount =
            Math.min(buffer[coords.y][coords.x] != null ? buffer[coords.y][coords.x] : 0d, amount);
        buffer[coords.y][coords.x] = buffer[coords.y][coords.x] - takeamount;
        return takeamount;
    }

    public Double[][] blur(double n)
    {
        Double[][] newblur = new Double[height][width];
        for (int y = 0; y < buffer.length; y++)
        {
            Double[] row = buffer[y];
            for (int x = 0; x < row.length; x++)
            {
                double sumNeighbors = 0d;
                Double current = row[x];
                for (int y1 = Math.max(0, y - 1); y1 <= Math.min(height - 1, y + 1); y1++)
                {
                    for (int x1 = Math.max(0, x - 1); x1 <= Math.min(width - 1, x + 1); x1++)
                    {
                        sumNeighbors += buffer[y1][x1] * n;
                    }
                }
                sumNeighbors += current * (1 - n);
                newblur[y][x] = (((9 * n + (1 - n))) != 0 ? sumNeighbors / (9 * n + (1 - n)) : 0);
            }

        }

        return this.buffer = newblur;
    }

    public void printlayer()
    {
        for (int y = 0; y < buffer.length; y++)
        {
            Double[] row = buffer[y];
            for (int x = 0; x < row.length; x++)
            {
                System.out.print(buffer[y][x] + " ");
            }
            System.out.println();

        }
    }

    public abstract void update();

    public Double getBuffer(int x,
        int y)
    {
        x = Math.min(SettingsProperties.instance.canvasWidth - 1, Math.max(x, 0)) / SettingsProperties.instance.scaling;
        y = Math.min(SettingsProperties.instance.canvasHeight - 1, Math.max(y, 0)) / SettingsProperties.instance.scaling;
        return buffer[y][x];
    }

    public Coords convertVecToCoords(Vec vec)
    {
        Integer x = vec.x.intValue();
        Integer y = vec.y.intValue();
        x = Math.min(SettingsProperties.instance.canvasWidth - 1, Math.max(x, 0)) / SettingsProperties.instance.scaling;
        y = Math.min(SettingsProperties.instance.canvasHeight - 1, Math.max(y, 0)) / SettingsProperties.instance.scaling;
        return new Coords(x, y);
    }
}