package com.grailsinaction

import groovy.util.GroovyTestCase;


class TagIntegrationTests extends GroovyTestCase {
	
	void testPostWithTags() {
		
		def user = new User(userId: 'joe', password: 'secret').save()
		
		def tagGroovy = new Tag(name: 'groovy')
		def tagGrails = new Tag(name: 'grails')
		user.addToTags(tagGroovy)
		user.addToTags(tagGrails)
		
		def tagNames = user.tags*.name
		assertEquals([ 'grails', 'groovy'] , tagNames.sort())
		
		def groovyPost = new Post(content: "A groovy post")
		user.addToPosts(groovyPost)
		groovyPost.addToTags(tagGroovy)
		assertEquals 1, groovyPost.tags.size()
		
		def bothPost = new Post(content: "A groovy and grails post")
		user.addToPosts(bothPost)
		bothPost.addToTags(tagGroovy)
		bothPost.addToTags(tagGrails)
		assertEquals 2, bothPost.tags.size()
	}
}
