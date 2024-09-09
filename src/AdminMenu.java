import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.concurrent.TimeUnit;
import java.util.Date;

public class AdminMenu {

    // 獲取資料庫密碼的環境變數
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    // 建立與資料庫的連接
    public static Connection connect() {
        try {
            // 確保環境變數不為空
            if (DB_PASSWORD == null || DB_PASSWORD.isEmpty()) {
                throw new RuntimeException("資料庫密碼環境變數(DB_PASSWORD)未設置");
            }
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", DB_PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 顯示管理員功能選單
    public static void admin_menu() {
        JFrame f = new JFrame("管理員介面");

        JButton create_but = new JButton("建立/重置");
        create_but.setBounds(50, 20, 120, 25);
        create_but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection connection = connect();
                try {
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate("DROP DATABASE IF EXISTS book_admin");
                    stmt.executeUpdate("CREATE DATABASE book_admin");
                    stmt.executeUpdate("USE book_admin");
                    stmt.executeUpdate("CREATE TABLE BOOKS (BID INT PRIMARY KEY AUTO_INCREMENT, BNAME VARCHAR(50), GENRE VARCHAR(20), AUTHOR VARCHAR(50), PRICE DOUBLE)");
                    stmt.executeUpdate("CREATE TABLE USERS (UID INT PRIMARY KEY AUTO_INCREMENT, USERNAME VARCHAR(50), CONTACT VARCHAR(20))");
                    stmt.executeUpdate("CREATE TABLE ISSUED (IID INT PRIMARY KEY AUTO_INCREMENT, UID INT, BID INT, ISSUED_DATE DATE, RETURN_DATE DATE, PERIOD INT, FINE DOUBLE)");
                    JOptionPane.showMessageDialog(null, "資料庫建立/重置成功！");
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });

        JButton view_but = new JButton("檢視書籍");
        view_but.setBounds(200, 20, 120, 25);
        view_but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection connection = connect();
                try {
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate("USE book_admin");
                    ResultSet rs = stmt.executeQuery("SELECT * FROM BOOKS");
                    JTable book_table = new JTable();
                    book_table.setModel(new javax.swing.table.DefaultTableModel(
                            new Object[][]{},
                            new String[]{
                                    "BID", "BNAME", "GENRE", "AUTHOR", "PRICE", "STATUS"
                            }
                    ));
                    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) book_table.getModel();
                    while (rs.next()) {
                        model.addRow(new Object[]{rs.getInt("BID"), rs.getString("BNAME"), rs.getString("GENRE"), rs.getString("AUTHOR"), rs.getDouble("PRICE"), rs.getString("STATUS")});
                    }
                    JOptionPane.showMessageDialog(null, new JScrollPane(book_table));
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });

