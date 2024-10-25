package vo;

// 회원종류 테이블 //
// VO(Value Object) : 데이터베이스에서 가져온 레코드(튜플)를 자바 객체로 매핑하는데 사용
public class Member_Type_VO {
    private int member_Type_Num;
    // 회원종류번호 / INTEGER / PRIMARY KEY
    private String member_Type_Name;
    // 회원종류이름 / VARCHAR2(50) / UNIQUE, NOT NULL,
    // CHECK IN ('admin', 'member')

    public Member_Type_VO(int member_Type_Num, String member_Type_Name) {
        this.member_Type_Num = member_Type_Num;
        this.member_Type_Name = member_Type_Name;
    }
    public int getMember_Type_Num() {
        return member_Type_Num;
    }
    public void setMember_Type_Num(int member_Type_Num) {
        this.member_Type_Num = member_Type_Num;
    }
    public String getMember_Type_Name() {
        return member_Type_Name;
    }
    public void setMember_Type_Name(String member_Type_Name) {
        this.member_Type_Name = member_Type_Name;
    }
}
