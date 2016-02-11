<%-- 
    Document   : header
    Created on : 13 d�c. 2014, 19:26:40
    Author     : YemYem
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<header>

    <div id="header-content">

        <div id="header-content-left">
            <h1 id="site-logo"><a href="<c:url value="/"></c:url>">SupSMS</a></h1>

            <h2 id="site-description">Send & receive SMS ! So powerfull !</h2>

        </div>

        <div id="header-content-right">
            <img id="site-image" alt="supsms logo" src="<c:url value="/assets/img/supsms_logo.png"></c:url>">
        </div>
    </div>
    <nav>
        <ul id="main-nav" class="clearfix">
            <c:if test="${ sessionScope.user != null }" var="connected">

                <li class="nav_item"><a href="<c:url value="/"></c:url>">Home</a></li>
                <li class="nav_item"><a href="<c:url value="/user/logout"></c:url>">Logout</a></li>
                <li class="nav_item"><a href="<c:url value="/user/contacts"></c:url>">Contacts</a></li>
                <li class="nav_item"><a href="<c:url value="/user/sendSms"></c:url>">Send SMS</a></li>
            </c:if>
            <c:if test="${ !connected }">

                <li class="nav_item"><a href="<c:url value="/"></c:url>">Home</a></li>
                <li class="nav_item"><a href="<c:url value="/login"></c:url>">Login</a></li>
                <li class="nav_item"><a href="<c:url value="/register"></c:url>">Register</a></li>
                <li class="nav_item"><a href="<c:url value="/offer"></c:url>">Our Offer</a></li>

            </c:if>
        </ul>
    </nav>

</header>
