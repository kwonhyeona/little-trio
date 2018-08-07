#include <SoftwareSerial.h> // 시리얼 통신을 위한 라이브러리 선언
#include <SPI.h>
#include <MFRC522.h>

//const int led = 3;

#define RST_PIN   9                            // reset핀은 9번으로 설정
#define SS_PIN    10                           // SS핀은 10번으로 설정
                                               // SS핀은 데이터를 주고받는 역할의 핀( SS = Slave Selector )
                                               
MFRC522 mfrc(SS_PIN, RST_PIN);                 // MFR522를 이용하기 위해 mfrc객체를 생성해 줍니다.                       
SoftwareSerial bluetooth(2,3); // tx :2  rx :3  
String myString ="";
//char myChar = "";


void setup() 
{
  //pinMode(led,OUTPUT);
  
  Serial.begin(9600); // PC와 아두이노간 시리얼 통신속도를 9600bps로 설정
  bluetooth.begin(9600); // 블루투스와 아두이노간 시리얼 통신속도를 9600bps로 설정
  SPI.begin();                                // SPI 초기화
                                              // (SPI : 하나의 마스터와 다수의 SLAVE(종속적인 역활)간의 통신 방식)
  mfrc.PCD_Init();
  Serial.println("Scan ID...");
}
 
void loop()
{
  //byte data = bluetooth.read();
  if(bluetooth.available())
    Serial.write(bluetooth.read());
  if (Serial.available()) {          // 시리얼 모니터로부터 받은 데이터가 있으면
    bluetooth.write(Serial.read());  // 블루투스로 전송
  }
  //Look for new cards
  if (!mfrc.PICC_IsNewCardPresent()) {
    return;
  }

  //select one of the cards
  if (!mfrc.PICC_ReadCardSerial()) {
    return;
  }

  //Dump debug ingo about the card.PICC_HaltA() is automatically called
  mfrc.PICC_DumpToSerial(&(mfrc.uid));
  //while(bluetooth.available()) { // 블루투스로부터 받은 데이터가 있으면
    //Serial.write(bluetooth.read()); 
 
   //myString += myChar;
    // delay(5);
     //Serial.println(myChar);
 // }
 // if(!myString.equals("")){
   //      Serial.println("input value" + myString);
   /* if(data == 'y'){
      digitalWrite(led,HIGH);
      //delay(1000);
    }else if (data == 'n'){
        digitalWrite(led,LOW);
     }*/ 
 // myString ="";
  //}
}

