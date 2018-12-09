package Shapes;

import Shapes.Figures.Circle;
import Shapes.Figures.Square;
import Shapes.Figures.Triangle;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] a) {
        JFrame f = new JFrame();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        MyPanel panel = new MyPanel();
        panel.shapes.add(new Circle("1", 100, 100, 30));
        panel.shapes.add(new Triangle("2", 300, 400, 50));
        panel.shapes.add(new Square("3", 0, 50, 100));
        f.setContentPane(panel);
        f.setSize(800, 600);
        f.setVisible(true);
    }
}
