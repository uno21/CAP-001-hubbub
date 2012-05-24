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
	
	def showPosts = {
		def user = User.findByUserId(session.userId)
		return [user : user]
	}
	
	def add = {
		def newPost = postService.createPost(session.userId, params.content)
		redirect(action:'showPosts')
	}
	
	def addPostAjax = {
		def newPost = postService.createPost(session.userId, params.content)
		def user = User.findByUserId(session.userId)
		def recentPost = Post.findAllByUser(user,[sort:'dateCreated', order: 'desc', max: 20])
		render(template:"postentries",collection: recentPost,var:'post')	
	}
}
