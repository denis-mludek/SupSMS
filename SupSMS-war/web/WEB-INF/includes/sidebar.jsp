<aside id="sidebar">

    <c:if test="${sessionScope.user != null }" var="connected">

        <section class="widget">
            <h4 class="widgettitle">Welcome ${sessionScope.user.firstName}</h4>
            <ul>
                <li><a href="<c:url value="/user/myProfile"></c:url>">Edit my profile</a></li>
                <li><a href="<c:url value="/user/myInvoices"></c:url>">My invoices</a></li>
            </ul>
        </section>

        <c:if test="${sessionScope.user.adminBool == true}" var="isAdmin">
            <section class="widget">
                <h4 class="widgettitle">Administration</h4>
                <ul>
                    <li><a href="<c:url value="/admin/users"></c:url>">Manage users</a></li>
                    <li><a href="<c:url value="/admin/allInvoices"></c:url>">See all invoices</a></li>
                </ul>
            </section>
        </c:if>
    </c:if>
    <c:if test="${ !connected }">
        <section class="widget">
            <h4 class="widgettitle">Sup SMS</h4>

            <p id="community_speech">Join the community<br>Right now !</p>

            <img id="rabbit" src="/SupSMS-war/assets/img/rabbit.png" alt="rabbit">
        </section>
    </c:if>
</aside>
