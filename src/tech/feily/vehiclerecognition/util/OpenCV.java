package tech.feily.vehiclerecognition.util;


import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
 
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.util.Date;

public class OpenCV {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
    }
 
    public static String[] finalImgPath(String imgPath)  throws InterruptedException {
    	
    	String timestamp = String.valueOf(new Date().getTime());
    	
    	String[] imgTempPath = {Util.TEMP_IMG + timestamp + "gray.jpg", Util.TEMP_IMG + timestamp + "binary.jpg", Util.TEMP_IMG + timestamp + "dest.jpg"};
 
        /**
         * 1. ��ȡԭʼͼ��ת��ΪOpenCV��Mat���ݸ�ʽ
         */
 
        Mat srcMat = Imgcodecs.imread(imgPath);  
 
 
        /**
         * 2. ǿԭʼͼ��ת��Ϊ�Ҷ�ͼ��
         */
        Mat grayMat = new Mat(); 
        Imgproc.cvtColor(srcMat, grayMat, Imgproc.COLOR_RGB2GRAY);
        BufferedImage grayImage =  toBufferedImage(grayMat);
        saveJpgImage(grayImage, imgTempPath[0]);
 
        /**
         * 3���ԻҶ�ͼ����ж�ֵ������
         */
        Mat binaryMat = new Mat(grayMat.height(),grayMat.width(),CvType.CV_8UC1);
        Imgproc.threshold(grayMat, binaryMat, 20,255, Imgproc.THRESH_BINARY);
        BufferedImage binaryImage =  toBufferedImage(binaryMat);
        saveJpgImage(binaryImage,  imgTempPath[1]);

        /**
         * 4��ͼ��ʴ---��ʴ���ø��ӿ�,��.����ʶ��--ʹ��3*3��ͼƬȥ��ʴ
         */
        Mat destMat = new Mat(); 
        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
        Imgproc.erode(binaryMat,destMat,element);
        BufferedImage destImage =  toBufferedImage(destMat);
        saveJpgImage(destImage,  imgTempPath[2]);
        
        return imgTempPath;
    }
    
    private static BufferedImage toBufferedImage(Mat matrix) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (matrix.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = matrix.channels() * matrix.cols() * matrix.rows();
        byte[] buffer = new byte[bufferSize];
        matrix.get(0, 0, buffer); 
        BufferedImage image = new BufferedImage(matrix.cols(), matrix.rows(), type);
        final byte[] targetPixels = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        return image;
    }
 
    private static void saveJpgImage(BufferedImage image, String filePath) {
 
        try {
            ImageIO.write(image, "jpg", new File(filePath));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }
}
