<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="print.css" />
        <script type="text/javascript" src="jquery-1.4.2.min.js"></script>
        <title>Train</title>
    </head>
    <body>
    <script type="text/javascript">
        function niceDate(myDate) {
            return myDate.substring(0, myDate.length-4);
        }
    </script>

        <table id="editWords">
        <s:iterator value="listLookup" status="stat">
            <tr id="tr<s:property value='#stat.index' />">
                <td class="date"><script type="text/javascript">document.write(niceDate("<s:property value='date'/>"));</script>&nbsp;</td>
                <td class="input"><s:property value="input"/></td>
                <td class="milieu">&nbsp;-&nbsp;</td>
                <td class="output"><s:property value="output"/></td>
            </tr>
        </s:iterator>
        </table>
        <br/>
    </body>
</html>
