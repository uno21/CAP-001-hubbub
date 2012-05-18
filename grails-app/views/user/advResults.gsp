<html>
	<head>
		<title>Advanced Search Results</title>
		<meta name="layout" content="main"/>
	</head>
	<body>
	
		<h1>Advanced Results</h1>
		<p>Searched	for items matching.
		Found <strong>${profiles.size()}</strong> hits.
		</p>
		<ul>
			<g:each var="profile" in="${profiles}">
				<li><g:link controller="user" action="profile" id="${profile.id}">${profile.fullName}</g:link></li>
			</g:each>
		</ul>
		
		<g:link action='advSearch'>Search Again</g:link>
	</body>
</html>
	