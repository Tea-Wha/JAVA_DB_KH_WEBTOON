package dao;

import common.Common;
import vo.Genre_VO;
import vo.Platform_VO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Genre_DAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    // Scanner 기능이 필요할 때 켜짐(?)
    public void Genre_DAO_Scanner(){
        scanner = new Scanner(System.in);
        try{
            fileInputStream = new FileInputStream(".csv");
        }
        catch (FileNotFoundException e){
            System.out.println("파일을 찾지 못했습니다.");
        }
    }
    // 테이블 CREATE 기능 구현 (참조 기능 없음)
    public void genreCreate(){
        try{
            conn = Common.getConnection();
            stmt = null;
            String createTableSQL = "CREATE TABLE 장르 ("+"장르번호 INTEGER PRIMARY KEY,"+"장르이름 VARCHAR2(30) NOT NULL UNIQUE)";
            String createSequenceSQL = "CREATE SEQUENCE 장르번호_SEQ "+" START WITH 1 "+" INCREMENT BY 1 "+" NOCACHE "+" NOCYCLE";
            String createTriggerSQL = "CREATE TRIGGER 장르_트리거 "+" BEFORE INSERT ON 장르 "+" FOR EACH ROW "+" BEGIN "+" :NEW.장르번호 := 장르번호_SEQ.NEXTVAL; "+" END;";
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
    // SELECT(조회) 기능 구현
    // 장르 확인 기능
    public List<Genre_VO> genre_Select(){
        List<Genre_VO> list = new ArrayList<>();
        try{
            conn = Common.getConnection(); // 오라클 DB 연결
            stmt = conn.createStatement(); // statement 생성
            String query = "SELECT * FROM 장르 ORDER BY 장르번호"; // 장르 유형 테이블 쿼리문 구성
            rs = stmt.executeQuery(query); // 쿼리문 실행
            while (rs.next()){
                int genre_Num = rs.getInt("장르번호"); // 장르번호 열 데이터 가져오기
                String genre_Name = rs.getString("장르이름"); // 장르이름 열 데이터 가져오기
                Genre_VO vo = new Genre_VO(genre_Num,genre_Name);
                list.add(vo); // 리스트 저장
            }
        }
        catch (Exception e){
            System.out.println("장르 조회 실패");
        }
        finally{
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        }
        return list; // 리스트 반환    
    }
    // 화면 결과 출력 구현
    public void genreSelect_Result(List<Genre_VO> list){
        System.out.println("---------------------------------");
        System.out.println("             장르 정보             ");
        System.out.println("---------------------------------");
        for(Genre_VO e : list){
            System.out.print("장르 번호 : "+ e.getGenre_Num()+" ");
            System.out.print("장르 이름 : "+e.getGenre_Name());
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
    // UPDATE 기능 구현 -> ADMIN 전용 기능
    // 장르 이름 변경 기능
    public boolean genre_Update(Genre_VO vo){
        String sql = "UPDATE 장르 SET 장르이름 = ? WHERE 장르이름 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,vo.getGenre_Name_Aft());
            psmt.setString(2, vo.getGenre_Name_Bef());
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("장르 UPDATE 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    // UPDATE Input 데이터 받는 기능 -> ADMIN 전용 기능
    public static Genre_VO genreUpdate_Input(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("변경할 장르 이름 입력 : ");
        String genre_Name_before = scanner.next();
        System.out.print("변경 후 장르 이름 입력 : ");
        String genre_Name_after = scanner.next();
        Genre_VO vo = new Genre_VO(genre_Name_before, genre_Name_after);
        return vo;
    }
    // INSERT 기능 구현 -> ADMIN 전용 기능
    // 장르 번호 및 이름 추가 기능
    public boolean genre_Insert(Genre_VO vo){
        String sql = "INSERT INTO 장르 (장르이름) VALUES (?)";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, vo.getGenre_Name());
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("장르 INSERT 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    // INSERT Input 데이터 받는 기능 -> ADMIN 전용 기능
    public static Genre_VO genreInsert_Input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("장르 정보 입력");
        System.out.print("장르 이름 : ");
        String genre_Name = scanner.next();
        Genre_VO vo = new Genre_VO(genre_Name);
        return vo;
    }
    // DELETE -> ADMIN 전용 기능
    public boolean genre_Delete(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("삭제 장르 이름 입력 : ");
        String genre_Name = scanner.next();
        String sql = "DELETE FROM 장르 WHERE 장르이름 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, genre_Name);
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("장르 DELETE 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
}
