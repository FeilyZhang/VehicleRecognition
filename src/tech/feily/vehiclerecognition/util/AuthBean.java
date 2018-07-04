package tech.feily.vehiclerecognition.util;

public class AuthBean {

	boolean flag;
	String token;
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public boolean isFlag() {
		return flag;
	}
	
	public String getToken() {
		return token;
	}
}
