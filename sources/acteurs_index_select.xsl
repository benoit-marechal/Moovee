<?xml version="1.0" encoding="ISO-8859-1" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output
		omit-xml-declaration="yes"
		method="xml"
		encoding="ISO-8859-1"
		indent="yes" />
	<xsl:template match="/">
		<xsl:for-each select="//result">
			<option><xsl:value-of select="substring(binding/uri,32)" /></option>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>