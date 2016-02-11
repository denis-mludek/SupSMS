<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href='<c:url value="/assets/css/main.css"></c:url>' rel="stylesheet" type="text/css"/>
    <title>My invoices</title>

</head>

<body>

<!-- header -->
<%@ include file="/WEB-INF/includes/header.jsp" %>

<!-- content -->
<main role="main">

    <%@ include file="/WEB-INF/includes/sidebar.jsp" %>

    <div id="content">

        <h1 class="post-title">My invoices</h1>

        <ul id="contact-list">
            <c:forEach var="invoice" items="${ invoices }">
                <li>
                    <img alt="edit" src="/SupSMS-war/assets/img/icon_user.png">
                    <span>Date : ${invoice.getDateFromTimestamp()} </span>
                    <span class="details"> Amount : $ ${ invoice.amount} </span>
                </li>
            </c:forEach>
        </ul>

    </div>

</main>

<!-- footer -->
<%@ include file="/WEB-INF/includes/footer.jsp" %>

</body>

</html>
