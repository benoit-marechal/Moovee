<?xml version="1.0" encoding="ISO-8859-1" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output
		omit-xml-declaration="yes"
		method="xml"
		encoding="ISO-8859-1"
		indent="yes" />
		
		
	<xsl:template match="/">
	
	<h3> <xsl:value-of select="//binding[@name='titre']/literal" /></h3>
	<table>
		<tr>
			<td>
<img src="/moovee/film_images/{//binding[@name='path']/literal}" width="100" ></img>
			</td>
	<td>
	Année : <strong><xsl:value-of select="//binding[@name='annee']/literal" /></strong>
	<br />Réalisateur : <strong><xsl:value-of select="//binding[@name='realisateurprenom']/literal" /></strong>
	&#160;<strong><xsl:value-of select="//binding[@name='realisateurnom']/literal" /></strong>
	

	</td>
	</tr>
	</table>

	
	<object width="425" height="355">
	<param name="movie" value="http://www.youtube.com/v/{//binding[@name='pathex']/literal}">
	</param>
	<param name="wmode" value="transparent">
	</param>
	<embed src="http://www.youtube.com/v/{//binding[@name='pathex']/literal}" type="application/x-shockwave-flash" wmode="transparent" width="425" height="355">
	</embed>
	</object>

	</xsl:template>
	
	

	
</xsl:stylesheet>