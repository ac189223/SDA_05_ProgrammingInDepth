package exercise02;


import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Class to control the flow
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class Controller {
    private final static Controller cntr = new Controller();
    private XMLReader xmlReader = new XMLReader();
    private final String xmlFileName = "src/main/java/exercise02/bars.xml";
    private DataSet dataSet;

    /**
     * Main method of the application
     *
     * @param args  not used
     */
    public static void main(String[] args) {
        cntr.dataSet = cntr.xmlReader.readXmlFile(cntr.xmlFileName);
        cntr.giveChoice();
    }

    /**
     * Choosing the action to do on read data
     */
    private void giveChoice() {
        System.out.println("Choose from the following options:");
        System.out.println("1. show names of all bars,");
        System.out.println("2. print bars sorted based on highest protein content,");
        System.out.println("3. print bars sorted based on highest fat content,");
        System.out.println("4. filter bars which has less than X fiber and print them sorted from highest,");
        System.out.println("5. find all protein bars with more than X protein reviewed by Y.");
        Scanner scanner = new Scanner(System.in);
        int chosen = 0;
        while (chosen < 1 || chosen > 5)
            try {
                chosen = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                chosen = 0;
            }
        proceed(chosen);
    }

    private void proceed(int chosen) {
        switch (chosen) {
            case 1:
                printoutAllNames();
                break;
            case 2:
                printoutSortedOnProtein();
                break;
            case 3:

                break;
            case 4:

                break;
        }
    }

    private void printoutAllNames() {
        dataSet.getBars().stream().map(Bar::getName).distinct().sorted()
                .forEach(System.out::println);
    }

    private void printoutSortedOnProtein() {
        dataSet.getBars().stream()
                .sorted(Comparator.comparing(Bar::getName)).collect(Collectors.toList())
                .stream()
                .sorted(Comparator.comparing(Bar::getProtein)).collect(Collectors.toList())
                .forEach(bar -> System.out.println(bar.getName() + " - " + bar.getProtein()));
    }

}
