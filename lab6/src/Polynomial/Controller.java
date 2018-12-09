package Polynomial;

import PeopleManager.Person;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    @FXML
    private Button drawPlot;

    @FXML
    private TextField coef;

    @FXML
    private TextField a;

    @FXML
    private TextField b;

    @FXML
    private TextField freq;

    @FXML
    private Pane window;

    @FXML
    private LineChart chart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    public void drawPlot() {
        chart.getData().clear();
        String[] coefs = coef.getText().split(",");
        double[] coefsDouble = Arrays.stream(coefs).mapToDouble(Double::parseDouble).toArray();
        ArrayList<Double> arrayList = new ArrayList<>();
        for(double x: coefsDouble){
            arrayList.add(x);
        }
        Polynomial poly = new Polynomial(arrayList,Double.parseDouble(a.getText()), Double.parseDouble(b.getText()),Double.parseDouble(freq.getText()));

        //defining a series
        XYChart.Series series = new XYChart.Series();
        String name = "";
        int degree = coefs.length -1;
        for(String c: coefs){
            if (degree>0){
                name += c + "*x^" + degree + '+';
            }
            else {
                name += c;
            }
            degree -= 1;
        }
        series.setName(name);
        //populating the series with data
        for(int index = 0; index < poly.values.size(); ++index){
            series.getData().add(new XYChart.Data(poly.arguments.get(index), poly.values.get(index)));
        }

        chart.getData().add(series);

        coef.setText("");
        a.setText("");
        b.setText("");
        freq.setText("");

    }

}

