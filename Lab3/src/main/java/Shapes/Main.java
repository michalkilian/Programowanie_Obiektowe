package Shapes;

public class Main {
    public static void main(String[] args) {
        Square square = new Square(11, "square");
        square.draw();

        Rectangle rec = new Rectangle(13, 6, "rec");
        rec.draw();

        Triangle triangle = new Triangle(11, "tria");
        triangle.draw();

        Circle circle = new Circle(7, "cir");
        circle.draw();
    }
}
