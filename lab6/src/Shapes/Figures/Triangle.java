package Shapes.Figures;

import java.awt.*;
import java.awt.geom.Path2D;

public class Triangle extends Shape {

    private int base;

    public Triangle(String name, int x, int y, int base) {
        super(name, x, y);
        this.base = base;
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.YELLOW);
        path = new Path2D.Double();

        path.moveTo(this.x, this.y);
        path.lineTo(x + (this.base / 2), y + this.base * Math.sqrt(3) / 2);
        path.lineTo(x + base, this.y);
        path.closePath();
        graphics2D.fill(path);
    }

}
