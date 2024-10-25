package common;

import java.security.spec.ECField;
import java.sql.*;
import java.sql.Connection;

// 1. DB 연결정보 생성

public class Common {
    // DB 연결 정보 생성
    final static String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final static String ORACLE_ID = "KHWEBTOON";
    final static String ORACLE_PW = "1234";
    final static String ORACLE_DRV = "oracle.jdbc.driver.OracleDriver";
    
    // 오라클 드라이버 로딩 및 연결
    // Connection은 java.sql 패키지에 포함된 인터페이스 -> 이 인터페이스를 통해 데이터베이스와 상호작용 가능
    // 데이터베이스에 연결되면 Connection 객체를 사용하여 SQL 쿼리를 실행하거나 트랜잭션 처리 등의 작업 수행 가능
    public static Connection getConnection(){
        Connection conn = null; // 초기값 null
        try{
            Class.forName(ORACLE_DRV);
            conn = DriverManager.getConnection(ORACLE_URL, ORACLE_ID, ORACLE_PW);
            // 연결 성공 시 conn 변수에는 null 이 아닌 Connection 객체 저장
//            System.out.println("오라클 DB 연결 성공");
        }
        catch (Exception e){
            System.out.println("오라클 DB 연결 실패");
        }
        return conn;
    }
    // Java의 메소드 시그니처 -> Connection conn : 매개변수 (호출 시 외부에서 전달되는 Connection 객체)
    public static void close(Connection conn){
        try{
            if (conn != null && !conn.isClosed()){ // isClosed() -> 현재 데이터베이스 연결이 닫혀있는지 여부
                // boolean 값 반환 / 데이터베이스 연결이 닫혀 있으면 true, 열려 있으면 false
                conn.close(); // conn.close() -> 연결 해제
//                System.out.println("Connection 해제 성공");
            }
        }
        catch (Exception e){
            System.out.println("Connection 해제 실패");
        }
    }
    // Java의 JDBC API에서 제공하는 인터페이스 -> SQL 쿼리를 실행하기 위해 사용 (java.sql 패키지에 포함)
    // SQL 쿼리(SELECT, INSERT, UPDATE, DELETE 등)를 데이터베이스 전달하고 그 결과를 처리하는 데 중요한 역할
    // PreparedStatement -> 성능과 보안 측면에서 좀 더 개선된 버전
    // Statement -> 정적 SQL 처리 / PreparedStatement -> 파라미터화된 SQL 쿼리 -> 변동이 필요한 값 ? 표현
    // 반환값 -> SQL 실행 결과로 ResultSet 또는 갱신된 행의 개수 반환
    public static void close(Statement stmt){
        try{
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
//                System.out.println("Statement 해제 성공");
            }
        }
        catch (Exception e){
            System.out.println("Statement 해제 실패");
        }
    }
    // ResultSet은 java.sql 패키지에 포함된 인터페이스 -> SQL 쿼리의 실행 결과를 테이블 형식으로 반환받는 객체
    // ResultSet은 데이터베이스에 대한 SELECT 쿼리의 결과를 표현 / 한 행씩 데이터를 순차적으로 읽는 데 사용
    // 쿼리 결과로 반환된 데이터를 탐색하고 처리하는데 사용 (주 역할) -> 결과 저장 / 탐색 및 처리
    // 반환값 -> SQL SELECT 쿼리의 결과 집합을 반환하여 탐색
    public static void close(ResultSet rset){
        try{
            if(rset != null && !rset.isClosed()){
                // ResultSet을 닫아야 하는 이유 : ResultSet 객체는 데이터베이스에서 리소스를 사용하기 때문에
                // 더 이상 필요하지 않으면 반드시 close() 메소드를 호출해 리소스 해제해야함 -> 메모리 누수 방지
                rset.close();
//                System.out.println("Result set 해제 성공");
            }
        }
        catch (Exception e){
            System.out.println("Result set 해제 실패");
        }
    }
    public static void commit(Connection conn){
        try{
            if(conn != null && !conn.isClosed()){
                conn.commit();
//                System.out.println("커밋 완료");
            }
        }
        catch (Exception e){
            System.out.println("커밋 실패");
        }
    }
    public static void rollback(Connection conn){
        try{
            if(conn != null && !conn.isClosed()){
                conn.rollback();
//                System.out.println("롤백 완료");
            }
        }
        catch (Exception e){
            System.out.println("롤백 실패");
        }
    }
}
