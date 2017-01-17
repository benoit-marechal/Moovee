<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
	<ul>
		<xsl:for-each select="//result">
			<li>
				<a href="/moovee/films/controller?id={substring(binding/uri,32)}"><xsl:value-of select="binding/literal" /></a><br />
			</li>
		</xsl:for-each>
	</ul>	
	</xsl:template>
</xsl:stylesheet>