        JButton users_but = new JButton("檢視使用者");
        users_but.setBounds(350, 20, 120, 25);
        users_but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection connection = connect();
                try {
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate("USE book_admin");
                    ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
                    JTable user_table = new JTable();
                    user_table.setModel(new javax.swing.table.DefaultTableModel(
                            new Object[][]{},
                            new String[]{
                                    "UID", "USERNAME", "CONTACT"
                            }
                    ));
                    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) user_table.getModel();
                    while (rs.next()) {
                        model.addRow(new Object[]{rs.getInt("UID"), rs.getString("USERNAME"), rs.getString("CONTACT")});
                    }
                    JOptionPane.showMessageDialog(null, new JScrollPane(user_table));
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });

        JButton issued_but = new JButton("檢視已借出書籍");
        issued_but.setBounds(500, 20, 150, 25);
        issued_but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection connection = connect();
                try {
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate("USE book_admin");
                    ResultSet rs = stmt.executeQuery("SELECT * FROM ISSUED");
                    JTable issued_table = new JTable();
                    issued_table.setModel(new javax.swing.table.DefaultTableModel(
                            new Object[][]{},
                            new String[]{
                                    "IID", "UID", "BID", "ISSUED_DATE", "RETURN_DATE", "PERIOD", "FINE"
                            }
                    ));
                    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) issued_table.getModel();
                    while (rs.next()) {
                        model.addRow(new Object[]{rs.getInt("IID"), rs.getInt("UID"), rs.getInt("BID"), rs.getDate("ISSUED_DATE"), rs.getDate("RETURN_DATE"), rs.getInt("PERIOD"), rs.getDouble("FINE")});
                    }
                    JOptionPane.showMessageDialog(null, new JScrollPane(issued_table));
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });

        JButton add_user = new JButton("新增使用者");
        add_user.setBounds(50, 60, 120, 25);
        add_user.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame g = new JFrame("輸入使用者資訊");

                JLabel l1, l2;
                l1 = new JLabel("姓名");
                l1.setBounds(30, 15, 100, 30);

                l2 = new JLabel("聯絡方式");
                l2.setBounds(30, 53, 100, 30);

                JTextField F_name = new JTextField();
                F_name.setBounds(110, 15, 200, 30);

                JTextField F_contact = new JTextField();
                F_contact.setBounds(110, 53, 200, 30);

                JButton create_but = new JButton("提交");
                create_but.setBounds(130, 100, 100, 25);
                create_but.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String name = F_name.getText();
                        String contact = F_contact.getText();

                        Connection connection = connect();
                        try {
                            Statement stmt = connection.createStatement();
                            stmt.executeUpdate("USE book_admin");
                            stmt.executeUpdate("INSERT INTO USERS(USERNAME, CONTACT) VALUES ('" + name + "','" + contact + "')");
                            JOptionPane.showMessageDialog(null, "使用者已新增！");
                            g.dispose();
                        } catch (SQLException e1) {
                            JOptionPane.showMessageDialog(null, e1);
                        }
                    }
                });

                g.add(l1);
                g.add(l2);
                g.add(F_name);
                g.add(F_contact);
                g.add(create_but);
                g.setSize(350, 200);
                g.setLayout(null);
                g.setVisible(true);
                g.setLocationRelativeTo(null);
            }
        });

        JButton add_book = new JButton("新增書籍");
        add_book.setBounds(200, 60, 120, 25);
        add_book.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame g = new JFrame("輸入書籍資訊");

                JLabel l1, l2, l3, l4;
                l1 = new JLabel("書名");
                l1.setBounds(30, 15, 100, 30);

                l2 = new JLabel("類型");
                l2.setBounds(30, 53, 100, 30);

                l3 = new JLabel("價格");
                l3.setBounds(30, 90, 100, 30);

                l4 = new JLabel("作者");
                l4.setBounds(30, 127, 100, 30);

                JTextField F_name = new JTextField();
                F_name.setBounds(110, 15, 200, 30);

                JTextField F_genre = new JTextField();
                F_genre.setBounds(110, 53, 200, 30);

                JTextField F_price = new JTextField();
                F_price.setBounds(110, 90, 200, 30);

                JTextField F_author = new JTextField();
                F_author.setBounds(110, 127, 200, 30);

                JButton create_but = new JButton("提交");
                create_but.setBounds(130, 170, 100, 25);
                create_but.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String name = F_name.getText();
                        String genre = F_genre.getText();
                        String author = F_author.getText();
                        String priceStr = F_price.getText();
                        double price = Double.parseDouble(priceStr);

                        Connection connection = connect();
                        try {
                            Statement stmt = connection.createStatement();
                            stmt.executeUpdate("USE book_admin");
                            stmt.executeUpdate("INSERT INTO BOOKS(BNAME, GENRE, AUTHOR, PRICE) VALUES ('" + name + "','" + genre + "','" + author + "'," + price + ")");
                            JOptionPane.showMessageDialog(null, "書籍已新增！");
                            g.dispose();
                        } catch (SQLException e1) {
                            JOptionPane.showMessageDialog(null, e1);
                        }
                    }
                });

                g.add(l1);
                g.add(l2);
                g.add(l3);
                g.add(l4);
                g.add(F_name);
                g.add(F_genre);
                g.add(F_price);
                g.add(F_author);
                g.add(create_but);
                g.setSize(350, 250);
                g.setLayout(null);
                g.setVisible(true);
                g.setLocationRelativeTo(null);
            }
        });

        f.add(create_but);
        f.add(view_but);
        f.add(users_but);
        f.add(issued_but);
        f.add(add_user);
        f.add(add_book);
        f.setSize(700, 200);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        admin_menu();
    }
}
