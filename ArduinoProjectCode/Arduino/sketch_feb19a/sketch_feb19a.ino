#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <twi.h>
/******************************************
 * 	PURPOSE:	Displays text sent over the serial port (e.g. from the Serial Monitor) on
 * an attached LCD.
 * 	Created by      Sam Leong
 * 	Website:	www.b2cqshop.com
 * 	DATE:		2012/9/20
 *******************************************/
/*
 *Compatible with the Arduino IDE 1.0
 *Library version:1.1
 */


//by Sohail

LiquidCrystal_I2C lcd(0x27,16,2);  // set the LCD address to 0x27 for a 16 chars and 2 line display

void setup()
{
  lcd.init();                      // initialize the lcd 
  lcd.begin(16,4);
  //lcd.backlight();
  //lcd.setCursor(0,0);
  //lcd.print("welcome");
 
  //lcd.noBacklight();

  Serial.begin(9600);
  //lcd.setCursor(0,0);
  //delay(100);
  //lcd.setCursor(0,0);
  //lcd.print("welcome");
  //delay(100);

}

void loop()
{
  Serial.print("1");
    Serial.print("1");
      Serial.print("2");
        Serial.print("1");
        
          Serial.print("3");
          
            Serial.print("3");
            
              Serial.print("4");
              
                Serial.print("8");
        
  lcd.clear();
  Serial.println("44");
  lcd.backlight();
  //delay(100);
  lcd.setCursor(2,2);
  //delay(100);
  lcd.printstr("a");
  
   delay(1000); 
  //lcd.noBacklight();
  delay(1000); 

}




