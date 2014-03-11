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
	static BufferedReader input;

	static OutputStream output;
	
	//This function will read value from serial port and send email if the value is more than 400
		public static void readFromArduinoAndCreateBarChart() throws Exception {
			// for linux
			// CommPortIdentifier portId =
			// CommPortIdentifier.getPortIdentifier("/dev/ttyACM3");

			// for windows
			CommPortIdentifier portId = CommPortIdentifier
					.getPortIdentifier("COM4");
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
						
						System.out.println(value);
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
				
				port.close();
			}

		}
		
		public static void process(String responseValues) {
			//int newArray[]= new int [30];
			
			char[] newArray = responseValues.toCharArray();
			
			System.out.println(newArray[0]);
			System.out.println(newArray[1]);
			System.out.println(newArray[2]);
			System.out.println(newArray[3]);
			System.out.println(newArray[4]);
			System.out.println(newArray[5]);
			System.out.println(newArray[6]);
			System.out.println(newArray[7]);
			
			
		}
		
		public static void main(String[]args) throws Exception {
			//process("23223211134445");
			
			readFromArduinoAndCreateBarChart();
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

	

