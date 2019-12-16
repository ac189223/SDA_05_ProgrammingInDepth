package AntProblemTwo;

import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collections;



public class Router {
    private HoneyComb comb ;   ////need to return to private after testing !!
    private ArrayList<Integer> paths = new ArrayList<Integer> () ;
    private int startingPoint ;


    public int getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(int startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Router () {

    }

    public HoneyComb getHoneyComb() {
        return comb ;
    }

    public ArrayList<Integer> getPathList(){
        return paths ;
    }

    public void createGrid(int side) {
        comb = new HoneyComb(side); //creating grid object as grounds for path selection
        comb.createHoneyComb();
    }


    public void blockCell(int cellID) {  //delete cell ID effectively eliminating it as a possible neighbor if the ID is supposed to be an obstacle
        comb.findCellByID(cellID).setId(0);
    }


    public void findPaths(int currentID, int targetID, int numOfSteps, int maxSteps) { // routing method that finds
        // the all possible paths within
        // the accepted number of steps
        // to destination cell
        // method works recursively finding every possible next step
        //for the current cell and returns when limit is exceeded or target is reached



        if (currentID == targetID) { //if target has been reached
            paths.add((Integer) numOfSteps);
            return;
        } else if (numOfSteps >= maxSteps) { // if max number of steps has been reached break
            return;
        } else if (paths.size() != 0 && numOfSteps > Collections.min(paths)){//if a shorter path has already been found , break.
            return ;
        }else {
            int[] neighbours = comb.findNeighbours(currentID); //retrieving neighbours
            numOfSteps++;
            for (int i = 0; i < 6; i++) {
                if (neighbours[i] != 0 && neighbours[i] != currentID && neighbours[i]!= startingPoint) { //making sure not to go to empty , blocked or origin cells
                    //if (paths.size() != 0 && numOfSteps > Collections.min(paths)) {return ;} //if a shorter path has already been found , break.

                    findPaths(neighbours[i], targetID, numOfSteps, maxSteps);

                }
            }

        }

        return;
    }




}