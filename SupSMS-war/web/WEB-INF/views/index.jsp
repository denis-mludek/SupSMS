<%-- 
    Document   : index
    Created on : 12 déc. 2014, 15:39:38
    Author     : lucas
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href='<c:url value="/assets/css/main.css"></c:url>' rel="stylesheet" type="text/css"/>

    <title>Home</title>
    <script>
        function supprimerConversation(id, name) {
            if (confirm('Are you sure you want to delete the conversation of ' + name + '?')) {
                location.href = '<c:url value="/"></c:url>?delete=' + id;
            }
            return false;
        }
    </script>
</head>

<body>

<!-- header -->
<%@ include file="/WEB-INF/includes/header.jsp" %>
<!-- content -->
<main role="main">

    <%@ include file="/WEB-INF/includes/sidebar.jsp" %>

    <div id="content">

        <c:if test="${ !connected }">

            <h1 class="post-title">Sup SMS</h1>

            <p id="commercial_speech">
                Want to stay in touch with all your friends ?<br>
                Get some news of your family ? Talk at any time with anybody ?<br><br>
                <span>We are THE solution !</span><br><br>
                An amazing product, easy to use, efficient, for a small price.
                Subscribe now and start using our incredible service right now !
            </p>

            <p id="stats_speech">
                By now, there is already <span>${userCount}</span> users who have sent <span>${smsCount}</span> sms !
            </p>

        </c:if>

        <c:if test="${ connected }">

            <h1 class="post-title">Conversation History</h1>

            <ul id="contact-list">

                <c:forEach items="${conversations}" var="conversation">

                    <li>
                        <a href="<c:url value="/user/conversations?num=${conversation.id}"></c:url>">
                            <img alt="edit" src="/SupSMS-war/assets/img/icon_message.png">
                                ${conversation.getContactRecipient(sessionScope.user,contactService)}
                        </a>
                        <a class="contact-delete"
                           onclick="supprimerConversation(${conversation.id},'${conversation.getContactRecipient(sessionScope.user,contactService)}')">
                            <img alt="delete" src="/SupSMS-war/assets/img/icon_trash.png">
                        </a>
                    </li>
                </c:forEach>

            </ul>
        </c:if>

    </div>
</main>
<!-- footer -->
<%@ include file="/WEB-INF/includes/footer.jsp" %>

</body>

</html>
