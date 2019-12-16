package WAAD_CarRental;

public class Car {
	private String brand;
	private String regNr;
	private String type;
	private int seats;
	private int doors;
	private String transmission;
	private String luggageSpace;
	private boolean available;
	private double price;

	public Car() { }
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getRegNr() {
		return regNr;
	}
	public void setRegNr(String regNr) {
		this.regNr = regNr;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getDoors() {
		return doors;
	}
	public void setDoors(int doors) {
		this.doors = doors;
	}

	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getluggageSpace() {
		return luggageSpace;
	}
	public void setLuggage(String luggageSpace) {
		this.luggageSpace = luggageSpace;
	}

	public boolean getAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
