package Shapes.Figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

public class Circle extends Shape {
    private int radius;

    public Circle(String name, int x, int y, int radius) {
        super(name, x, y);
        this.radius = radius;
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.BLUE);
        path = new Path2D.Double();
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, this.radius, this.radius);
        path.append(circle, true);


        graphics2D.fill(path);
    }
}



