package vo;

public class Board_VO_Mod {
    private int boardNum;
    private String boardName_Bef;
    private String boardName_Aft;

    public Board_VO_Mod(int boardNum, String boardName_Bef, String boardName_Aft) {
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
