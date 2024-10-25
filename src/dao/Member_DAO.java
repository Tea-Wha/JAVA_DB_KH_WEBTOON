package dao;

import common.Common;
import vo.Comment_VO;
import vo.Member_VO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// DAO(Database Access Object) : 데이터베이스에 접근하여 데이터를 조회하거나 수정하는 데 사용
public class Member_DAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    // Scanner 기능이 필요할 때 켜짐(?)
    public void Member_DAO_Scanner() {
        scanner = new Scanner(System.in);
        try {
            fileInputStream = new FileInputStream(".csv");
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾지 못했습니다.");
        }
    }
    // 테이블 CREATE 기능 구현
    public void memberCreate(){
        try{
            conn = Common.getConnection();
            stmt = null;
            String createTableSQL = "CREATE TABLE 회원 ("+"회원번호 INTEGER PRIMARY KEY,"+"아이디 VARCHAR2(50) NOT NULL UNIQUE,"+"비밀번호 VARCHAR2(20) NOT NULL CHECK (LENGTH (비밀번호) BETWEEN 8 AND 20),"+"이메일 VARCHAR2(50) UNIQUE NOT NULL CHECK (이메일 LIKE '%@%'),"+"생년월일 DATE NOT NULL,"+"닉네임 VARCHAR2(20) UNIQUE NOT NULL CHECK (LENGTH (닉네임) BETWEEN 1 AND 20),"+"가입일 DATE NOT NULL,"+"탈퇴여부 NUMBER(1) DEFAULT 0 CHECK(탈퇴여부 IN (0,1)),"+"회원종류번호 INTEGER NOT NULL,"+" CONSTRAINT fk_회원종류번호 FOREIGN KEY (회원종류번호) REFERENCES 회원종류 (회원종류번호))";
            String createSequenceSQL = "CREATE SEQUENCE 회원번호_SEQ "+" START WITH 1 "+" INCREMENT BY 1 "+" NOCACHE "+" NOCYCLE";
            String createTriggerSQL = "CREATE TRIGGER 회원_트리거 "+" BEFORE INSERT ON 회원 "+" FOR EACH ROW "+" BEGIN "+" :NEW.회원번호 := 회원번호_SEQ.NEXTVAL; "+" END;";
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
    // Comment Menu 따로 구현 -> 클래스 안에 포함?
    // SELECT(조회) 기능 구현
    // 댓글 테이블 확인 기능
    public List<Member_VO> memberSelect(){
        List<Member_VO> list = new ArrayList<>();
        try{
            conn = Common.getConnection(); // 오라클 DB 연결
            stmt = conn.createStatement(); // statement 생성
            String query = "SELECT * FROM 회원 ORDER BY 회원번호"; // 회원 테이블 쿼리문 구성 (회원번호 정렬)
            rs = stmt.executeQuery(query); // 쿼리문 실행
            while (rs.next()){
                int member_Num = rs.getInt("회원번호"); // 댓글번호 열 데이터 가져오기
                String member_ID = rs.getString("아이디"); // 본문 열 데이터 가져오기
                String member_PW = rs.getString("비밀번호"); // 작성일 열 데이터 가져오기
                String member_Email = rs.getString("이메일"); // 공감수 열 데이터 가져오기
                Date member_Birth_Date = rs.getDate("생년월일"); // 비공감수 열 데이터 가져오기
                String member_Nickname = rs.getString("닉네임");
                Date member_Reg_Date = rs.getDate("가입일"); // 가입일 열 데이터 가져오기
                int member_Exist = rs.getInt("탈퇴여부"); // 게시글번호 열 데이터 가져오기
                int member_Type_Num = rs.getInt("회원종류번호"); //
                Member_VO vo = new Member_VO(member_Num,member_ID,member_PW,member_Email,member_Birth_Date,member_Nickname,member_Reg_Date,member_Exist,member_Type_Num);
                list.add(vo); // 리스트 저장
            }
        }
        catch (Exception e){
            System.out.println("회원 조회 실패");
        }
        finally{
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        }
        return list; // 리스트 반환
    }
}
