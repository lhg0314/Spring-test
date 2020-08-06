package common.exception;

//상수를 효율적으로 다루기 위해서 자바 1.5버전 부트 추가
//코드(에러코드,상태코드,회원등급코드...)를 다루기 위해 사용
//코드와 연관된 데이터들을 일괄적으로 다룰수 있다
public enum ErrorCode {
	
	//메일 에러코드 등록
	
	M_ERROR_01("메일 전송중 에러가 발생하였습니다","join"),
	F_Error_01("파일 등록 중 에러가 발생하였습니다","notice");
	private final String Message;
	private final String URL;
	
	ErrorCode(String msg,String url){
		this.Message=msg;
		this.URL=url;
		
		
	}

	public String getMessage() {
		return Message;
	}

	public String getURL() {
		return URL;
	}
}
