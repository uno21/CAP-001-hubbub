<html>
	<head>
		<title>Search Hubbub</title>
		<meta name="layout" content="main"/>
	</head>
	<body>
	
		<formset>
			<legend>Advanced Search for Friends</legend>
			
		<g:form action="advResults">
			<div>
				<label for="fullName">Name</label>
				<g:textField name="fullName" />
			</div>
			<div>
				<label for="email">Email</label>
				<g:textField name="email" />
			</div>
			<div>
				<label for="homepage">Homepage</label>
				<g:textField name="homepage" />
			</div>
			Query Type:
			<g:radio name="queryType" value="and" checked="checked"/>AND
			<g:radio name="queryType" value="or"/>OR
			<g:radio name="queryType" value="not"/>NOT
			<div>
				<g:submitButton name="advSearch" value="Search"/>
			</div>
		</g:form>

		</formset>
	</body>
</html>	