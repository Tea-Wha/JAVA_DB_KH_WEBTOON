package vo;

// 댓글평가 테이블 //
// VO(Value Object) : 데이터베이스에서 가져온 레코드(튜플)를 자바 객체로 매핑하는데 사용
public class Comment_Evaluation_VO {
    private int member_Num;
    // 회원번호 / INTEGER / PRIMARY KEY, FOREIGN KEY
    // 다중 속성 기본키 검색
    private int comment_Num;
    // 댓글번호 / INTEGER / PRIMARY KEY, FOREIGN KEY
    private int comment_Eval_Type;
    // 평가유형 / NUMBER(1) / CHECK IN (0,1), NOT NULL


    public Comment_Evaluation_VO(int member_Num, int comment_Num, int comment_Eval_Type) {
        this.member_Num = member_Num;
        this.comment_Num = comment_Num;
        this.comment_Eval_Type = comment_Eval_Type;
    }
    public int getMember_Num() {
        return member_Num;
    }
    public void setMember_Num(int member_Num) {
        this.member_Num = member_Num;
    }
    public int getComment_Num() {
        return comment_Num;
    }
    public void setComment_Num(int comment_Num) {
        this.comment_Num = comment_Num;
    }
    public int getComment_Eval_Type() {
        return comment_Eval_Type;
    }
    public void setComment_Eval_Type(int comment_Eval_Type) {
        this.comment_Eval_Type = comment_Eval_Type;
    }
}
