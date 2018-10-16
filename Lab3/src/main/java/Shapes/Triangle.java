package Shapes;

public class Triangle extends Shape{

    private int base;

    public Triangle(int base, String name){
        this.base = base;
        this.name = name;
    }

    @Override
    public void draw(){
        for(int i = 1; i <= base; ++i){
            for(int j = (base - i); j > 0; --j){
                System.out.print(' ');
            }

            for(int j = i; j > 0; --j){
                System.out.print("x ");
            }
        System.out.println();
        }

    }
}
