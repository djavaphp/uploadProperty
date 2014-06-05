package com.cygnet.showmyhome.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class UploadAndMeargeImage {

	private static final int IMG_WIDTH = 800;
	private static final int IMG_HEIGHT =600;
	
	private static final int FRAME_IMG_WIDTH = 830;
	private static final int FRAME_IMG_HEIGHT = 700;

	private int space=30;
	
	
	
public UploadAndMeargeImage() {
		super();
	}

/** Method allows to type text on image
     * @param String text
     * @param BufferedImage image
     * @throws int space
     * @return BufferedImage Object
 *
 */
	public static BufferedImage drawTextOnImage(String text, BufferedImage image, int space) {
        BufferedImage bi = new BufferedImage(IMG_WIDTH,IMG_HEIGHT +space, BufferedImage.SCALE_DEFAULT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();     
        g2d.drawImage(image,0,0, IMG_WIDTH,IMG_HEIGHT, null);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Calibri", Font.BOLD, 20));
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        //center text at bottom of image in the new space
        g2d.drawString(text, (bi.getWidth() / 2) - textWidth / 2, bi.getHeight()-5);

        g2d.dispose();
        return bi;
    }
	
	 /**
	   * Save changed single image
	   * TODO: Check if this can be replaced by SaveCommand object
	   *
	   *@param text Specify text to write on image
	   * @param outputFilePath Specify path to output image
	   * @param image BufferedImage object representing image to manipulate
	   * @throws IOException 
	   */
	  public void saveImageWithText(String text, BufferedImage image,String outputFilePath) throws IOException {
		  ImageIO.write(UploadAndMeargeImage.drawTextOnImage(text, image,space),"jpeg", new File(outputFilePath));
	  }
	  
	  /**
	   * Merge image with frame and text and save into file system
	   * TODO: Check if this can be replaced by SaveCommand object
	   *
	   *@param text Specify text to write on image
	   * @param outputFilePath Specify path to output image
	   * @param image BufferedImage object representing image to manipulate
	   * @throws IOException 
	   */
	  public void mergeImageWithTextAndFrame(String text, BufferedImage origanal,BufferedImage frame,String outputFilePath) throws IOException {		
		  origanal=UploadAndMeargeImage.drawTextOnImage(text, origanal,space);
		  ImageIO.write(origanal, "png",new File("C:/hello.png")); 
			BufferedImage img = new BufferedImage(
					FRAME_IMG_WIDTH, // Final image will have width and height as
					FRAME_IMG_HEIGHT, // addition of widths and heights of the images we already have
			BufferedImage.TYPE_INT_RGB);
			
			boolean image1Drawn = img.createGraphics().drawImage(origanal, 0, 0, null); 
			if(!image1Drawn) System.out.println("Problems drawing first image"); 

			boolean image2Drawn = img.createGraphics().drawImage(frame, 0, 0,FRAME_IMG_WIDTH,FRAME_IMG_HEIGHT, null); 
			if(!image2Drawn) System.out.println("Problems drawing second image"); 
			// horizontally
			File final_image = new File(outputFilePath); 
			boolean final_Image_drawing = ImageIO.write(img, "png", final_image); 
			ImageIO.write(img, "png",new File("C:/final12.png"));
			if(!final_Image_drawing) System.out.println("Problems drawing final image");
	  }
	  
	  public static void main(String args[]){
		  try{
		  
		  System.out.println("Hello");
		  BufferedImage img1 = ImageIO.read(new File("C:/Kitchen.jpeg"));
	    	BufferedImage img2 = ImageIO.read(new File("C:/frame2.png"));

		  UploadAndMeargeImage obj=new UploadAndMeargeImage();
		  obj.mergeImageWithTextAndFrame("This is my Kitchen ! This is my Kitchen ! This is my Kitchen ! This is my Kitchen !" , img1, img2, "c:/Final1.png");
		  }catch(Exception e){
			  e.printStackTrace();
		  }
	  }
}
