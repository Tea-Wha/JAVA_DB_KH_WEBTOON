package dao;

import common.Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.*;
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
    public void insertDataFromCSV(String filePath) {
        String insertSQL = "INSERT INTO 웹툰 (제목, 대표작가, 장르번호, 이용가능연령, 평점, 구독자수, 조회수, 연재요일, 웹툰페이지URL, 썸네일URL, 플랫폼번호) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Common.getConnection();
             PreparedStatement psmt = conn.prepareStatement(insertSQL);
             Scanner scanner = new Scanner(new File(filePath))) {

            conn.setAutoCommit(false);  // 트랜잭션을 수동으로 설정

            System.out.println("CSV 파일을 읽는 중...");
            if (scanner.hasNextLine()) {
                scanner.nextLine();  // 첫 줄(헤더) 건너뜀
            }

            int rowCount = 0;  // 삽입된 행 수를 카운트

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                // 디버그 로그 출력
                System.out.println("현재 줄: " + line);

                // 데이터 설정
                psmt.setString(1, data[0].trim());  // 제목
                psmt.setString(2, data[1].trim());  // 대표작가
                psmt.setInt(3, Integer.parseInt(data[2].trim()));  // 장르번호
                psmt.setInt(4, Integer.parseInt(data[3].trim()));  // 이용가능연령
                psmt.setBigDecimal(5, new BigDecimal(data[4].trim()));  // 평점
                psmt.setInt(6, Integer.parseInt(data[5].trim()));  // 구독자수
                psmt.setInt(7, Integer.parseInt(data[6].trim()));  // 조회수
                psmt.setInt(8, Integer.parseInt(data[7].trim()));  // 연재요일
                psmt.setString(9, data[8].trim());  // 웹툰페이지URL
                psmt.setString(10, data[9].trim());  // 썸네일URL
                psmt.setInt(11, Integer.parseInt(data[10].trim()));  // 플랫폼번호

                psmt.addBatch();  // 배치에 추가
                rowCount++;  // 행 수 증가
                System.out.println("배치에 추가 완료, 현재 행 수: " + rowCount);
            }

            // 배치 실행 및 커밋
            if (rowCount > 0) {
                int[] result = psmt.executeBatch();  // 배치 실행
                conn.commit();  // 커밋
                System.out.println("CSV 데이터 삽입 완료, 총 삽입된 행 수: " + result.length);
            } else {
                System.out.println("CSV 파일에서 삽입할 데이터가 없습니다.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("CSV 파일을 찾을 수 없습니다: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("데이터베이스 오류: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("데이터 형식 오류: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("CSV 파일의 열 개수와 예상하는 열 개수가 맞지 않습니다: " + e.getMessage());
        }
    }

}
