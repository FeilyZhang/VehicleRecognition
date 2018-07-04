package tech.feily.vehiclerecognition.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;

import tech.feily.vehiclerecognition.util.Base64;
import tech.feily.vehiclerecognition.util.CutImage;
import tech.feily.vehiclerecognition.util.FingerPrint;
import tech.feily.vehiclerecognition.util.GetTokenForApi;
import tech.feily.vehiclerecognition.util.HttpRequest;
import tech.feily.vehiclerecognition.util.LicensePlateBean;
import tech.feily.vehiclerecognition.util.OpenCV;
import tech.feily.vehiclerecognition.util.OperateFile;
import tech.feily.vehiclerecognition.util.ReParseJSON;

public class Recognition {

	String[] xy = null;
	char[] valueXY = null;
	List<String> value = null;
	List<String> valueCar = new ArrayList<>();
	List<String> valueArray = new ArrayList<>();
	List<String> valueCarArray = new ArrayList<>();
	String token, data, licensePlate, carType, finalDataLicense = "",finalDataCar = "",fileName;
	double x, y, width, height;
	String[] vertexesLocationKey = {"x", "y"};
	String[] licenseKey = {"color", "number", "vertexes_location"};
	String[] carKey = {"left","top","width","height"};
	
	OperateFile of = new OperateFile();
	ReParseJSON parseJson = new ReParseJSON();
	OperateFile op = new OperateFile();
	LicensePlateBean lp = new LicensePlateBean();

	String[] imgTempPath = null;
	double[] trueSim = new double[Main.SAMPLE_IMG_PATH.length + 1];
	String trueCarType;
	String carTypes;
	
