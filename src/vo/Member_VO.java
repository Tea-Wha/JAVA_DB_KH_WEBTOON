package vo;

import java.sql.Date;
// 회원 테이블 //
// VO(Value Object) : 데이터베이스에서 가져온 레코드(튜플)를 자바 객체로 매핑하는데 사용
public class Member_VO {
    private int member_Num;
    // 회원번호 / INTEGER / PRIMARY KEY
    private String member_ID;
    // 아이디 / VARCHAR2(50) / UNIQUE, NOT NULL
    private String member_PW;
    // 비밀번호 / VARCHAR2(20) / NOT NULL,
    // CHECK LENGTH(member_PW) BETWEEN 8 AND 20
    private String member_Email;
    // 이메일 / VARCHAR2(50) / UNIQUE, NOT NULL
    // CHECK member_Email Like '%@%'
    private Date member_Birth_Date;
    // 생년월일 / DATE / NOT NULL
    private String member_Nickname;
    // 닉네임 / VARCHAR2(20) / UNIQUE, NOT NULL
    // CHECK LENGTH(member_Nickname) BETWEEN 1 AND 20
    private Date member_Reg_Date;
    // 가입일 / DATE / NOT NULL
    private int member_Exist;
    // 탈퇴여부 / NUMBER(1) / NOT NULL, DEFAULT 0, CHECK member_Exist IN (0,1)
    private int member_Type_Num;
    // 회원종류번호 / INTEGER / NOT NULL,
    // FOREIGN KEY REFERENCES member_Type(member_Type_Num)

    public Member_VO(int member_Num, String member_ID, String member_PW, String member_Email, Date member_Birth_Date, String member_Nickname, Date member_Reg_Date, int member_Exist, int member_Type_Num) {
        this.member_Num = member_Num;
        this.member_ID = member_ID;
        this.member_PW = member_PW;
        this.member_Email = member_Email;
        this.member_Birth_Date = member_Birth_Date;
        this.member_Nickname = member_Nickname;
        this.member_Reg_Date = member_Reg_Date;
        this.member_Exist = member_Exist;
        this.member_Type_Num = member_Type_Num;
    }
    public int getMember_Num() {
        return member_Num;
    }
    public void setMember_Num(int member_Num) {
        this.member_Num = member_Num;
    }
    public String getMember_ID() {
        return member_ID;
    }
    public void setMember_ID(String member_ID) {
        this.member_ID = member_ID;
    }
    public String getMember_PW() {
        return member_PW;
    }
    public void setMember_PW(String member_PW) {
        this.member_PW = member_PW;
    }
    public String getMember_Email() {
        return member_Email;
    }
    public void setMember_Email(String member_Email) {
        this.member_Email = member_Email;
    }
    public Date getMember_Birth_Date() {
        return member_Birth_Date;
    }
    public void setMember_Birth_Date(Date member_Birth_Date) {
        this.member_Birth_Date = member_Birth_Date;
    }
    public String getMember_Nickname() {
        return member_Nickname;
    }
    public void setMember_Nickname(String member_Nickname) {
        this.member_Nickname = member_Nickname;
    }
    public Date getMember_Reg_Date() {
        return member_Reg_Date;
    }
    public void setMember_Reg_Date(Date member_Reg_Date) {
        this.member_Reg_Date = member_Reg_Date;
    }
    public int getMember_Exist() {
        return member_Exist;
    }
    public void setMember_Exist(int member_Exist) {
        this.member_Exist = member_Exist;
    }
    public int getMember_Type_Num() {
        return member_Type_Num;
    }
    public void setMember_Type_Num(int member_Type_Num) {
        this.member_Type_Num = member_Type_Num;
    }
}
