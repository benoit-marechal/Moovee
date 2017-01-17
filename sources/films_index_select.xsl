<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
	<ul>
		<xsl:for-each select="//result">
			<option>
			<xsl:value-of select="substring(binding/uri,32)" />
			</option>
		</xsl:for-each>
	</ul>	
	</xsl:template>
</xsl:stylesheet>