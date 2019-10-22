package exercise02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests of XML reader
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class TestXMLReader {

    /**
     * Create dataSet with records from XML file
     */
    @Test
    public void testCreateAnimalsCollection() {
        // Arrange
        XMLReader xmlReader = new XMLReader();
        // Act
        DataSet fetchedData = xmlReader.readXmlFile("src/main/java/exercise02/bars.xml");
        String firstBarFett = fetchedData.getBars().get(0).getFett();
        String firstReviewScore = fetchedData.getReviews().get(0).getScore();
        // Assert
        assertEquals("19.57", firstBarFett);
        assertEquals("3", firstReviewScore);
    }
}
