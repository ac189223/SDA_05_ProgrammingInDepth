package Qualified_K;

import java.util.*;

public class Graph {
    private static Graph graph = new Graph();

    /**
     * Method to find the fastest route in with given connections
     *
     * @param from              starting points of given connections
     * @param to                target points of given connections
     * @param locationA         starting point of application
     * @param locationB         target of application
     * @return                  minimal length of connections needed to pass from locationA to locationB
     *                          or -1 when path does not exist
     * @author =-_-=
     */
    public int fastestRoute(int[] from, int[] to, int locationA, int locationB) {
        // Constant scope, all numbers belong to <0, 50>
        final int SIZE = 51;
        // Initialization of the final result
        int fastestWay = -1;

        // Catching easy possibility
        if (locationA == locationB)
            fastestWay = 0;
        else {
            // Initialization of table for lengths of connections
            int[][] table = new int[SIZE][SIZE];
            // Marking provided connections with length 1 in the table
            for (int i = 0; i < from.length; i++) {
                table[from[i]][to[i]] = 1;
            }

// This section prints primal connections
//            for (int ii = 0; ii < SIZE; ii++) {
//                for (int iiii = 0; iiii < SIZE; iiii++) {
//                    System.out.print(table[ii][iiii] + ",");
//                }
//                System.out.println();
//            }
//            System.out.println();

            // Initialize variable to search further connection from B to C
            int rowTo = 0;
            // Max as much steps as the amount of numbers
            for (int steps = 0; steps < (from.length * 2); steps++) {
                // Checking line by line
                for (int rowFrom = 0; rowFrom < SIZE; rowFrom++) {
                    // Checking column by column
                    for (int colFrom = 0; colFrom < SIZE; colFrom++) {
                        // If there is a connection from A to B
                        if (table[rowFrom][colFrom] != 0) {
                            // Check if there is further connection from B
                            rowTo = colFrom;
                            for (int colTo = 0; colTo < SIZE; colTo++)
                                // Found connection from B to C
                                if (colTo != rowFrom && table[rowTo][colTo] != 0)
                                    // Check if this connection from A to C is the fastest until now
                                    if (table[rowFrom][colTo] == 0 ||
                                            table[rowFrom][colFrom] + table[rowTo][colTo] < table[rowFrom][colTo])
                                        // Write it to the table
                                        table[rowFrom][colTo] = table[rowFrom][colFrom] + table[rowTo][colTo];
                        }
                    }
                }

// This section prints progress in finding connections
//                for (int ii = 0; ii < SIZE; ii++) {
//                    for (int iiii = 0; iiii < SIZE; iiii++) {
//                        System.out.print(table[ii][iiii] + ",");
//                    }
//                    System.out.println();
//                }
//                System.out.println();
            }
            // Read the length of needed way if exist in the table
            if (table[locationA][locationB] != 0)
                fastestWay = table[locationA][locationB];
        }
        // Return the value
        return fastestWay;
    }

    public static void main(String[] args) {
        DataSet dataSet = createData(50);

        System.out.println("Possible connections ");
        for (int index = 0; index < dataSet.getFrom().length; index++)
            System.out.print(dataSet.getFrom()[index] + "-->" + dataSet.getTo()[index] + ", ");
        System.out.println();
        System.out.println("Starting point " + dataSet.getLocationA());
        System.out.println("Target " + dataSet.getLocationB());

        System.out.println(graph.fastestRoute(dataSet.getFrom(), dataSet.getTo(), dataSet.getLocationA(), dataSet.getLocationB()));

        // For playing on specific set comment ALL above in main and uncomment this one above
        //
        //System.out.println(fastestRoute(new int[]{0, 0, 1, 2}, new int[]{1, 2, 3, 0}, 2, 3));
    }

    private static DataSet createData(int maxSize) {
        Random random = new Random();
        // Find size and prepare arrays for connections
        int size = random.nextInt(maxSize + 1);
        int[] from = new int[size];
        int[] to = new int[size];
        // Fill in arrays with connections
        for (int index = 0; index < size; index++) {
            from[index] = random.nextInt(size);
            to[index] = random.nextInt(size);
        }
        // Set starting point and target
        int locationA = random.nextInt(size);
        int locationB = random.nextInt(size);
        // Create and return DataSet
        DataSet dataSet = new DataSet(from, to, locationA, locationB);
        return dataSet;
    }
}

/**
 * Represents format of data needed to start searching for the optimal connection
 *
 * a
 * @author =-_-=
 */
class DataSet {
    private int[] from;
    private int[] to;
    private int locationA;
    private int locationB;

    // Constructor
    public DataSet(int[] from, int[] to, int locationA, int locationB) {
        this.setFrom(from);
        this.setTo(to);
        this.setLocationA(locationA);
        this.setLocationB(locationB);
    }

    // Getters
    public int[] getFrom() { return from; }
    public int[] getTo() { return to; }
    public int getLocationA() { return locationA; }
    public int getLocationB() { return locationB; }

    // Setters
    public void setFrom(int[] from) { this.from = from; }
    public void setTo(int[] to) { this.to = to; }
    public void setLocationA(int locationA) { this.locationA = locationA; }
    public void setLocationB(int locationB) { this.locationB = locationB; }
}