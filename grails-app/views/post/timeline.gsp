<html>

<g:javascript>
	function clearPost(e) {
		$('postContent').value='';
	}

	function showSpinner(visible) {
		$('spinner').style.display = visible ? "inline" : "none";
	}
</g:javascript>
	<head>
		<title>
			Timeline for ${user.profile.fullName }
		</title>
		<meta name="layout" content="main"/>
		<r:require module="jquery"/>
		<r:layoutResources/>
	</head>
	<body>

		<div id="newPost">
			<h3>
				What is ${user.profile.fullName} hacking on right now?
			</h3>
			
		<g:if test="${flash.message}">
			<div class="flash">
				${flash.message}
			</div>
		</g:if>
			
		<p>
			<g:form action="ajaxAdd">
				<g:textArea id='postContent' name="content" rows="3" cols="50"/><br/>
				<g:submitToRemote value="Post"
				url="[controller: 'post', action: 'addPostAjax']"
				update="allPosts"
				onSucces="clearPost(e)"/>
			<img id="spinner" style="display:none"
				src="<g:createLinkTo dir='/images' file='spinner.gif'/>"/>	
			</g:form>
		</p>
		</div>
				
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
		
	</body>
</html>