package AntProblemTwo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HoneyComb {
    private int side ;
    private int diameter ;
    private ArrayList <ArrayList> grid ;

    public HoneyComb(int side) {
        this.side = side ;
        this.diameter =(2*side) -1 ;
        grid =  new ArrayList<ArrayList>();
    }

    public ArrayList <ArrayList> getGrid (){ // the arrayList of Cell Arrays which emulates the honeycomb
        return grid ;
    }

    public void createHoneyComb() {  //creating the honeycomb grid as cells in an array list
        int counter = 1 ;
        for (int i = 0 ; i < diameter  ; i ++) {
            if (i < side) {
                ArrayList<Cell> row = new ArrayList<Cell>();
                for (int x = 0 ; x < side+i ; x ++) {
                    row.add(new Cell(counter,i));
                    counter ++ ;
                }
                getGrid().add(row);
            }
            else if (i>=side) {
                ArrayList<Cell> row = new ArrayList<Cell>();
                for (int x = 0; x < diameter-(i-side)-1  ; x ++) {
                    row.add(new Cell(counter,i));
                    counter ++ ;
                }
                getGrid().add(row);
            }
        }

    }

    public Cell findCellByID(int cellID) { //return cell object based in cell ID ; return null if ID is invalid
        Cell tmpcell = null ;
        int i = 0 ;
        boolean found = false ;
        while (i < diameter && found == false  ) {
            ArrayList<Cell> row =  getGrid().get(i) ;
            List list = row.stream().filter(cell -> cell.getId() == cellID).collect(Collectors.toList()) ;
            if (list.size() != 0) {
                tmpcell = (Cell) list.get(0);
                found = true ;
            }
            i ++ ;
        }

        return tmpcell ;
    }



    public int[] findNeighbours(int cellID){  //return an array of 6 int numbers , containing the IDs of the 6 possible neighbours for the cell , returns 0 is neighbour is unavailable or blocked
        Cell tmpcell = findCellByID(cellID);
        int[] neighbours = new int[6] ;
        int row = tmpcell.getrowNum() ;  //row is equivalent to y position
        int xPos = getGrid().get(row).indexOf(tmpcell); // place within "row" arrayList which is similar to xPos
        int rowsize = getGrid().get(row).size();
        //System.out.println("row num " + tmpcell.getrowNum() + "," + grid.size() );
        //System.out.println("xy " + tmpcell.getrowNum() +  "," + xPos );
        //neighbour fetching is enclosed in try catch blocks for easiest handling of "edge" cells .
        //order of array is 0 : top left , 1 : top right , 2 : left , 3 : right , 4 : bottom left , 5 : bottom right
        try {
            neighbours[2] = ((Cell) getGrid().get((row)).get((xPos - 1))).getId();// left neighbour
        } catch (Exception e) {
            neighbours[2] = 0;
        }

        try {
            neighbours[3] = ((Cell) getGrid().get((row)).get((xPos + 1))).getId(); // right neighbour
        } catch (Exception e) {
            neighbours[3] = 0;
        }
        //diagonal neighbours will differ depending on which half ot the shape you are in (top or bottom )
        if (row < (side - 1)) {
            try {
                neighbours[0] = ((Cell) getGrid().get((row - 1)).get((xPos - 1))).getId();// top left neighbour
            } catch (Exception e) {
                neighbours[0] = 0;
            }

            try {
                neighbours[1] = ((Cell) getGrid().get((row - 1)).get((xPos))).getId(); // top right neigbnour
            } catch (Exception e) {
                neighbours[1] = 0;
            }

            try {
                neighbours[4] = ((Cell) getGrid().get((row + 1)).get((xPos))).getId();// bottom left neighbour
            } catch (Exception e) {
                neighbours[4] = 0;
            }

            try {
                neighbours[5] = ((Cell) getGrid().get((row + 1)).get((xPos + 1))).getId(); // bottom right neighbour
            } catch (Exception e) {
                neighbours[5] = 0;
            }
        } else if (row == (side - 1)) {
            try {
                neighbours[0] = ((Cell) getGrid().get((row - 1)).get((xPos-1))).getId();// top left neighbour
            } catch (Exception e) {
                neighbours[0] = 0;
            }

            try {
                neighbours[1] = ((Cell) getGrid().get((row - 1)).get((xPos))).getId(); // top right neigbnour
            } catch (Exception e) {
                neighbours[1] = 0;
            }

            try {
                neighbours[4] = ((Cell) getGrid().get((row + 1)).get((xPos -1))).getId();// bottom left neighbour
            } catch (Exception e) {
                neighbours[4] = 0;
            }

            try {
                neighbours[5] = ((Cell) getGrid().get((row + 1)).get((xPos))).getId(); // bottom right neighbour
            } catch (Exception e) {
                neighbours[5] = 0;
            }
        } else if (row > (side - 1)) {
            try {
                neighbours[0] = ((Cell) getGrid().get((row - 1)).get((xPos))).getId();// top left neighbour
            } catch (Exception e) {
                neighbours[0] = 0;
            }

            try {
                neighbours[1] = ((Cell) getGrid().get((row - 1)).get((xPos + 1))).getId(); // top right neigbnour
            } catch (Exception e) {
                neighbours[1] = 0;
            }

            try {
                neighbours[4] = ((Cell) getGrid().get((row + 1)).get((xPos -1))).getId();// bottom left neighbour
            } catch (Exception e) {
                neighbours[4] = 0;
            }

            try {
                neighbours[5] = ((Cell) getGrid().get((row + 1)).get((xPos))).getId(); // bottom right neighbour
            } catch (Exception e) {
                neighbours[5] = 0;
            }
        }

        return neighbours;
    }





}