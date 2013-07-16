<%-- 
    Document   : index
    Created on : Jul 13, 2013, 4:54:03 PM
    Author     : kamir
--%>

<%@page import="genix.core.GenIXShell"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GenIX</title>
    </head>
    <body>
        <H1>Rechenkraft:GenIX</H1>
        <h4>version: <%=GenIXShell.version %></h4>
        <hr>
        HBase Version 0.92 (CDH4.1.1)
        <hr>
        Features:
        <ol>
            <li>Import Gene-File(s)</li>
        </ol>
        TODO:
        <ol>
            <li>F:Parser & Converter</li>
            <li>F:PIG script with HBase adapter </li>
            <li>O:Simple Gene-Statistics</li>
            <li>A:HotSpot Analyse</li>
            
        </ol>
        F:functional O:optional A:administrational

    </body>
</html>
