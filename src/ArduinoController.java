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
					.getPortIdentifier("COM3");
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
							System.out.println("finish and create bar chart");
							//insertIntoDatabase(new Date().getTime(),responseValues);
							drawChart(responseValues);
							
							break;
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				port.close();
			}

		}
		
	//responses = 112221111323114	
	public static void drawChart(String responses){
		
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
		
		numofOnes=6;
		numofTwos=2;
		numofThrees=2;
		numofFours=1;
		
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
		
	public static void main(String[] args) throws Exception {
		
		//readFromArduinoAndCreateBarChart();
		drawChart("12121212");

}
	
}
