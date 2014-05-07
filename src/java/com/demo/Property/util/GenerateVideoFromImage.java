package com.demo.Property.util;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;

public class GenerateVideoFromImage {
	
	private static Dimension screenBounds;
	
	
	public boolean generateVideoFromImage(String imageLocation,String videoLocation,String outputFileName) {
		
		boolean status = true;
		
		final File dir = new File(imageLocation);
		final String[] EXTENSIONS = new String[]{
	        "gif", "png", "bmp", "jpg", "jpeg" // and other formats you need
	    };
		
		final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
		        @Override
		        public boolean accept(final File dir, final String name) {
		            for (final String ext : EXTENSIONS) {
		                if (name.endsWith("." + ext)) {
		                    return (true);
		                }
		            }
		            return (false);
		        }
	    };
	    
	    if (dir.isDirectory()) {
	    	
			 // let's make a IMediaWriter to write the file.
			final IMediaWriter writer = ToolFactory.makeWriter(videoLocation+"//"+outputFileName);
			long startTime = System.nanoTime();
			screenBounds = Toolkit.getDefaultToolkit().getScreenSize();
			writer.addVideoStream(0, 0, screenBounds.width/2, screenBounds.height/2);
			
	    	 for (final File f : dir.listFiles(IMAGE_FILTER)) {
	    		 try {
	    			 BufferedImage bgrScreen = getImage(f);	
	    			 writer.encodeVideo(0, bgrScreen, System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
	    			 Thread.sleep(3000);
	    		 } catch (InterruptedException e) {
	    			 status = false;
	    		 }
	    	 }
	    	 writer.close();	
	    }
		 return status;
	}
	
	private BufferedImage getImage(File fileObj) {
		BufferedImage buffered  = null;
		try { 
			BufferedImage img = ImageIO.read(fileObj);
			Image image = img.getScaledInstance(screenBounds.width/2, screenBounds.height/2, Image.SCALE_SMOOTH);
			buffered = new BufferedImage(screenBounds.width/2, screenBounds.height/2, BufferedImage.TYPE_3BYTE_BGR);
			buffered.getGraphics().drawImage(image, 0, 0 , null);
		} catch (Exception ex) {
			buffered = null;
		}
		return buffered;
	}
}
