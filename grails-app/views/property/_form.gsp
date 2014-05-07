<%@ page import="com.demo.Property.imageupload" %>



<div class="fieldcontain ${hasErrors(bean: propertyInstance, field: 'captionvalue', 'error')} required">
	<label for="captionvalue">
		<g:message code="property.captionvalue.label" default="Captionvalue" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="captionvalue" required="" value="${propertyInstance?.captionvalue}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: propertyInstance, field: 'imageExtension', 'error')} ">
	<label for="imageExtension">
		<g:message code="property.imageExtension.label" default="Image Extension" />
		
	</label>
	<%--<g:textField name="imageExtension" value="${propertyInstance?.imageExtension}"/>
	  --%><input type="file" name="foto" />

</div>

