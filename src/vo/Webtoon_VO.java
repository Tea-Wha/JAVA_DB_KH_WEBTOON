package vo;

import java.math.BigDecimal;

public class Webtoon_VO {
    private int webtoon_Num;
    // 웹툰번호 / INTEGER / PRIMARY KEY
    private String webtoon_Name;
    // 제목 / VARCHAR2(100) / NOT NULL
    private String webtoon_Author;
    // 대표작가 / VARCHAR2(100) / NOT NULL
    private int genre_Num;
    // 대표장르번호 / INTEGER / FOREIGN KEY REFERENCES genre(genre_Num), NOT NULL
    private int webtoon_Ava_Age;
    // 이용가능연령 / NUMBER(2) / 0 -> ALL, 1 -> 12세, 2 -> 15세, 3 -> 18세, NOT NULL
    private BigDecimal webtoon_Rating;
    // 평점 / NUMBER(4,1) / CHECK webtoon_Rating BETWEEN 0.0 AND 10.0, NOT NULL
    private int webtoon_Sub_Num;
    // 구독자수 / INTEGER / Default -> 0
    private int webtoon_View_Num;
    // 조회수 / INTEGER / Default -> 0
    private int webtoon_Rel_Day;
    // 연재요일 / NUMBER(1) / CHECK webtoon_Rel_day BETWEEN 1 AND 7, NOT NULL
    private String webtoon_Page_Url;
    // 웹툰페이지 URL / VARCHAR2(255) / NOT NULL
    private String webtoon_Thu_Url;
    // 썸네일 URL / VARCHAR2(255) / NOT NULL
    private int platform_Num;
    // 플랫폼 번호 / INTEGER / FK REFERENCES platform(platform_Num), NOT NULL

    public Webtoon_VO(int webtoon_Num, String webtoon_Name, String webtoon_Author, int genre_Num, int webtoon_Ava_Age, BigDecimal webtoon_Rating, int webtoon_Sub_Num, int webtoon_View_Num, int webtoon_Rel_Day, String webtoon_Page_Url, String webtoon_Thu_Url, int platform_Num) {
        this.webtoon_Num = webtoon_Num;
        this.webtoon_Name = webtoon_Name;
        this.webtoon_Author = webtoon_Author;
        this.genre_Num = genre_Num;
        this.webtoon_Ava_Age = webtoon_Ava_Age;
        this.webtoon_Rating = webtoon_Rating;
        this.webtoon_Sub_Num = webtoon_Sub_Num;
        this.webtoon_View_Num = webtoon_View_Num;
        this.webtoon_Rel_Day = webtoon_Rel_Day;
        this.webtoon_Page_Url = webtoon_Page_Url;
        this.webtoon_Thu_Url = webtoon_Thu_Url;
        this.platform_Num = platform_Num;
    }

    public int getWebtoon_Num() {
        return webtoon_Num;
    }
    public void setWebtoon_Num(int webtoon_Num) {
        this.webtoon_Num = webtoon_Num;
    }
    public String getWebtoon_Name() {
        return webtoon_Name;
    }
    public void setWebtoon_Name(String webtoon_Name) {
        this.webtoon_Name = webtoon_Name;
    }
    public String getWebtoon_Author() {
        return webtoon_Author;
    }
    public void setWebtoon_Author(String webtoon_Author) {
        this.webtoon_Author = webtoon_Author;
    }
    public int getGenre_Num() {
        return genre_Num;
    }
    public void setGenre_Num(int genre_Num) {
        this.genre_Num = genre_Num;
    }
    public int getWebtoon_Ava_Age() {
        return webtoon_Ava_Age;
    }
    public void setWebtoon_Ava_Age(int webtoon_Ava_Age) {
        this.webtoon_Ava_Age = webtoon_Ava_Age;
    }
    public BigDecimal getWebtoon_Rating() {
        return webtoon_Rating;
    }
    public void setWebtoon_Rating(BigDecimal webtoon_Rating) {
        this.webtoon_Rating = webtoon_Rating;
    }
    public int getWebtoon_Sub_Num() {
        return webtoon_Sub_Num;
    }
    public void setWebtoon_Sub_Num(int webtoon_Sub_Num) {
        this.webtoon_Sub_Num = webtoon_Sub_Num;
    }
    public int getWebtoon_View_Num() {
        return webtoon_View_Num;
    }
    public void setWebtoon_View_Num(int webtoon_View_Num) {
        this.webtoon_View_Num = webtoon_View_Num;
    }
    public int getWebtoon_Rel_Day() {
        return webtoon_Rel_Day;
    }
    public void setWebtoon_Rel_Day(int webtoon_Rel_Day) {
        this.webtoon_Rel_Day = webtoon_Rel_Day;
    }
    public String getWebtoon_Page_Url() {
        return webtoon_Page_Url;
    }
    public void setWebtoon_Page_Url(String webtoon_Page_Url) {
        this.webtoon_Page_Url = webtoon_Page_Url;
    }
    public String getWebtoon_Thu_Url() {
        return webtoon_Thu_Url;
    }
    public void setWebtoon_Thu_Url(String webtoon_Thu_Url) {
        this.webtoon_Thu_Url = webtoon_Thu_Url;
    }
    public int getPlatform_Num() {
        return platform_Num;
    }
    public void setPlatform_Num(int platform_Num) {
        this.platform_Num = platform_Num;
    }
}
