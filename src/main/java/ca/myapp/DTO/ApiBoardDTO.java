package ca.myapp.DTO;

/**
 * DTO class for the REST API to define object structures required by the front-end.
 * HINT: Create static factory methods (or constructors) which help create this object
 *       from the data stored in the model, or required by the model.
 */
public class ApiBoardDTO {
    public int boardWidth;
    public int boardHeight;

    // celState[row]col] = {"fog", "hit", "fort", "miss", "field"}
    public String[][] cellStates;

    public ApiBoardDTO(int boardWidth, int boardHeight, String[][] cellStates) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.cellStates = cellStates;
    }

    public ApiBoardDTO(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public String[][] getCellStates() {
        return cellStates;
    }

    public void setCellStates(String[][] cellStates) {
        this.cellStates = cellStates;
    }

    public void initializeFog(){
        if(cellStates == null){
            cellStates = new String[boardHeight][boardWidth];
        }
        for(int i = 0; i < boardHeight; i++){
            for(int j = 0; j < boardWidth; j++){
                cellStates[i][j] = "fog";
            }
        }
    }
}
