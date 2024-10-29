package controller;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Webtoon_Day_Genre_Controller {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    public static void webtoon_Day_Genre_Start() {
        Top_Controller controller = new Top_Controller();
        Scanner scanner = new Scanner(System.in);
        int choice_Day;
        int choice_Genre = 0;

        System.out.println("====== KH WEBTOON 요일 + 장르별 웹툰 =======");
        System.out.println("[1]월요일 웹툰 [2]화요일 웹툰 [3]수요일 웹툰 [4]목요일 웹툰 [5]금요일 웹툰 [6]토요일 웹툰 [7]일요일 웹툰 [8]뒤로 가기 [9]메인페이지 이동 [10]종료");
        System.out.print("요일 선택 : ");
        choice_Day = scanner.nextInt();
        if (choice_Day <=7 && choice_Day >0){
            System.out.println("[1]드라마 [2]로맨스 [3]무협 [4]액션 [5]판타지 [6]기타 [7]뒤로 가기 [8]메인페이지 이동 [9]종료");
            System.out.print("장르 선택 : ");
            choice_Genre = scanner.nextInt();
        }
        switch (choice_Day) {
            case 1:
                switch (choice_Genre){
                    case 1: // 월요일 + 드라마
                        System.out.println("====== 월요일 + 드라마 웹툰 =======");
                        break;
                    case 2: // 월요일 + 로맨스
                        System.out.println("====== 월요일 + 로맨스 웹툰 =======");
                        break;
                    case 3: // 월요일 + 무협
                        System.out.println("====== 월요일 + 무협 웹툰 =======");
                        break;
                    case 4: // 월요일 + 액션
                        System.out.println("====== 월요일 + 액션 웹툰 =======");
                        break;
                    case 5: // 월요일 + 판타지
                        System.out.println("====== 월요일 + 판타지 웹툰 =======");
                        break;
                    case 6: // 월요일 + 기타
                        System.out.println("====== 월요일 + 기타 웹툰 =======");
                        break;
                    case 7:
                        Webtoon_Controller.setIsDayGenre(false);
                        break;
                    case 8:
                        Webtoon_Controller.setIsDayGenre(false);
                        controller.setWebtoonIn(false);
                        break;
                    case 9:
                        System.exit(0);
                        break;
                }
                break;
            case 2:
                switch (choice_Genre){
                    case 1: // 화요일 + 드라마
                        System.out.println("====== 화요일 + 드라마 웹툰 =======");
                        break;
                    case 2: // 화요일 + 로맨스
                        System.out.println("====== 화요일 + 로맨스 웹툰 =======");
                        break;
                    case 3: // 화요일 + 무협
                        System.out.println("====== 화요일 + 무협 웹툰 =======");
                        break;
                    case 4: // 화요일 + 액션
                        System.out.println("====== 화요일 + 액션 웹툰 =======");
                        break;
                    case 5: // 화요일 + 판타지
                        System.out.println("====== 화요일 + 판타지 웹툰 =======");
                        break;
                    case 6: // 화요일 + 기타
                        System.out.println("====== 화요일 + 기타 웹툰 =======");
                        break;
                    case 7:
                        Webtoon_Controller.setIsDayGenre(false);
                        break;
                    case 8:
                        Webtoon_Controller.setIsDayGenre(false);
                        controller.setWebtoonIn(false);
                        break;
                    case 9:
                        System.exit(0);
                        break;
                }
                break;
            case 3:
                switch (choice_Genre){
                    case 1: // 수요일 + 드라마
                        System.out.println("====== 수요일 + 드라마 웹툰 =======");
                        break;
                    case 2: // 수요일 + 로맨스
                        System.out.println("====== 수요일 + 로맨스 웹툰 =======");
                        break;
                    case 3: // 수요일 + 무협
                        System.out.println("====== 수요일 + 무협 웹툰 =======");
                        break;
                    case 4: // 수요일 + 액션
                        System.out.println("====== 수요일 + 액션 웹툰 =======");
                        break;
                    case 5: // 수요일 + 판타지
                        System.out.println("====== 수요일 + 판타지 웹툰 =======");
                        break;
                    case 6: // 수요일 + 기타
                        System.out.println("====== 수요일 + 기타 웹툰 =======");
                        break;
                    case 7:
                        Webtoon_Controller.setIsDayGenre(false);
                        break;
                    case 8:
                        Webtoon_Controller.setIsDayGenre(false);
                        controller.setWebtoonIn(false);
                        break;
                    case 9:
                        System.exit(0);
                        break;
                }
                break;
            case 4:
                switch (choice_Genre){
                    case 1: // 목요일 + 드라마
                        System.out.println("====== 목요일 + 드라마 웹툰 =======");
                        break;
                    case 2: // 목요일 + 로맨스
                        System.out.println("====== 목요일 + 로맨스 웹툰 =======");
                        break;
                    case 3: // 목요일 + 무협
                        System.out.println("====== 목요일 + 무협 웹툰 =======");
                        break;
                    case 4: // 목요일 + 액션
                        System.out.println("====== 목요일 + 액션 웹툰 =======");
                        break;
                    case 5: // 목요일 + 판타지
                        System.out.println("====== 목요일 + 판타지 웹툰 =======");
                        break;
                    case 6: // 목요일 + 기타
                        System.out.println("====== 목요일 + 기타 웹툰 =======");
                        break;
                    case 7:
                        Webtoon_Controller.setIsDayGenre(false);
                        break;
                    case 8:
                        Webtoon_Controller.setIsDayGenre(false);
                        controller.setWebtoonIn(false);
                        break;
                    case 9:
                        System.exit(0);
                        break;
                }
                break;
            case 5:
                switch (choice_Genre){
                    case 1: // 금요일 + 드라마
                        System.out.println("====== 금요일 + 드라마 웹툰 =======");
                        break;
                    case 2: // 금요일 + 로맨스
                        System.out.println("====== 금요일 + 로맨스 웹툰 =======");
                        break;
                    case 3: // 금요일 + 무협
                        System.out.println("====== 금요일 + 무협 웹툰 =======");
                        break;
                    case 4: // 금요일 + 액션
                        System.out.println("====== 금요일 + 액션 웹툰 =======");
                        break;
                    case 5: // 금요일 + 판타지
                        System.out.println("====== 금요일 + 판타지 웹툰 =======");
                        break;
                    case 6: // 금요일 + 기타
                        System.out.println("====== 금요일 + 기타 웹툰 =======");
                        break;
                    case 7:
                        Webtoon_Controller.setIsDayGenre(false);
                        break;
                    case 8:
                        Webtoon_Controller.setIsDayGenre(false);
                        controller.setWebtoonIn(false);
                        break;
                    case 9:
                        System.exit(0);
                        break;
                }
                break;
            case 6:
                switch (choice_Genre){
                    case 1: // 토요일 + 드라마
                        System.out.println("====== 토요일 + 드라마 웹툰 =======");
                        break;
                    case 2: // 토요일 + 로맨스
                        System.out.println("====== 토요일 + 로맨스 웹툰 =======");
                        break;
                    case 3: // 토요일 + 무협
                        System.out.println("====== 토요일 + 무협 웹툰 =======");
                        break;
                    case 4: // 토요일 + 액션
                        System.out.println("====== 토요일 + 액션 웹툰 =======");
                        break;
                    case 5: // 토요일 + 판타지
                        System.out.println("====== 토요일 + 판타지 웹툰 =======");
                        break;
                    case 6: // 토요일 + 기타
                        System.out.println("====== 토요일 + 기타 웹툰 =======");
                        break;
                    case 7:
                        Webtoon_Controller.setIsDayGenre(false);
                        break;
                    case 8:
                        Webtoon_Controller.setIsDayGenre(false);
                        controller.setWebtoonIn(false);
                        break;
                    case 9:
                        System.exit(0);
                        break;
                }
                break;
            case 7:
                switch (choice_Genre){
                    case 1: // 일요일 + 드라마
                        System.out.println("====== 일요일 + 드라마 웹툰 =======");
                        break;
                    case 2: // 일요일 + 로맨스
                        System.out.println("====== 일요일 + 로맨스 웹툰 =======");
                        break;
                    case 3: // 일요일 + 무협
                        System.out.println("====== 일요일 + 무협 웹툰 =======");
                        break;
                    case 4: // 일요일 + 액션
                        System.out.println("====== 일요일 + 액션 웹툰 =======");
                        break;
                    case 5: // 일요일 + 판타지
                        System.out.println("====== 일요일 + 판타지 웹툰 =======");
                        break;
                    case 6: // 일요일 + 기타
                        System.out.println("====== 일요일 + 기타 웹툰 =======");
                        break;
                    case 7:
                        Webtoon_Controller.setIsDayGenre(false);
                        break;
                    case 8:
                        Webtoon_Controller.setIsDayGenre(false);
                        controller.setWebtoonIn(false);
                        break;
                    case 9:
                        System.exit(0);
                        break;
                }
                break;
            case 8:
                Webtoon_Controller.setIsDayGenre(false);
                break;
            case 9:
                Webtoon_Controller.setIsDayGenre(false);
                controller.setWebtoonIn(false);
                break;
            case 10:
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
