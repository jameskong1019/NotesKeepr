<%-- 
    Document   : login
    Created on : Oct 8, 2016, 9:33:14 PM
    Author     : james
--%>
<%@ include file="/WEB-INF/jspf/headerLogin.jspf"%>
<div class="w3-display-container w3-green" style="width: 100%; height:100vh">
    <div class="w3-display-middle">
        <form action="login" method="Post">
            <table class="w3-table w3-left-align" id="login">
                <tr>
                    <td colspan="2">
                        <h1><i class="fa fa-sticky-note-o" aria-hidden="true"></i> Notes Keeper</h1>
                    </td>
                </tr>
                <tr>
                    <td><input class="w3-input w3-green" type="text" name="username" style=" width:100%" placeholder="Username"></td>
                </tr>
                <tr>
                    <td><input class="w3-input w3-green" type="password" name="password" style=" width:100%" placeholder="Password"></td>
                </tr>
                <tr> 
                    <td style="text-align: center"><input class="w3-btn w3-white w3-hover-teal w3-round-xlarge" type="submit" value="Login" style=" width:100%"></td>
                    </form>
                </tr>
                <tr> 
                    <td style="text-align: center">
                        <form action="login" method="get">
                            <input type="hidden" name="action" value="signup">
                            <input class="w3-btn w3-white w3-hover-teal w3-round-xlarge" type="submit" value="Sign Up" style=" width:100%">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        ${displayMessage}
                    </td>
                </tr>
                <c:if test="${not empty loginError}">
                    <tr id="resetPassword">
                        <td>
                            Did you forget your password?<br> Please,
                            <a href="account?action=resetPassword" style="color:blue">Reset password</a>
                        </td>
                    </tr>
                </c:if>
            </table>
    </div>
</div>
</body>
</html>
