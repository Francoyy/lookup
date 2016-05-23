<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" />        
        <title>Login to Lookup</title>
    </head>
    <body>

        <span id="main">
            Welcome to Lookup, please sign up by entering the following information :<br/><br/>
            <s:form action="/signup.action" >
                <s:textfield name="login" label="login" />
                <s:password name="password" label="password" />
				<s:select label="Input language" name="inputLanguage" headerKey="1" list="#{'sv':'Swedish','en':'English','fr':'French','es':'Spanish','zh-CHT':'Chinese (Traditional)','zh-CHS':'Chinese (Simplified)','th':'Thai','ja':'Japanese'}" value="inputLanguage" />
				<s:select label="Output language" name="outputLanguage" headerKey="1" list="#{'en':'English','sv':'Swedish','fr':'French','es':'Spanish','zh-CHT':'Chinese (Traditional)','zh-CHS':'Chinese (Simplified)','th':'Thai','ja':'Japanese'}" value="outputLanguage" />
                <s:submit value="Sign up" />
            </s:form>
            <span class="error"><s:property value="message"/></span>
            <br/><br/>
        </span>
        <script type="text/javascript">
            var login = "<s:property value='#session.login' />";
            if(login!="") {
                //modify main span
                var welcomeText = "Welcome to Lookup! <br/>You can now make some lookups, manage your vocabulary list and train on your vocabulary!";
                    welcomeText += "<br/><br/><a href='lookup.jsp'>Start using lookup</a>";
                document.getElementById("main").innerHTML=welcomeText;
            }

        </script>



    </body>


</html>
