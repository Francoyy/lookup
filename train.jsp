<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="style.css" />
        <title>Train</title>
    </head>
    <body>

    <a href="#" onclick="hideInput()">Hide input</a>
        -
        <a href="#" onclick="hideOutput()">Hide output</a>
        -
        <a href="#" onclick="seeAll()">Unhide</a>
        -
        <a href="train.action?action=all">Full list</a>
        -
        <a href="#bottom" name="top">To the bottom</a>
        <br/>
        <s:property value="info"/>
        <br/>
        <a href="train.action?action=prevW" class="browseTime">&lt;&lt;</a> week
        <script type="text/javascript">
            var noF = "<s:property value='noFurtherWeek'/>";
            if(noF=="false") {
                document.write("<a href='train.action?action=nextW' class='browseTime'>&gt;&gt;</a>");
            }
        </script>
        <br/>
        <a href="train.action?action=prevM" class="browseTime">&lt;&lt;</a> month
        <script type="text/javascript">
            var noF = "<s:property value='noFurtherMonth'/>";
            if(noF=="false") {
                document.write("<a href='train.action?action=nextM' class='browseTime'>&gt;&gt;</a>");
            }
        </script>
        <br/> 
        <table id="trainWords">
        <s:iterator value="listLookup" status="stat">
            <tr>
            	<td class="swapbuttons">
                    <input type="button" class="btn" value="check" onclick="see(<s:property value='#stat.index' />)"/>
                </td>
                <td id="input<s:property value='#stat.index' />" class="input">
                    <s:if test="%{#session.inputLanguage.toString().equals(inputLanguage.toString())}" ><s:property value="input"/></s:if>
                    <s:else><s:property value="output"/></s:else>
                </td>
                <td id="output<s:property value='#stat.index' />" class="output">
                    <s:if test="%{#session.outputLanguage.toString().equals(outputLanguage.toString())}"><s:property value="output"/></s:if>
                    <s:else><s:property value="input"/></s:else>
                </td>             
            </tr>
        </s:iterator>
        </table>



        <script type="text/javascript">
            function see(j) {
                document.getElementById("output"+j).style.visibility="visible";
                document.getElementById("input"+j).style.visibility="visible";
            }

            function hideInput() {
                applyStyleToClass("input", "hidden");
            }

            function hideOutput() {
                applyStyleToClass("output", "hidden");
            }

            function seeAll() {
                applyStyleToClass("input", "visible");
                applyStyleToClass("output", "visible");
            }

            function applyStyleToClass(theClass, styleVis) {
                var allHTMLTags=document.getElementsByTagName("*");
                for (i=0; i<allHTMLTags.length; i++) {
                    //Get all tags with the specified class name.
                    if (allHTMLTags[i].className==theClass) {
                        allHTMLTags[i].style.visibility=styleVis;
                    }
                }
            }

            function options() {

            }
        </script>
        <a href="#top" name="bottom">To the top</a>
        <br/>
        <br/>
        <a id="return" href="lookup.jsp">Return</a>
    </body>
</html>
