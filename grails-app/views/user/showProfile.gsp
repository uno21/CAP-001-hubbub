<html>
	<head>
		<title>Profile Hubbub</title>
		<meta name="layout" content="main"/>
	</head>
	<body>
	
	<div class="profilePic">
		<g:if test="${user.profile.photo}">
			<img src="
				<g:createLink controller='image'
					action='renderImage' id='${user.profile.id}'/>"
			/>
		</g:if>
		<p>Profile for <strong>${user.profile.fullName}</strong></p>
		<p>Bio: ${user.profile.bio}</p>
		<!--  People im following -->
		<p>People im following</p>
		<div id="friendsThumbnails">
			<g:each var="followUser" in="${user.following}">
				<p>${followUser.userId}<p>
				<h:tinyThumbnail userId="${followUser.userId}"/>
			</g:each>
		</div>
	</div>
	</body>
</html>	