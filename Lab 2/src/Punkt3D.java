public class Punkt3D extends Punkt2D{
    private double z;

    public Punkt3D(double x_, double y_, double z_, String name){
        super(x_, y_, name);
        this.z = z_;
    }

    public double GetZ(){
        return this.z;
    }

    public void SetZ(double z_){
        this.z = z_;
    }

    public double distance(Punkt3D punkt2){
        return Math.sqrt((Math.pow(punkt2.GetX() - this.x, 2) + (Math.pow(punkt2.GetY() - this.y, 2))) + (Math.pow(punkt2.GetZ() - this.z, 2)));
    }
}