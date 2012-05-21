package com.grailsinaction

class User {
	
	String userId
	String password
	
	Date dateCreated
	Profile profile
	
    static constraints = {
		userId(size: 3..20, unique: true, blank: false)
		password(size: 6..8, blank: false, validator: { passwd, user -> 
			return passwd != user.userId
		})
		dateCreated()
		profile(nullable: true)
    }
	
	static hasMany = [ posts : Post, tags : Tag, following : User ]
	
	static mapping = {
		following lazy:false
		profile  lazy:false
		posts sort:'dateCreated'
	}
}
