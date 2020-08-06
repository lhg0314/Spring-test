package refactory;

import java.util.Scanner;

public abstract class Coffie {
	private int price;//판매가
	private int cost;//원가
	private int stock;//재고
	private int saftyStock;//안전재고
	private int salesCount;//판매량
	
	
	@Override
	public String toString() {
		return "Coffie [price=" + price + ", cost=" + cost + ", stock=" + stock + ", saftyStock=" + saftyStock
				+ ", salesCount=" + salesCount + "]";
	}




	public int getPrice() {
		return price;
	}




	public void setPrice(int price) {
		this.price = price;
	}




	public int getCost() {
		return cost;
	}




	public void setCost(int cost) {
		this.cost = cost;
	}




	public int getStock() {
		return stock;
	}




	public void setStock(int stock) {
		this.stock = stock;
	}




	public int getSaftyStock() {
		return saftyStock;
	}




	public void setSaftyStock(int saftyStock) {
		this.saftyStock = saftyStock;
	}




	public int getSalesCount() {
		return salesCount;
	}




	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}




	public abstract void coffeeinto(Scanner sc);
	
	
	

}
