<html>
	<head>
		<title>Search Hubbub</title>
		<meta name="layout" content="main"/>
	</head>
	<body>
	<g:uploadForm action="upload">
		<div>
			User Id:
			<g:select name="userId" from="${userList}"
				optionKey="userId" optionValue="userId" />
		</div>
		<div>
			Photo: <input name="photo" type="file" />
			<g:submitButton name="upload" value="Upload"/>
		</div>
	</g:uploadForm>
	</body>
</html>	