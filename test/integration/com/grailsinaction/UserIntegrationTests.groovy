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
	
	void testEvilTest() {
	
		def user = new User(userId: 'chuck_norris',
			password: 'tiny', homepage: 'not-a-url')
		assertFalse user.validate()
		assertTrue user.hasErrors()
		
		def errors = user.errors
		
		assertEquals "size.toosmall",
			errors.getFieldError("password").code
		assertEquals "tiny",
			errors.getFieldError("password").rejectedValue
		
		assertEquals "url.invalid",
			errors.getFieldError("homepage").code
		assertEquals "not-a-url",
			errors.getFieldError("homepage").rejectedValue
		
		assertNull errors.getFieldError("userId")
		
	}
	
	void testEvilSaveCorrected() {
		
		def user = new User(userId: 'chuck_norris',
			password: 'tiny', homepage: 'not-a-url')
		assertFalse(user.validate())
		assertTrue(user.hasErrors())
		assertNull user.save()
		
		user.password = "fistfist"
		user.homepage = "http://www.chucknorrisfacts.com"
		assertTrue(user.validate())
		assertFalse(user.hasErrors())
		assertNotNull user.save()
		
	}
	
	

}
