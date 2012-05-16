package com.grailsinaction

import grails.test.GrailsUnitTestCase;

class UserIntegrationTests extends GroovyTestCase {

	void testFirstSaveEver() {
		
		def user = new User(userId: 'joe', password: 'secret',
				homepage: 'http://www.grailsinaction.com')
		assertNotNull user.save()
		assertNotNull user.id
		
		User foundUser = User.get(user.id)
		assertEquals 'joe', foundUser.userId
		
	}
	
	void testSaveAndUpdate() {
		
		def user = new User(userId: 'joe', password: 'secret',
				homepage: 'http://www.grailsinaction.com')
		assertNotNull user.save()
		
		def foundUser = User.get(user.id)
		foundUser.password = 'sesame'
		foundUser.save()
		
		def editedUser = User.get(user.id)
		assertEquals 'sesame', editedUser.password
		
	}
	
	void testSaveThenDelete() {
		
		def user = new User(userId: 'joe', password: 'secret',
			homepage: 'http://www.grailsinaction.com')
		assertNotNull user.save()
		
		def foundUser = User.get(user.id)
		foundUser.delete()
		
		assertFalse User.exists(foundUser.id)
		
	}
	
	

}
