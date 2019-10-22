package exercise02;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Read XML file and creates list of bars
 */
public class XMLReader {
    static final String BAR = "BAR";
    static final String SN = "SN";
    static final String FETT = "fett";
    static final String ENERGY = "energy";
    static final String KOLHYDRAT = "kolhydrat";
    static final String PROTEIN = "protein";
    static final String FIBER = "fiber";

    static final String REVIEWER = "reviewer";
    static final String PERSON_ID = "personID";
    static final String DATE = "date";
    static final String SCORE = "score";

    /**
     * Read data from XML file
     *
     * @param xmlFileName            XML file to read data from
     * @return                      dataSet of read records
     */
    @SuppressWarnings({ "unchecked", "null" })
    public DataSet readXmlFile(String xmlFileName) {
        DataSet dataSet = new DataSet();
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(xmlFileName);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Bar bar = new Bar();
            Review review = new Review();

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElementBar = event.asStartElement();
                    // If we have a bar element, we create a new bar
                    if (startElementBar.getName().getLocalPart()
                            .substring(startElementBar.getName().getLocalPart().length() - 3)
                            .equals(BAR)) {
                        bar = new Bar();
                        bar.setName(startElementBar.getName().getLocalPart());
                        // We read the attributes from this tag and add the SN attribute to our object
                        Iterator<Attribute> attributes = startElementBar.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            if (attribute.getName().toString().equals(SN))
                                bar.setSN(attribute.getValue());
                        }
                    }
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(FETT)) {
                            event = eventReader.nextEvent();
                            bar.setFett(event.asCharacters().getData());
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(ENERGY)) {
                        event = eventReader.nextEvent();
                        bar.setEnergy(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(KOLHYDRAT)) {
                        event = eventReader.nextEvent();
                        bar.setKolhydrat(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(PROTEIN)) {
                        event = eventReader.nextEvent();
                        bar.setProtein(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(FIBER)) {
                        event = eventReader.nextEvent();
                        bar.setFiber(event.asCharacters().getData());
                        continue;
                    }

                    if (bar.getSN() != null && bar.getFett() != null && bar.getEnergy() != null &&
                            bar.getKolhydrat() != null && bar.getProtein() != null && bar.getFiber() != null) {
                        if (event.isStartElement()) {
                            StartElement startElementReview = event.asStartElement();
                            // If we have a review element, we create a new review
                            if (startElementReview.getName().getLocalPart().equals(REVIEWER)) {
                                review = new Review();
                                review.setSN(bar.getSN());
                                // We read the attributes from this tag and add the personID attribute to our object
                                Iterator<Attribute> attributes = startElementReview.getAttributes();
                                while (attributes.hasNext()) {
                                    Attribute attribute = attributes.next();
                                    if (attribute.getName().toString().equals(PERSON_ID))
                                        review.setPersonID(attribute.getValue());
                                }
                            }
                            if (event.isStartElement()) {
                                if (event.asStartElement().getName().getLocalPart().equals(DATE)) {
                                    event = eventReader.nextEvent();
                                    review.setDate(event.asCharacters().getData());
                                    continue;
                                }
                            }
                            if (event.asStartElement().getName().getLocalPart().equals(SCORE)) {
                                event = eventReader.nextEvent();
                                review.setScore(event.asCharacters().getData());
                                continue;
                            }
                        }
                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    // If we reach the end of a review element, we add it to the list
                    if (endElement.getName().getLocalPart().equals(REVIEWER)) {
                        dataSet.getReviews().add(review);
                    }
                    // If we reach the end of a bar element, we add it to the list
                    if (endElement.getName().getLocalPart()
                            .substring(endElement.getName().getLocalPart().length() - 3)
                            .equals(BAR)) {
                        dataSet.getBars().add(bar);
                    }
                }

            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return dataSet;
    }
}
