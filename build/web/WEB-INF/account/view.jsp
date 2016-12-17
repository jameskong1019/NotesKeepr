<%-- 
    Document   : view
    Created on : Nov 3, 2016, 5:44:40 PM
    Author     : james
--%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="w3-container w3-padding-top w3-padding-left" style="margin-top:40px">
    <h1><div id="menuTitle">View Account</div></h1>
    <hr>
</div>
<div class="w3-container w3-padding" style="min-height: 70%">
    <form action="account" method="get">
        <div id="responsive-table">
            <table class="w3-table w3-bordered" style="width:100%">
                <tr>
                    <th>
                        Username
                    </th>
                    <td>
                        ${loginUser.username}
                    </td>
                </tr>
                <tr>
                    <th>
                        <label>Password</label>
                    </th>
                    <td>
                        <c:out value="${loginUser.password}"></c:out>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label>Email</label>
                        </th>
                        <td>
                        ${loginUser.email}
                    </td>
                </tr>
                <tr>
                    <th>
                        <label>First Name</label>
                    </th>
                    <td>
                        ${loginUser.firstname}
                    </td>
                </tr>
                <tr>
                    <th>
                        <label>Last Name</label>
                    </th>
                    <td>
                        ${loginUser.lastname}
                    </td>
                </tr>
                <tr>
                    <th>
                        <label>Phone Number</label>
                    </th>
                    <td>
                        ${loginUser.phonenumber}
                    </td>
                </tr>
                <tr>
                    <th>
                        <label>Gender</label>
                    </th>
                    <td>
                        <c:if test="${loginUser.gender eq 'M'}">Male</c:if>
                        <c:if test="${loginUser.gender eq 'F'}">Female</c:if>
                        <c:if test="${loginUser.gender eq 'U'}">Unknown</c:if>
                        <c:if test="${loginUser.gender eq 'N'}">Non-applicable</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"  style="text-align: right">
                            <input type="hidden" name="action" value="change">
                            <input class="w3-btn w3-blue-grey" type="submit" value="Change Account">
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</body>
</html>
