package dao;

import common.Common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Webtoon_DAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    // Scanner 기능이 필요할 때 켜짐(?)
    public void Webtoon_DAO_Scanner(){
        scanner = new Scanner(System.in);
        try{
            fileInputStream = new FileInputStream(".csv");
        }
        catch (FileNotFoundException e){
            System.out.println("파일을 찾지 못했습니다.");
        }
    }
    // 테이블 CREATE 기능 구현 (참조 기능 없음)
    public void webtoonCreate(){
        try{
            conn = Common.getConnection();
            stmt = null;
            String createTableSQL = "CREATE TABLE 웹툰 ("+"웹툰번호 INTEGER PRIMARY KEY,"+"제목 VARCHAR2(100) NOT NULL,"+"대표작가 VARCHAR2(100) NOT NULL,"+"장르번호 INTEGER NOT NULL,"+"이용가능연령 NUMBER(2) NOT NULL,"+"평점 NUMBER(4,2) NOT NULL CHECK (평점 BETWEEN 0.00 AND 10.00),"+"구독자수 INTEGER,"+"조회수 INTEGER,"+"연재요일 NUMBER(1) NOT NULL CHECK (연재요일 BETWEEN 1 AND 7),"+"웹툰페이지URL VARCHAR2(255) NOT NULL,"+"썸네일URL VARCHAR2(255) NOT NULL,"+"플랫폼번호 INTEGER NOT NULL,"+"FOREIGN KEY (장르번호) REFERENCES 장르 (장르번호),"+"FOREIGN KEY (플랫폼번호) REFERENCES 플랫폼 (플랫폼번호))";
            String createSequenceSQL = "CREATE SEQUENCE 웹툰번호_SEQ "+" START WITH 1 "+" INCREMENT BY 1 "+" NOCACHE "+" NOCYCLE";
            String createTriggerSQL = "CREATE TRIGGER 웹툰_트리거 "+" BEFORE INSERT ON 웹툰 "+" FOR EACH ROW "+" BEGIN "+" :NEW.웹툰번호 := 웹툰번호_SEQ.NEXTVAL; "+" END;";
            stmt = conn.createStatement();
            stmt.executeUpdate(createTableSQL);
            stmt.executeUpdate(createSequenceSQL);
            stmt.executeUpdate(createTriggerSQL);
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
