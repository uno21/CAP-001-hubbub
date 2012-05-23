package com.grailsinaction

class PostController {
	
	def postService
  //  def scaffold = true
	
	def createPost = {
		postService.createPost(session.userId,params.content)
		redirect(controller:'user', action:'showPosts')	
	}
	
	def timeline = {
		def user = User.findByUserId(params.id)
		[ user : user ]
	}
	
	def add_Post = {
	
			def newPost = 
				postService.createPost(params.id, 
								params.content)
			flash.message = "Added new post: ${newPost.content}"	
		
		redirect(action: 'timeline', id: params.id)
	}
	
}
