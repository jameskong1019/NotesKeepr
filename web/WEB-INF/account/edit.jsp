<%-- 
    Document   : edit
    Created on : Nov 3, 2016, 5:45:01 PM
    Author     : james
--%>

<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="w3-container w3-padding-top w3-padding-left" style="margin-top:40px;">
    <h1><div id="menuTitle">Change<c:if test="${empty loginUser.roleList}">/Delete</c:if> Account</div></h1>
        <hr>
    </div>
    <div class="w3-container w3-padding" style="min-height: 70%">
        <form action="account" method="post">
            <table id="editUserForm" class="w3-table w3-bordered" style="width:100%">
                <tr>
                    <th>
                        Username
                    </th>
                    <td>
                        <input type="text" name="username" value="${loginUser.username}" readonly/>
                </td>
            </tr>
            <tr>
                <th>
                    Password
                </th>
                <td>
                    <input type="password" name="password" value="${loginUser.password}"/>
                </td>
            </tr>
            <tr>
                <th>
                    Email
                </th>
                <td>
                    <input type="text" name="email" value="${loginUser.email}" readonly/>
                </td>
            </tr>
            <tr>
                <th>
                    First Name
                </th>
                <td>
                    <input type="text" name="firstname" value="${loginUser.firstname}"/>
                </td>
            </tr>
            <tr>
                <th>
                    Last name
                </th>
                <td>
                    <input type="text" name="lastname" value="${loginUser.lastname}"/>
                </td>
            </tr>
            <tr>
                <th>
                    Phone Number
                </th>
                <td>
                    <input type="text" name="phonenumber" value="${loginUser.phonenumber}"/>
                </td>
            </tr>
            <tr>
                <th>
                    Gender
                </th>
                <td>
                    <select name="gender">
                        <option value="M" <c:if test="${loginUser.gender eq 'M'}">selected</c:if>>Male</option>
                        <option value="F" <c:if test="${loginUser.gender eq 'F'}">selected</c:if>>Female</option>
                        <option value="U" <c:if test="${loginUser.gender eq 'U'}">selected</c:if>>Unknown</option>
                        <option value="N" <c:if test="${loginUser.gender eq 'N'}">selected</c:if>>Non-applicable</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: right">
                        <input type="hidden" name="action" value="save">
                        <input type="submit" class="w3-btn w3-blue-grey" value="Change Account">
                        </form>
                    <c:if test="${empty loginUser.roleList}">
                        <form action="account" method="post">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="username" value="${loginUser.username}">
                            <input type="submit" class="w3-btn w3-blue-grey" onclick="return confirm('Do you wish to delete this user?');return false;" value="Delete Account">
                        </form>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan='2' style="text-align: right">
                    <form action="account" method="get">
                        <input type="submit" class="w3-btn w3-blue-grey" value="Cancel">
                    </form>
                </td>
            </tr>
        </table>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</body>
</html>
