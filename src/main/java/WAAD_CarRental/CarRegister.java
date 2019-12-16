package WAAD_CarRental;

import java.util.*;

public class CarRegister {
	private HashMap<String, Car> carReg;
	
	public CarRegister() {
		setCarReg(new HashMap<String, Car>());
	}

	public HashMap<String, Car> getCarReg() {
		return carReg;
	}
	public void setCarReg(HashMap<String, Car> carReg) {
		this.carReg = carReg;
	}
	
	
	
}
