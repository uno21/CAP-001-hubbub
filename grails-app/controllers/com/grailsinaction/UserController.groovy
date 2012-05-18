package com.grailsinaction

class UserController {

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
		[ profile : profile]
	}
}
