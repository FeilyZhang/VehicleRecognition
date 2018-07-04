package tech.feily.vehiclerecognition.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class CutImage {

    private  double x, y, width, height ;

    public CutImage( double x, double y, double width, double height) {
        this .x = x ;
        this .y = y ;
        this .width = width ;
        this .height = height ;
    }

    public void cut(String imgPath, String savePath)throws IOException {

        FileInputStream is =  null ;
        ImageInputStream iis = null ;

        try {
            is =new FileInputStream(imgPath);
            Iterator < ImageReader > it=ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = it.next();
            iis = ImageIO.createImageInputStream(is);
            reader.setInput(iis, true ) ;
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect =  new Rectangle((int)x, (int)y, (int)width, (int)height);
            param.setSourceRegion(rect);
            BufferedImage bi=reader.read(0,param);
            ImageIO.write(bi,"jpg",new File(savePath));
        } finally {
            if (is != null ) is.close() ;
            if (iis != null ) iis.close();
        }

    }
    
}
