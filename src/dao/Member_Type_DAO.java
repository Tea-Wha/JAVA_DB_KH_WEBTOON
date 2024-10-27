package dao;

import common.Common;
import vo.Board_VO;
import vo.Member_Type_VO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Member_Type_DAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    // Scanner 기능이 필요할 때 켜짐(?)
    public void Member_Type_DAO_Scanner(){
        scanner = new Scanner(System.in);
        try{
            fileInputStream = new FileInputStream(".csv");
        }
        catch (FileNotFoundException e){
            System.out.println("파일을 찾지 못했습니다.");
        }
    }
    // 테이블 CREATE 기능 구현 (참조 기능 없음)
    public void member_Type_Create(){
        try{
            conn = Common.getConnection();
            stmt = null;
            String createTableSQL = "CREATE TABLE 회원종류 ("+"회원종류번호 INTEGER PRIMARY KEY,"+"회원종류이름 VARCHAR2(50) NOT NULL UNIQUE CHECK (회원종류이름 IN('ADMIN','MEMBER')))";
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
    // SELECT(조회) 기능 구현
    // 회원 종류 확인 기능
    public List<Member_Type_VO> memberTypeSelect(){
        List<Member_Type_VO> list = new ArrayList<>();
        try{
            conn = Common.getConnection(); // 오라클 DB 연결
            stmt = conn.createStatement(); // statement 생성
            String query = "SELECT * FROM 회원종류 ORDER BY 회원종류번호"; // 회원 종류 테이블 쿼리문 구성
            rs = stmt.executeQuery(query); // 쿼리문 실행
            while (rs.next()){
                int member_Type_Num = rs.getInt("회원종류번호"); // 회원종류번호 열 데이터 가져오기
                String member_Type_Name = rs.getString("회원종류이름"); // 게시판이름 열 데이터 가져오기
                Member_Type_VO vo = new Member_Type_VO(member_Type_Num,member_Type_Name);
                list.add(vo); // 리스트 저장
            }
        }
        catch (Exception e){
            System.out.println("회원 종류 조회 실패");
        }
        finally{
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        }
        return list; // 리스트 반환
    }
    // 화면 결과 출력 구현
    public void memberTypeSelect_Result(List<Member_Type_VO> list){
        System.out.println("---------------------------------");
        System.out.println("           회원 유형 정보          ");
        System.out.println("---------------------------------");
        for(Member_Type_VO e : list){
            System.out.print("회원종류 번호 : "+ e.getMember_Type_Num()+" ");
            System.out.print("회원유형 이름 : "+e.getMember_Type_Name());
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
    // UPDATE 기능 구현 -> ADMIN 전용 기능
    // 회원 종류 번호 및 이름 변경 기능
    public boolean memberType_Update(Member_Type_VO vo){
        String sql = "UPDATE 회원종류 SET 회원종류번호 = ?, 회원종류이름 = ? WHERE 회원종류이름 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,vo.getMember_Type_Num());
            psmt.setString(2,vo.getMember_Type_Name_Aft());
            psmt.setString(3, vo.getMember_Type_Name_Bef());
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("회원 UPDATE 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    // UPDATE Input 데이터 받는 기능 -> ADMIN 전용 기능
    public static Member_Type_VO memberTypeUpdate_Input(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("변경할 회원종류 이름 입력 : ");
        String member_Type_Name_before = scanner.next();
        System.out.print("변경 후 회원종류 이름 입력 : ");
        String member_Type_Name_after = scanner.next();
        System.out.print("변경 후 회원종류 번호 입력 : ");
        int member_Type_Num = scanner.nextInt();
        Member_Type_VO vo = new Member_Type_VO(member_Type_Num,member_Type_Name_before,member_Type_Name_after);
        return vo;
    }
    // INSERT 기능 구현 -> ADMIN 전용 기능
    // 회원 종류 번호 및 이름 추가 기능
    public boolean memberType_Insert(Member_Type_VO vo){
        String sql = "INSERT INTO 회원종류 (회원종류번호, 회원종류이름) VALUES (?,?)";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, vo.getMember_Type_Num());
            psmt.setString(2, vo.getMember_Type_Name());
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("회원종류 INSERT 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    // INSERT Input 데이터 받는 기능 -> ADMIN 전용 기능
    public static Member_Type_VO memberTypeInsert_Input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("회원종류 정보 입력");
        System.out.print("회원종류 번호 : ");
        int member_Type_Num = scanner.nextInt();
        System.out.print("회원종류 이름 : ");
        String member_Type_Name = scanner.next();
        Member_Type_VO vo = new Member_Type_VO(member_Type_Num, member_Type_Name);
        return vo;
    }
    // DELETE 회원종류 삭제 기능 구현 -> ADMIN 전용 기능
    public boolean memberType_Delete(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("삭제 회원종류 이름 입력 : ");
        String member_Type_Name = scanner.next();
        String sql = "DELETE FROM 회원종류 WHERE 회원종류이름 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, member_Type_Name);
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("회원종류 DELETE 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
}
