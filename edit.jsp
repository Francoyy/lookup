<%-- 
    Document   : index
    Created on : Feb 28, 2009, 8:22:37 AM
    Author     : Meyyappan Muthuraman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="style.css" />
        <script type="text/javascript" src="jquery-1.4.2.min.js"></script>
        <title>Train</title>
    </head>
    <body>
        <span class="info">Click on a lookup to edit it.</span><br/><br/>
        <a href="#bottom" name="top">To the bottom</a>
        <br/>
        <br/>

        <table id="editWords">
        <s:iterator value="listLookup" status="stat">
            <tr id="tr<s:property value='#stat.index' />">
                <td id="inputL<s:property value='#stat.index' />"><s:property value="inputLanguage"/>&nbsp;&gt;</td>
                <td id="outputL<s:property value='#stat.index' />"><s:property value="outputLanguage"/>&nbsp;&nbsp;-&nbsp;</td>
                <td id="input<s:property value='#stat.index' />" onclick="editWord('<s:property value='#stat.index' />')"><s:property value="input"/></td>
                <td id="output<s:property value='#stat.index' />" onclick="editWord('<s:property value='#stat.index' />')"><s:property value="output"/></td>
                <td id="save<s:property value='#stat.index' />" style="visibility:hidden"><input type="button" name="save" value="save" onclick="saveWord(<s:property value='#stat.index' />)"/></td>
                <td id="delete<s:property value='#stat.index' />" style="visibility:hidden"><input type="button" name="delete" value="delete" onclick="deleteWord(<s:property value='#stat.index' />)"/></td>
                <td id="oldInput<s:property value='#stat.index' />" style="visibility:hidden"><s:property value="input"/></td>
                <td id="oldOutput<s:property value='#stat.index' />" style="visibility:hidden"><s:property value="output"/></td>
            </tr>
        </s:iterator>
        </table>
        <br/>

        <input type="button" name="addWord" value="Add new lookup" onclick="addNewWord()"/>
        <br/>
        <br/>
        <a href="#top" name="bottom">To the top</a>
        <br/>
        <br/>
        <a id="return" href="lookup.jsp">Return</a>

        <script type="text/javascript">
            function addNewWord() {
            	
            	var defaultInputLang = "<s:property value='#session.inputLanguage' />";
                var defaultOutputLang = "<s:property value='#session.outputLanguage' />";
                
                var newRowId = $("#editWords > tbody > tr").size();
                var newTr = '<tr id="tr'+newRowId+'"><td id="inputL'+newRowId+'"><select name="inputLanguage"><option value="'+defaultOutputLang+'">'+defaultOutputLang+'</option><option selected="true" value="'+defaultInputLang+'">'+defaultInputLang+'</option></select></td>';
                newTr += '<td id="outputL'+newRowId+'"><select name="outputLanguage"><option selected="true" value="'+defaultOutputLang+'">'+defaultOutputLang+'</option><option value="'+defaultInputLang+'">'+defaultInputLang+'</option></select></td>';
                newTr += '<td id="input'+newRowId+'"><input type="text" name="input" /></td>';
                newTr += '<td id="output'+newRowId+'"><input type="text" name="output" /></td>';
                newTr += '<td id="save'+newRowId+'"><input type="button" name="save" value="Add" onclick="saveNewWord('+newRowId+')" /></td>';
                newTr += '<td id="delete'+newRowId+'" style="visibility:hidden"><input type="button" name="delete" value="delete" onclick="deleteWord('+newRowId+')"/></td>';
                newTr += '<td id="oldInput'+newRowId+'" style="visibility:hidden"></td>';
                newTr += '<td id="oldOutput'+newRowId+'" style="visibility:hidden"></td>';

                newTr += '</tr>';
                $("#editWords > tbody").append(newTr);
                
            }

            function saveNewWord(id) {

                var inputL = $("#inputL"+id+" > select").val();
                var outputL = $("#outputL"+id+" > select").val();
                var input = $("#input"+id+" > input").attr("value");
                var output = $("#output"+id+" > input").attr("value");



                $("#oldInput"+id).text(input);
                $("#oldOutput"+id).text(output);
                $("#save"+id).html('<input type="button" name="save" value="save" onclick="saveWord('+id+')"/>');
                $("#save"+id).attr("style", "visibility:hidden");

                $("#inputL"+id).text(inputL);
                $("#outputL"+id).text(outputL);
                $("#input"+id).text(input);
                $("#output"+id).text(output);

                document.getElementById('input'+id).setAttribute("onclick","editWord("+id+")");
                document.getElementById('output'+id).setAttribute("onclick","editWord("+id+")");


                $.ajax({
                        async: false,
                        type: "POST",
                        url: "edit.action",
                        data: "newInputL="+inputL+"&newOutputL="+outputL+"&input="+input+"&output="+output+"&add=true",
                        success: function(data){
                           
                        }
                    });

            }


            function saveWord(id) {

                    var input = document.getElementById('editInput'+id).value;
                    var output= document.getElementById('editOutput'+id).value;
                    if(input=="") {
                        input="...";
                    }

                    if(output=="") {
                        output="...";
                    }

                    document.getElementById('input'+id).innerHTML=input
                    document.getElementById('output'+id).innerHTML=output

                    var oldInput=document.getElementById('oldInput'+id).innerHTML;
                    var oldOutput=document.getElementById('oldOutput'+id).innerHTML;

                    document.getElementById("save"+id).style.visibility="hidden";
                    document.getElementById("delete"+id).style.visibility="hidden";
                    document.getElementById("input"+id).setAttribute("onclick", "editWord("+id+")");
                    document.getElementById("output"+id).setAttribute("onclick", "editWord("+id+")");

                    $.ajax({
                        async: false,
                        type: "POST",
                        url: "edit.action",
                        data: "oldInput="+oldInput+"&oldOutput="+oldOutput+"&input="+input+"&output="+output,
                        success: function(data){

                        }
                    });
                };

             function deleteWord(id) {
                    var oldInput=$("#oldInput"+id).text();
                    var oldOutput=$("#oldOutput"+id).text();
                    document.getElementById("save"+id).style.visibility="hidden";
                    document.getElementById('delete'+id).style.visibility="hidden";
                    var monTr = document.getElementById("tr"+id);
                    monTr.parentNode.removeChild(monTr);
    
                    $.ajax({
                        async: false,
                        type: "POST",
                        url: "edit.action",
                        data: "oldInput="+oldInput+"&oldOutput="+oldOutput+"&delete=true",
                        success: function(data){

                        }
                    });
                };

            function editWord(id) {
                var input = $("#input"+id).text();
                var output = $("#output"+id).text();
                $("#input"+id).html("<input type='textField' id='editInput"+id+"' name='editInput"+id+"' value='' />");
                $("#output"+id).html("<input type='textField' id='editOutput"+id+"' name='editOutput"+id+"' value='' />");

                $("#input"+id+" > input").attr("value", input);
                $("#output"+id+" > input").attr("value", output);

                document.getElementById('input'+id).setAttribute("onclick","");
                document.getElementById('output'+id).setAttribute("onclick","");
                document.getElementById('save'+id).style.visibility="visible";
                document.getElementById('delete'+id).style.visibility="visible";
            }


        </script>

    </body>
</html>
