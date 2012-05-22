package com.pennapps.pennmeet.helpers;

//import java.awt.Color; 
//import java.awt.Graphics2D; 
//import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import javax.imageio.ImageIO; 

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import com.google.zxing.ReaderException; 
import com.google.zxing.WriterException; 
import com.google.zxing.common.BitMatrix; 
import com.google.zxing.common.DecoderResult; 
import com.google.zxing.qrcode.decoder.Decoder; 
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel; 
import com.google.zxing.qrcode.encoder.Encoder; 
import com.google.zxing.qrcode.encoder.QRCode;

public class QREncoder { 
		
	String str = "";
        public QREncoder(String idInput) {
                str = idInput; 
                //Encode the text 
        }
        
        public void encode() throws Exception{
                QRCode qrcode = new QRCode(); 
                try { 
                        Encoder.encode(str, ErrorCorrectionLevel.H, qrcode); 
                } catch (WriterException e) { 
                        // TODO Auto-generated catch block 
                        e.printStackTrace(); 
                } 
                int magnify = 50; //The resolution of the QRCode 
                byte[][] matrix = qrcode.getMatrix().getArray(); 
                int size = qrcode.getMatrixWidth()*magnify;
                
                Canvas im = new Canvas (Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888));
                //Make the BufferedImage that are to hold the QRCode 
                /*BufferedImage im = new BufferedImage (size,size,BufferedImage.TYPE_INT_RGB); 
                im.createGraphics(); 
                Graphics2D g = (Graphics2D)im.getGraphics(); 
                g.setColor(Color.WHITE); 
                g.fillRect(0, 0, size, size);*/ 
                //BitMatrix for validation 
                BitMatrix bm = new BitMatrix(qrcode.getMatrixWidth()); 
                //paint the image using the ByteMatrik 
                for(int h = 0;h<qrcode.getMatrixWidth();h++){ 
                        for(int w = 0;w<qrcode.getMatrixWidth();w++){ 
                                //Find the colour of the dot 
                                if(matrix[w][h] == 0) 
                                        im.drawColor(Color.WHITE); 
                                else{ 
                                        im.drawColor(Color.BLACK); 
                                        bm.set(h, w);//build the BitMatrix 
                                } 
                                //Rect qr = new Rect(h*magnify, w*magnify, w*magnify + magnify, h*magnify);
                                //Paint the dot 
                                im.drawRect(h*magnify, w*magnify, w*magnify + magnify, h*magnify + magnify, null); 
                        
                        } 
                } 
                //Try to decode the BitMatrix 
                Decoder decoder = new Decoder(); 
                DecoderResult result = null; 
                try { 
                        result = decoder.decode(bm); 
                } catch (ReaderException e1) { 
                        // TODO Auto-generated catch block 
                        e1.printStackTrace(); 
                } 
                //Compare the decoded BitMatrix with the input string 
                if(!result.getText().equals(str)) 
                        throw new Exception("Error encodeing the QRCode"); 
                //Write the image to a file 
     //           try { 
    //                    ImageIO.write(im, "png", new File("@drawable/" + str + ".png")); 
      //          } catch (IOException e) { 
                        // TODO Auto-generated catch block 
   //                     e.printStackTrace(); 
     //           } 
                
               
        }
}