/*
void setup() {
    Serial.begin(9600);               //initial the Serial
}

void loop()
{
    if(Serial.available())
    {
        Serial.write(Serial.read());    //send what has been received
    }
}
*/

/* Arduino Hall Effect Sensor Project by Arvind Sanjeev Please check out http://diyhacking.com for the tutorial of this project. DIY Hacking */ 
volatile byte half_revolutions; 
unsigned int rpm; 
unsigned long timeold; 
void setup(){
  Serial.begin(9600); 
  pinMode(13, OUTPUT);
  attachInterrupt(0, magnet_detect, RISING);//Initialize the intterrupt pin (Arduino digital pin 2) 
  half_revolutions = 0; 
  rpm = 0; 
  timeold = 0; 
} 

void loop()//Measure RPM 
{ 
   Serial.print(digitalRead(2));
if (half_revolutions >= 20) { 
  rpm = 30*1000/(millis() - timeold)*half_revolutions; 
  timeold = millis(); 
  half_revolutions = 0; //
  Serial.print(rpm); 
  digitalWrite(13, LOW);
} 
} 

void magnet_detect()//This function is called whenever a magnet/interrupt is detected by the arduino 
{ 
half_revolutions++; 
  digitalWrite(13, HIGH);
} 
