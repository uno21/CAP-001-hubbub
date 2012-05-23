package hubbub

class UtilTagLib {
	
	static namespace = "h"
	
	def tinyThumbnail = { attrs ->
		def userId = attrs.userId
			out << "<img src='"
			out << g.createLink(action: "tiny",
				controller: "image", id: userId)
			out << "' alt='${userId}'"
			out << "' title='${userId}'"
	}
	
	def isUserLoggedIn = { attrs, body ->
		if (attrs.userId) {
			out << body()
		}
		
	}

}
