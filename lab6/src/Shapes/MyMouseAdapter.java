package Shapes;

import Shapes.Figures.Shape;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;


public class MyMouseAdapter extends MouseAdapter {
    private Point pPressed = null;
    private MyPanel pane_to_paint;
    public Shape toEdit = null;


    public MyMouseAdapter(MyPanel panel_to_repaint) {
        pane_to_paint = panel_to_repaint;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        toEdit = getShape(e.getPoint());
        if (e.getButton() != MouseEvent.BUTTON1) {
            return;
        }
        if (toEdit != null) {
            pPressed = e.getPoint();
        }
    }

    public Shape getShape(Point2D point2D) {
        for (int i = 0; i < pane_to_paint.shapes.size(); i++) {
            if (pane_to_paint.shapes.get(i).path.contains(point2D)) {
                return pane_to_paint.shapes.get(i);
            }
        }
        return null;
    }

    public void mouseDragged(MouseEvent e) {
        drag(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drag(e);
        pPressed = null;
        toEdit = null;
    }

    private void drag(MouseEvent e) {
        if (pPressed == null) {
            return;
        }
        Point p = e.getPoint();
        toEdit.x = p.x;
        toEdit.y = p.y;
        pPressed = p;
        pane_to_paint.repaint();
    }

    ;

}