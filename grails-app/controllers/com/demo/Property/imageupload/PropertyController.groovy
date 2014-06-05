package com.cygnet.showmyhome.imageupload


import static org.springframework.http.HttpStatus.*

import gui.ava.html.image.generator.HtmlImageGenerator;
import grails.transaction.Transactional

import java.awt.Color;
import java.awt.Dimension
import java.awt.Font
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


import com.cygnet.showmyhome.util.GenerateVideoFromImage;
import com.cygnet.showmyhome.util.UploadAndMeargeImage;

@Transactional(readOnly = true)
class PropertyController {
	def grailsApplication
	def burningImageService
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Property.list(params), model:[propertyInstanceCount: Property.count()]
    }

    def show(Property propertyInstance) {
        respond propertyInstance
    }

    def create() {
        respond new Property(params)
    }

    @Transactional
    def save(Property propertyInstance) {
		println "caption value ::"+propertyInstance
        if (propertyInstance == null) {
            notFound()
            return
        }
		def file1 = request.getFile('theme')
		println "theme data -----"+ file1
		def file = request.getFile('foto')
		println "file data -----"+ file.getOriginalFilename()
		println "output file path : " +grailsApplication.config.grails.showmyhome.image.location
		
		//util code
		UploadAndMeargeImage upload= new UploadAndMeargeImage();
		 BufferedImage img1 = ImageIO.read(file.getInputStream());
	     BufferedImage img2 = ImageIO.read(file1.getInputStream());
		  
		  upload.mergeImageWithTextAndFrame(propertyInstance.toString(), img1, img2, grailsApplication.config.grails.showmyhome.image.location+file.getOriginalFilename());
		
		//plugin code 
		/*burningImageService.doWith(file, grailsApplication.config.grails.showmyhome.image.location)
		.execute {img ->
							img.scaleAccurate(500,400)
							img.text(Color.RED,new Font('Arial', Font.PLAIN, 25),{
                            it.write(propertyInstance.toString(), 80, 390)						
                        })					
                   }*/
		 

        propertyInstance.save flush:true
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'image.upload.success', 
					args: [message(code: 'propertyInstance.label', default: 'Image'), propertyInstance.id])
                redirect propertyInstance
            }
            '*' { respond propertyInstance, [status: CREATED] }
        }
    }

    def edit(Property propertyInstance) {
        respond propertyInstance
    }

    @Transactional
    def update(Property propertyInstance) {
        if (propertyInstance == null) {
            notFound()
            return
        }

        if (propertyInstance.hasErrors()) {
            respond propertyInstance.errors, view:'edit'
            return
        }

        propertyInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Property.label', default: 'Property'), propertyInstance.id])
                redirect propertyInstance
            }
            '*'{ respond propertyInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Property propertyInstance) {

        if (propertyInstance == null) {
            notFound()
            return
        }

        propertyInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Property.label', default: 'Property'), propertyInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
	def convertToImage(Property propertyInstance){
		def description = params.foto
		def title = params.captionvalue
		println description
		println title
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator()
		Dimension d = new Dimension();
		d.setSize(830, 700);
		imageGenerator.setSize(d)
		println imageGenerator.getSize();
		imageGenerator.loadHtml(description)
		
		String location = grailsApplication.config.grails.showmyhome.image.location
		File file = new File(location + "/" + title + ".png");
		imageGenerator.saveAsImage(file);
		propertyInstance.save flush:true
		 redirect (uri:"/viewTextEditor")
	}
	
	def renderTextEditor() {
		redirect (uri:"/viewTextEditor")
	}
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'propertyInstance.label', default: 'Property'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
