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

        <c:if test="${ action == 'add' }" var="add">
            <h1 class="post-title">Add a contact</h1>
        </c:if>
        <c:if test="${ action == 'edit' }" var="edit">
            <h1 class="post-title">Edit a contact</h1>
        </c:if>
        <form id="register_form" method="POST">

            <input hidden="" value="${action}" name="action"/>
            <input hidden="" value="${contactId}" name="contactId"/>
            <ul>

                <li>
                    <label for="firstName">Firstname</label>
                    <input type="text" required="required" value="${firstName}" name="firstName"/>
                </li>

                <li>
                    <label for="lastName">Lastname</label>
                    <input type="text" required="required" value="${lastName}" name="lastName"/>
                </li>
                <li>
                    <label for="phoneNumber">Phone number</label>
                    <input type="text" required="required" value="${phoneNumber}" name="phoneNumber"/>
                </li>

                <li>
                    <label for="email">Email</label>
                    <input type="email" required="required" value="${email}" name="email"/>
                </li>

                <li>
                    <label for="postalAddress">Postal address</label>
                    <input type="text" required="required" value="${postalAddress}" name="postalAddress"/>
                </li>
                <li>
                    <c:if test="${ add }">
                        <input class="button" type="submit" value="Add contact"/>
                    </c:if>
                    <c:if test="${ edit }">
                        <input class="button" type="submit" value="Save"/>
                    </c:if>
                </li>
            </ul>
        </form>

    </div>

</main>

<!-- footer -->
<%@ include file="/WEB-INF/includes/footer.jsp" %>

</body>

</html>
