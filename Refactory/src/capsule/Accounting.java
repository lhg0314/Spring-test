package capsule;

public class Accounting {
	
	private int balance;// 잔고
	private int salesPrice;//매출
	private int expences;//지출
	
	public Accounting() {
		// TODO Auto-generated constructor stub
	}
	
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(int salesPrice) {
		this.salesPrice = salesPrice;
	}
	public int getExpences() {
		return expences;
	}
	public void setExpences(int expences) {
		this.expences = expences;
	}

	@Override
	public String toString() {
		return "Accounting [balance=" + balance + ", salesPrice=" + salesPrice + ", expences=" + expences + "]";
	}
	
	//매출등록을 담당하는 메서드
	public int regusterSales(int payPrice) {
		
		balance += payPrice;
		salesPrice += payPrice;
		return balance;
	}
	
	//지출 등록을 담당하는 메서드
	public boolean regusterExpences(int expencesPrice) {
		if(balance > expencesPrice) {
			balance -=expencesPrice;
			expences += expencesPrice;
			return true;
		}else {
			return false;
		}
	}
	//반품을 담당하는 메서드
	public void registerRefund(int expencesPrice) {
		balance += expencesPrice;//반품으로 잔고가 들어난다
		expences -=expencesPrice;//지출이 줄어든다
	}
	//환불을 담당하는 메서드
	public void refund(int price) {
		
		balance -= price;//잔고에서 환불한 만큼의 금액을 줄인다
		salesPrice -= price;//매출에서 환불한 만큼의 금액을 줄인다
	}
	
	

}
