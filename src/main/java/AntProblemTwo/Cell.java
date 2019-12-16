package AntProblemTwo;

public class Cell {
    private int id ;
    private int rowNum ;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getrowNum() {
        return rowNum;
    }
    public void setrowNum(int rowNum) {
        this.rowNum = rowNum;
    }


    public Cell(int n , int row ) {
        this.setId(n);
        this.setrowNum(row);
    }
    public String toString() {
        return this.getId() + ",";
    }

}