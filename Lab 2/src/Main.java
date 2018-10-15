public class Main{
    public static void main(String[] args){
        Punkt2D a = new Punkt2D(1,1, "A");
        Punkt2D b = new Punkt2D(2,2, "B");
        System.out.println(b.distance(a));

        Punkt3D c = new Punkt3D(1, 1, 1, "C");
        Punkt3D d = new Punkt3D(2, 2, 2, "D");
        System.out.println(d.distance(c));
    }
}