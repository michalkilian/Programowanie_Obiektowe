package Shapes;

public class Circle extends Shape {
    private int radius;

    public Circle(int radius, String name) {
        this.radius = radius;
        this.name = name;
    }

    @Override
    public void draw() {
        if (radius == 1) {
            System.out.println(" x ");
            System.out.println("x x");
            System.out.println(" x ");
        } else {

            for (int j = 0; j < radius; ++j) {
                for (int i = 0; i < radius - j - 1; ++i) {
                    System.out.print("  ");
                }
                System.out.print('x');
                for (int i = 0; i < 2 * j; ++i) {
                    System.out.print("  ");
                }
                System.out.println('x');

            }


            for (int j = radius - 1; j >= 0; --j) {
                for (int i = 0; i < radius - j - 1; ++i) {
                    System.out.print("  ");
                }
                System.out.print('x');
                for (int i = 0; i < 2 * j; ++i) {
                    System.out.print("  ");
                }
                System.out.println('x');

            }
        }

    }

}
