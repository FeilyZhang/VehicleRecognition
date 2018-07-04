package tech.feily.vehiclerecognition.main;

import java.io.IOException;
import java.util.Scanner;

public class Sample {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, InterruptedException {
		new Recognition(new Scanner(System.in).nextLine());
	}
}
