<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href='<c:url value="/assets/css/main.css"></c:url>' rel="stylesheet" type="text/css"/>
    <title>Contacts</title>

</head>

<body>

<!-- header -->
<%@ include file="/WEB-INF/includes/header.jsp" %>

<!-- content -->
<main role="main">

    <%@ include file="/WEB-INF/includes/sidebar.jsp" %>

    <div id="content">

        <h1 class="post-title">Manage my contacts</h1>
        <a id="add-contact" href="<c:url value="/user/contacts?action=add"></c:url>">Add a contact</a>
        <ul id="contact-list">
            <c:forEach var="contact" items="${ listContacts }">
                <li>
                    <img alt="edit" src="/SupSMS-war/assets/img/icon_message.png">
                    <span> ${ contact.lastName } ${ contact.firstName } </span>
                    <a class="contact-delete"
                       href="<c:url value="/user/contacts?action=delete&id=${ contact.id }"></c:url>"
                       onclick="return confirm('Are you sure you want to delete this item?');">
                        <img alt="delete" src="/SupSMS-war/assets/img/icon_trash.png">
                    </a>
                    <a class="contact-edit"
                       href="<c:url value="/user/contacts?action=edit&id=${ contact.id }"></c:url>">
                        <img alt="edit" src="/SupSMS-war/assets/img/icon_edit.png">
                    </a><br>
                    <span class="details"> ${ contact.phoneNumber } - ${ contact.email } - ${ contact.postalAddress } </span>
                </li>
            </c:forEach>
        </ul>

    </div>

</main>

<!-- footer -->
<%@ include file="/WEB-INF/includes/footer.jsp" %>

</body>

</html>
