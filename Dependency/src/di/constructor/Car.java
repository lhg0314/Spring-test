package di.constructor;

import di.tire.Tire;

public class Car {
	
	private Tire tire;
	
	public Car(Tire tire) {
		//의존성 주입되는곳
		this.tire=tire;
	}
	public String getTire() {
		return tire.getProduct()+"장착함!";
	}

}
