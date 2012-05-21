package com.grailsinaction

class UserController {
	
	static navigation = [
		[group:'tabs', action:'search', order: 90],
		[action: 'advSearch', title: 'Advanced Search', order: 95],
		[action: 'register', order: 99, isVisible: { true }]
	]

    def scaffold = true
	
	def search = { 
	}
	
	def results = {
		def users = User.findAllByUserIdLike("%${params.userId}%")
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
	
	def register = {
		def user = new User(params)
		
		if (user.validate()) {
			user.save()
			flash.message = "Seccesfuly Created User"
				redirect(uri: '/')	
		} else {
			flash.messages = "Error Registering User"
			return [ user: user ]
		}
		
	}
	
	def profile = {
		def profile = Profile.findById(params.id)
		def user = User.findByProfile(profile)
		[ profile : profile, following : user.following]
	}
	
}
