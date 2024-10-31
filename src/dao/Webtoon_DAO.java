package dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import common.Common;
import vo.Webtoon_VO;

import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
            String createTableSQL = "CREATE TABLE 웹툰 ("+"웹툰번호 INTEGER PRIMARY KEY,"+"제목 VARCHAR2(500) NOT NULL,"+"대표작가 VARCHAR2(500) NOT NULL,"+"장르번호 INTEGER NOT NULL,"+"이용가능연령 NUMBER(2) NOT NULL,"+"평점 NUMBER(4,2) NOT NULL CHECK (평점 BETWEEN 0.00 AND 10.00),"+"구독자수 NUMBER,"+"조회수 NUMBER,"+"연재요일 NUMBER(1) NOT NULL CHECK (연재요일 BETWEEN 1 AND 7),"+"웹툰페이지URL VARCHAR2(255) NOT NULL,"+"썸네일URL VARCHAR2(255) NOT NULL,"+"플랫폼번호 INTEGER NOT NULL,"+"FOREIGN KEY (장르번호) REFERENCES 장르 (장르번호),"+"FOREIGN KEY (플랫폼번호) REFERENCES 플랫폼 (플랫폼번호))";
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
    public void insertDataFromCSV(String filePath) {
        String insertSQL = "INSERT INTO 웹툰 (제목, 대표작가, 장르번호, 이용가능연령, 평점, 구독자수, 조회수, 연재요일, 웹툰페이지URL, 썸네일URL, 플랫폼번호) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath),"CP949"));
            conn = Common.getConnection();
            psmt = conn.prepareStatement(insertSQL);
            String[] line;
            reader.readNext();

            while ((line = reader.readNext()) != null){
                psmt.setString(1, line[0] != null ? line[0].trim() : "");
                psmt.setString(2, line[1] != null ? line[1].trim() : "");
                psmt.setInt(3,parseIntOrDefault(line[2].trim(),0));
                psmt.setInt(4,parseIntOrDefault(line[3].trim(),0));
                psmt.setBigDecimal(5,parseBigDecimalOrDefault(line[4], BigDecimal.ZERO));
                psmt.setBigDecimal(6,parseBigDecimalOrDefault(line[5],BigDecimal.ZERO));
                psmt.setBigDecimal(7,parseBigDecimalOrDefault(line[6],BigDecimal.ZERO));
                psmt.setInt(8,parseIntOrDefault(line[7],0));
                psmt.setString(9,line[8] != null ? line[8].trim() : "");
                psmt.setString(10,line[9] != null ? line[9].trim() : "");
                psmt.setInt(11,parseIntOrDefault(line[10],0));

                psmt.addBatch();
            }
            psmt.executeBatch();
            System.out.println("데이터 삽입 완료");
        }
        catch (FileNotFoundException | SQLException e) {
            System.out.println("CSV 파일을 찾을 수 없습니다 : " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
    private static int parseIntOrDefault (String value, int defaultValue){
        try{
            return (value == null || value.trim().isEmpty() ? defaultValue : Integer.parseInt(value.trim()));
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }
    private static BigDecimal parseBigDecimalOrDefault (String value, BigDecimal defaultValue){
        try{
            return (value == null || value.trim().isEmpty()) ? defaultValue : new BigDecimal(value.trim());
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }

}
