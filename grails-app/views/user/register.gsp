<html>
	<head>
		<title>Register New User</title>
		<meta name="layout" content="main"/>
	</head>
	<body>

		<h1>Register New User (Complete - Chapter 6)</h1>
			<g:uploadForm>
				<dt>User Id: </dt>
				<dd><g:textField name="userId" /></dd>
				
				<dt>Password:</dt>
				<dd><g:passwordField name="password" /></dd>
				<dt>(repeat)</dt>
				<dd><g:passwordField name="passwordRepeat" /></dd>
				<dt>Full Name:</dt>
				<dd><g:textField name="profile.fullName"/>
				<dt>Homepage:</dt>
				<dd><g:textField name="profile.homepage"/>
				<dt>Email:</dt>
				<dd><g:textField name="profile.email"/>
				<dt>Photo:</dt>
				<dd><input type="file" name="profile.photo"/></dd>
				<td>Country:</td>
				<dd><g:countrySelect name="profile.country"
				noSelection="['':'Choose your country...']"/></dd>
				<dt>Timezone:</dt>
				<dd><g:timeZoneSelect name="profile.timezone"/></dd>
			<p>
				<g:actionSubmit action="register" value="Register"/>
		    </p>
			</g:uploadForm>
			<p>
				<g:link action="login">Back to Hubbub</g:link>	
		    </p>
	
	</body>
</html>
