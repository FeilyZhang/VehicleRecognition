package tech.feily.vehiclerecognition.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OperateFile {
	
	OutputStream os = null;
	InputStream is = null;
	File file= null;
	byte[] byteData =null;
	
	public void write(String filePath, String stringData, boolean flag) throws IOException {
		byteData = stringData.getBytes();
		file = new File(filePath);
		if (!file.exists()) file.createNewFile();
		os = new FileOutputStream(file,flag);
		os.write(byteData);
		os.close();
	}

	public String read(String filePath) throws IOException {
		file = new File(filePath);
		if (!file.exists()) return "0";
		is = new FileInputStream(file);
		byteData = new byte[1024];
		is.read(byteData);
		is.close();
		return new String(byteData);
	}
	
}
