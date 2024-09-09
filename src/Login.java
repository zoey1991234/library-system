import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {
    public static void displayLogin() {
        JFrame loginFrame = new JFrame("登入");
        loginFrame.setTitle("登入 - 圖書管理系統");  // 修改窗口標題
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 創建標題標籤
        JLabel titleLabel = new JLabel("圖書管理系統");
        titleLabel.setBounds(30, 15, 200, 30);  // 設置標題標籤的位置和大小
        titleLabel.setFont(new Font("宋體", Font.BOLD, 18));  // 設置標題標籤的字體和大小

        JLabel userLabel = new JLabel("使用者名稱:");
        userLabel.setBounds(30, 50, 100, 30);
        JTextField userField = new JTextField();
        userField.setBounds(110, 50, 200, 30);

        JLabel passLabel = new JLabel("密碼:");
        passLabel.setBounds(30, 85, 100, 30);
        JPasswordField passField = new JPasswordField();
        passField.setBounds(110, 85, 200, 30);

        JButton loginButton = new JButton("登入");
        loginButton.setBounds(130, 125, 80, 25);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "請輸入使用者名稱跟密碼");
                } else {
                    Connection connection = DatabaseConnection.connect();
                    try {
                        Statement stmt = connection.createStatement();
                        String query = "SELECT * FROM USERS WHERE USERNAME='" + username + "' AND PASSWORD='" + password + "'";
                        ResultSet rs = stmt.executeQuery(query);

                        if (rs.next()) {
                            String admin = rs.getString("ADMIN");
                            String UID = rs.getString("UID");
                            loginFrame.dispose();
                            if (admin.equals("1")) {
                                AdminMenu.admin_menu();  // 調用 AdminMenu 類中的 admin_menu 方法
                            } else {
                                UserMenu.displayUserMenu(UID);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "錯誤使用者名稱或密碼!");
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // 將標題標籤和其他元件添加到 JFrame 中
        loginFrame.add(titleLabel);
        loginFrame.add(userLabel);
        loginFrame.add(userField);
        loginFrame.add(passLabel);
        loginFrame.add(passField);
        loginFrame.add(loginButton);

        loginFrame.setSize(400, 200);  // 調整窗口大小
        loginFrame.setLayout(null);
        loginFrame.setVisible(true);
        loginFrame.setLocationRelativeTo(null);
    }
}