	public Recognition(String imgPath) throws IOException, InterruptedException {

		if ("0".equals(of.read(Main.ACCESS_TOKEN))) GetTokenForApi.getAuth();
		token = new OperateFile().read(Main.ACCESS_TOKEN);
		
		data = "image="+HttpRequest.urlEncode(Base64.enCode(imgPath),"UTF-8");
		licensePlate = HttpRequest.sendPost(Main.LICENSE_PLATE+token,data,false);
		lp = parseJson.extractKey(new JSONObject(licensePlate));
		finalDataLicense += "----------" + Main.TITLE_LICENSE + "----------" + "\r\n";
		if (lp.isFlag()) value = parseJson.getJSONObjectValue(lp.getLicensePlate(), licenseKey);
		if (value!=null) {
			for (int i = 0; i < licenseKey.length; i++) {
				finalDataLicense += Main.LICENSEKEYCHINESE[i] + " : " + value.get(i) + "\r\n";
			}
			Collections.addAll(valueArray, parseJson.getJSONArrayValue(new JSONArray(value.get(2)), vertexesLocationKey));
			x = Double.parseDouble(parseJson.getData(valueArray.get(0))[0]);
			y = Double.parseDouble(parseJson.getData(valueArray.get(0))[1]);
			height = Double.parseDouble(parseJson.getData(valueArray.get(2))[1]) - y;
			width = Double.parseDouble(parseJson.getData(valueArray.get(1))[0]) - x;
			fileName = String.valueOf(new Date().getTime()) + ".jpg";
			new CutImage(x, y, width, height).cut(imgPath, Main.LICENSEPLATE_FOLDER + fileName);
			finalDataLicense += "图中车牌已裁剪并保存至" + Main.LICENSEPLATE_FOLDER + fileName + "\r\n\r\n";
		}
		else finalDataLicense += Main.ERROR_NECOGNITION + "\r\n";

		carType = HttpRequest.sendPost(Main.CAR+token, data,false);
		finalDataCar += "----------" + Main.TITLE_CAR + "----------" + "\r\n";
		if (new JSONObject(carType).has("location_result")) {
			valueCar = parseJson.getJSONObjectValue((JSONObject) new JSONObject(carType).get("location_result"), carKey);
			if (Integer.parseInt(valueCar.get(0))==0 && Integer.parseInt(valueCar.get(0))==0 && Integer.parseInt(valueCar.get(0))==0 && Integer.parseInt(valueCar.get(0))==0) {
				double[] tempSim = new double[Main.SAMPLE_IMG_PATH[0].length];
				imgTempPath = OpenCV.finalImgPath(imgPath);
				for (int i = 0; i < Main.SAMPLE_IMG_PATH[0].length; i++) {
					FingerPrint fp1 = new FingerPrint(ImageIO.read(new File(Main.SAMPLE_IMG_PATH[0][i])));
					FingerPrint fp2 =new FingerPrint(ImageIO.read(new File(imgPath)));
					tempSim[i] = fp1.compare(fp2);
					System.out.println(Main.SAMPLE_IMG_PATH[0][i] + "   "+tempSim[i]);
					//trueCarType = Main.SAMPLE_IMG_TAG[0];
				}/*
				if (tempSim[0] >= tempSim[1]) trueSim[0] = tempSim[0];
				else trueSim[0] = tempSim[1];*/				

				trueSim[0] = new MaxNumber().bubbleSort(tempSim);
			} else {	
				int flag = 0;//m = 1;
				double[] tempSim = new double[(Main.SAMPLE_IMG_PATH.length - 1) * Main.SAMPLE_IMG_PATH[1].length];
				//imgTempPath = OpenCV.finalImgPath(imgPath);  这一行可以注释掉
				for(int m = 1; m < Main.SAMPLE_IMG_PATH.length ; m++) {
					for (int n = 0; n < Main.SAMPLE_IMG_PATH[m].length; n++) {
						FingerPrint fp1 = new FingerPrint(ImageIO.read(new File(Main.SAMPLE_IMG_PATH[m][n])));
						FingerPrint fp2 =new FingerPrint(ImageIO.read(new File(imgPath)));  //替换为imgPath   
						//System.out.println(imgTempPath[1]);
						tempSim[flag++] = fp1.compare(fp2);//++m;
						System.out.println(Main.SAMPLE_IMG_PATH[m][n] + "   "+tempSim[flag - 1]);
		     		//System.out.println(tempSim[flag-1]);
					}
				}/*
				if (tempSim[0] >= tempSim[1]) trueSim[1] = tempSim[0];
				else trueSim[1] = tempSim[1];
				if (tempSim[2] >= tempSim[3]) trueSim[2] = tempSim[2];
				else trueSim[2] = tempSim[3];
				if (tempSim[4] >= tempSim[5]) trueSim[3] = tempSim[4];
				else trueSim[3] = tempSim[5];
				if (tempSim[6] >= tempSim[7]) trueSim[4] = tempSim[6];
				else trueSim[4] = tempSim[7];
				trueSim[5] = 1.0;*/
				int b = 1;
				double[] temp = new double[Main.SAMPLE_IMG_PATH[0].length];
				for (int i = 1 ;i < Main.SAMPLE_IMG_PATH.length; i++) {
					int a = 0;
					for (int j = 0;j < Main.SAMPLE_IMG_PATH[i].length; j++) {
						temp[a++] = tempSim[Main.SAMPLE_IMG_PATH[i].length * (i-1) + j];
					}
					trueSim[b++] = new MaxNumber().bubbleSort(temp);
					//for (double d :temp) System.out.println(d);
				}trueSim[5] = 1.0;
				//for (double d :trueSim) System.out.println(d);
			}
			//if ((trueSim[1] > 0.5) || (trueSim[2] > 0.5) || (trueSim[3] > 0.5)  || (trueSim[4] > 0.5)) {
				if( trueSim[5] == 1.0 ) {
					for(int o = 1;o < trueSim.length-1; o++) {
						finalDataCar += "有" + trueSim[o]*100 + "%" + "的把握认为该车型为" + Main.SAMPLE_IMG_TAG[o] + "\r\n";
					}
				} else {
					finalDataCar += "有" + trueSim[0]*100 + "%" + "的把握认为该车型为" + Main.SAMPLE_IMG_TAG[0] + "\r\n";
				}/*
				finalDataCar += "相关临时文件已存盘，具体路径为：\r\n";
				for(int p = 0;p < imgTempPath.length; p++) {
					finalDataCar += imgTempPath[p] + "\r\n";
				}*/
			//}else {
				//finalDataCar +="请确认图中是否含有图片或图片特征不明显\r\n";
			//}
		} else {
			finalDataCar +="请确认图中是否含有图片或图片特征不明显\r\n";
		}
		new OperateFile().write(Main.RESULT_PATH, finalDataLicense + finalDataCar , false);
		finalDataCar += "最终结果已存盘，路径为\t" + Main.RESULT_PATH;
		System.out.println(finalDataLicense + finalDataCar); 
	}
	class MaxNumber {
	    public double bubbleSort(double[] numbers){
	        double temp = 0;
	        int size = numbers.length;
	        for(int i = 0 ; i < size-1; i ++){
	        	for(int j = 0 ;j < size-1-i ; j++) {
	        		if(numbers[j] > numbers[j+1]) {
	        			temp = numbers[j];
	        			numbers[j] = numbers[j+1];
	        			numbers[j+1] = temp;
	        		}
	        	}
	        }
	        return numbers[numbers.length-1];
	    }
	}
	
}
