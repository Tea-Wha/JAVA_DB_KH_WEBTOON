package dao;

import common.Common;
import vo.Board_VO;
import vo.Reply_VO;
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
    private String member_ID;
    private Object member_PW;

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
            String createTableSQL = "CREATE TABLE 회원 ("+"회원번호 INTEGER PRIMARY KEY,"+"아이디 VARCHAR2(50) NOT NULL UNIQUE,"+"비밀번호 VARCHAR2(20) NOT NULL CHECK (LENGTH (비밀번호) BETWEEN 8 AND 20),"+"이메일 VARCHAR2(50) UNIQUE NOT NULL CHECK (이메일 LIKE '%@%'),"+"생년월일 DATE NOT NULL,"+"닉네임 VARCHAR2(20) UNIQUE NOT NULL CHECK (LENGTH (닉네임) BETWEEN 1 AND 20),"+"가입일 DATE NOT NULL,"+"탈퇴여부 NUMBER(1) DEFAULT 0 CHECK(탈퇴여부 IN (0,1)),"+"회원종류번호 INTEGER DEFAULT 1,"+" CONSTRAINT fk_회원종류번호 FOREIGN KEY (회원종류번호) REFERENCES 회원종류 (회원종류번호))";
            String createSequenceSQL = "CREATE SEQUENCE 회원번호_SEQ "+" START WITH 1 "+" INCREMENT BY 1 "+" NOCACHE "+" NOCYCLE";
            String createTriggerSQL = "CREATE TRIGGER 회원_트리거 "+" BEFORE INSERT ON 회원 "+" FOR EACH ROW "+" BEGIN "+" :NEW.회원번호 := 회원번호_SEQ.NEXTVAL; "+" END;";
            stmt = conn.createStatement();
            stmt.executeUpdate(createTableSQL);
            stmt.executeUpdate(createSequenceSQL);
            stmt.executeUpdate(createTriggerSQL);
            System.out.println("테이블 생성 성공");
        }
        catch (Exception e){
            System.out.print(e);
            System.out.println("테이블 생성 실패");
        }
        finally{
            Common.close(conn);
            Common.close(stmt);
        }
    }
    // Comment Menu 따로 구현 -> 클래스 안에 포함?
    // SELECT(조회) 기능 구현
    // 회원 테이블 확인 기능
    public List<Member_VO> member_Select(){
        List<Member_VO> list = new ArrayList<>();
        try{
            conn = Common.getConnection(); // 오라클 DB 연결
            stmt = conn.createStatement(); // statement 생성
            String query = "SELECT * FROM 회원 ORDER BY 회원번호"; // 회원 테이블 쿼리문 구성 (회원번호 정렬)
            rs = stmt.executeQuery(query); // 쿼리문 실행
            while (rs.next()){
                int member_Num = rs.getInt("회원번호"); // 회원번호 열 데이터 가져오기
                String member_ID = rs.getString("아이디"); // 아이디 열 데이터 가져오기
                String member_PW = rs.getString("비밀번호"); // 비밀번호 열 데이터 가져오기
                String member_Email = rs.getString("이메일"); // 이메일 열 데이터 가져오기
                Date member_Birth_Date = rs.getDate("생년월일"); // 생년월일 열 데이터 가져오기
                String member_Nickname = rs.getString("닉네임"); // 닉네임 열 데이터 가져오가
                Date member_Reg_Date = rs.getDate("가입일"); // 가입일 열 데이터 가져오기
                int member_Exist = rs.getInt("탈퇴여부"); // 탈퇴여부 열 데이터 가져오기
                int member_Type_Num = rs.getInt("회원종류번호"); // 회원종류번호 열 데이터 가져오기
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
    // 화면 결과 출력 구현
    public void memberSelect_Result(List<Member_VO> list){
        System.out.println("---------------------------------");
        System.out.println("            회원  정보            ");
        System.out.println("---------------------------------");
        for(Member_VO e : list){
            System.out.print("회원 번호 : "+ e.getMember_Num()+" ");
            System.out.print("아이디 : "+e.getMember_ID()+" ");
            System.out.print("비밀번호 : "+e.getMember_PW()+" ");
            System.out.print("이메일 : "+e.getMember_Email()+" ");
            System.out.print("생년월일 : "+e.getMember_Birth_Date()+" ");
            System.out.print("닉네임 : "+e.getMember_Nickname()+" ");
            System.out.print("가입일 : "+e.getMember_Reg_Date()+" ");
            System.out.print("탈퇴여부 : "+e.getMember_Exist()+" ");
            System.out.print("회원종류번호 : "+e.getMember_Type_Num());
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
    // INSERT 기능 구현 -> 회원 가입 기능
    // 회원 가입 기능
    public boolean member_Insert(Member_VO vo){
        String sql = "INSERT INTO 회원 (아이디, 비밀번호, 이메일, 생년월일, 닉네임, 가입일) VALUES (?,?,?,?,?,SYSDATE)";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,vo.getMember_ID());
            psmt.setString(2,vo.getMember_PW());
            psmt.setString(3,vo.getMember_Email());
            psmt.setDate(4,vo.getMember_Birth_Date());
            psmt.setString(5,vo.getMember_Nickname());
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("회원 INSERT 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    // INSERT Input 데이터 받는 기능 -> 회원 가입
    public static Member_VO memberInsert_Input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("회원 정보 입력");
        System.out.print("회원 ID : ");
        String member_ID = scanner.next();
        System.out.print("회원 PW : ");
        String member_PW = scanner.next();
        System.out.print("회원 Email : ");
        String member_Email = scanner.next();
        System.out.print("회원 생년월일 : "); // Date 정보
        Date member_Birth = Date.valueOf(scanner.next());
        System.out.print("회원 닉네임 : ");
        String member_Nickname = scanner.next();
        Member_VO vo = new Member_VO(member_ID, member_PW, member_Email, member_Birth, member_Nickname);
        return vo;
    }
    // UPDATE 기능 구현 -> 회원 전용 기능 / 회원 페이지 (마이 페이지) 기능 -> 추후 수정 필요
    // 회원 비밀번호 변경 기능
    public boolean member_Update(Member_VO vo){
        String sql = "UPDATE 회원 SET 비밀번호 = ? WHERE 아이디 = ? AND 비밀번호 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,vo.getMember_PW_Aft());
            psmt.setString(2,vo.getMember_ID());
            psmt.setString(3, vo.getMember_PW_Bef());
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("회원 정보 변경 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    // UPDATE Input 데이터 받는 기능 -> 회원 전용 기능
    public static Member_VO memberUpdate_Input(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID 입력 : ");
        String member_ID = scanner.next();
        System.out.print("현재 PW 입력 : ");
        String member_PW_before = scanner.next();
        System.out.print("변경 PW 입력 : ");
        String member_PW_after = scanner.next();
        Member_VO vo = new Member_VO(member_ID, member_PW_before, member_PW_after);
        return vo;
    }
    public boolean member_Delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("삭제 회원 ID 입력 : ");
        String member_ID = scanner.next();
        System.out.print("삭제 회원 PW 입력 : ");
        String member_PW = scanner.next();
        if (member_Check(member_ID, member_PW)) {
            System.out.print("회원 탈퇴하시겠습니까? [1]예 [2]아니오 : ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    String sql = "DELETE FROM 회원 WHERE 아이디 = ?";
                    try {
                        conn = Common.getConnection();
                        psmt = conn.prepareStatement(sql);
                        psmt.setString(1, member_ID);
                        psmt.executeUpdate();
                        return true;
                    } catch (Exception e) {
                        System.out.println("회원 탈퇴 실패");
                        return false;
                    } finally {
                        Common.close(psmt);
                        Common.close(conn);
                    }
                case 2:
                    System.out.print("메뉴로 돌아갑니다.");
                    return false;
                default:
                    System.out.print("입력이 잘못되었습니다. 메뉴로 돌아갑니다.");
                    return false;
            }
        }
        else return false;
    }
    public boolean member_Check(String member_ID, String member_PW){
        this.member_ID = member_ID;
        this.member_PW = member_PW;
        boolean check = false;
        String sql = "SELECT COUNT(*) FROM 회원 WHERE 아이디 = ? AND 비밀번호 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, member_ID);
            psmt.setString(2, member_PW);
            rs = psmt.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1);
                    check = count > 0;
                }
            return check;
        }
        catch (Exception e){
            System.out.print("입력된 정보가 일치하지 않습니다.");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
}
