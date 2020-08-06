package capsule;

public class Coffee {
	
	private String name;
	private int salesPrice;
	private int perchacePrice;
	private int stock;//재고
	private int safetyStock;//안전재고
	private int salesCount;
	
	private Accounting accounting;
	
	public Coffee() {
		
	}
	public Coffee(Accounting accounting)
	{
		this.setAccounting(accounting);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(int salesPrice) {
		this.salesPrice = salesPrice;
	}

	public int getPerchacePrice() {
		return perchacePrice;
	}

	public void setPerchacePrice(int perchacePrice) {
		this.perchacePrice = perchacePrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getSafetyStock() {
		return safetyStock;
	}

	public void setSafetyStock(int safetyStock) {
		this.safetyStock = safetyStock;
	}

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	public Accounting getAccounting() {
		return accounting;
	}

	public void setAccounting(Accounting accounting) {
		this.accounting = accounting;
	}
	
	
	//판매등록
	public int registerSales(int salesCnt) {
		int payPrice =deductStock(salesCnt,salesPrice);
		if(payPrice>0) {
			
			salesCount += salesCnt;
		}
		return payPrice;
	}
	
	//재고 차감
	private int deductStock(int coffeeCnt,int price) {
		secureSafetyStock();
		
		if(stock > coffeeCnt) {//주문수 보다 재고가 많을때
			stock -=coffeeCnt;
			return price * coffeeCnt;
		}else if(addStock(coffeeCnt,perchacePrice)>0){//재고는 없는데 재고가 확보가 가능할때
			stock -=coffeeCnt;
			return price * coffeeCnt;
		}else {//재고가 없을때
			salesCount -= coffeeCnt;
			return 0;
		}
		
	}
	//재고 추가
	public int addStock(int coffeeCnt,int price) {
		int expencesPrice=coffeeCnt * price;
		if(accounting.regusterExpences(expencesPrice)) {//잔고가 쓸돈 보다 많을때
			System.out.println("*재고를 "+coffeeCnt+"개 추가합니다");
			stock += coffeeCnt;
			return expencesPrice;
			
		}else {//잔고가 쓸돈보다 적을때 -> 돈이 부족할때
			System.out.println("*잔고가 부족해 재고를 추가하지 못했습니다");
			
			return 0;
		}
	}
	
	//안전재고 확보
	private void secureSafetyStock() {
		if(stock <safetyStock) {
			System.out.println("안전재고가 부족해 재고를 확보합니다");
			int needCnt = safetyStock *2;//필요한 재고
			addStock(needCnt,perchacePrice);
		}
	}
	
	//반품처리하는 메서드
	public void refundStock(int coffeeCnt,int price) {
		stock -= coffeeCnt;//반품해서 재고가 줄어듬
		accounting.registerRefund(price*coffeeCnt);
	} 
	
	

}
