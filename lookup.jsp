<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" />        
        <title>Word lookup</title>
    </head>
    <body onload="init()">
        <s:form action="/lookupWordAction.action" >
            <s:textfield name="inputWord" label="word" />
            <s:hidden name="inputLanguage" id="myInputLanguage" value="" />
            <s:hidden name="outputLanguage" id="myOutputLanguage" value="" />
            <s:submit value="Lookup" />
        </s:form>
        <span id="inputL">&nbsp;</span> <a href="#" onclick="swapLanguages()" class="swapLanguage">&gt;&gt;</a> <span id="outputL">&nbsp;</span><br/><br/>
        <s:property value="message" />
        <br/><br/>



        
        <script type="text/javascript">
       
        function init() {
             document.getElementById("lookupWordAction_inputWord").focus();
             var inputLanguage = "<s:property value="inputLanguage" />";
             var outputLanguage = "<s:property value="outputLanguage" />";
             
             var defaultInputLang = "<s:property value='#session.inputLanguage' />";
             var defaultOutputLang = "<s:property value='#session.outputLanguage' />";
             
             if(inputLanguage!="" && outputLanguage!="") {
                document.getElementById("myInputLanguage").setAttribute("value", inputLanguage);
                document.getElementById("myOutputLanguage").setAttribute("value", outputLanguage);
                document.getElementById("inputL").innerHTML=inputLanguage;
                document.getElementById("outputL").innerHTML=outputLanguage;
             }
             else { //if no previous search, they are set up according to default languages in account creation
            	 document.getElementById("myInputLanguage").setAttribute("value", defaultInputLang);
                 document.getElementById("myOutputLanguage").setAttribute("value", defaultOutputLang);
                 document.getElementById("inputL").innerHTML=defaultInputLang;
                 document.getElementById("outputL").innerHTML=defaultOutputLang;
             }
        }

        function swapLanguages() {
        	var tempI = document.getElementById("myInputLanguage").getAttribute("value");
        	var tempO = document.getElementById("myOutputLanguage").getAttribute("value");
	        document.getElementById("myInputLanguage").setAttribute("value", tempO);
	        document.getElementById("myOutputLanguage").setAttribute("value", tempI);
	        document.getElementById("inputL").innerHTML=tempO;
	        document.getElementById("outputL").innerHTML=tempI;
        }

        var login = "<s:property value='#session.login' />";
       
        if(login!="") {

            document.write("<a href='listWordsAction.action'>See list</a>");
            document.write("&nbsp;-&nbsp;<a href='train.action'>Train</a>");
            document.write("&nbsp;-&nbsp;<a href='edit.action'>Edit</a>");
            document.write("&nbsp;-&nbsp;<a href='print.action'>Print</a>");
            document.write("&nbsp;-&nbsp;<a href='logout.action'>Logout</a> ("+login+")");
        }
        else {
            document.write("<a href='login.jsp'>Log in</a>");    
        }
    </script>

    </body>


</html>
