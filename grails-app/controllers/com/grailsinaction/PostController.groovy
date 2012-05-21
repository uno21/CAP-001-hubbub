package com.grailsinaction

class PostController {
	
	static navigation = [
		[group:'tabs', action:'timeline', title:'My timeline', order:0],
		[action:'global', title: 'Global Timeline', order:1]	
	]
	
	def postService
  //  def scaffold = true
	
	def index = {
		if (!params.id) 
			params.id = "ale09"	
		redirect(action: 'timeline',params: params)		
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
	
	def paginatePost = {
		def posts = User.fidByUserId(params.id).posts
		[post: posts, postCount:posts.count]
	}
}
