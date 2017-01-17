<?xml version="1.0" encoding="ISO-8859-1" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output
		omit-xml-declaration="yes"
		method="xml"
		encoding="ISO-8859-1"
		indent="yes" />
	<xsl:template match="/">
	<ul>
		<xsl:for-each select="//result">
			<li>
				<a href="/moovee/genres/controller?id={binding/literal}">
			<xsl:value-of select="binding/literal" />
		
				</a><br />
			</li>
		</xsl:for-each>
	</ul>	
	</xsl:template>
</xsl:stylesheet>