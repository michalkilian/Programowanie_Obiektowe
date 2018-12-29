package Shapes;

public class Main {
    public static void main(String[] args) {
        ListOfShapes listOfShapes = new ListOfShapes();

        listOfShapes.addShape(new Square(3, "mały kwadrat"));
        listOfShapes.addShape(new Rectangle(13, 6, "prostokącik"));
        listOfShapes.addShape(new Rectangle(16, 10, "prostokąt"));

        listOfShapes.addShape(new Triangle(11, "tria11"));
        listOfShapes.addShape(new Triangle(8, "tria8"));

        listOfShapes.addShape(new Circle(7, "cir"));
        listOfShapes.getList();
        listOfShapes.interactWithList();
    }
}
