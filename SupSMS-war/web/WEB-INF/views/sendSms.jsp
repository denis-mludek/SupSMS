<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href='<c:url value="/assets/css/main.css"></c:url>' rel="stylesheet" type="text/css"/>
    <title>JSP Page</title>

</head>

<body>

<!-- header -->
<%@ include file="/WEB-INF/includes/header.jsp" %>

<!-- content -->
<main role="main">

    <%@ include file="/WEB-INF/includes/sidebar.jsp" %>

    <div id="content">

        <h1 class="post-title">Send SMS</h1>

        <form id="message_form" method="POST">

            <div id="message_form_top">

                <input id="message_to" name="phoneNumber" value="${phone}" required="required" class="text-field"
                       type="text" placeholder="Phone number"/>
                <input id="message_to_selector" class="button" type="button" value="Contacts List"
                       onClick='window.open("<c:url
                               value="/user/SendSmsContact"></c:url>","Choose Contact", "width=380,height=360,scrollbars=yes");'/>

            </div>

            <div id="message_form_content">

                <textarea id="message_content" name="msg" placeholder="Message"
                          onkeydown="if (event.keyCode === 13) sendMessage();"></textarea>
                <input class="button" type="submit" value="Send" onclick="sendMessage();"/>
            </div>

        </form>

    </div>

</main>

<!-- footer -->
<%@ include file="/WEB-INF/includes/footer.jsp" %>

</body>

</html>
