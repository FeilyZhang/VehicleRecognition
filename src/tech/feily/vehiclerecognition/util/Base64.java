package tech.feily.vehiclerecognition.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import sun.misc.BASE64Encoder;

public class Base64 {

	public static String enCode(String imgPath) throws IOException {
		InputStream is = null;
		byte[] data = null;
		try {
			is = new FileInputStream(imgPath);
			data = new byte[is.available()];
			is.read(data);
			is.close();
		} catch (IOException e) {
			new OperateFile().write(Util.ERROR_PATH, new Date() + "\t" + "Fail to get access_token" + "\r\n",true);
			e.printStackTrace();
		}
		return new BASE64Encoder().encode(data);
	}
	
}
