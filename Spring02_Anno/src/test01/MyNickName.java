package test01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyNickName {
	
	
	@Autowired
	private NickName nickName;
	
	public MyNickName() {
		// TODO Auto-generated constructor stub
	}

	public NickName getNickName() {
		return nickName;
	}

	public void setNickName(NickName nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "MyNickName [nickName=" + nickName + "]";
	}
	
	

}
