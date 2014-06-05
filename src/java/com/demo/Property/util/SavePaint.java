package com.cygnet.showmyhome.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class SavePaint 
{
	private static final int IMG_WIDTH = 800;
	private static final int IMG_HEIGHT =600;
	public SavePaint(String var){
		try {
			
		
		BufferedImage img1 = ImageIO.read(new File("C:/Kitchen.jpeg"));
    	BufferedImage img2 = ImageIO.read(new File("C:/url.png"));

		/*int widthImg1 = img1.getWidth();
		int heightImg1 = img1.getHeight();

		int widthImg2 = img2.getWidth();
		int heightImg2 = img2.getHeight();*/

		BufferedImage img = new BufferedImage(
				IMG_WIDTH, // Final image will have width and height as
				IMG_HEIGHT, // addition of widths and heights of the images we already have
		BufferedImage.TYPE_INT_RGB);

		boolean image1Drawn = img.createGraphics().drawImage(img1, 0, 0, null); // 0, 0 are the x and y positions
		if(!image1Drawn) System.out.println("Problems drawing first image"); //where we are placing image1 in final image

		boolean image2Drawn = img.createGraphics().drawImage(img2, 0, 0, null); // here width is mentioned as width of
		if(!image2Drawn) System.out.println("Problems drawing second image"); // image1 so both images will come in same level
		// horizontally
		File final_image = new File("c:/Final.png"); // "png can also be used here"
		boolean final_Image_drawing = ImageIO.write(img, "png", final_image); //if png is used, write "png" instead "jpeg"
		if(!final_Image_drawing) System.out.println("Problems drawing final image");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		}
	
    public SavePaint()
    {
        try
        {
        	 BufferedImage image = ImageIO.read(new File("C:/Kitchen.jpeg"));
        	// BufferedImage image2 = ImageIO.read(new File("C:/pinkframe.jpeg"));
        	 

        	// load source images
        	/*BufferedImage image = ImageIO.read(new File("C:/Kitchen.jpeg"));
        	BufferedImage overlay = ImageIO.read(new File("C:/LivingRoom.jpeg"));

        	// create the new image, canvas size is the max. of both image sizes
        	int w = Math.max(image.getWidth(), overlay.getWidth());
        	int h = Math.max(image.getHeight(), overlay.getHeight());
        	BufferedImage combined = new BufferedImage(w, h, BufferedImage.SCALE_DEFAULT);

        	// paint both images, preserving the alpha channels
        	Graphics g = combined.getGraphics();
        	g.drawImage(image, 0, 0, null);
        	g.drawImage(overlay, 0, 0, null);

        	// Save as new image
        	ImageIO.write(combined, "jpeg", new File("C:/combined.jpeg"));*/
             ImageIO.write(drawTextOnImage("MY Dinning Area View", image,30),"jpeg", new File("C:/jmemPractice1.jpeg"));
        }
        catch(Exception exception)
        {
        	exception.printStackTrace();
            //code
        }
    }
    
    public BufferedImage drawTextOnImage(String text, BufferedImage image, int space) {
        BufferedImage bi = new BufferedImage(IMG_WIDTH,IMG_HEIGHT + space, BufferedImage.SCALE_DEFAULT);
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

    protected void paintComponent(Graphics g)
    {
        g.drawRect(50,50,50,50);
    }

    public static void main(String[] args)
    {
        new SavePaint("hello");
        new SavePaint();
    	
    }
}