<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8"/>

    <xsl:template match="/">
        <html>
            <head>
                <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Beer Catalog</title>
                <style>
                    table { border-collapse: collapse; width: 100%; }
                    th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
                    th { background-color: #f2f2f2; }
                </style>
            </head>
            <body>
                <h2>Beer Catalog</h2>
                <table>
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Al</th>
                        <th>Manufacturer</th>
                        <th>Ingredients</th>
                        <th>Chars</th>
                    </tr>
                    <xsl:for-each select="Beer/BeerItem">
                        <tr>
                            <td><xsl:value-of select="Name"/></td>
                            <td><xsl:value-of select="Type"/></td>
                            <td><xsl:value-of select="Al"/></td>
                            <td><xsl:value-of select="Manufacturer"/></td>
                            <td>
                                <xsl:for-each select="Ingredients/Ingredient">
                                    <xsl:value-of select="."/><xsl:text>, </xsl:text>
                                </xsl:for-each>
                            </td>
                            <td>
                                <xsl:for-each select="Chars/Char">
                                    <xsl:value-of select="."/><xsl:text>, </xsl:text>
                                </xsl:for-each>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
