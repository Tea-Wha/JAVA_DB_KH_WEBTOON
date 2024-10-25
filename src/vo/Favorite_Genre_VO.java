package vo;

// 선호 장르 테이블 //
// VO(Value Object) : 데이터베이스에서 가져온 레코드(튜플)를 자바 객체로 매핑하는데 사용
public class Favorite_Genre_VO {
    private int member_Num;
    // 회원번호 / INTEGER / PRIMARY KEY, FOREIGN KEY
    private int genre_Num;
    // 대표장르번호 / INTEGER / PRIMARY KEY, FOREIGN KEY

    public Favorite_Genre_VO(int member_Num, int genre_Num) {
        this.member_Num = member_Num;
        this.genre_Num = genre_Num;
    }
    public int getMember_Num() {
        return member_Num;
    }
    public void setMember_Num(int member_Num) {
        this.member_Num = member_Num;
    }
    public int getGenre_Num() {
        return genre_Num;
    }
    public void setGenre_Num(int genre_Num) {
        this.genre_Num = genre_Num;
    }
}
