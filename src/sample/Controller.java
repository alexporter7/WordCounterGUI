package sample;

//Author Name: Alex Porter
//Date: 10/11/2020
//Program Name: Controller
//Purpose: Gives functionality to the GUI provided in the sample.fxml

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Controller {

    public XYChart.Series dataSeries = new XYChart.Series();
    public CategoryAxis xAxis = new CategoryAxis();
    public NumberAxis yAxis = new NumberAxis();

    @FXML
    public Button calculateButton;

    @FXML
    public BarChart dataChart;

    public Controller() {

    }

    public void initialize() {

        xAxis.setLabel("Words");
        yAxis.setLabel("Frequency");
        dataSeries.setName("Word Frequency");

    }

    @FXML
    private void calculateButtonClick() throws IOException {

        //Start the WordCounter && get the return after analyzing
        WordCount.start();
        HashMap<String, Integer> wordCount = WordCount.returnText();

        //Create an Iterator for the hashmap
        Iterator mapIterator = wordCount.entrySet().iterator();

        //create a counter to get the top 10 words
        int counter = 0;
        while (mapIterator.hasNext() && counter < 10) {
            Map.Entry mapElement = (Map.Entry)mapIterator.next();
            dataSeries.getData().add(new XYChart.Data<String, Number>(
                    String.valueOf(mapElement.getKey()),
                    (Integer)mapElement.getValue()
            ));
            counter++;
        }

        //Add the data to the graph
        dataChart.getData().addAll(dataSeries);

    }

}
