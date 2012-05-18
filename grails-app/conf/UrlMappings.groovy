class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	
		"/users/$id" {
			controller = "post"
			action = "timeline"
		}
	}
}
