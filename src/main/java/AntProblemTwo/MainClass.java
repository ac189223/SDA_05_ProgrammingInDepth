package AntProblemTwo;

import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainClass {


    public static void main(String[] args) {

        Scanner scan = new Scanner (System.in) ; //reading inputs
        Router router = new Router () ;
        int side = scan.nextInt();
        int maxSteps = scan.nextInt() ;
        int currentID  = scan.nextInt();
        int targetId  = scan.nextInt();
        int numOfObstacles = scan.nextInt();
        router.createGrid(side); //creating grid

        for (int i = 0 ; i < numOfObstacles ; i ++) {
            router.blockCell(scan.nextInt());
        }

        scan.close();
        router.setStartingPoint(currentID);
        router.findPaths(currentID,targetId,0,maxSteps) ;
        if (router.getPathList().size()== 0 ) {System.out.println("No");}

        //else { System.out.println(router.getPathList().stream().sorted().collect(Collectors.toList()).get(0)); }
        else { System.out.println(Collections.min(router.getPathList()));}
    }

}