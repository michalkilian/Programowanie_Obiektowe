package Shapes.Figures;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape {
    protected int sideALength;
    protected int sideBLength;

    public Rectangle(String name, int x, int y, int sideALength, int sideBLength) {
        super(name, x, y);
        this.sideALength = sideALength;
        this.sideBLength = sideBLength;
    }


    @Override
    public void draw(Graphics graphic) {
        Graphics2D graphics2D = (Graphics2D) graphic;
        graphics2D.setColor(Color.RED);
        path = new Path2D.Double();
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, sideALength, sideBLength);
        path.append(rect, true);

        graphics2D.fill(path);
    }
}
