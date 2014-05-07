<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="layout" content="main" />
<title>Rich Text Editor</title>
<script type="text/javascript" src="js/nicEdit.js"></script>
<script type="text/javascript">
	bkLib.onDomLoaded(function() {
		new nicEditor({
			fullPanel : true,
			maxHeight : 200,
			scrollbars : true
		}).panelInstance('foto');

	});
</script>
</head>
<body>
	<div class="body">
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message
							code="default.home.label" /></a></li>
				<%--<li><g:link class="list" action="index" controller="property">
						<g:message code="property.imageList.label" />
					</g:link></li>
			--%></ul>
		</div>
		<form action="convertToImage" controller="property" method="post">
			<center>
				<h1>Create opening and closing frames</h1>
			</center>
			<b>Title:</b> &nbsp;
			<g:textField name="captionvalue" id="captionvalue" required="true" />

			<br> <b>Description:</b> &nbsp;
			<g:textArea name="foto" id="foto" style="width: 65%; height:65%">Sample Text.....</g:textArea>

			<br>
			<g:submitButton name="submit" value="Export to Image" />

		</form>
	</div>
</body>
</html>