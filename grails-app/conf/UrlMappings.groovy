class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
		"/property"(view:'/property/property')
		"/viewTextEditor" (view:"/property/textEditor")
		name renderTextEditor: "/renderTextEditor" (controller:'Property', action:'renderTextEditor')
		name convertToImage: "/convertToImage" (controller:'Property',action: 'convertToImage')
		
		name processvideowork: "/processvideowork" (controller:'ProcessVideo',action: 'processvideo')
		
	}
	
	
}
