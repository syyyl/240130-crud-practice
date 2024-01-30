package com.ohgiraffers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("삭제할 도시 번호 : ");
        int cityId = sc.nextInt();

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/world-query"));
            String query = prop.getProperty("deleteCity");

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, cityId);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }
        
        if (result>0) {
            System.out.println("삭제 성공");
        } else {
            System.out.println("삭제 실패");
        }

    }
}
