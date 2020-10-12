package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * 
 * Class for generating charts for the word distribution of the hash table
 * 
 * JavaFx must be installed and properly configured inside eclipse in order to work this.
 *
 */

public class Chart extends Application {
	private ArrayList<Integer> data1 = Main.bucket_status1;
	private ArrayList<Integer> data2 = Main.bucket_status2;
	
	
	@Override	
	public void start(Stage window) {
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		
		xAxis.setLabel("Bucket Number");
		yAxis.setLabel("Capacity");
		
		LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
		lineChart.setTitle("Content distribution over buckets");
		
		XYChart.Series bucketData1 = new XYChart.Series();
		bucketData1.setName("1st hash function (division method)");
		
		XYChart.Series bucketData2 = new XYChart.Series();
		bucketData2.setName("2nd hash function (multiplication method)");
		
		// adding data of the 1st hash function
		for(int i=0; i<this.data1.size(); i++) {
			bucketData1.getData().add(new XYChart.Data(i, this.data1.get(i)));			
		}
		
		// adding data of the 2nd hash function
		for(int i=0; i<this.data2.size(); i++) {
			bucketData2.getData().add(new XYChart.Data(i, this.data2.get(i)));			
		}
		
		lineChart.getData().addAll(bucketData1, bucketData2);
		bucketData1.getNode().setStyle("-fx-stroke-width: 2px;");
		bucketData2.getNode().setStyle("-fx-stroke-width: 2px;");
		lineChart.setCreateSymbols(false);
		
		Scene scene = new Scene(lineChart, 700, 500);
		
		window.setScene(scene);
		window.show();		
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
