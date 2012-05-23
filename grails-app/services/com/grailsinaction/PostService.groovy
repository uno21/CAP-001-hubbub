package com.grailsinaction

class PostException extends RuntimeException {
	String message
	Post post		
}

class PostService {
	
	def userService
	
	boolean transactional = true

    Post createPost(String userId, String content) {
		def user = userService.findByUserId(userId)
		if (user) {
			def post = new Post(content: content)
			userService.addPost(user, post)
			if (userService.save(user)) {
				return post	
			} else {
				throw new PostException(
					message: 'Invalid or empty post', post: post)
			}
		}
		throw new PostException(messages: "Invalid User Id")
	}
		
}
