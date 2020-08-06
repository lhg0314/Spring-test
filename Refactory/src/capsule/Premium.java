package capsule;

public class Premium extends Coffee {
	
	private String name;
	private int salesPrice;
	private int perchacePrice;
	private int stock;//재고
	private int safetyStock;//안전재고
	private int salesCount;
	private Accounting accounting;
	
	//판매등록 오버라이드
	@Override
	public int addStock(int coffeeCnt, int price) {
		int expencesPrice=coffeeCnt * price+2000;//프리미엄커피는 2000원이 더든다
		if(accounting.regusterExpences(expencesPrice)) {//잔고가 쓸돈 보다 많을때
			System.out.println("*재고를 "+coffeeCnt+"개 추가합니다");
			stock += coffeeCnt;
			return expencesPrice;
			
		}else {//잔고가 쓸돈보다 적을때 -> 돈이 부족할때
			System.out.println("*잔고가 부족해 재고를 추가하지 못했습니다");
			
			return 0;
		}
	}

}
