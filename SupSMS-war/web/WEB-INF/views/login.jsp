<%-- 
    Document   : login
    Created on : 12 déc. 2014, 15:39:38
    Author     : YemYem
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href='<c:url value="/assets/css/main.css"></c:url>' rel="stylesheet" type="text/css"/>
    <title>Login</title>

</head>

<body>

<!-- header -->
<%@ include file="/WEB-INF/includes/header.jsp" %>

<!-- content -->
<main role="main">

    <%@ include file="/WEB-INF/includes/sidebar.jsp" %>

    <div id="content">

        <h1 class="post-title">Log me in !!</h1>

        <form id="register_form" method="POST">

            <%@ include file="/WEB-INF/includes/error.jsp" %>

            <ul>
                <li>
                    <label for="phoneNumber">Phone Number :</label>
                    <input type="text" value="${phoneNumberCo}" name="phoneNumber"/>
                </li>
                <li>
                    <label for="password">Password :</label>
                    <input type="password" name="password"/>
                </li>
                <li>
                    <input class="button" type="submit" value="Sign in"/>
                </li>
            </ul>
        </form>

    </div>
</main>

<!-- footer -->
<%@ include file="/WEB-INF/includes/footer.jsp" %>

</body>

</html>