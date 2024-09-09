import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.proteanit.sql.DbUtils;

public class UserMenu {
    private static String currentUserID;

    public static void displayUserMenu(String UID) {
        currentUserID = UID;
        JFrame userFrame = new JFrame("讀者功能選單");

        JButton viewBooksButton = new JButton("查看書籍");
        viewBooksButton.setBounds(20, 20, 120, 25);
        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame booksFrame = new JFrame("可用書籍");
                Connection connection = DatabaseConnection.connect();
                String sql = "SELECT * FROM books";
                try {
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    JTable bookList = new JTable();
                    bookList.setModel(DbUtils.resultSetToTableModel(rs));

                    JScrollPane scrollPane = new JScrollPane(bookList);
                    booksFrame.add(scrollPane);
                    booksFrame.setSize(800, 400);
                    booksFrame.setVisible(true);
                    booksFrame.setLocationRelativeTo(null);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });

        JButton myBooksButton = new JButton("我的書籍");
        myBooksButton.setBounds(150, 20, 120, 25);
        myBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame myBooksFrame = new JFrame("我的書籍");
                Connection connection = DatabaseConnection.connect();
                String sql = "SELECT issued.IID, issued.UID, issued.BID, issued.ISSUED_DATE, issued.RETURN_DATE, issued.PERIOD, issued.FINE, books.BNAME, books.GENRE, books.PRICE " +
                        "FROM issued JOIN books ON issued.BID = books.BID WHERE issued.UID = " + UID;
                try {
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    JTable bookList = new JTable();
                    bookList.setModel(DbUtils.resultSetToTableModel(rs));

                    JScrollPane scrollPane = new JScrollPane(bookList);
                    myBooksFrame.add(scrollPane);
                    myBooksFrame.setSize(800, 400);
                    myBooksFrame.setVisible(true);
                    myBooksFrame.setLocationRelativeTo(null);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });

        JButton searchByTitleButton = new JButton("按標題搜尋");
        searchByTitleButton.setBounds(20, 55, 120, 25);
        searchByTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog(null, "請輸入書籍標題:");
                if (title != null && !title.isEmpty()) {
                    JFrame searchByTitleFrame = new JFrame("標題搜尋結果: " + title);
                    Connection connection = DatabaseConnection.connect();
                    String sql = "SELECT * FROM books WHERE BNAME LIKE '%" + title + "%'";
                    try {
                        Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        JTable bookList = new JTable();
                        bookList.setModel(DbUtils.resultSetToTableModel(rs));

                        JScrollPane scrollPane = new JScrollPane(bookList);
                        searchByTitleFrame.add(scrollPane);
                        searchByTitleFrame.setSize(800, 400);
                        searchByTitleFrame.setVisible(true);
                        searchByTitleFrame.setLocationRelativeTo(null);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });

        JButton searchByAuthorButton = new JButton("按作者搜尋");
        searchByAuthorButton.setBounds(150, 55, 120, 25);
        searchByAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String author = JOptionPane.showInputDialog(null, "請輸入作者名:");
                if (author != null && !author.isEmpty()) {
                    JFrame searchByAuthorFrame = new JFrame("作者搜尋結果: " + author);
                    Connection connection = DatabaseConnection.connect();
                    String sql = "SELECT * FROM books WHERE AUTHOR LIKE '%" + author + "%'";
                    try {
                        Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        JTable bookList = new JTable();
                        bookList.setModel(DbUtils.resultSetToTableModel(rs));

                        JScrollPane scrollPane = new JScrollPane(bookList);
                        searchByAuthorFrame.add(scrollPane);
                        searchByAuthorFrame.setSize(800, 400);
                        searchByAuthorFrame.setVisible(true);
                        searchByAuthorFrame.setLocationRelativeTo(null);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });

        JButton searchByTopicButton = new JButton("按主題搜尋");
        searchByTopicButton.setBounds(280, 55, 120, 25);
        searchByTopicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String topic = JOptionPane.showInputDialog(null, "請輸入主題:");
                if (topic != null && !topic.isEmpty()) {
                    JFrame searchByTopicFrame = new JFrame("主題搜尋結果: " + topic);
                    Connection connection = DatabaseConnection.connect();
                    // Use 'GENRE' column for topic search
                    String sql = "SELECT * FROM books WHERE GENRE LIKE '%" + topic + "%'";
                    try {
                        Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        JTable bookList = new JTable();
                        bookList.setModel(DbUtils.resultSetToTableModel(rs));

                        JScrollPane scrollPane = new JScrollPane(bookList);
                        searchByTopicFrame.add(scrollPane);
                        searchByTopicFrame.setSize(800, 400);
                        searchByTopicFrame.setVisible(true);
                        searchByTopicFrame.setLocationRelativeTo(null);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });


        JButton viewProfileButton = new JButton("查看個人訊息");
        viewProfileButton.setBounds(20, 90, 120, 25);
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame userProfileFrame = new JFrame("個人訊息");
                JLabel profileLabel = new JLabel("用戶ID: " + currentUserID);
                profileLabel.setBounds(20, 20, 200, 30);
                userProfileFrame.add(profileLabel);
                userProfileFrame.setSize(300, 100);
                userProfileFrame.setLayout(null);
                userProfileFrame.setVisible(true);
                userProfileFrame.setLocationRelativeTo(null);
            }
        });

        JButton borrowBookButton = new JButton("借書");
        borrowBookButton.setBounds(150, 90, 120, 25);
        borrowBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookID = JOptionPane.showInputDialog(null, "請輸入書籍ID:");
                if (bookID != null && !bookID.isEmpty()) {
                    Connection connection = DatabaseConnection.connect();
                    String borrowSql = "INSERT INTO issued (UID, BID, ISSUED_DATE, RETURN_DATE, PERIOD, FINE) VALUES (" + currentUserID + ", " + bookID + ", CURDATE(), DATE_ADD(CURDATE(), INTERVAL 14 DAY), 14, 0)";
                    try {
                        Statement stmt = connection.createStatement();
                        stmt.executeUpdate(borrowSql);

                        // 更新書籍狀態為已借出
                        String updateStatusSql = "UPDATE books SET STATUS = 1 WHERE BID = " + bookID;
                        Statement updateStmt = connection.createStatement();
                        updateStmt.executeUpdate(updateStatusSql);

                        JOptionPane.showMessageDialog(null, "書籍借閱成功!");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });

        JButton returnBookButton = new JButton("還書");
        returnBookButton.setBounds(280, 90, 120, 25);
        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String returnBookID = JOptionPane.showInputDialog(null, "請輸入借閱ID:");
                if (returnBookID != null && !returnBookID.isEmpty()) {
                    Connection connection = DatabaseConnection.connect();
                    String checkIssuedSql = "SELECT * FROM issued WHERE IID = " + returnBookID + " AND UID = " + currentUserID;
                    try {
                        Statement checkStmt = connection.createStatement();
                        ResultSet rs = checkStmt.executeQuery(checkIssuedSql);
                        if (rs.next()) {
                            int bookID = rs.getInt("BID");
                            // 檢查書籍狀態是否已借出
                            String checkStatusSql = "SELECT * FROM books WHERE BID = " + bookID + " AND STATUS = 1";
                            Statement statusStmt = connection.createStatement();
                            ResultSet statusRs = statusStmt.executeQuery(checkStatusSql);
                            if (statusRs.next()) {
                                // 歸還書籍並更新狀態
                                String returnSql = "DELETE FROM issued WHERE IID = " + returnBookID;
                                Statement returnStmt = connection.createStatement();
                                returnStmt.executeUpdate(returnSql);

                                String updateStatusSql = "UPDATE books SET STATUS = 0 WHERE BID = " + bookID;
                                Statement updateStmt = connection.createStatement();
                                updateStmt.executeUpdate(updateStatusSql);

                                JOptionPane.showMessageDialog(null, "書籍歸還成功!");
                            } else {
                                JOptionPane.showMessageDialog(null, "無法歸還此書籍，該書籍狀態不是已借出。");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "無法歸還此書籍，請檢查借閱ID是否正確或該書籍是否為您所借閱。");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });

        userFrame.add(viewBooksButton);
        userFrame.add(myBooksButton);
        userFrame.add(searchByTitleButton);
        userFrame.add(searchByAuthorButton);
        userFrame.add(searchByTopicButton);
        userFrame.add(viewProfileButton);
        userFrame.add(borrowBookButton);
        userFrame.add(returnBookButton);
        userFrame.setSize(450, 180);
        userFrame.setLayout(null);
        userFrame.setVisible(true);
        userFrame.setLocationRelativeTo(null);
    }
}
