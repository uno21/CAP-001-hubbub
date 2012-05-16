package com.grailsinaction

import grails.test.GrailsUnitTestCase;

class UserIntegrationTests extends GroovyTestCase {

	void testFirstSaveEver() {
		
		def user = new User(userId: 'joe', password: 'secret')
		assertNotNull user.save()
		assertNotNull user.id
		
		User foundUser = User.get(user.id)
		assertEquals 'joe', foundUser.userId
	}
	
	void testSaveAndUpdate() {
		
		def user = new User(userId: 'joe', password: 'secret')
		assertNotNull user.save()
		
		def foundUser = User.get(user.id)
		foundUser.password = 'sesame'
		foundUser.save()
		
		def editedUser = User.get(user.id)
		assertEquals 'sesame', editedUser.password
	}
	
	void testSaveThenDelete() {
		
		def user = new User(userId: 'joe', password: 'secret')
		assertNotNull user.save()
		
		def foundUser = User.get(user.id)
		foundUser.delete()
		
		assertFalse User.exists(foundUser.id)
	}
	
	void testEvilTest() {
	
		def user = new User(userId: 'chuck_norris', password: 'tiny')
		assertFalse user.validate()
		assertTrue user.hasErrors()
		
		def errors = user.errors
		
		assertEquals 'size.toosmall',
			errors.getFieldError('password').code
		assertEquals 'tiny',
			errors.getFieldError('password').rejectedValue
		
		assertNull errors.getFieldError('userId')
	}
	
	void testEvilSaveCorrected() {
		
		def user = new User(userId: 'chuck_norris', password: 'tiny')
		assertFalse(user.validate())
		assertTrue(user.hasErrors())
		assertNull user.save()
		
		user.password = 'fistfist'
		assertTrue(user.validate())
		assertFalse(user.hasErrors())
		assertNotNull user.save()
	}
	
	void testUserIdMatchPasswordError() {
		
		def user = new User(userId: 'chucky', password: 'chucky')
		assertFalse(user.validate())
		assertTrue(user.hasErrors())
		
		def errors = user.errors
		assertEquals 'validator.invalid',
			errors.getFieldError('password').code
		assertEquals 'chucky',
			errors.getFieldError('password').rejectedValue
	}
	
	void testFollowing() {
	
		def glen = new User(userId: 'glen', password:'password').save()
		def peter = new User(userId: 'peter', password:'password').save()
		def sven = new User(userId: 'sven', password:'password').save()
		
		glen.addToFollowing(peter)
		glen.addToFollowing(sven)
		assertEquals 2, glen.following.size()
		
		sven.addToFollowing(peter)
		assertEquals 1, sven.following.size()
	}
	
}
