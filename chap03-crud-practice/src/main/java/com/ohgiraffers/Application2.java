package com.ohgiraffers;

import com.ohgiraffers.CityDTO.CityDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("도시 이름 입력 : ");
        String cityName = sc.nextLine();
        System.out.println("국가코드 입력 : ");
        String countryCode = sc.nextLine();
        System.out.println("구역 입력 : ");
        String district = sc.nextLine();
        System.out.println("인구 입력 : ");
        int population = sc.nextInt();

        CityDTO newCity = new CityDTO();
        newCity.setName(cityName);
        newCity.setConutryCode(countryCode);
        newCity.setDistrict(district);
        newCity.setPopulation(population);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src\\main\\java\\com\\ohgiraffers\\mapper\\world-query.xml"));
            String query = prop.getProperty("insertCity");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newCity.getName());
            pstmt.setString(2, newCity.getConutryCode());
            pstmt.setString(3, newCity.getDistrict());
            pstmt.setInt(4, newCity.getPopulation());

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
            System.out.println("추가 성공");
        } else {
            System.out.println("추가 실패");
        }


    }
}
