/**
 * @version     1.0                 (current version number of program)
 * @since     25 Feb 2014
 * This program has been modified by xyz.
 */
// March 2014 - xyz - Modified the code to implement the  abc feature
//replace xyz with your name and abc with the name of feature

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;

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
							Barchart();
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
				
			 Barchart();
			var myData = newArray(2005, 2, 2006, 1, 2007, 3, 2008, 6);
			Object chartid;
			Object bar;
			var myChart = new JSChart();
			myChart.setDataArray(myData);
			Object aBdB;
			Object red = null;
			myChart.setBarColor(red);
			myChart.setBarOpacity(0.8);
			String black = null;
			myChart.setBarBorderColor(black);
			myChart.setBarValues(false);
			Object blue = null;
			myChart.setTitleColor(blue);
			myChart.setAxisColor(black);
			myChart.setAxisValuesColor(black);
			myChart.draw();
			
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
			
			
			
			
			
		}
		
		private static void Barchart() {
			// TODO Auto-generated method stub
			
		}

		private static var newArray(int i, int j, int k, int l, int m, int n,
				int o, int p) {
			// TODO Auto-generated method stub
			return null;
		}

		public static void main(String[]args) throws Exception {
			//process("23223211134445");
			
			readFromArduinoAndCreateBarChart();
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

	

