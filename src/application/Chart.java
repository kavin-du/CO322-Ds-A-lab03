package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Chart extends Application {
	private ArrayList<Integer> data = Main.bucket_status;
	
	
	@Override	
	public void start(Stage window) {
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		
		xAxis.setLabel("Bucket Number");
		yAxis.setLabel("Capacity");
		
		LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
		lineChart.setTitle("Content distribution over buckets");
		XYChart.Series bucketData = new XYChart.Series();
		//bucketData.setName("Recursive");
		
		
		for(int i=0; i<this.data.size(); i++) {
			bucketData.getData().add(new XYChart.Data(i, this.data.get(i)));
			
		}

		lineChart.getData().add(bucketData);
		
		Scene scene = new Scene(lineChart, 1000, 500);
		
		window.setScene(scene);
		window.show();		
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
