<html>
	<head>
		<g:javascript library="jquery" />
		<r:layoutResources/>
		<title>
			Post for ${user.profile.fullName }
		</title>
		<meta name="layout" content="main"/>
	</head>
	
	<div id="newPost">
		<h3>
			What is ${user.profile.fullName} hacking on right now?
		</h3>
	
		<p>
			<g:form controller="post" action="add">
				<g:textArea id='postContent' name="content" rows="3" cols="50"/><br/>
				<g:submitButton name="Post"/>
			</g:form>
		</p>
		
		<div class="allPosts">
			<g:each in="${user.posts}" var="post">
				<div class="postEntry">
					<div class="postText">
						${post.content}
					</div>
					<div class="postDate">
						<h:dateFromNow date="${post.dateCreated}"/> 
					</div>
				</div>
			</g:each>
		</div>
	</div>

</html>