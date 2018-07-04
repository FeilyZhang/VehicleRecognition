package tech.feily.vehiclerecognition.util;

import java.io.IOException;
import java.util.Date;
import org.json.JSONObject;

public class GetTokenForApi {

	public static AuthBean getAuth() throws IOException {
		AuthBean ab = new AuthBean();
		HttpResultBean result = new HttpResultBean();
		OperateFile write = new OperateFile();
		String access_token=null, resultOfResponse = "";
		String authHost = "https://aip.baidubce.com/oauth/2.0/token";
		String param = "grant_type=client_credentials&client_id=" + Util.CLIENT_API_KEY + "&client_secret=" + Util.CLIENT_SECRET_KEY;
		try {
			result = HttpRequest.sendGet(authHost, param);
			for (String key : result.getMap().keySet()) write.write(Util.GETAUTH_PATH, new Date() + "\t" + key + "--->" +result.getMap().get(key) + "\r\n", true);
			access_token = new JSONObject(result.getResult()).getString("access_token");
			write.write(Util.ACCESS_TOKEN, access_token , false);
			write.write(Util.GETAUTH_PATH, new Date() + "\t" + resultOfResponse + "\r\n", true);
			write.write(Util.GETAUTH_PATH, new Date() + "\t" + "access_token = " + access_token + "\r\n", true);
		} catch (Exception e) {
			write.write(Util.ERROR_PATH, new Date() + "\t" + "Fail to get access_token" + "\r\n", true);
			e.printStackTrace();
		}
		if (access_token == null) ab.setFlag(false);
		else ab.setFlag(true);
		ab.setToken(access_token);
		return ab;
	}
	
	public static void main(String[] args) throws IOException {
		GetTokenForApi.getAuth();
	}
	
}
