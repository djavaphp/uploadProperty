
<%@ page import="com.demo.Property.imageupload" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'property.label', default: 'Property')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-property" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="property.imageList.label" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="property.uploadImage.label" /></g:link></li>
			</ul>
		</div>
		<div id="show-property" class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list property">
			
				<g:if test="${propertyInstance?.captionvalue}">
				<li class="fieldcontain">
					<span id="captionvalue-label" class="property-label"><g:message code="property.captionvalue.label" default="Captionvalue" /></span>
					
						<span class="property-value" aria-labelledby="captionvalue-label"><g:fieldValue bean="${propertyInstance}" field="captionvalue"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${propertyInstance?.imageExtension}">
				<li class="fieldcontain">
					<span id="imageExtension-label" class="property-label"><g:message code="property.imageExtension.label" default="Image Extension" /></span>
					
						<span class="property-value" aria-labelledby="imageExtension-label"><g:fieldValue bean="${propertyInstance}" field="imageExtension"/></span>
					
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>
