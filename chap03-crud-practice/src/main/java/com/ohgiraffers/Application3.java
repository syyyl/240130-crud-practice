package com.ohgiraffers;

import com.ohgiraffers.CityDTO.CityDTO;

import javax.naming.Name;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("변경할 도시 번호 입력 : ");
        int cityId = sc.nextInt();
        System.out.println("변경할 도시 이름 입력 : ");
        sc.nextLine();
        String cityName = sc.nextLine();
        System.out.println("변경할 국가코드 입력 : ");
        String countryCode = sc.nextLine();
        System.out.println("변경할 구역 입력 : ");
        String district = sc.nextLine();
        System.out.println("변경할 인구 입력 : ");
        int population = sc.nextInt();
        sc.nextLine();

        CityDTO changeCity = new CityDTO();
        changeCity.setId(cityId);
        changeCity.setName(cityName);
        changeCity.setConutryCode(countryCode);
        changeCity.setDistrict(district);
        changeCity.setPopulation(population);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src\\main\\java\\com\\ohgiraffers\\mapper\\world-query.xml"));
            String query = prop.getProperty("updateCity");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, changeCity.getName());
            pstmt.setString(2, changeCity.getConutryCode());
            pstmt.setString(3, changeCity.getDistrict());
            pstmt.setInt(4, changeCity.getPopulation());

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
            System.out.println("변경 성공");
        } else {
            System.out.println("변경 실패");
        }

    }
}
