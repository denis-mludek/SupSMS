<!DOCTYPE html>
<html>

<head>

    <title>Conversation</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href='<c:url value="/assets/css/main.css"></c:url>' rel="stylesheet" type="text/css"/>

    <script>
        // Websocket Endpoint url
        var endPointURL = "ws://" + window.location.host + "/SupSMS-war/user/chat/${numConversation}";
        var chatClient = null;
        function connect() {

            chatClient = new WebSocket(endPointURL);
            chatClient.onmessage = function (event) {

                var messagesArea = document.getElementById("messages");

                var jsonData = JSON.parse(event.data);

                var phoneNumber = jsonData.phone;

                var sessionNumber = '${sessionScope.user.phoneNumber}';

                if (phoneNumber === sessionNumber) {

                    message = "<li><div class=\"my_message\">" + jsonData.sms + "</div></li>";
                }
                else {

                    message = "<li><div class=\"message\">" + jsonData.sms + "</div></li>";
                }

                messagesArea.innerHTML = messagesArea.innerHTML + message;
                messagesArea.scrollTop = messagesArea.scrollHeight;
            };
        }
        function disconnect() {

            chatClient.close();
        }
        function sendMessage() {

            var inputElement = document.getElementById("message_text");
            var message = inputElement.value.trim();

            if (message !== "") {

                chatClient.send(message);
                inputElement.value = "";
            }

            inputElement.focus();
        }
    </script>

</head>

<body onload="connect();" onunload="disconnect();">

<%@ include file="/WEB-INF/includes/header.jsp" %>

<main role="main">

    <%@ include file="/WEB-INF/includes/sidebar.jsp" %>

    <div id="content">
        <h1 class="post-title">Conversation : ${conversation.getContactRecipient(sessionScope.user,contactService)}</h1>

        <div id="conversation_content">
            <ul id="messages">
                <c:forEach items="${conversationSMS}" var="sms">

                    <c:if test="${sms.phoneNumberSender == sessionScope.user.phoneNumber}" var="isMe">

                        <li>
                            <div class="my_message">${sms.getMessage()}</div>
                        </li>

                    </c:if>

                    <c:if test="${!isMe}">

                        <li>
                            <div class="message">${sms.getMessage()}</div>
                        </li>

                    </c:if>
                </c:forEach>
            </ul>
            <input id="message_text" class="text-field" type="text" placeholder="Message"
                   onkeydown="if (event.keyCode === 13) sendMessage();"/>
            <input class="button" type="submit" value="Send" onclick="sendMessage();"/>

        </div>

    </div>

</main>

<!-- footer -->
<%@ include file="/WEB-INF/includes/footer.jsp" %>

</body>

</html>
