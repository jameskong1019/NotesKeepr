<%-- 
    Document   : main
    Created on : Oct 8, 2016, 11:17:09 PM
    Author     : james
--%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="w3-container w3-padding" style="min-height:80%; margin-top:40px">
    <h1>Admin Menu</h1>
    <hr>
    <h2>Admin option</h2>
    <form action="admin/users" method="post">
        <input class="w3-btn w3-green w3-xlarge" type="submit" value="Manage Users">
    </form>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
