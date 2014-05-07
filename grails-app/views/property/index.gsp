
<%@ page import="com.demo.Property.imageupload" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'property.label', default: 'Property')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-property" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="property.uploadImage.label" /></g:link></li>
				<li><g:link class="edit" mapping="processvideowork"><g:message code="default.button.processvideo.label" default='Process Video' /></g:link></li>
			</ul>
		</div>
		<div id="list-property" class="content scaffold-list" role="main">
			<h1><g:message code="property.imageList.label" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="captionvalue" title="${message(code: 'property.captionvalue.label', default: 'Captionvalue')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${propertyInstanceList}" status="i" var="propertyInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: propertyInstance, field: "captionvalue")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${propertyInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
