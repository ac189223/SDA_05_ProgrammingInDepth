package exercise02;


import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
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
                printoutSortedOnFat();
                break;
            case 4:
                filterByFiber();
                break;
            case 5:
                filterByProteinAndReviewer();
                break;
        }
    }

    /**
     * Printing all the names of bars
     */
    private void printoutAllNames() {
        dataSet.getBars().stream().map(Bar::getName).distinct().sorted()
                .forEach(System.out::println);
    }

    /**
     * Printing bars sorted by proteins, from highest
     */
    private void printoutSortedOnProtein() {
        dataSet.getBars().stream()
                .sorted(Comparator.comparing(Bar::getName)).collect(Collectors.toList())
                .stream()
                .sorted(Comparator.comparing(Bar::getProtein).reversed()).collect(Collectors.toList())
                .forEach(bar -> System.out.println(bar.getName() + " - " + bar.getProtein()));
    }

    /**
     * Printing bars sorted by fat, from highest
     */
    private void printoutSortedOnFat() {
        dataSet.getBars().stream()
                .sorted(Comparator.comparing(Bar::getName)).collect(Collectors.toList())
                .stream()
                .sorted(Comparator.comparing(Bar::getFett).reversed()).collect(Collectors.toList())
                .forEach(bar -> System.out.println(bar.getName() + " - " + bar.getFett()));
    }

    /**
     * Printing bars filtered by fiber under maximum amount, from highest
     */
    private void filterByFiber() {
        System.out.println("Enter maximum fiber amount that you accept");
        Scanner scanner = new Scanner(System.in);
        double amount = -1;
        while (amount < 0)
            try {
                amount = Double.parseDouble(scanner.next());
            } catch (Exception e) {
                amount = -1;
            }
        double finalAmount = amount;
        dataSet.getBars().stream()
                .filter(bar -> bar.getFiber() <= finalAmount)
                .sorted(Comparator.comparing(Bar::getName)).collect(Collectors.toList())
                .stream()
                .sorted(Comparator.comparing(Bar::getFiber).reversed()).collect(Collectors.toList())
                .forEach(bar -> System.out.println(bar.getName() + " - " + bar.getFiber()));

    }

    /**
     * Printing bars filtered by protein over minimum amount
     * and additionally reviewed by chosen person, from highest
     */
    private void filterByProteinAndReviewer() {
        System.out.println("Enter minimum protein amount that you accept");
        Scanner scanner = new Scanner(System.in);
        double amount = -1;
        while (amount < 0)
            try {
                amount = Double.parseDouble(scanner.next());
            } catch (Exception e) {
                amount = -1;
            }

        List<String> reviewers = dataSet.getReviews().stream()
                .map(Review::getPersonID).collect(Collectors.toList());
        System.out.println("Enter valid reviewer id");
        String reviewerId = "";
        while (!reviewers.contains(reviewerId))
            try {
                reviewerId = scanner.next();
            } catch (Exception e) {
                reviewerId = "";
            }
        String finalReviewerId = reviewerId;
        List<String> barsSN =dataSet.getReviews().stream()
                .filter(review -> review.getPersonID().equals(finalReviewerId))
                .map(Review::getSN).collect(Collectors.toList());

        double finalAmount = amount;
        dataSet.getBars().stream()
                .filter(bar -> bar.getProtein() >= finalAmount)
                .filter(bar -> barsSN.contains(bar.getSN()))
                .forEach(bar -> System.out.println(bar.getName() + " - " + bar.getProtein()));
    }

}
