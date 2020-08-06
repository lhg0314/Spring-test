package capsule;

public class Sales {
	
	private Accounting accounting;
	
	public Sales() {
		// TODO Auto-generated constructor stub
	}

	public Accounting getAccounting() {
		return accounting;
	}

	public void setAccounting(Accounting accounting) {
		this.accounting = accounting;
	}
	public Sales(Accounting accounting) {
		this.accounting=accounting;
	}
	
	//제품 판매
	public int sellProduct(Coffee coffee,int salesCnt) {
		//커피객체에 판매를 등록하고 결제금액을 반환받는다
		int payPrice=coffee.registerSales(salesCnt);
		
		if(payPrice>0) {//결제가 정상적으로 진행되었을때
			
			accounting.regusterSales(payPrice);//계좌객체에 매출을 등록한다
		}
		return payPrice;
		
		
	}
	//환불
	public void refundProd(Coffee coffee ,int salesCnt) {
		
		accounting.refund(salesCnt*coffee.getSalesPrice());//커피가격*주문량만큼의 돈을 한불한다
		coffee.setSalesCount(coffee.getSalesCount()-salesCnt);//판매한 커피의 수 환불한 만큼 감소
		coffee.setStock(coffee.getStock()+salesCnt);//환불한 커피수 만큼 재고 증가
	}
	
	

}
