package Qualified_K;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class GraphBothWays {
    private static GraphBothWays graphBothWays = new GraphBothWays();

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
                table[to[i]][from[i]] = 1;
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
            for (int steps = 0; steps < (from.length * 4); steps++) {
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
        DataSetBothWays dataSetBothWays = createDataBothWays(50);

        ArrayList<String> connections = new ArrayList<>();
        String con;
        for (int index = 0; index < dataSetBothWays.getFrom().length; index++) {
            con = dataSetBothWays.getFrom()[index] + "-->" + dataSetBothWays.getTo()[index];
            if (!connections.contains(con))
                connections.add(con);
        }
        System.out.println("Possible connections ");
        connections.forEach(conn -> System.out.print(conn + ", "));
        System.out.println();
        System.out.println("Starting point " + dataSetBothWays.getLocationA());
        System.out.println("Target " + dataSetBothWays.getLocationB());

        int fastestWayLength;
        fastestWayLength = graphBothWays.fastestRoute(dataSetBothWays.getFrom(), dataSetBothWays.getTo(), dataSetBothWays.getLocationA(), dataSetBothWays.getLocationB());
        if (fastestWayLength == -1)
            System.out.println("Way from A to B does not exist");
        else {
            System.out.println("Fastest way length is " + fastestWayLength);
            if (fastestWayLength > 0) {
                ArrayList<String> fastestWay = graphBothWays
                        .locateFastestWay(dataSetBothWays.getFrom(), dataSetBothWays.getTo(), dataSetBothWays.getLocationA(), dataSetBothWays.getLocationB(), fastestWayLength);
                fastestWay.forEach(way -> System.out.println(way));
            }
        }
        // For playing on specific set comment ALL above in main and uncomment this one above
        //
        //System.out.println(graphBothWays.fastestRoute(new int[]{0, 0, 1, 2}, new int[]{1, 2, 3, 0}, 2, 3));
    }

    private ArrayList<String> locateFastestWay(int[] from, int[] to, int locationA, int locationB, int fastestWayLength) {

        ArrayList<String>[] waysFromA = new ArrayList[fastestWayLength + 1];
        ArrayList<String> fastestWay = new ArrayList<>();
        int[] fromTwoWays = new int[from.length * 2];
        int[] toTwoWays = new int[to.length * 2];

        for (int i = 0; i < from.length; i++) {
            fromTwoWays[i] = from[i];
            toTwoWays[i] = to[i];
        }
        for (int i = from.length; i < fromTwoWays.length; i++) {
            fromTwoWays[i] = to[i - from.length];
            toTwoWays[i] = from[i - from.length];
        }

        waysFromA[0] = new ArrayList<>();
        waysFromA[0].add(String.valueOf(locationA));
        for (int i = 1; i < fastestWayLength + 1; i++) {
            waysFromA[i] = new ArrayList<>();
            for (String way : waysFromA[i - 1]) {
                int endPoint = Integer.parseInt(way.substring(way.lastIndexOf(">") + 1));
                for (int j = 0; j < fromTwoWays.length; j++)
                    if (fromTwoWays[j] == endPoint)
                        if (!way.contains(">" + toTwoWays[j]) && !way.contains(toTwoWays[j] + "-"))
                            if (!waysFromA[i].contains(way + "-->" + toTwoWays[j]))
                                waysFromA[i].add(way + "-->" + toTwoWays[j]);
            }
        }

        fastestWay = (ArrayList<String>) waysFromA[fastestWayLength].stream()
                .filter(way ->  Integer.parseInt(way.substring(way.lastIndexOf(">") + 1)) == locationB)
                .collect(Collectors.toList());

        return fastestWay;
    }

    private static DataSetBothWays createDataBothWays(int maxSize) {
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
        DataSetBothWays dataSetBothWays = new DataSetBothWays(from, to, locationA, locationB);
        return dataSetBothWays;
    }
}

/**
 * Represents format of data needed to start searching for the optimal connection
 *
 * a
 * @author =-_-=
 */
class DataSetBothWays {
    private int[] from;
    private int[] to;
    private int locationA;
    private int locationB;

    // Constructor
    public DataSetBothWays(int[] from, int[] to, int locationA, int locationB) {
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