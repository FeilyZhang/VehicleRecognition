package tech.feily.vehiclerecognition.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReParseJSON {
	
	public LicensePlateBean extractKey(JSONObject jsonObjectSource) {
		LicensePlateBean lp = new LicensePlateBean();
		if (!jsonObjectSource.has("words_result")) {
			lp.setFlag(false);
			lp.setLicensePlate(null);
		} else {
			lp.setFlag(true);
			lp.setLicensePlate((JSONObject) jsonObjectSource.get("words_result"));
		}
		return lp;
	}
	
	public JSONObject getObject(JSONObject jsonObjectSource, String key) {
		JSONObject jsonObject =new JSONObject();
		return (JSONObject) jsonObject.get(key);
	}
	
	public List<String> getJSONObjectValue(JSONObject jsonObjectSource, String[] key) {
		List<String> value = new ArrayList<>();
		for (String k : key) {
			if (jsonObjectSource.has(k)) value.add(jsonObjectSource.get(k).toString());
			else continue;
		}
		return value;
	}
	
	public String[] getJSONArrayValue(JSONArray jsonArraySource, String[] key) {
		String[] valueArray = null;
		valueArray = new String[jsonArraySource.length()];
		for (int i = 0; i < jsonArraySource.length(); i++) {
			valueArray[i] = new ReParseJSON().getJSONObjectValue((JSONObject) jsonArraySource.get(i),key).toString();
		}
		return valueArray;
	}

	public String[] getData(String data) {
		String[] dataOne = data.split(",");
		int dataElementLenth = dataOne[0].length();
		int length = dataOne.length;
		for (int i = 0;i < length; i++) {
			if (i == 0) dataOne[i] = dataOne[i].substring(1, dataElementLenth);
			else if (i == dataOne.length-1) dataOne[length-1] = dataOne[length-1].substring(0, dataElementLenth);
		}
		return dataOne;
	}
	
}
