package tech.feily.vehiclerecognition.util;

import org.json.JSONObject;

public class LicensePlateBean {

	boolean flag;
	JSONObject licensePlate;
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public void setLicensePlate(JSONObject licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	public boolean isFlag() {
		return flag;
	}
	
	public JSONObject getLicensePlate() {
		return licensePlate;
	}
	
}
