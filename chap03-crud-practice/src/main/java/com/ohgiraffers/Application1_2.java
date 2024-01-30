package com.ohgiraffers;

import com.ohgiraffers.CityDTO.CityDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1_2 {

    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        CityDTO selectedCity = null;
        List<CityDTO> cityList = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 도시 이름을 입력하세요 : ");
        String cityName = sc.nextLine();

        String query =  "SELECT Name FROM city WHERE Name LIKE CONCAT('%' ,?, '%')";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, cityName);

            rset = pstmt.executeQuery();

            cityList = new ArrayList<>();

            while (rset.next()){
                selectedCity = new CityDTO();

                selectedCity.setName(rset.getString("Name"));

                cityList.add(selectedCity);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        for (CityDTO c : cityList) {
            System.out.println(c);
        }

    }
}
