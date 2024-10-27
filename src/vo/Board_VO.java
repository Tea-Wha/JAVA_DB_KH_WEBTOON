package vo;

// 게시판유형 테이블 //
// VO(Value Object) : 데이터베이스에서 가져온 레코드(튜플)를 자바 객체로 매핑하는데 사용
public class Board_VO {
    private int boardNum;
    // 게시판 유형번호 / INTEGER / PRIMARY KEY
    private String boardName;
    // 게시판 이름 / VARCHAR2(20) / NOT NULL, UNIQUE, CHECK IN('공지', '자유')
    private String boardName_Bef;
    // 수정 전 게시판 이름
    private String boardName_Aft;

    public Board_VO(int boardNum, String boardName) {
        this.boardNum = boardNum;
        this.boardName = boardName;
    }
    public Board_VO(int boardNum, String boardName_Bef, String boardName_Aft) {
        this.boardNum = boardNum;
        this.boardName_Bef = boardName_Bef;
        this.boardName_Aft = boardName_Aft;
    }
    public int getBoardNum() {
        return boardNum;
    }
    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }
    public String getBoardName() {
        return boardName;
    }
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
    public String getBoardName_Bef() {
        return boardName_Bef;
    }
    public void setBoardName_Bef(String boardName_Bef) {
        this.boardName_Bef = boardName_Bef;
    }
    public String getBoardName_Aft() {
        return boardName_Aft;
    }
    public void setBoardName_Aft(String boardName_Aft) {
        this.boardName_Aft = boardName_Aft;
    }
}
