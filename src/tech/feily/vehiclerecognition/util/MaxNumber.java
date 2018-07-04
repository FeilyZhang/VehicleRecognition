package tech.feily.vehiclerecognition.util;
class MaxNumber {
	public double getMaxNumber(double[] array) {
		int iPos;
		double arrTemp;
		double[] arr = array;
		for (int i = 0; i < arr.length - 1; i++) {
			arrTemp = arr[i];
			iPos = i;
			for (int j = 1; j < arr.length; j++) {
				if (arr[j] < arrTemp) {
					arrTemp = arr[j];
					iPos = j;
				}
			}
			arr[iPos] = arr[i];
			arr[i] = arrTemp;
			System.out.println(arr[i]);
		}
		return arr[arr.length - 1];
	}
    public static double bubbleSort(double[] numbers)
    {
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


