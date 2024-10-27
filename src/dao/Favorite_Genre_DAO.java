package dao;

import common.Common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Favorite_Genre_DAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    // Scanner 기능이 필요할 때 켜짐(?)
    public void Favorite_Genre_DAO_Scanner(){
        scanner = new Scanner(System.in);
        try{
            fileInputStream = new FileInputStream(".csv");
        }
        catch (FileNotFoundException e){
            System.out.println("파일을 찾지 못했습니다.");
        }
    }
    // 테이블 CREATE 기능 구현 (참조 기능 없음)
    public void favorite_Gen_Create(){
        try{
            conn = Common.getConnection();
            stmt = null;
            String createTableSQL = "CREATE TABLE 선호장르 ("+"회원번호 INTEGER,"+"장르번호 INTEGER,"+"PRIMARY KEY (회원번호, 장르번호),"+"FOREIGN KEY (회원번호) REFERENCES 회원 (회원번호),"+"FOREIGN KEY (장르번호) REFERENCES 장르 (장르번호))";
            stmt = conn.createStatement();
            stmt.executeUpdate(createTableSQL);
            System.out.println("테이블 생성 성공");
        }
        catch (Exception e){
            System.out.println("테이블 생성 실패");
        }
        finally{
            Common.close(conn);
            Common.close(stmt);
        }
    }

}
