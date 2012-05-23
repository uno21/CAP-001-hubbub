package com.grailsinaction

import grails.test.GrailsUnitTestCase

class PostServiceTests extends GrailsUnitTestCase {
	
	void testCreatePost() {
		def user = new User(userId:'ales87',password:'12345678') 
		
		def userControl = mockFor(UserService)
		userControl.demand.findByUserId(1..1) { String userId -> user }
		userControl.demand.addPost(1..1) { User u, Post p -> void}
		userControl.demand.save(1..1) { User u -> true }
		
		def testService = new PostService()
		testService.userService = userControl.createMock()
		
		def posted = testService.createPost('ales87', 'Nuevo Post')
		
		assert 'Nuevo Post' == posted.content
		
		userControl.verify()
	}
}
