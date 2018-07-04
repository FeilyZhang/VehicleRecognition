package tech.feily.vehiclerecognition.util;

import java.util.List;
import java.util.Map;

public class HttpResultBean {
	
	boolean flag;
	Map<String, List<String>> map;
	String result;
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public void setMap(Map<String, List<String>> map) {
		this.map = map;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public boolean isFlag() {
		return flag;
	}
	public Map<String, List<String>> getMap() {
		return map;
	}
	
	public String getResult() {
		return result;
	}
}
