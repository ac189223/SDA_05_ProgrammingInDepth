package WAAD_CarRental;

public class OfferType {
    private String offerName;
    private int discount;

    public OfferType(String offerName, int discount) {
        setOfferName(offerName);
        setDiscount(discount);
    }

    public String getOfferName() { return offerName; }
    public void setOfferName(String offerName) { this.offerName = offerName; }

    public int getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }
}
