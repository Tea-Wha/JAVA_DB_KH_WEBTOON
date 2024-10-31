package controller;

import java.util.Scanner;

public class Webtoon_Controller {
    private static boolean isDay =  false;
    private static boolean isGenre = false;
    private static boolean isDayGenre = false;
    private static boolean isRecommend = false;

    public static void webtoon_None_Member_Start() {
        Top_Controller controller = new Top_Controller();
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== KH WEBTOON 웹툰 페이지(비회원) =======");
        System.out.println("[1]웹툰 조회 [2]웹툰 검색 [3]웹툰 추천 [4]뒤로 가기 [5] 종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("====== 웹툰 정렬 방식 ======");
                System.out.println("[1]요일별 웹툰 조회 [2]장르별 웹툰 조회 [3]요일+장르별 웹툰 조회 [4]전체 조회");
                System.out.print("방식 선택 : ");
                int select = scanner.nextInt();
                switch (select){
                    case 1:
                        isDay = true;
                        while (isDay){
                            Webtoon_Day_Controller.webtoon_Day_Start();
                        }
                        break;
                    case 2:
                        isGenre = true;
                        while (isGenre){
                            Webtoon_Genre_Controller.webtoon_Genre_Start();
                        }
                        break;
                    case 3:
                        isDayGenre = true;
                        while (isDayGenre){
                            Webtoon_Day_Genre_Controller.webtoon_Day_Genre_Start();
                        }
                        break;
                    case 4:
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                System.out.println("웹툰 검색 : ");
                break;
            case 3:
                System.out.println("추천 웹툰 이동 중 ...");
                for (int i = 1; i <= 100; i++){
                    System.out.print("\r" + i + "%");
                    try{
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e){
                        System.out.println("로딩 오류 ...");
                    }
                }
                isRecommend = true;
                while (isRecommend){
                    Webtoon_Recommend_Controller.webtoon_Recommend_None_Member_Start();
                }
                break;
            case 4:
                controller.setWebtoonIn(false);
                break;
            case 5:
                System.exit(0);
                break;
            default:
                break;
        }
    }
    public static void webtoon_Member_Start() {
        Top_Controller controller = new Top_Controller();
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== KH WEBTOON 웹툰 페이지(회원) =======");
        System.out.println("[1]웹툰 조회 [2]웹툰 검색 [3]맞춤형 웹툰 추천 [4]선호 장르 설정 [5]웹툰 즐겨찾기 설정 [6]뒤로 가기 [7] 종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("====== 웹툰 정렬 방식 ======");
                System.out.println("[1]요일별 웹툰 조회 [2]장르별 웹툰 조회 [3]요일+장르별 웹툰 조회 [4]전체 조회");
                System.out.print("방식 선택 : ");
                int select = scanner.nextInt();
                switch (select){
                    case 1:
                        isDay = true;
                        while (isDay){
                            Webtoon_Day_Controller.webtoon_Day_Start();
                        }
                        break;
                    case 2:
                        isGenre = true;
                        while (isGenre){
                            Webtoon_Genre_Controller.webtoon_Genre_Start();
                        }
                        break;
                    case 3:
                        isDayGenre = true;
                        while (isDayGenre){
                            Webtoon_Day_Genre_Controller.webtoon_Day_Genre_Start();
                        }
                        break;
                    case 4:
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                System.out.println("웹툰 검색 : ");
                break;
            case 3:
                System.out.println("맞춤형 추천 웹툰 이동 중 ...");
                for (int i = 1; i <= 100; i++){
                    System.out.print("\r" + i + "%");
                    try{
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e){
                        System.out.println("로딩 오류 ...");
                    }
                }
                isRecommend = true;
                while (isRecommend){
                    Webtoon_Recommend_Controller.webtoon_Recommend_Member_Start();
                }
                break;
            case 4:
                System.out.println("선호 장르 설정");
                break;
            case 5:
                System.out.println("즐겨찾기 설정");
                break;
            case 6:
                controller.setWebtoonIn(false);
                break;
            case 7:
                System.exit(0);
                break;
            default:
                break;
        }
    }
    public static boolean isIsDay() {
        return isDay;
    }
    public static void setIsDay(boolean isDay) {
        Webtoon_Controller.isDay = isDay;
    }
    public static boolean isIsGenre() {
        return isGenre;
    }
    public static void setIsGenre(boolean isGenre) {
        Webtoon_Controller.isGenre = isGenre;
    }
    public static boolean isIsDayGenre() {
        return isDayGenre;
    }
    public static void setIsDayGenre(boolean isDayGenre) {
        Webtoon_Controller.isDayGenre = isDayGenre;
    }
    public static boolean isIsRecommend() {
        return isRecommend;
    }
    public static void setIsRecommend(boolean isRecommend) {
        Webtoon_Controller.isRecommend = isRecommend;
    }
}
