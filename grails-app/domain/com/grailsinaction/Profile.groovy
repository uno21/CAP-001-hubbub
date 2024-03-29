package com.grailsinaction

class Profile {
	
	byte[] photo
	String fullName
	String bio
	String homepage
	String email
	String timezone
	String country
	String jabberAddress
	
	static constraints = {
		fullName(nullable: true)
		bio(nullable: true, maxSize: 1000)
		homepage(url: true, nullable: true)
		email(email: true, nullable: true)
		photo(nullable: true, maxSize: 1024 * 4)
		country(nullable: true)
		timezone(nullable: true)
		jabberAddress(email: true, nullable: true)
	}
		
	
	String toString() {
		"Profile for ${fullName} (${id})" 
	}

	static belongsTo = User
}
