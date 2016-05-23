<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="description" content="Lookup is a multi-language translator using google translate.">
	<meta name="keywords" content="lookup, translator, multi-language, multi language, language, swedish, french, english, translate, google translate, language, learning, language learning, wafrat">
        <link rel="stylesheet" href="style.css" />        
        <title>Login to Lookup</title>
    </head>
    <body onload="giveFocus()">
        Welcome to Lookup, please login :<br/><br/>
        <s:form action="/login.action" >
            <s:textfield name="login" label="login" />
            <s:password name="password" label="password" />
            <s:submit value="Login" />
        </s:form>

        <br/><br/>
	<table width=450><tr><td>
	<b>Lookup is a multi-language translator using google translate.</b><br/><br/>
	What makes Lookup useful for language learning is that all lookups are automatically saved, and the user can then train the memory on the previous lookups. The lookups can also be edited to correct or add information to google translate's result
        </td></tr></table><br/>
	<a href="signup.jsp">Sign up</a>

	<script type="text/javascript">
		function giveFocus() {
			 document.getElementById("login_login").focus();
		}
	</script>


    </body>


</html>
