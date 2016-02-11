<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href='<c:url value="/assets/css/main.css"></c:url>' rel="stylesheet" type="text/css"/>
    <title>Choose your contact</title>
    <script>
        function returnContactToParentWindow(contact) {
            window.opener.location.assign("<c:url value="/user/sendSms"></c:url>?phone=" + contact);
            window.close();
        }
    </script>
</head>

<body>

<!-- content -->
<main role="main" id="popup">

    <div id="content">

        <h1 class="post-title">Choose your contact</h1>

        <ul id="contact-list">
            <c:forEach var="contact" items="${ listContacts }">
                <li>
                    <a class="contact-name" onclick="returnContactToParentWindow('${contact.phoneNumber}' )">
                        <img alt="edit" src="/SupSMS-war/assets/img/icon_message.png">
                        <span>${ contact.lastName } ${ contact.firstName }</span>
                    </a>

                </li>
            </c:forEach>
        </ul>

    </div>

</main>

</body>

</html>
