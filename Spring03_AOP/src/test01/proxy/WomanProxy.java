package test01.proxy;

public class WomanProxy implements Dev {

	@Override
	public void classWork() {
		System.out.println("출석카드를 찍는다");
		
		try{
			new Woman().classWork();
		}catch (Exception e) {
			System.out.println("쉬는날이었다");
		}finally {
			System.out.println("집에간다");
		}
		
	}

}
