<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href='<c:url value="/assets/css/main.css"></c:url>' rel="stylesheet" type="text/css"/>
    <title>Access refused</title>

</head>

<body>

<!-- header -->
<%@ include file="/WEB-INF/includes/header.jsp" %>
<!-- content -->
<main role="main">

    <%@ include file="/WEB-INF/includes/sidebar.jsp" %>

    <div id="content">

        <h1 class="post-title">Access refused</h1>

        <div id="access_refused">

            Oops ! <br><br>
            You don't have the power to access this page.<br><br>
            Try something else.<br><br>
            <span>=)</span>

        </div>

    </div>
</main>
<!-- footer -->
<%@ include file="/WEB-INF/includes/footer.jsp" %>
</body>

</html>
