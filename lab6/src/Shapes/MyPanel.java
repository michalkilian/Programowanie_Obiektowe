package Shapes;

import Shapes.Figures.Shape;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class MyPanel extends Panel {
    List<Shape> shapes = new LinkedList<>();

    public MyPanel() {
        MyMouseAdapter myMouseAdapter = new MyMouseAdapter(this);
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }
}
