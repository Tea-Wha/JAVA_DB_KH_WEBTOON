package dao;

import common.Common;
import vo.Board_VO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 게시판 유형
// DAO(Database Access Object) : 데이터베이스에 접근하여 데이터를 조회하거나 수정하는 데 사용
public class Board_DAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    // Scanner 기능이 필요할 때 켜짐(?) -> 게시판 유형
    public void Board_DAO_Scanner(){
        scanner = new Scanner(System.in);
        try{
            fileInputStream = new FileInputStream(".csv");
        }
        catch (FileNotFoundException e){
            System.out.println("파일을 찾지 못했습니다.");
        }
    }
    // 테이블 CREATE 기능 구현 -> 게시판 유형
    public void boardCreate(){
        try{
            conn = Common.getConnection();
            stmt = null;
            String createTableSQL = "CREATE TABLE 게시판유형 ("+"게시판유형번호 INTEGER PRIMARY KEY, "+"게시판이름 VARCHAR2(20) NOT NULL UNIQUE CHECK (게시판이름 IN('공지','자유')))";
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
    // 게시판 기본값 입력 기능 구현
    public void boardDefault_Value(){
        try{
            conn = Common.getConnection();
            stmt = null;
            String insertTableSQL1 = "INSERT INTO 게시판유형 (게시판유형번호, 게시판이름) VALUES (0, '공지')";
            String insertTableSQL2 = "INSERT INTO 게시판유형 (게시판유형번호, 게시판이름) VALUES (1, '자유')";
            stmt = conn.createStatement();
            stmt.executeUpdate(insertTableSQL1);
            stmt.executeUpdate(insertTableSQL2);
            System.out.println("값 입력 성공");
        }
        catch (Exception e){
            System.out.println("값 입력 실패");
        }
        finally{
            Common.close(conn);
            Common.close(stmt);
        }
    }
    // SELECT(조회) 기능 구현
    // 게시판 유형 확인 기능
    public List<Board_VO> boardSelect(){
        List<Board_VO> list = new ArrayList<>();
        try{
            conn = Common.getConnection(); // 오라클 DB 연결
            stmt = conn.createStatement(); // statement 생성
            String query = "SELECT * FROM 게시판유형 ORDER BY 게시판유형번호"; // 게시판 유형 테이블 쿼리문 구성
            rs = stmt.executeQuery(query); // 쿼리문 실행
            while (rs.next()){
                int boardNum = rs.getInt("게시판유형번호"); // 게시판유형번호 열 데이터 가져오기
                String boardName = rs.getString("게시판이름"); // 게시판이름 열 데이터 가져오기
                Board_VO vo = new Board_VO(boardNum,boardName);
                list.add(vo); // 리스트 저장
            }
        }
        catch (Exception e){
            System.out.println("게시판 유형 조회 실패");
        }
        finally{
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        }
        return list; // 리스트 반환    
    }
    // 화면 결과 출력 구현
    public void boardSelect_Result(List<Board_VO> list){
        System.out.println("---------------------------------");
        System.out.println("            게시판 정보            ");
        System.out.println("---------------------------------");
        for(Board_VO e : list){
            System.out.print("게시판 번호 : "+ e.getBoardNum()+" ");
            System.out.print("게시판 이름 : "+e.getBoardName());
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
    // UPDATE 기능 구현 -> ADMIN 전용 기능
    // 게시판 유형 번호 및 이름 변경 기능
    public boolean boardUpdate(Board_VO vo){
        String sql = "UPDATE 게시판유형 SET 게시판유형번호 = ?, 게시판이름 = ? WHERE 게시판이름 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,vo.getBoardNum());
            psmt.setString(2,vo.getBoardName_Aft());
            psmt.setString(3, vo.getBoardName_Bef());
            psmt.executeUpdate();
            return true;
        } 
        catch (Exception e){
            System.out.println("게시판 UPDATE 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    // UPDATE Input 데이터 받는 기능 -> ADMIN 전용 기능
    public static Board_VO board_Update_Input(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("변경할 게시판 이름 입력 : ");
        String boardName_before = scanner.next();
        System.out.print("변경 후 게시판 이름 입력 : ");
        String boardName_after = scanner.next();
        System.out.print("변경 후 게시판 번호 입력 : ");
        int boardNum = scanner.nextInt();
        Board_VO vo = new Board_VO(boardNum, boardName_before, boardName_after);
        return vo;
    }
    // INSERT 기능 구현 -> ADMIN 전용 기능
    // 게시판 유형 번호 및 이름 추가 기능
    public boolean boardInsert(Board_VO vo){
        String sql = "INSERT INTO 게시판유형 (게시판유형번호, 게시판이름) VALUES (?,?)";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, vo.getBoardNum());
            psmt.setString(2, vo.getBoardName());
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("게시판 INSERT 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    // INSERT Input 데이터 받는 기능 -> ADMIN 전용 기능
    public static Board_VO board_Insert_Input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("게시판 정보 입력");
        System.out.print("게시판 번호 : ");
        int boardNum = scanner.nextInt();
        System.out.print("게시판 이름 : ");
        String boardName = scanner.next();
        Board_VO vo = new Board_VO(boardNum, boardName);
        return vo;
    }
    public boolean boardDelete(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("삭제 게시판 이름 입력 : ");
        String boardName = scanner.next();
        String sql = "DELETE FROM 게시판유형 WHERE 게시판이름 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, boardName);
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("게시판 DELETE 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
}
