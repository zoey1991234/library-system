import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection con;

    public static Connection connect() {
        if (con != null) {
            return con;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功載入資料庫驅動！");

            // 從環境變數中讀取資料庫密碼
            String url = "jdbc:mysql://localhost:3306/book_admin?serverTimezone=UTC";
            String username = "root";
            String password = System.getenv("DB_PASSWORD");  // 從環境變數獲取密碼

            if (password == null || password.isEmpty()) {
                System.out.println("資料庫密碼環境變數未設定！");
                return null;
            }

            con = DriverManager.getConnection(url, username, password);
            System.out.println("成功連接資料庫伺服器");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到資料庫驅動");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("連接資料庫伺服器出現錯誤");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("發生其他錯誤");
            e.printStackTrace();
        }

        return con;
    }

    public static void closeCon(Connection con) {
        if (con != null) {
            try {
                con.close();
                System.out.println("資料庫連接已關閉");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
