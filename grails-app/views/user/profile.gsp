<html>
	<head>
		<title>Profile Hubbub</title>
		<meta name="layout" content="main"/>
	</head>
	<body>
	
	<div class="profilePic">
		<g:if test="${profile.photo}">
			<img src="
				<g:createLink controller='image'
					action='renderImage' id='${profile.id}'/>"
			/>
		</g:if>
		<p>Profile for <strong>${profile.fullName}</strong></p>
		<p>Bio: ${profile.bio}</p>
	</div>
	</body>
</html>	