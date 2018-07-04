package tech.feily.vehiclerecognition.util;

import java.util.Scanner;

public class Produce {

	public static String PATH = "\"C:\\\\Users\\\\HP\\\\Desktop\\\\sampleimg\\\\";
	public static String[] fileName = {"gongjiaolei", "mianbaolei", "yueyelei", "jiaochelei", "kachelei"};
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String result = "{{";
		int n = new Scanner(System.in).nextInt();
		for (int i = 0; i < fileName.length; i++) {
			for (int j = 0; j < n; j++) {
				if (j == n - 1 && i == fileName.length - 1) result += PATH + fileName[i] + String.valueOf(j) + ".jpg\"}" + "\n";
				else if (j == n - 1) result += PATH + fileName[i] + String.valueOf(j) + ".jpg\"},{" + "\n";
				else result += PATH + fileName[i] + String.valueOf(j) + ".jpg\", ";
			}
		}
		System.out.println(result + "}");
	}
}
