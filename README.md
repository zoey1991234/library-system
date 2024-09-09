![image](https://github.com/user-attachments/assets/eeac0fa4-ec0f-47df-a1c2-5ace929bef73)# library-system

![image](https://github.com/user-attachments/assets/82f79bd6-bec6-4563-8f98-b90dedbede01)

IntelliJ IDEA:
點擊專案 > Project Structer> Modules > Dependencies > + > JARs or directories。

選擇下載的 `rs2xml.jar` 跟 `mysql-connector-java.jar`
![image](https://github.com/user-attachments/assets/043becb9-65a7-4b26-bc5b-9e5d5db90264)![image](https://github.com/user-attachments/assets/bd72e023-3cb1-4cf0-994a-da27790c2f2d)

設置環境變數將資料庫的密碼設置在環境變數中，然後在程式碼中讀取該變數
 *在「系統變數」部分中，點擊「新建」。
 *設置變數名為 DB_PASSWORD，並在變數值中輸入你的資料庫密碼。
 *點擊「確定」來保存更改。
 *打開命令提示符，輸入以下命令來確認 DB_PASSWORD 環境變數是否設置正確：
 'echo %DB_PASSWORD%' 確保這個命令返回了正確的密碼，而不是 %DB_PASSWORD%。
 *重新啟動IntelliJ IDEA。
