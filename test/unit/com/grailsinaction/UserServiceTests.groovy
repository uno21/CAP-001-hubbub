package com.grailsinaction

import grails.test.GrailsUnitTestCase

class UserServiceTests extends GrailsUnitTestCase{

   void testAuthenticate() {
	   
	   def user = new User(userId:"user",password:"1234567")
	   mockDomain(User,[user])
	   
	   def testService = new UserService()
	   assert true == testService.authenticate("user", "1234567") 
	   assert false == testService.authenticate("user", "123457")
	   assert false == testService.authenticate("user09", "1234567")
   			
   }
   
   void testSaveAndFindByUserId() {
    	mockDomain(User,[])
		def testService = new UserService()
		
		def user = new User(userId:"user",password:"1234567")
		testService.save(user)
		
		def foundUser = testService.findByUserId("user")
		
		assert "user" , foundUser.userId
		assert "1234567", foundUser.password
		
		def notFoundUser = testService.findByUserId("user09")
		assert null == notFoundUser
			   
   }
   
   void testRegister() {
		mockDomain(User,[])
		def testService = new UserService() 
		
		assert true == testService.register(new User(userId:"eze.giorgi", password:"12345678",profile:new Profile(fullName:'Alejandro Giorgi')))
		def registerUser = testService.findByUserId("eze.giorgi")
		
		assert "eze.giorgi" , registerUser.userId
		assert "12345678", registerUser.password
		assert 'Alejandro Giorgi', registerUser.profile.fullName
		
		assert false == testService.register(new User(userId:"A", password:"1")) 
		def notRegisterUser = testService.findByUserId("A")
		
		assertNull(notRegisterUser)
		
   }
   
   void testSearchByUserId() {
		mockDomain(User,[
			new User(userId:'ales87',password:'12345678'),
			new User(userId:'pepe',password:'pepe1234'),
			new User(userId:'pepe12',password:'pepon1')])
		
		def testService = new UserService()
		
		def foundUser = testService.searchByUserId('ales87')
		
		assert 1 == foundUser.size
		assert 'ales87' == foundUser[0].userId
		assert '12345678' == foundUser[0].password
		
		def foundUsers = testService.searchByUserId('pepe')
		
		assert 2 == foundUsers.size
		assert 'pepe' == foundUsers[0].userId
		assert 'pepe1234' == foundUsers[0].password
		assert 'pepe12' == foundUsers[1].userId
		assert 'pepon1' == foundUsers[1].password
		
		def notFoundUsers = testService.searchByUserId('juan')
		
		assert 0 == notFoundUsers.size
   }
   
   void testAddPost() {
		def user = new User(userId:'ales87',password:'12345678')
		def post = new Post(content:'Nuevo post')
		
		mockDomain(User,[user])
		
		assert null == user.posts
		
		def testService = new UserService()
		testService.addPost(user, post)
		
		def addedPosts = user.posts.collect { it.content }
		
		assert [post.content] == addedPosts
		  
   }
	
}
