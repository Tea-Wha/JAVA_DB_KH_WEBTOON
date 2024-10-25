package dao;

import common.Common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Post_DAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    // Scanner 기능이 필요할 때 켜짐(?)
    public void Post_DAO_Scanner(){
        scanner = new Scanner(System.in);
        try{
            fileInputStream = new FileInputStream(".csv");
        }
        catch (FileNotFoundException e){
            System.out.println("파일을 찾지 못했습니다.");
        }
    }
    // 테이블 CREATE 기능 구현 (참조 기능 없음)
    public void postCreate(){
        try{
            conn = Common.getConnection();
            stmt = null;
            String createTableSQL = "CREATE TABLE 게시글 ("+"게시글번호 INTEGER PRIMARY KEY,"+"제목 VARCHAR2(100) NOT NULL CHECK (LENGTH (제목) BETWEEN 1 AND 100),"+"본문 VARCHAR2(1000) NOT NULL CHECK (LENGTH (본문) BETWEEN 1 AND 1000),"+"작성일 DATE NOT NULL,"+"회원번호 INTEGER NOT NULL,"+"게시판유형번호 INTEGER NOT NULL,"+"FOREIGN KEY (회원번호) REFERENCES 회원 (회원번호),"+" FOREIGN KEY (게시판유형번호) REFERENCES 게시판유형 (게시판유형번호))";
            String createSequenceSQL = "CREATE SEQUENCE 게시글번호_SEQ "+" START WITH 1 "+" INCREMENT BY 1 "+" NOCACHE "+" NOCYCLE";
            String createTriggerSQL = "CREATE TRIGGER 게시글번호_자동증가 "+" BEFORE INSERT ON 게시글 "+" FOR EACH ROW "+" BEGIN "+" :NEW.게시글번호 := 게시글번호_SEQ.NEXTVAL; "+" END;";
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
