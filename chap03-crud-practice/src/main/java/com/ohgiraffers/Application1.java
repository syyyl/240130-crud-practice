package com.ohgiraffers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {

    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("ID를 입력해주세요 : ");
        String cityId = sc.nextLine();
        String query = "SELECT * FROM city WHERE ID = ?";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, cityId);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                System.out.println(rset.getString("Name") + ", "
                + rset.getString("CountryCode") + ", "
                + rset.getString("District") + ", "
                + rset.getInt("Population") + ", "
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }


    }
}
