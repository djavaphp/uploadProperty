package com.demo.Property.imageupload


import com.demo.Property.util.GenerateVideoFromImage
import java.text.SimpleDateFormat;




class ProcessVideoController {
	def grailsApplication
	
	def processvideo() {
		
		Calendar calendar = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd_M_yyyy");
		String date = sdf.format(new Date());
		
		def  hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
		def minute     = calendar.get(Calendar.MINUTE);
		def second     = calendar.get(Calendar.SECOND);
		def millisecond= calendar.get(Calendar.MILLISECOND);
		println calendar.getTimeInMillis();
		
		String fileName = 'PropertyView'+date+'_'+hourOfDay+'_'+minute+'_'+second+'_'+millisecond
		
		
		// generate video file from list of available images.
		GenerateVideoFromImage generatevideo = new GenerateVideoFromImage()
		generatevideo.generateVideoFromImage(grailsApplication.config.grails.showmyhome.image.location,
			grailsApplication.config.grails.showmyhome.video.file.location,fileName+'.mp4')

		
		render(contentType: "video/mp4",
			file: new File("c:\\showmyhome\\videos\\${fileName}.mp4"),
			fileName: "${fileName}.mp4")
		
	
	}

	
}
