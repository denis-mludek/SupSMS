<%-- 
    Document   : error
    Created on : 17 d�c. 2014, 11:16:20
    Author     : YemYem
--%>
<div id="error-display">

    <c:forEach var="error" items="${ errors }">
        <p>Error : ${error}</p>
    </c:forEach>

</div>
