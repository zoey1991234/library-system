# library-system


## 運行配置

IntelliJ IDEA:
點擊專案 > Project Structer> Modules > Dependencies > + > JARs or directories。

選擇下載的 `rs2xml.jar` 跟 `mysql-connector-java.jar`

![image](https://github.com/user-attachments/assets/043becb9-65a7-4b26-bc5b-9e5d5db90264)![image](https://github.com/user-attachments/assets/bd72e023-3cb1-4cf0-994a-da27790c2f2d)

設置環境變數將資料庫的密碼設置在環境變數中，然後在程式碼中讀取該變數
 * 在「系統變數」部分中，點擊「新建」。
 * 設置變數名為 DB_PASSWORD，並在變數值中輸入你的資料庫密碼。
 * 點擊「確定」來保存更改。
 * 打開命令提示符，輸入以下命令來確認 DB_PASSWORD 環境變數是否設置正確：
 ' echo %DB_PASSWORD% ' 確保這個命令返回了正確的密碼，而不是 %DB_PASSWORD%。
 * 重新啟動IntelliJ IDEA。

---------------------------------------

使用者分為

讀者
借書 還書(有罰款機制) 按主題/作者/標題搜尋書籍 查看書籍 查看個人訊息

管理員
借出書籍 歸還書籍 新增書籍 新增使用者  檢視書籍 檢視使用者 檢視已借出書籍 重置資料庫

功能架構圖

![image](https://github.com/user-attachments/assets/ddeac98a-e9e0-42f2-bd18-55fecdc3741b)

UML Class Diagram

![image](https://github.com/user-attachments/assets/790825f4-e1a4-451e-96e4-06cb4ca88acc) 

![image](https://github.com/user-attachments/assets/f82eeb54-2f48-42f8-bfde-2855877ce3a6)

資料庫

![image](https://github.com/user-attachments/assets/f44aef82-0a87-496c-9b83-e9139ed939d5)

使用者Users

![image](https://github.com/user-attachments/assets/b66dc826-f39b-450b-9a61-ff7f75ad8773)

書籍Books

![image](https://github.com/user-attachments/assets/82187d86-cbef-480c-a134-5226de7d7189)

已借出書籍issued

![image](https://github.com/user-attachments/assets/31f60361-664e-41b2-80b2-562af7dda126)

管理員介面

![image](https://github.com/user-attachments/assets/2e66ad6e-d2da-4d76-b71b-d681676a45e6)

讀者介面

![image](https://github.com/user-attachments/assets/3a40e18e-a040-47e8-882f-d34729c6f307)

查看書籍

![image](https://github.com/user-attachments/assets/f8f19249-fb5a-4b72-8ec3-ef35eba863b5)

我的書籍
![image](https://github.com/user-attachments/assets/789e5dc0-c489-4187-9773-c2128ef76696)

可以按照標題/作者/主題搜尋

![image](https://github.com/user-attachments/assets/51c906a1-8bd8-4f76-afde-e5246c500ace)


![image](https://github.com/user-attachments/assets/c60ad9b9-1952-4a23-a887-331d3de6daff)

查看個人訊息

![image](https://github.com/user-attachments/assets/a3efede7-300d-484e-b5a5-385f5cadc502)

借書(輸入書籍ID)

![image](https://github.com/user-attachments/assets/64287c55-0254-402f-b668-302bbea8284b)

還書(輸入歸還ID)

