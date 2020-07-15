package di.setter;

import di.tire.Tire;

public class Car {
	
	private Tire tire;
	
	public void setTire(Tire tire) {
		this.tire=tire;
	}
	
	public String getTire() {
		return tire.getProduct() + "장착함";
	}

}
