public class Punkt2D{
    protected double x;
    protected double y;
    private String name;

    public Punkt2D(double x_, double y_, String name){
        this.x = x_;
        this.y = y_;
        this.name = name;
    }

    public double GetX(){
        return this.x;
    }

    public double GetY(){
        return this.y;
    }
    public String GetName(){
        return this.name;
    }

    public void SetX(double x_){
        this.x = x_;
    }

    public void SetY(double y_){
        this.y = y_;
    }

    public void SetName(String name){
        this.name = name;
    }

    public double distance (Punkt2D punkt2){
        return Math.sqrt((Math.pow(punkt2.GetX() - this.x, 2) + (Math.pow(punkt2.GetY() - this.y, 2))));
    }


}