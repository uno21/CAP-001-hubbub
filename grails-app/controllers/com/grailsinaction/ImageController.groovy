package com.grailsinaction

class PhotoUploadCommand {
	byte[] photo
	String userId
	
	static constraints = {
		photo(nullable: true, maxSize: 1024 * 4)
	} 
}
	

class ImageController {

    def upload = { PhotoUploadCommand puc ->
		def user = User.findByUserId(puc.userId)
		user.profile.photo = puc.photo
		redirect(action: 'view', id: puc.userId,params: [userId: puc.userId])
	}
	
	def form= {
		[ userList : User.list() ]	
	}
	
	def view = {
		
	} 
	
	def renderImage = {
		def profile = Profile.findById(params.id)
		if (profile?.photo) {
			response.setContentLength(profile.photo.length)
			response.outputStream.write(profile.photo)
		} else {
			response.sendError(404)
		}		
	}

}
