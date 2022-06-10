#include <ESP8266HTTPClient.h>

#include <ESP8266WiFi.h>                                               
#include <FirebaseArduino.h>                                     
 WiFiClient client;
 WiFiServer server(80);
#define FIREBASE_HOST "smart-door-lock-1f825-default-rtdb.firebaseio.com"              // the project name address from firebase id
#define FIREBASE_AUTH "smvPaBS1PaRqFpORKD2XaqaPpDoOHZQUaaZ8r0WR"       // the secret key generated from firebase
#define WIFI_SSID "dlink1"                                          
#define WIFI_PASSWORD ""                                  
 
String fireStatus = "";                                                     // led status received from firebase
int led = 14;  
                                                              
void setup() 
{
  Serial.begin(9600);
  delay(1000);    
  pinMode(led, OUTPUT);       
  digitalWrite(led,LOW);          
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);                               
  Serial.print("Connecting to ");
  Serial.print(WIFI_SSID);
  while (WiFi.status() != WL_CONNECTED) 
  {
    Serial.print(".");
    delay(500);
  }
  server.begin();
  Serial.println();
  Serial.print("Connected to ");
  Serial.println(WIFI_SSID);
  Firebase.begin(FIREBASE_HOST,FIREBASE_AUTH);                  // connect to firebase
 // Firebase.setString("LED_STATUS", "OFF");                       //send initial string of led status
}
 
void loop() 
{
  fireStatus = Firebase.getString("LED_STATUS");                                      // get ld status input from firebase
  if (fireStatus == "ON") 
  {                                                          // compare the input of led status received from firebase
    Serial.println("Led Turned ON");                                                        
    digitalWrite(led, HIGH);                                                         // make external led ON
  } 
  else if (fireStatus == "OFF") 
  {                                                  // compare the input of led status received from firebase
    Serial.println("Led Turned OFF");
    digitalWrite(led, LOW);                                                         // make external led OFF
  }
  else 
  {
    Serial.println("Command Error! Please send ON/OFF");
    digitalWrite(led,LOW);
  }}
