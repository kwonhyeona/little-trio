#include <SPI.h>
#include <MFRC522.h>

#define RST_PIN   9                            // reset핀은 9번으로 설정
#define SS_PIN    10                           // SS핀은 10번으로 설정
// SS핀은 데이터를 주고받는 역할의 핀( SS = Slave Selector )

MFRC522 mfrc(SS_PIN, RST_PIN);                 // MFR522를 이용하기 위해 mfrc객체를 생성해 줍니다.

void setup() {
  Serial.begin(9600);                         // 시리얼 통신, 속도는 9600
  SPI.begin();                                // SPI 초기화
  // (SPI : 하나의 마스터와 다수의 SLAVE(종속적인 역활)간의 통신 방식)
  mfrc.PCD_Init();
  Serial.println("Scan PICC to see UID and type...");
}

void loop() {
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
}
