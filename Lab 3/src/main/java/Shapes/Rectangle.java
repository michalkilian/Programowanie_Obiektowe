package Shapes;

public class Rectangle extends Shape {
    protected int sideALength;
    protected int sideBLength;

    public Rectangle() {
        this.sideALength = 1;
        this.sideBLength = 1;
        this.name = "";
    }

    public Rectangle(int sideALength, int sideBLength, String name) {
        this.sideALength = sideALength;
        this.sideBLength = sideBLength;
        this.name = name;
    }

    @Override
    public void draw() {
        String character = "*";
        for (int i = 1; i < sideALength; ++i) {
            character += " *";
        }
        System.out.println(character);
        String space = "   ";
        for (int i = 3; i < sideALength; ++i) {
            space += "  ";
        }
        for (int i = sideBLength - 2; i > 0; --i) {
            System.out.println('*' + space + '*');
        }
        System.out.println(character);
    }
}
