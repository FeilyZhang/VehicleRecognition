/**
 * 
 */
/**
 * @author HP
 *
 */
package tech.feily.vehiclerecognition.main;

class Main {
	static String LICENSE_PLATE = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate?access_token=";
	static String CAR = "https://aip.baidubce.com/rest/2.0/image-classify/v1/car?access_token=";
	static String ACCESS_TOKEN = "C:\\Users\\HP\\Desktop\\access_token.txt";
	static String RESULT_PATH = "C:\\Users\\HP\\Desktop\\result.txt";
	static String ERROR_NECOGNITION = "δ��⵽������Ϣ����ȷ��ͼ���Ƿ��г��ƻ��ƺ��Ƿ���Ϲ淶";
	static String TITLE_LICENSE = "���Ƽ��������";
	static String TITLE_CAR = "���ͼ��������";
	static String LICENSEPLATE_FOLDER = "C:\\Users\\HP\\Desktop\\";
	//static String CAR_FOLDER = "C:\\Users\\HP\\Desktop\\";
	static String[] LICENSEKEYCHINESE = {"��ɫ", "����", "��������"};
	static String[][] SAMPLE_IMG_PATH = {{"C:\\Users\\HP\\Desktop\\sampleimg\\gongjiaolei.jpg", "C:\\Users\\HP\\Desktop\\sampleimg\\gongjiaolei1.jpg"
		,"C:\\Users\\HP\\Desktop\\sampleimg\\gongjiaolei2.jpg", "C:\\Users\\HP\\Desktop\\sampleimg\\gongjiaolei3.jpg", "C:\\Users\\HP\\Desktop\\sampleimg\\gongjiaolei4.jpg"} 
		,{"C:\\Users\\HP\\Desktop\\sampleimg\\mianbaolei.jpg", "C:\\Users\\HP\\Desktop\\sampleimg\\mianbaolei1.jpg"
		,"C:\\Users\\HP\\Desktop\\sampleimg\\mianbaolei2.jpg", "C:\\Users\\HP\\Desktop\\sampleimg\\mianbaolei3.jpg", "C:\\Users\\HP\\Desktop\\sampleimg\\mianbaolei4.jpg"} 
		,{"C:\\Users\\HP\\Desktop\\sampleimg\\yueyelei.jpg", "C:\\Users\\HP\\Desktop\\sampleimg\\yueyelei1.jpg"
		,"C:\\Users\\HP\\Desktop\\sampleimg\\yueyelei2.jpg", "C:\\Users\\HP\\Desktop\\sampleimg\\yueyelei3.jpg","C:\\Users\\HP\\Desktop\\sampleimg\\yueyelei4.jpg"} 
		,{"C:\\Users\\HP\\Desktop\\sampleimg\\jiaochelei.jpg", "C:\\Users\\HP\\Desktop\\sampleimg\\jiaochelei1.jpg"
		,"C:\\Users\\HP\\Desktop\\sampleimg\\jiaochelei2.jpg", "C:\\Users\\HP\\Desktop\\sampleimg\\jiaochelei3.jpg", "C:\\Users\\HP\\Desktop\\sampleimg\\jiaochelei4.jpg"} 
		,{"C:\\Users\\HP\\Desktop\\sampleimg\\kachelei.jpg", "C:\\Users\\HP\\Desktop\\sampleimg\\kachelei1.jpg"
		,"C:\\Users\\HP\\Desktop\\sampleimg\\kachelei2.jpg", "C:\\Users\\HP\\Desktop\\sampleimg\\kachelei3.jpg", "C:\\Users\\HP\\Desktop\\sampleimg\\kachelei4.jpg"}};
	static String[] SAMPLE_IMG_TAG = {"���������", "���������", "ԽҰ������", "��ͨ�γ���" ,"����������"};
}