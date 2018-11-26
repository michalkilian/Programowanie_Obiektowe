package Histogram;

import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;

public class Histogram extends Application {

    final static String ndst = "Niedostateczny";
    final static String dst = "Dostateczny";
    final static String db= "Dobry";
    final static String bdb = "Bardzo dobry";
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final StackedBarChart<String, Number> sbc =
            new StackedBarChart<String, Number>(xAxis, yAxis);
    final XYChart.Series<String, Number> series1 =
            new XYChart.Series<String, Number>();
    int npzm = 0;
    int npzm2 = 18;

    @Override
    public void start(Stage stage) {

        stage.setTitle("Histogram");
        sbc.setTitle("Oceny na studiach");
        xAxis.setLabel("Ocena");
        xAxis.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList(ndst, dst, db, bdb)));
        yAxis.setLabel("Amount");
        series1.setName("2017");

        series1.getData().add(new XYChart.Data<String, Number>(ndst, npzm));
        series1.getData().add(new XYChart.Data<String, Number>(dst, npzm));
        series1.getData().add(new XYChart.Data<String, Number>(db, npzm + 1));
        series1.getData().add(new XYChart.Data<String, Number>(bdb, npzm2));

        StackPane spChart = new StackPane();
        sbc.setAnimated(false);
        spChart.getChildren().add(sbc);

        Button button = new Button("Dodaj 5.0");
        button.setOnMouseClicked((event -> {
            series1.getData().add(new XYChart.Data<String, Number>(bdb, 1));
            sbc.getData().add(series1);

        }));
        StackPane spButton = new StackPane();
        spButton.getChildren().add(button);


        VBox vbox = new VBox();
        VBox.setVgrow(spChart, Priority.ALWAYS);
        vbox.getChildren().addAll(spChart, spButton);

        Scene scene = new Scene(vbox, 800, 600);
        sbc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}