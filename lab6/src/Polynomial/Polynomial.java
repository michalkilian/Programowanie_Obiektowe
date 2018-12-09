package Polynomial;

import java.util.ArrayList;

public class Polynomial {
    public ArrayList<Double> coefficients;
    public ArrayList<Double> arguments = new ArrayList<>();
    public ArrayList<Double> values = new ArrayList<>();
    public Polynomial(ArrayList<Double> coefficients, double a, double b, double freq){
        this.coefficients = coefficients;
        for(double i = a; i <= b; i+=freq){
            this.arguments.add(i);
            this.values.add(calculateValue(i, coefficients));
        }
    }

    public double calculateValue(double argument, ArrayList<Double> coefficients){
        Double value = (double) 0;
        int pow = coefficients.size()-1;
        for(double coef : coefficients){
            value += (coef*Math.pow(argument, pow));
            pow -= 1;
        }

        return value;
    }
}
