package Shapes.Figures;

import java.awt.*;
import java.awt.geom.Path2D;

public abstract class Shape {
    public String name;
    public int x;
    public int y;
    public Path2D path;

    public Shape(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Graphics graphics);
}