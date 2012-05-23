package com.grailsinaction

class UserController {
	
	def userService;
		
	static navigation = [
		[group:'tabs', action:'search', order: 1],
		[action: 'advSearch', title: 'Advanced Search', order: 2],
		[action: 'showPosts', title: 'My Post', order: 3],
		[action: 'showProfile', title: 'My Profile', order: 3],
		[action: 'logout', title: 'Logout', order: 5]
	]

    def scaffold = true
	
	def login = { }
	
	def doLogin = {
			if ( userService.authenticate(params.userId,params.password)) {
				session.userId = params.userId
				redirect(controller:"user", action:"home",params:[userId:params.userId])
			} else { 
				flash.message = "Invalid User Id or Password"
				redirect(action:"login")
			}
	}
	
	def register = {
		def user = new User(params)
		
		if (userService.register(user)) {
			flash.message = "Seccesfuly Created User"
			session.userId = user.userId
			redirect(controller:"user", action:"home",params:[userId:user.userId])
		} else {
			flash.message = "Error Registering User"
			return [ user: user ]
		}
		
	}
	
	def home = {
		return [userId:params.userId]
	}
	
	def search = { 
	}
	
	def results = {
		def users = userService.searchByUserId(params.userId)
		return [ users: users, term: params.userId ]	
	}
	
	def advSearch = {
	}
	
	def advResults = {
		def profileProps =
			Profile.metaClass.properties*.name
		
		params.toMapString()	
		
		def profiles = Profile.withCriteria {
			"${params.queryType}" {
				
				params.each { field, value ->
					
					if (profileProps.grep(field) && value ) {
						ilike(field,value)
					}
				}
			}
			
		}	
		
		[ profiles: profiles]	
		
	}
	
	def showPosts = {
		def user = userService.findByUserId(session.userId)
		return [user : user]
	}
	
	def showProfile = {
		def user = userService.findByUserId(session.userId)
		return [user : user]
	}
	
	def profile = {
		def profile = Profile.findById(params.id)
		def user = User.findByProfile(profile)
		[ profile : profile, following : user.following]
	}
	
	def logout = {
		session.userId = null
		redirect(controller:"user",action:"login")
	}
	
}
