package com.grailsinaction

class UserService {

   Boolean authenticate(String userId, String password) {
	   def user = User.findByUserIdAndPassword(userId,password)
	  
	    if(user) {
		   return true   
	   } else { 
	   		return false
	   }
   }
   
   User save(User user) {
		return user.save()   
   }
   
   User findByUserId(String userId) {
   		return User.findByUserId(userId)
   }
   
   Boolean register(User user) {
	   
	   if (user.validate()) {
			this.save(user)
			return true   
	   }
	   
	   return false
   }
   
   List<User> searchByUserId(String userId) {
	   return User.findAllByUserIdLike("%${userId}%")
   }
   
   void addPost(User user,Post post) {
		   user.addToPosts(post)
   }
   
}
