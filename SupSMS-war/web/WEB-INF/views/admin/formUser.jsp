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

        <h1 class="post-title">Add an user</h1>

        <%@ include file="/WEB-INF/includes/error.jsp" %>

        <form id="register_form" method="POST">

            <ul>
                <li>
                    <label for="checkAdmin">Administrator </label>
                    <input type="checkbox" name="checkAdmin" value="1">
                </li>

                <li>
                    <label for="phoneNumber">Phone number</label>
                    <input type="text" required="required" value="${phoneNumber}" name="phoneNumber"/>
                </li>
                <li>
                    <label for="email">Email address</label>
                    <input type="email" required="required" value="${email}" name="email"/>
                </li>
                <li>
                    <label for="firstName">Firstname</label>
                    <input type="text" required="required" value="${firstName}" name="firstName"/>
                </li>
                <li>
                    <label for="lastName">Lastname</label>
                    <input type="text" required="required" value="${lastName}" name="lastName"/>
                </li>
                <li>
                    <label for="creditCard">Credit card number</label>
                    <input type="text" pattern="^[0-9]{16}" required="required" value="${creditCard}"
                           name="creditCard"/>
                </li>
                <li>
                    <label for="Expiration">Expiration date <span>(mm/yy)</span></label>
                    <input type="text" pattern="(?:0[1-9]|1[0-2])/[0-9]{2}" title="Expiration **/**" required="required"
                           value="${Expiration}" name="Expiration"/>
                </li>
                <li>
                    <label for="Cryptogramme">Security Code</label>
                    <input type="text" pattern="^[0-9]{3,4}" required="required" value="${Cryptogramme}"
                           name="Cryptogramme"/>
                </li>
                <li>
                    <label for="password">Password <span>(6 char minimum)</span></label>
                    <input type="password" pattern=".{6,}" required="required" value="${password}" name="password"/>
                </li>

                <li>
                    <input class="button" type="submit" value="Add"/>
                </li>
            </ul>
        </form>

    </div>

</main>

<!-- footer -->
<%@ include file="/WEB-INF/includes/footer.jsp" %>

</body>

</html>
