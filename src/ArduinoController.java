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
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;

import com.googlecode.charts4j.*;

import jxl.*; 
import jxl.write.*; 
import jxl.write.Number;


public class ArduinoController {
	private static final String D9EDF7 = null;

	private static final String C8383 = null;

	static BufferedReader input;

	static OutputStream output;
	
	static int numofOnes=0;
	static int numofTwos=0;
	static int numofThrees=0;
	static int numofFours=0;
	
	
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
				
			 //The following script prints the number 1 and counts the number of times it has been pressed );
			
			System.out.print("1: ");
			for(int i=0;i<numofOnes;i++){
				System.out.print("*");
			}

			System.out.println();
			 //The following script prints the number 2 and counts the number of times it has been pressed );
			System.out.print("2: ");
			for(int i=0;i<numofTwos;i++){
				System.out.print("*");
			}

			System.out.println();

			 //The following script prints the number 3 and counts the number of times it has been pressed );
			System.out.print("3: ");
			for(int i=0;i<numofThrees;i++){
				System.out.print("*");
			}

			System.out.println();

			 //The following script prints the number 4 and counts the number of times it has been pressed );
			System.out.print("4: ");
			for(int i=0;i<numofFours;i++){
				System.out.print("*");
			}
			
			
			//generateBarChart(numofOnes, numofTwos, numofThrees, numofFours );
			int total=numofOnes+ numofTwos + numofThrees + numofFours;
			//generateBarChart(numofOnes*100/total, numofTwos*100/total, numofThrees*100/total, numofFours*100/total );
			
			//generateBarChart(4,4 ,3 , 3 );
			//generateBarChart(4,4 ,3 , 3 );
			//generateBarChart(60,10 ,30 , 0 );
			//generateExcelReport(60,10 ,30 , 0);
			
			
			
			
		}
		
		public static int getMax(int numofOnes, int numofTwos, int numofThrees, int numofFours){
			
			int max=0;
			
			if(max < numofOnes){
				max=numofOnes;
			}
			
			
			return max;
		}
		public static void generateExcelReport(int numofOnes, int numofTwos, int numofThrees, int numofFours) throws Exception{
			
			WritableWorkbook workbook = Workbook.createWorkbook(new File("test1.xls"));
			WritableSheet sheet = workbook.createSheet("First Sheet", 0);
			Label lblEmpnickname=new Label(0,1,"Voting Options");
			Label lblEmpnickname1=new Label(1,1,"Voting results");
			    Label lblEmpName=new Label(0,2,"1");
			    Label lblEmpID=new Label(0,3,"2");
			    Label lblSex=new Label(0,4,"3");
			    Label lblDOB=new Label(0,5,"4");
			    
			    Label lblEmpName1=new Label(1,2,numofOnes+"");
			    Label lblEmpID1=new Label(1,3,numofTwos+"");
			    Label lblSex1=new Label(1,4,numofThrees+"");
			    Label lblDOB1=new Label(1,5,numofFours+"");
			    
			    
			    
			    sheet.addCell(lblEmpnickname);
			    sheet.addCell(lblEmpnickname1);
			    sheet.addCell(lblEmpName);
			    sheet.addCell(lblEmpID);
			    sheet.addCell(lblSex);
			    sheet.addCell(lblDOB);
			    
			    
			    sheet.addCell(lblEmpName1);
			    sheet.addCell(lblEmpID1);
			    sheet.addCell(lblSex1);
			    sheet.addCell(lblDOB1);
			    
			    
			//Label label = new Label(0, 2, "1"); 
			//Label label = new Label(0, 3, "2"); 
			//Label label= new Label(0, 4, "3"); 
			//Label label= new Label(0, 5, "4"); 
			//sheet.addCell(label); 
	       // Number number = new Number(1, 2, 6); 
			//Number number = new Number(1, 3, 3);
			//Number number = new Number(1, 4, 4);
			//Number number = new Number(1, 5, 3); 
			//sheet.addCell(number);
			// All sheets and cells added. Now write out the workbook 
			workbook.write(); 
			workbook.close();
			
		}
		
	
		 //code by Rich
		   public static void generateBarChart(int numofOnes, int numofTwos, int numofThrees, int numofFours ) {
			   int max=getMax(numofOnes,numofTwos, numofThrees, numofFours);
			   
			   numofOnes=numofOnes*100/max;
			   numofTwos=numofTwos*100/max;
			   numofThrees=numofThrees*100/max;
			   numofFours=numofFours*100/max;
			   
			   
		       // EXAMPLE CODE START
		       // Defining data plots.
		       //BarChartPlot team1 = Plots.newBarChartPlot(Data.newData(25, 43, 12, 30), BLUEVIOLET, "Team A");
			   BarChartPlot team1 = Plots.newBarChartPlot(Data.newData(numofOnes,numofTwos, numofThrees, numofFours), BLUEVIOLET, "Team A");
			   
			   //BarChartPlot team1 = Plots.newBarChartPlot(Data.newData(4, 5, 8, 3), BLUEVIOLET, "Team A");
		       
		       // Instantiating chart.
		       final BarChart chart = GCharts.newBarChart(team1);

		       // Defining axis info and styles
		       AxisStyle axisStyle = AxisStyle.newAxisStyle(BLACK, 13, AxisTextAlignment.CENTER);
		       AxisLabels score = AxisLabelsFactory.newAxisLabels("Score", 50.0);
		       score.setAxisStyle(axisStyle);
		       AxisLabels year = AxisLabelsFactory.newAxisLabels("Answer", 50.0);
		       year.setAxisStyle(axisStyle);

		       // Adding axis info to chart.
		       chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("A", "B", "C", "D"));
		       //chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, 20));
		       chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, max));
		       chart.addYAxisLabels(score);
		       chart.addXAxisLabels(year);
		       	
		       chart.setSize(600, 450);
		       chart.setBarWidth(100);
		       chart.setSpaceWithinGroupsOfBars(20);
		       
		       //chart.setDataStacked(true);
		       chart.setTitle("Result in Percentages", BLACK, 16);
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
		       
		       class InternetBrowser {

					public void main(String[] args) throws IOException {
						String url = "chart.toURLString()";
						//String url="http://www.google.com";
						java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));

					}

				}
		   }
		   
		
		

		private static var newArray(int i, int j, int k, int l, int m, int n,
				int o, int p) {
			// TODO Auto-generated method stub
			return null;
		}

		public static void main(String[]args) throws Exception {
			//process("22222211111133433434324342342434323223211134441");
			
			readFromArduinoAndCreateBarChart();
			generateBarChart( numofOnes,  numofTwos,  numofThrees,  numofFours);
			//readFromArduino();
			generateExcelReport(numofOnes,  numofTwos,  numofThrees,  numofFours);
			
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

	

