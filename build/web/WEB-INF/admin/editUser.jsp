<%-- 
    Document   : user
    Created on : Oct 8, 2016, 10:55:32 PM
    Author     : james
--%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="w3-padding-left w3-left" style="width:100%; margin-top:60px;">
    <h2><div id="menuTitle">Edit User</div></h2>
    <hr>
</div>
<div class="w3-padding w3-center w3-margin-top" style="min-height: 80%">
    <form action="users" method="post">
        <table id="editUserForm" class="w3-table w3-bordered" style="width: 100%;" >
            <tr>
                <th>
                    Username
                </th>
                <td>
                    <input type="text" name="username" value="${selectedUser.username}" readonly/>
                </td>
            </tr>
            <tr>
                <th>
                    Password
                </th>
                <td>
                    <input type="password" name="password" value="${selectedUser.password}"/>
                </td>
            </tr>
            <tr>
                <th>
                    Email
                </th>
                <td>
                    <input type="text" name="email" value="${selectedUser.email}" readonly/>
                </td>
            </tr>
            <tr>
                <th>
                    First Name
                </th>
                <td>
                    <input type="text" name="firstname" value="${selectedUser.firstname}"/>
                </td>
            </tr>
            <tr>
                <th>
                    Last Name
                </th>
                <td>
                    <input type="text" name="lastname" value="${selectedUser.lastname}"/>
                </td>
            </tr>
            <tr>
                <th>
                    Phone Number
                </th>
                <td>
                    <input type="text" name="phonenumber" value="${selectedUser.phonenumber}"/>
                </td>
            </tr>
            <tr>
                <th>
                    Gender
                </th>
                <td>
                    <select name="gender">
                        <option value="M" <c:if test="${selectedUser.gender eq 'M'}">selected</c:if>>Male</option>
                        <option value="F" <c:if test="${selectedUser.gender eq 'F'}">selected</c:if>>Female</option>
                        <option value="U" <c:if test="${selectedUser.gender eq 'U'}">selected</c:if>>Unknown</option>
                        <option value="N" <c:if test="${selectedUser.gender eq 'N'}">selected</c:if>>Non-applicable</option>
                        </select>
                    </td>
                </tr>
            <c:if test="${loginUser.username != selectedUser.username}">
                <tr>
                    <th>
                        Permission
                    </th>
                    <td>
                        <select name="permission">
                            <option value="admin" <c:if test="${not empty selectedUser.roleList}">selected</c:if>>Admin</option>
                            <option value="user" <c:if test="${empty selectedUser.roleList}">selected</c:if>>User</option>
                            </select>
                        </td>
                    </tr>
            </c:if>
            <tr>
                <td colspan="2"  style="text-align: right">
                    <input class="w3-btn w3-blue-grey" type="submit" value="Save">
                    <input type="hidden" name="page" value="${page}">
                    <input type="hidden" name="action" value="save">
                    </form>
                    <form action="users" method="get">
                        <input type="hidden" name="page" value="${page}">
                        <input type="hidden" name="action" value="cancel">
                        <input class="w3-btn w3-blue-grey" type="submit" value="Cancel">
                    </form>
                </td>
            </tr>
        </table>

</div>


<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</body>
</html>
