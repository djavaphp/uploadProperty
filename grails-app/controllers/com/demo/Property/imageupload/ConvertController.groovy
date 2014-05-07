package com.demo.Property.imageupload

import gui.ava.html.image.generator.HtmlImageGenerator;


class ConvertController {
	def grailsApplication
	def renderTextEditor() {
		redirect (uri:"/viewTextEditor")
	}
	def convertToImage(){
		def description = params.myeditor
		def title = params.title
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator()
		imageGenerator.loadHtml(description)
		String location = grailsApplication.config.grails.showmyhome.image.location
		File file = new File(location + "/" + title + ".png");
		imageGenerator.saveAsImage(file);
		 redirect (uri:"/viewTextEditor")
	}
}
