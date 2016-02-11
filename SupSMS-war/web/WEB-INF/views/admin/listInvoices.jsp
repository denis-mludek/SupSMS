<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href='<c:url value="/assets/css/main.css"></c:url>' rel="stylesheet" type="text/css"/>
    <title>List of invoices</title>

</head>

<body>

<!-- header -->
<%@ include file="/WEB-INF/includes/header.jsp" %>

<!-- content -->
<main role="main">

    <%@ include file="/WEB-INF/includes/sidebar.jsp" %>

    <div id="content">

        <h1 class="post-title">List of invoices</h1>

        <ul id="contact-list">
            <c:forEach var="invoice" items="${ listInvoices }">
                <li>
                    <img alt="edit" src="/SupSMS-war/assets/img/icon_user.png">
                    <span>${ invoice.user.phoneNumber}</span>
                    <span class="details"> ${invoice.getDateFromTimestamp()}  - Amount : $ ${ invoice.amount} </span>
                </li>
            </c:forEach>
        </ul>

    </div>

</main>

<!-- footer -->
<%@ include file="/WEB-INF/includes/footer.jsp" %>

</body>

</html>
