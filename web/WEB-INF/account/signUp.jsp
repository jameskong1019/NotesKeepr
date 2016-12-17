<%-- 
    Document   : user
    Created on : Oct 8, 2016, 10:55:32 PM
    Author     : james
--%>
<header class="w3-container w3-teal">
    <h1>Notes Keeper <i class="fa fa-sticky-note-o" aria-hidden="true"></i></h1>
</header>
<%@ include file="/WEB-INF/jspf/headerLogin.jspf"%>
<div class="w3-container w3-padding-top w3-padding-left w3-center" style="margin-top: 60px; width:60%">
</div>
<div class="w3-padding w3-margin-top w3-hide-small" style="min-height: 80%">
    <form action="account" method="post">
        <table class="w3-table w3-bordered" style="width: 60%;" id="registrationForm">
            <tr>
                <td colspan="2">
                    <h1>Sign up</h1>
                </td>
            </tr>
            <tr>
                <th>
                    <label>Username</label>
                </th>
                <td>
                    <input id="editbox" type="text" name="username"/>
                </td>
            </tr>
            <tr>
                <th>
                    <label>First Name</label>    
                </th>
                <td>
                    <input type="text" name="firstname"/>
                </td>
            </tr>
            <tr>
                <th>
                    <label>Last Name</label>    
                </th>
                <td>
                    <input type="text" name="lastname"/>
                </td>
            </tr>
            <tr>
                <th>
                    <label>Password</label>
                </th>
                <td>
                    <input type="password" name="password"/>
                </td>
            </tr>
            <tr>
                <th>
                    <label>Phone Number</label>    
                </th>
                <td>
                    <input type="text" name="phonenumber"/>
                </td>
            </tr>
            <tr>
                <th>
                    <label>Email</label>    
                </th>
                <td>
                    <input type="text" name="email"/>
                </td>
            </tr>
            <tr>
                <th>
                    <label>Gender</label>
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
                    <input class="w3-btn w3-blue-grey" type="submit" value="Sign up">
                    <input type="hidden" name="action" value="signup">                    
                    </form>
                    <form action="login" method="get">
                        <input class="w3-btn w3-blue-grey" type="submit" value="Cancel">
                    </form>
                </td>
            </tr>
        </table>
</div>
<div class="w3-padding w3-margin-top w3-hide-large w3-hide-medium" style="min-height: 80%">
    <table class="w3-table w3-bordered" style="width: 90%;" id="registrationForm">
        <tr>
            <td colspan="2">
                <h1>Sign up</h1>
            </td>
        </tr>
        <tr>
            <th>
                <label>Username</label>
            </th>
            <td>
                <input id="editbox" type="text" name="username"/>
            </td>
        </tr>
        <tr>
            <th>
                <label>First Name</label>    
            </th>
            <td>
                <input type="text" name="firstname"/>
            </td>
        </tr>
        <tr>
            <th>
                <label>Last Name</label>    
            </th>
            <td>
                <input type="text" name="lastname"/>
            </td>
        </tr>
        <tr>
            <th>
                <label>Password</label>
            </th>
            <td>
                <input type="password" name="password"/>
            </td>
        </tr>
        <tr>
            <th>
                <label>Phone Number</label>    
            </th>
            <td>
                <input type="text" name="phonenumber"/>
            </td>
        </tr>
        <tr>
            <th>
                <label>Email</label>    
            </th>
            <td>
                <input type="text" name="email"/>
            </td>
        </tr>
        <tr>
            <th>
                <label>Gender</label>
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
                <input class="w3-btn w3-blue-grey" type="submit" value="Sign up">
                <input type="hidden" name="action" value="signup">                    
                </form>
                <form action="login" method="get">
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
