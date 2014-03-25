/*
Created By: Samleong
Created On: 2011-9-17
Website / More Infomation: http://www.b2cqshop.com
Email: b2cqshop@gmail.com
*/

/* Edited/Coded By: Ricahrd Dobie
              Sohil Shaikh
              Cleveland Rouge
              Joseph Webb
*/
#define IR_IN  8  //Infrared receiver Pin

int Pulse_Width=0;//Storage width 
int adr_code=0x00;// User-coded values
char comL_code=0x00;//Command code
char comH_code=0x00;//Anti-code command


//new function coded by team
int convertHexToKeyPressed(char a){
  int result=0;
  
  if(a==0x16 ){
  result=0;
  //The follwoing hex values will be changed to  either 1, 2, 3 or 4 as they will be our voting options 
  }else if (a == 0xC){
  result=1;
  }else if (a == 0x18){
  result=2;
  }else if (a == 0x5E){
  result=3;
  }else if (a == 0x8){
  result=4;
  }else if (a == 0x1C){
  result=5;
  }else if (a == 0x5A){
  result=6;
  }else if (a == 0x42){
  result=7;
  }else if (a == 0x52){
  result=8;
  }else if (a == 0x4A){
  result=9;
  }
  
  return result;
}

//The following code was taking from the user help guide to one members Arduino kit
//This is the code for our remote sensor
void timer1_init(void)//Timer initialization function
{
  TCCR1A = 0X00; 
  TCCR1B = 0X05;//To the timer clock source
  TCCR1C = 0X00;
  TCNT1 = 0X00;
  TIMSK1 = 0X00;	//Disable timer overflow interrupt
}
void remote_deal(void)//The results of the implementation of decoding function
{ 
      //Show Data
      if(adr_code!=0XFF)
      {
        //Serial.print("aaaaa the Address Code is : "); 
        //Serial.println(adr_code, HEX);//Hexadecimal display
        //Serial.println(adr_code);
        //Serial.print("the Command code is : ");
        //Serial.println(comL_code, HEX);//Hexadecimal display
        //Serial.println(comL_code);
        
        //Serial.print("the key value is : ");
        Serial.println(convertHexToKeyPressed(comL_code));
      }
}
char logic_value()//Determine the logic value "0" and "1" Functions
{
  TCNT1 = 0X00;
  while(!(digitalRead(IR_IN))); //Low wait
  Pulse_Width=TCNT1;
  TCNT1=0;
  if(Pulse_Width>=7&&Pulse_Width<=10)//Low 560us
  {
    while(digitalRead(IR_IN));//Is waiting for another job
    Pulse_Width=TCNT1;
    TCNT1=0;
    if(Pulse_Width>=7&&Pulse_Width<=10)//Then high 560us
      return 0;
    else if(Pulse_Width>=25&&Pulse_Width<=27) //Then high 1.7ms
      return 1;
  }
  return -1;
}
void pulse_deal()//Receiver address code and command code pulse function
{
  int i;
  int j;
  adr_code=0x00;// Clear
  comL_code=0x00;// Clear
  comH_code=0x00;// Clear

  //Parsing remote code value in the user code  
  for(i = 0 ; i < 16; i++)
  {
    if(logic_value() == 1) //if 1
        adr_code |= (1<<i);//Save value
  }
  //Parsing code in the remote control command codes
  for(i = 0 ; i < 8; i++)
  {
    if(logic_value() == 1) //if 1
      comL_code |= (1<<i);//Save value
  }
  //Parsing code in the remote control command codes counter code
  for(j = 0 ; j < 8; j++)
  {
    if(logic_value() == 1) //if 1
        comH_code |= (1<<j);//Save value
  }
}
void remote_decode(void)//Decoding function
{
  TCNT1=0X00;       
  while(digitalRead(IR_IN))//if high then waiting
  {
    if(TCNT1>=1563)  //When the high lasts longer than 100ms, that no button is pressed at this time
    {
      adr_code=0x00ff;// User-coded values
      comL_code=0x00;//Key code value of the previous byte
      comH_code=0x00;//After a byte key code value
      return;
    }  
  }

  //If the high does not last more than 100ms
  TCNT1=0X00;

  while(!(digitalRead(IR_IN))); //Low wait
  Pulse_Width=TCNT1;
  TCNT1=0;
  if(Pulse_Width>=140&&Pulse_Width<=142)//9ms
  {

    while(digitalRead(IR_IN));//high wait
    Pulse_Width=TCNT1;
    TCNT1=0;
    if(Pulse_Width>=68&&Pulse_Width<=72)//4.5ms
    {  
      pulse_deal();
      return;
    }
    else if(Pulse_Width>=34&&Pulse_Width<=36)//2.25ms
    {
      while(!(digitalRead(IR_IN)));//low wait
      Pulse_Width=TCNT1;
      TCNT1=0;
      if(Pulse_Width>=7&&Pulse_Width<=10)//560us
      {
        return; 
      }
    }
  }
}
void setup()
{
  unsigned char i;
  pinMode(IR_IN,INPUT);//Set the infrared receiver input pin
  // start serial port at 9600 bps:
  Serial.begin(9600);
  
  //pinMode(8, INPUT);
}
void loop()
{  
  
  timer1_init();//Timer initialization
  while(1)
  {
    
    remote_decode();  //Decoding
    remote_deal();   //Perform decoding results
    //delay added to stop consecutive print of values
    delay(80);
  }  
  

}




