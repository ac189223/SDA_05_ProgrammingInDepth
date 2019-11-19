package Qualified_K;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class GraphByStrings {
    private static GraphByStrings graphByStrings = new GraphByStrings();

    public static void main(String[] args) {
        DataSetByStrings dataSetByStrings = createDataByStrings(50);

        ArrayList<String> connections = new ArrayList<>();
        String con1, con2;
        for (int index = 0; index < dataSetByStrings.getFrom().length; index++) {
            con1 = dataSetByStrings.getFrom()[index] + "-->" + dataSetByStrings.getTo()[index];
            con2 = dataSetByStrings.getTo()[index] + "-->" + dataSetByStrings.getFrom()[index];
            if (!connections.contains(con1))
                connections.add(con1);
            if (!connections.contains(con2))
                connections.add(con2);
        }
        System.out.println("Possible connections ");
        connections.forEach(conn -> System.out.print(conn + ", "));
        System.out.println();
        System.out.println("Starting point " + dataSetByStrings.getLocationA());
        System.out.println("Target " + dataSetByStrings.getLocationB());

        if (dataSetByStrings.getLocationA() == dataSetByStrings.getLocationB())
            System.out.println("Fastest way length is 0");
        else
            graphByStrings
                .locateFastestWay(dataSetByStrings.getFrom(), dataSetByStrings.getTo(),
                        dataSetByStrings.getLocationA(), dataSetByStrings.getLocationB());
    }

    private void locateFastestWay(int[] from, int[] to, int locationA, int locationB) {

        ArrayList<String>[] waysFromA = new ArrayList[51];
        ArrayList<String> fastestWay = new ArrayList<>();
        int[] fromTwoWays = new int[from.length * 2];
        int[] toTwoWays = new int[to.length * 2];
        Boolean found = false;

        // create extended version of connection table - little one works both ways
        for (int i = 0; i < from.length; i++) {
            fromTwoWays[i] = from[i];
            toTwoWays[i] = to[i];
        }
        for (int i = from.length; i < fromTwoWays.length; i++) {
            fromTwoWays[i] = to[i - from.length];
            toTwoWays[i] = from[i - from.length];
        }

        // write down starting point
        for (int i = 0; i < fromTwoWays.length; i++)
            if (fromTwoWays[i] == locationA) {
                waysFromA[0] = new ArrayList<>();
                waysFromA[0].add(String.valueOf(locationA));
                break;
            }

        // Build connections
        if (waysFromA[0].contains(String.valueOf(locationA))) {
            for (int i = 1; i < 51; i++) {
                waysFromA[i] = new ArrayList<>();
                for (String way : waysFromA[i - 1]) {
                    int endPoint = Integer.parseInt(way.substring(way.lastIndexOf(">") + 1));
                    for (int j = 0; j < fromTwoWays.length; j++)
                        if (fromTwoWays[j] == endPoint)
                            if (!way.contains(">" + toTwoWays[j]) && !way.contains(toTwoWays[j] + "-"))
                                if (!waysFromA[i].contains(way + "-->" + toTwoWays[j]))
                                    waysFromA[i].add(way + "-->" + toTwoWays[j]);
                }

                // Check if one of connections reached target point
                fastestWay = (ArrayList<String>) waysFromA[i].stream()
                        .filter(way -> Integer.parseInt(way.substring(way.lastIndexOf(">") + 1)) == locationB)
                        .collect(Collectors.toList());
                if (fastestWay.size() > 0) {
                    if (!found) {
                        System.out.println("Fastest way length is " + i);
                        fastestWay.forEach(way -> System.out.println(way));
                        found = true;
                    } else {
                        System.out.println("There is another way of length " + i);
                        fastestWay.forEach(way -> System.out.println(way));
                    }
                }
            }
        }
        // if not found
        if (!found)
            System.out.println("There is no way from " + locationA + " to " + locationB);
    }

    private static DataSetByStrings createDataByStrings(int maxSize) {
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
        DataSetByStrings dataSetByStrings = new DataSetByStrings(from, to, locationA, locationB);
        return dataSetByStrings;
    }
}

/**
 * Represents format of data needed to start searching for the optimal connection
 *
 * a
 * @author =-_-=
 */
class DataSetByStrings {
    private int[] from;
    private int[] to;
    private int locationA;
    private int locationB;

    // Constructor
    public DataSetByStrings(int[] from, int[] to, int locationA, int locationB) {
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