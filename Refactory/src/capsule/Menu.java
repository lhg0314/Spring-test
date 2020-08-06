package capsule;

import java.util.Scanner;

public class Menu {
	Scanner sc=new Scanner(System.in);
	
	private Accounting accounting;
	private Sales sales;
	private Coffee[] coffeeArr;
	
	public Menu() {
		accounting=new Accounting();
		System.out.println("잔고를 입력하세요: ");
		
		accounting.setBalance(sc.nextInt());//잔고 입력받음
		sales=new Sales(accounting);//accounting 객체를 의존성주입받는다
		
		System.out.println("---------------------------------");
		
		System.out.println("커피종류의 가짓수를 입력하세요: ");
		coffeeArr=new Coffee[sc.nextInt()];//사용자가 입력한 종류의 수 만큼 커피의 종류를 만듬
		
		System.out.println("---------------------------------");
		
		for (int i = 0; i < coffeeArr.length; i++) {//커피객체를 생성해서 커피객체배열에 추가
			Coffee coffee=new Coffee(accounting);
			int no=i+1;
			System.out.println(no+" 번째 커피의 이름을 등록하세요: ");
			coffee.setName(sc.next());
			System.out.println(no+" 번째 커피의 판매가 등록하세요: ");
			coffee.setSalesPrice(sc.nextInt());
			System.out.println(no+" 번째 커피의 매입가 등록하세요: ");
			coffee.setPerchacePrice(sc.nextInt());
			System.out.println(no+" 번째 커피의 재고를 등록하세요: ");
			coffee.setStock(sc.nextInt());
			System.out.println(no+" 번째 커피의 안전재고를 등록하세요: ");
			coffee.setSafetyStock(sc.nextInt());
			
			System.out.println("---------------------------------");
			coffeeArr[i]=coffee;
		}
		
		
		
		
	}
	
	private void showStates() {
		System.out.println("================================");
		for (Coffee coffee : coffeeArr) {//모든 커피들의 재고와 판매량을 출력
			System.out.printf("%10s 재고 : %d | 판매량 : %d \n",coffee.getName(),coffee.getStock(),coffee.getSalesCount());
		}
		System.out.println("재고: "+accounting.getBalance() + "| 매출: "+accounting.getSalesPrice()+ "| 지출: "+accounting.getExpences());
		
		
		
	}
	private void showSales() {
		System.out.println("판매한 커피의 코드를 입력하세요");
		int inputType=sc.nextInt();
		System.out.println("주문량을 입력하세요");
		int orderCnt=sc.nextInt();
		int payPrice=sales.sellProduct(coffeeArr[inputType], orderCnt);
		if(payPrice>0) {
			System.out.println("제품명: "+coffeeArr[inputType].getName()
					+" 판매가: "+coffeeArr[inputType].getSalesPrice()
					+" 구매갯수: "+orderCnt
					+" 결제금액: "+payPrice
					+" 남은 재고: "+coffeeArr[inputType].getStock()
					
					);
		}else {
			System.out.println("주문이 취소되었습니다");
		}
	}
	
	private void showMenu() {
		System.out.println("==========음료메뉴");
		for (int i = 0; i < coffeeArr.length; i++) {
			System.out.println(coffeeArr[i].getName()+"("+i+")");
		}
		showSales();
	}
	
	//환불을 처리하는 메서드
	private void showRefund() {
		System.out.println("환불할 메뉴의 코드를 입력하시오: ");
		System.out.println("==========음료메뉴");
		for (int i = 0; i < coffeeArr.length; i++) {
			System.out.println(coffeeArr[i].getName()+"("+i+")");
		}
		int menu=sc.nextInt();
		System.out.println("환불할 수량은: ");
		int cnt=sc.nextInt();
		
		sales.refundProd(coffeeArr[menu], cnt);//환불처리하는 코드
		System.out.println("환불이 완료되었습니다");
		showStates();//환불된거확인
		
		
		
		
	}
	
	public void showIndex() {
		do {
			System.out.println("=======메뉴==========");
			System.out.println("메뉴(0)  ");
			System.out.println("현황(1)  ");
			System.out.println("환불(2)  ");
			System.out.println("종료(3)  ");
			System.out.print("입력 : ");
			int inputMenu = sc.nextInt();
			
			switch (inputMenu) {
			case 0:showMenu(); break;
			case 1:showStates(); break;
			case 2:showRefund(); break;
			case 3:System.out.println("프로그램 종료"); return;
				
			default:
				System.out.println("잘못입력했다");break;
			}
		}while(true);
	}
	
	

}
