package refactory;

import java.util.Scanner;

public class Latte extends Coffie{
	

	@Override
	public void coffeeinto(Scanner sc) {
		System.out.print("라떼 판매가를 등록하세요 : ");
		this.setPrice(sc.nextInt());
		System.out.print("라떼 원가를 등록하세요 : ");
		this.setCost(sc.nextInt());
		System.out.print("라떼 재고를 등록하세요 : ");
		this.setStock(sc.nextInt());
		System.out.print("라떼 안전재고를 등록하세요 : ");
		int latteSaftyStock = sc.nextInt();
		
		
		
	}

}
