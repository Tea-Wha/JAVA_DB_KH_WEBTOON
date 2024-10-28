package vo;

// 대표장르 테이블 //
// VO(Value Object) : 데이터베이스에서 가져온 레코드(튜플)를 자바 객체로 매핑하는데 사용
public class Genre_VO {
    private int genre_Num;
    // 대표장르번호 / INTEGER / PRIMARY KEY
    private String genre_Name;
    // 대표장르이름 / VARCHAR2(30) / NOT NULL, UNIQUE
    private String genre_Name_Bef;
    private String genre_Name_Aft;

    public Genre_VO(int genre_Num, String genre_Name) {
        this.genre_Num = genre_Num;
        this.genre_Name = genre_Name;
    }
    public Genre_VO(String genre_Name_Bef, String genre_Name_Aft) {
        this.genre_Name_Bef = genre_Name_Bef;
        this.genre_Name_Aft = genre_Name_Aft;
    }
    public Genre_VO(String genre_Name) {
        this.genre_Name = genre_Name;
    }
    public int getGenre_Num() {
        return genre_Num;
    }
    public void setGenre_Num(int genre_Num) {
        this.genre_Num = genre_Num;
    }
    public String getGenre_Name() {
        return genre_Name;
    }
    public void setGenre_Name(String genre_Name) {
        this.genre_Name = genre_Name;
    }
    public String getGenre_Name_Bef() {
        return genre_Name_Bef;
    }
    public void setGenre_Name_Bef(String genre_Name_Bef) {
        this.genre_Name_Bef = genre_Name_Bef;
    }
    public String getGenre_Name_Aft() {
        return genre_Name_Aft;
    }
    public void setGenre_Name_Aft(String genre_Name_Aft) {
        this.genre_Name_Aft = genre_Name_Aft;
    }
}
