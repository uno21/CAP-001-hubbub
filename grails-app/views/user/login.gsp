<html>
	<head>
		<title>Login Hubbub</title>
		<meta name="layout" content="main"/>
	</head>
	
	<p>Log in:</p>
	<g:if test="${flash.message}">
		<div style="color:red">
			${flash.message}
		</div>
	</g:if>
	<g:form action="doLogin">
		<div>
			<label for="userId">User id:</label>
			<g:textField name="userId"/>
		</div>
		<div>
			<label for="password">Password:</label>
			<g:passwordField name="password"/>
		</div>
		<div>
			<g:actionSubmit action="doLogin" value="Log in"/>
		</div>	
	</g:form>
	<div>
		<g:link controller="user" action="register">Register</g:link>
	</div>
</html>