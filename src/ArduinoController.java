/**
 * @version     1.0                 (current version number of program)
 * @since     25 Feb 2014
 * This program has been modified by xyz.
 */
// March 2014 - xyz - Modified the code to implement the  abc feature
//replace xyz with your name and abc with the name of feature

import static com.googlecode.charts4j.Color.ALICEBLUE;
import static com.googlecode.charts4j.Color.BLACK;
import static com.googlecode.charts4j.Color.BLUEVIOLET;
import static com.googlecode.charts4j.Color.LAVENDER;
import static com.googlecode.charts4j.Color.WHITE;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;

import com.googlecode.charts4j.BarChartPlot;

import com.googlecode.charts4j.*;


public class ArduinoController {
	private static final String D9EDF7 = null;

	private static final String C8383 = null;

	static BufferedReader input;

	static OutputStream output;
	
	
	//This function will read value from serial port and send email if the value is more than 400
		public static void readFromArduinoAndCreateBarChart() throws Exception {
			System.out.println("1111");
			// for linux
			// CommPortIdentifier portId =
			// CommPortIdentifier.getPortIdentifier("/dev/ttyACM3");

			// for windows
			CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier("COM3");
			// CommPortIdentifier portId =
			// CommPortIdentifier.getPortIdentifier("COM4");

			SerialPort port = (SerialPort) portId.open("serial talk", 4000);
			// input = port.getInputStream();
			input = new BufferedReader(new InputStreamReader(port.getInputStream()));
			port.setSerialPortParams(9600, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			
			String responseValues="";
			
			
			while (true) {
				
				try {
					String inputLine = null;
					if (input.ready()) {
						
						inputLine = input.readLine();
						// System.out.println(inputLine);
						int value = Integer.parseInt(inputLine);
						responseValues=responseValues+value;
						
						System.out.println("[" + responseValues + "]");
						if (value == 8) {
							
				/*		 System.out.println("finish and create bar chart");
							//insertIntoDatabase(new Date().getTime(),responseValues);
							drawChart(responseValues); */
							process(responseValues);
							break;
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				
			}
			
			port.close();
			
		}
		
		public static void process(String responseValues) {
			//int newArray[]= new int [30];
			
			char[] newArray = responseValues.toCharArray();
			
			int numofOnes=0;
			int numofTwos=0;
			int numofThrees=0;
			int numofFours=0;


			//parse array
			/*
			if(==1){
				numofOnes=numofOnes+1;
			}		if(==1){
				numofTwos=numofTwos+1;
			}
			*/

			
			
			for(int i=0;i<newArray.length;i++){
				if(newArray[i]=='1'){
					numofOnes=numofOnes+1;	
				}
				
				if(newArray[i]=='2'){
					numofTwos=numofTwos+1;	
				}
				
				
				if(newArray[i]=='3'){
					numofThrees=numofThrees+1;	
				}
				
				
				if(newArray[i]=='4'){
					numofFours=numofFours+1;	
				}
				
				//System.out.println(newArray[i]);
				}
				
			 
			System.out.print("1: ");
			for(int i=0;i<numofOnes;i++){
				System.out.print("*");
			}

			System.out.println();

			System.out.print("2: ");
			for(int i=0;i<numofTwos;i++){
				System.out.print("*");
			}

			System.out.println();


			System.out.print("3: ");
			for(int i=0;i<numofThrees;i++){
				System.out.print("*");
			}

			System.out.println();


			System.out.print("4: ");
			for(int i=0;i<numofFours;i++){
				System.out.print("*");
			}
			
			
			generateBarChart(numofOnes, numofTwos, numofThrees, numofFours );
			
			
			
		}
		
		
		
		 //code by Rich
		   public static void generateBarChart(int numofOnes, int numofTwos, int numofThrees, int numofFours ) {
		       // EXAMPLE CODE START
		       // Defining data plots.
		       //BarChartPlot team1 = Plots.newBarChartPlot(Data.newData(25, 43, 12, 30), BLUEVIOLET, "Team A");
			   BarChartPlot team1 = Plots.newBarChartPlot(Data.newData(numofOnes,numofTwos, numofThrees, numofFours), BLUEVIOLET, "Team A");
			   
			   //BarChartPlot team1 = Plots.newBarChartPlot(Data.newData(4, 5, 8, 3), BLUEVIOLET, "Team A");
		       
		       // Instantiating chart.
		       BarChart chart = GCharts.newBarChart(team1);

		       // Defining axis info and styles
		       AxisStyle axisStyle = AxisStyle.newAxisStyle(BLACK, 13, AxisTextAlignment.CENTER);
		       AxisLabels score = AxisLabelsFactory.newAxisLabels("Score", 50.0);
		       score.setAxisStyle(axisStyle);
		       AxisLabels year = AxisLabelsFactory.newAxisLabels("Answer", 50.0);
		       year.setAxisStyle(axisStyle);

		       // Adding axis info to chart.
		       chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("A", "B", "C", "D"));
		       chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, 20));
		       chart.addYAxisLabels(score);
		       chart.addXAxisLabels(year);
		       	
		       chart.setSize(600, 450);
		       chart.setBarWidth(100);
		       chart.setSpaceWithinGroupsOfBars(20);
		       //chart.setDataStacked(true);
		       chart.setTitle("Team Scores", BLACK, 16);
		       chart.setGrid(20, 10, 3, 2);
		       
		       
		       chart.setBackgroundFill(Fills.newSolidFill(ALICEBLUE));
		       LinearGradientFill fill = Fills.newLinearGradientFill(0, LAVENDER, 2-0);
		       fill.addColorAndOffset(WHITE, 0);
		       chart.setAreaFill(fill);
		       
		       
		       String url = chart.toURLString();
		       // EXAMPLE CODE END. Use this url string in your web or
		       // Internet application.
		       //Logger.global.info(url);
		       System.out.println("Open the following URL in any browser: " + url);
		       
		       
		   }
		   
		
		

		private static var newArray(int i, int j, int k, int l, int m, int n,
				int o, int p) {
			// TODO Auto-generated method stub
			return null;
		}

		public static void main(String[]args) throws Exception {
			process("23223211134441");
			
			//readFromArduinoAndCreateBarChart();
			//readFromArduino();
		}

	public static void drawChart(String responses){
		
		int responseValues=0;
		
		//parse array
		
		if(responseValues==1){
			responseValues=responseValues+1;
		}		if(responseValues==2){
			responseValues=responseValues+1;
		}
		
		
		
		
		System.out.print("1: ");
		for(int i=0;i<responseValues;i++){
			System.out.print("*");
		}
		
		System.out.println();
		
		System.out.print("2: ");
		for(int i=0;i<responseValues;i++){
			System.out.print("*");
		}
		
		System.out.println();
		
		
		System.out.print("3: ");
		for(int i=0;i<responseValues;i++){
			System.out.print("*");
		}
		
		System.out.println();
		
		
		System.out.print("4: ");
		for(int i=0;i<responseValues;i++){
			System.out.print("*");
		}

			
		
	}
		
	

}

	

