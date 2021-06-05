import java.awt.Color;

public class Wall {
    private double cx;
    private double cy;
    private double width;
    private double height;
    private Color color;
    private String id;

    public Wall(double var1, double var3, double var5, double var7, Color var9, String var10) {
        this.cx = var1;
        this.cy = var3;
        this.width = var5;
        this.height = var7;
        this.color = var9;
        this.id = var10;
    }

    public void draw() {
        GameLib.setColor(this.color);
        GameLib.fillRect(this.cx, this.cy, this.width, this.height);
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public double getCx() {
        return this.cx;
    }

    public double getCy() {
        return this.cy;
    }

    public String getId() {
        return this.id;
    }
}
