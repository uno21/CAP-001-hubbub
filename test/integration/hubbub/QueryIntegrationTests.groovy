package hubbub

import com.grailsinaction.Post
import com.grailsinaction.Profile
import com.grailsinaction.Tag
import com.grailsinaction.User

class QueryIntegrationTests extends GroovyTestCase {
	
	void testBasicDynamicFinders() {
		
		new User(userId: 'glen', password: 'secret',
			profile: new Profile(email: 'glen@glensmith.com')).save()
		new User(userId: 'peter', password: 'sesame',
			profile: new Profile(homepage: 'http://www.peter.com/')).save()
		
		def user = User.findByPassword('sesame')
		assertEquals 'peter',user.userId
		
		user = User.findByUserIdAndPassword('glen',
			'secret')
		assertEquals 'glen', user.userId
			
		def now = new Date()
			def users =
				User.findAllByDateCreatedBetween(now-1, now)
				assertEquals 2, users.size()
				
		def profiles = 
			Profile.findAllByEmailIsNotNull()
		assertEquals 1, profiles.size()
	}
	
	void testQueryByExample() {
		
		new User(userId: 'glen', password: 'password').save()
		new User(userId: 'peter', password: 'password').save()
		new User(userId: 'cynthia', password: 'sesame').save()
		
		def userToFind = new User(userId: 'glen')
		def u1 = User.find(userToFind)
		assertEquals('password', u1.password)
		
		userToFind = new User(userId: 'cynthia')
		def u2 = User.find(userToFind)
		assertEquals('cynthia', u2.userId)
		
		userToFind = new User(password: 'password')
		def u3 = User.findAll(userToFind)
		assertEquals(['glen', 'peter'], u3*.userId)
		
		
	}
	
	void testFindPostByCriteria() {
		
		def user = new User(userId: 'glen', password: 'secret',
						profile: new Profile(email: 'glen@glensmith.com')).save()
		
		def post = new Post(content: 'Grails post')
		def tag = new Tag(name: 'Grails')
										
		user.addToPosts(post)
		user.addToTags(tag)
		post.addToTags(tag)
		
		def foundPosts = Post.createCriteria().list {
			and {
				eq('user', user)
				tags {
					eq('name', 'Grails')
				}	
			}	
		}
		
		assertEquals(post.content,foundPosts.get(0).content)	
						
	}
	
	void testAdvProfileSearch() {
		
		def user1 = new User(userId: 'glen', password: 'secret',
			profile: new Profile(fullName: 'Glen Something')).save()
			
		def user2 = new User(userId: 'willy', password: '12345',
			profile: new Profile(homepage: 'http://willy.com')).save()
			
		def params = ['fullName':'Glen Something','homepage':'http://willy.com','queryType':'or']
		
		def profileProps =
			Profile.metaClass.properties*.name
		
		def profiles = Profile.withCriteria {
			"${params.queryType}" {
				
				params.each { field, value ->
					
					if (profileProps.grep(field) && value ) {
						ilike(field,value)
					}
				}
			}
			
		}
		
		assertEquals 1,profiles.size()
	}
	
}
