package com.demo.Property.imageupload


import static org.springframework.http.HttpStatus.*

import gui.ava.html.image.generator.HtmlImageGenerator;
import grails.transaction.Transactional
import java.awt.Font


import com.demo.Property.imageupload.Property;

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
		def file = request.getFile('foto')
		println "file data -----"+ file
		burningImageService.doWith(file, grailsApplication.config.grails.showmyhome.image.location)
                   .execute {img ->
                        img.text(new Font('Arial', Font.PLAIN, 50),{
                            it.write(propertyInstance.toString(), 200, 200)
                            
                        })
                   }

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
