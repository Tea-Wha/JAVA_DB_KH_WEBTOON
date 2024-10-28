package dao;

import common.Common;
import vo.Board_VO;
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

public class Platform_DAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    // Scanner 기능이 필요할 때 켜짐(?)
    public void Platform_DAO_Scanner(){
        scanner = new Scanner(System.in);
        try{
            fileInputStream = new FileInputStream(".csv");
        }
        catch (FileNotFoundException e){
            System.out.println("파일을 찾지 못했습니다.");
        }
    }
    // 테이블 CREATE 기능 구현 (참조 기능 없음)
    public void platformCreate(){
        try{
            conn = Common.getConnection();
            stmt = null;
            String createTableSQL = "CREATE TABLE 플랫폼 ("+"플랫폼번호 INTEGER PRIMARY KEY,"+"플랫폼이름 VARCHAR2(30) NOT NULL UNIQUE CHECK (플랫폼이름 IN('네이버','카카오')))";
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
    // 플랫폼 기본값 입력 기능 구현
    public void platformDefault_Value(){
        try{
            conn = Common.getConnection();
            stmt = null;
            String insertTableSQL1 = "INSERT INTO 플랫폼 (플랫폼번호, 플랫폼이름) VALUES (0, '네이버')";
            String insertTableSQL2 = "INSERT INTO 플랫폼 (플랫폼번호, 플랫폼이름) VALUES (1, '카카오')";
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
    // 플랫폼 확인 기능
    public List<Platform_VO> platform_Select(){
        List<Platform_VO> list = new ArrayList<>();
        try{
            conn = Common.getConnection(); // 오라클 DB 연결
            stmt = conn.createStatement(); // statement 생성
            String query = "SELECT * FROM 플랫폼 ORDER BY 플랫폼번호"; // 플랫폼 유형 테이블 쿼리문 구성
            rs = stmt.executeQuery(query); // 쿼리문 실행
            while (rs.next()){
                int platform_Num = rs.getInt("플랫폼번호"); // 플랫폼번호 열 데이터 가져오기
                String platform_Name = rs.getString("플랫폼이름"); // 플랫폼이름 열 데이터 가져오기
                Platform_VO vo = new Platform_VO(platform_Num,platform_Name);
                list.add(vo); // 리스트 저장
            }
        }
        catch (Exception e){
            System.out.println("플랫폼 조회 실패");
        }
        finally{
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        }
        return list; // 리스트 반환    
    }
    // 화면 결과 출력 구현
    public void platformSelect_Result(List<Platform_VO> list){
        System.out.println("---------------------------------");
        System.out.println("            플랫폼 정보            ");
        System.out.println("---------------------------------");
        for(Platform_VO e : list){
            System.out.print("플랫폼 번호 : "+ e.getPlatform_Num()+" ");
            System.out.print("플랫폼 이름 : "+e.getPlatform_Name());
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
    // UPDATE 기능 구현 -> ADMIN 전용 기능
    // 플랫폼 번호 및 이름 변경 기능
    public boolean platform_Update(Platform_VO vo){
        String sql = "UPDATE 플랫폼 SET 플랫폼번호 = ?, 플랫폼이름 = ? WHERE 플랫폼이름 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,vo.getPlatform_Num());
            psmt.setString(2,vo.getPlatform_Name_Aft());
            psmt.setString(3, vo.getPlatform_Name_Bef());
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("플랫폼 UPDATE 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    // UPDATE Input 데이터 받는 기능 -> ADMIN 전용 기능
    public static Platform_VO platformUpdate_Input(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("변경할 플랫폼 이름 입력 : ");
        String platform_Name_before = scanner.next();
        System.out.print("변경 후 플랫폼 이름 입력 : ");
        String platform_Name_after = scanner.next();
        System.out.print("변경 후 플랫폼 번호 입력 : ");
        int platform_Num = scanner.nextInt();
        Platform_VO vo = new Platform_VO(platform_Num, platform_Name_before, platform_Name_after);
        return vo;
    }
    // INSERT 기능 구현 -> ADMIN 전용 기능
    // 플랫폼 번호 및 이름 추가 기능
    public boolean platform_Insert(Platform_VO vo){
        String sql = "INSERT INTO 플랫폼 (플랫폼번호, 플랫폼이름) VALUES (?,?)";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, vo.getPlatform_Num());
            psmt.setString(2, vo.getPlatform_Name());
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("플랫폼 INSERT 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    // INSERT Input 데이터 받는 기능 -> ADMIN 전용 기능
    public static Platform_VO platformInsert_Input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("플랫폼 정보 입력");
        System.out.print("플랫폼 번호 : ");
        int platform_Num = scanner.nextInt();
        System.out.print("플랫폼 이름 : ");
        String platform_Name = scanner.next();
        Platform_VO vo = new Platform_VO(platform_Num, platform_Name);
        return vo;
    }
    public boolean platform_Delete(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("삭제 플랫폼 이름 입력 : ");
        String platform_Name = scanner.next();
        String sql = "DELETE FROM 플랫폼 WHERE 플랫폼이름 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, platform_Name);
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("플랫폼 DELETE 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
}
