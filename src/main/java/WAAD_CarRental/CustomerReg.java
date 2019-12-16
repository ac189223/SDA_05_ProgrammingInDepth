package WAAD_CarRental;

import java.util.*;

public class CustomerReg {
	private HashMap<String, Customer> custReg;
	
	public CustomerReg() {
		setCustReg(new HashMap<String, Customer>());
	}

	public HashMap<String, Customer> getCustReg() {
		return custReg;
	}
	public void setCustReg(HashMap<String, Customer> custReg) {
		this.custReg = custReg;
	}
	
	

}
