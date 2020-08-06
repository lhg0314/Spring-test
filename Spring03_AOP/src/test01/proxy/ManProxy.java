package test01.proxy;

public class ManProxy implements Dev{

	@Override
	public void classWork() {
		System.out.println("출석카드를 찍는다");
		
		try {
			new Man().classWork();//주기능을 요청한다
		} catch (Exception e) {
			System.out.println("쉬는날이었다");
		}finally {
			System.out.println("집에간다");
		}
		
	}

}
