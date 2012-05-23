package com.grailsinaction

class UserControllerTests extends grails.test.ControllerUnitTestCase {
	
	void testDoLoginSuccess() {
		
		this.controller.params.userId = 'ales09'
		this.controller.params.password = '1234567'
		
		def userControl = mockFor(UserService)
		userControl.demand.authenticate(1..1) {  String userId, String password -> true }
		
		this.controller.userService = userControl.createMock()
		
		this.controller.doLogin()
		
		assert 'ales09' == this.controller.session.userId
		assert 'home' == this.controller.redirectArgs['action']
	}
	
	void testDoLoginFail() {
		
		this.controller.params.userId = 'ales08'
		this.controller.params.password = '1234567'
		
		def userControl = mockFor(UserService)
		userControl.demand.authenticate(1..1) { String userId, String password -> false }
		
		this.controller.userService = userControl.createMock()
		
		this.controller.doLogin()
		
		assert 'login' == this.controller.redirectArgs['action']
		assert 'Invalid User Id or Password' == this.controller.flash.message
		
	}
	
	void testRegisterSuccess() {
		
		this.controller.params.userId = 'ales08'
		this.controller.params.password = '1234567'
		
		def userControl = mockFor(UserService)
		userControl.demand.register(1..1) { User user -> true }
		
		this.controller.userService = userControl.createMock()
		
		this.controller.register()
		
		assert 'Seccesfuly Created User' == this.controller.flash.message
		assert 'ales08' == this.controller.session.userId
		assert 'home' == this.controller.redirectArgs['action']
	}
	
	void testRegisterFail() {
		
		this.controller.params.userId = 'ales08'
		this.controller.params.password = '1234567'
		
		def userControl = mockFor(UserService)
		userControl.demand.register(1..1) { User user -> false }
		
		this.controller.userService = userControl.createMock()
		
		this.controller.register()
		
		assert 'Error Registering User' == this.controller.flash.message
		
	}
	
	void testLogout() {
		
		this.controller.logout()
		
		assert null == this.controller.session.userId
		assert 'login' == this.controller.redirectArgs['action']	
	}
	
	void testResults() {
		
		def foundUser = new User(userId:'ales87',password:'12345678')
		
		this.controller.params.userId = 'ales87'
		def userControl = mockFor(UserService)
		userControl.demand.searchByUserId(1..1) {String userId -> [foundUser] }
		
		this.controller.userService = userControl.createMock()
		def model = this.controller.results()
		
		assert 'ales87' == model['term']
		assert 'ales87' == model['users'][0].userId
		assert '12345678' == model['users'][0].password
		
	}
}
