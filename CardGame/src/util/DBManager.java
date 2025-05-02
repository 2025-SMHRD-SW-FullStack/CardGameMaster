package util;

import java.sql.*;

public class DBManager {
    private Connection conn = null;


     public Connection getConn() {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";
            String username = "campus_25SW_FS_p1_4";
            String pw = "smhrd4";

            conn = DriverManager.getConnection(url, username, pw);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    // 사용한 자원을 반납하는 메소드

}
