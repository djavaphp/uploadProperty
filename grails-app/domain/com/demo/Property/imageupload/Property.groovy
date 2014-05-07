package com.demo.Property.imageupload



import pl.burningice.plugins.image.ast.FileImageContainer

@FileImageContainer(field = 'photo')
class Property {
	String captionvalue
		
	static constraints = {
		captionvalue(nullable: false, blank: false, unique: true) 
		//photo(nullable:false,blank:false)
	}

	String toString() {
		captionvalue
	}

}
