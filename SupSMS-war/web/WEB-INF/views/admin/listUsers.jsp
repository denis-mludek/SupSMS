<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href='<c:url value="/assets/css/main.css"></c:url>' rel="stylesheet" type="text/css"/>
    <title>List of users</title>

</head>

<body>

<!-- header -->
<%@ include file="/WEB-INF/includes/header.jsp" %>

<!-- content -->
<main role="main">

    <%@ include file="/WEB-INF/includes/sidebar.jsp" %>

    <div id="content">

        <h1 class="post-title">List of users</h1>

        <a id="add-contact" href="<c:url value="/admin/users?action=add"></c:url>">Add an user</a>
        <ul id="contact-list">
            <c:forEach var="user" items="${ listUsers }">

                <li>
                    <img alt="edit" src="/SupSMS-war/assets/img/icon_user.png">
                    <span>${ user.lastName } ${ user.firstName }</span>
                    <a class="contact-delete"
                       href="<c:url value="/admin/users?action=delete&id=${ user.phoneNumber }"></c:url>"
                       onclick="return confirm('Are you sure you want to delete this item?');">
                        <img alt="delete" src="/SupSMS-war/assets/img/icon_trash.png">
                    </a><br>
                    <span class="details"> Phone : ${ user.phoneNumber } - Email : ${ user.email } </span>
                </li>

            </c:forEach>
        </ul>

    </div>

</main>

<!-- footer -->
<%@ include file="/WEB-INF/includes/footer.jsp" %>

</body>

</html>
