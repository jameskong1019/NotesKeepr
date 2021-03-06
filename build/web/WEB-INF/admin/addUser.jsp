<%-- 
    Document   : user
    Created on : Oct 8, 2016, 10:55:32 PM
    Author     : james
--%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="w3-padding-left w3-left" style="width:100%;margin-top:60px;">
    <h2><div id="menuTitle">Add User</div></h2> 
    <hr>
</div>
<div class="w3-padding w3-center w3-margin-top" style="min-height: 80%">
    <form action="users" method="post">
        <table id="editUserForm" class="w3-table w3-bordered" style="width: 100%;">
            <tr>
                <th>
                    Username
                </th>
                <td>
                    <input id="editbox" type="text" name="username"/>
                </td>
            </tr>
            <tr>
                <th>
                    First Name
                </th>
                <td>
                    <input type="text" name="firstname"/>
                </td>
            </tr>
            <tr>
                <th>
                    Last Name
                </th>
                <td>
                    <input type="text" name="lastname"/>
                </td>
            </tr>
            <tr>
                <th>
                    Password
                </th>
                <td>
                    <input type="password" name="password"/>
                </td>
            </tr>
            <tr>
                <th>
                    Phone Number
                </th>
                <td>
                    <input type="text" name="phonenumber"/>
                </td>
            </tr>
            <tr>
                <th>
                    Email
                </th>
                <td>
                    <input type="text" name="email"/>
                </td>
            </tr>
            <tr>
                <th>
                    Gender
                </th>
                <td>
                    <select name="gender">
                        <option value="M">Male</option>
                        <option value="F">Female</option>
                        <option value="U">Unknown</option>
                        <option value="N">Non-applicable</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2"  style="text-align: right">
                    <input class="w3-btn w3-blue-grey" type="submit" value="Add">
                    <input type="hidden" name="page" value="${page}">
                    <input type="hidden" name="action" value="add">                    
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

</div>
</body>
</html>
