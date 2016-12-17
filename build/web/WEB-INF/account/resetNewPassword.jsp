<%-- 
    Document   : user
    Created on : Oct 8, 2016, 10:55:32 PM
    Author     : james
--%>
<header class="w3-container w3-teal">
    <h1>Notes Keeper <i class="fa fa-sticky-note-o" aria-hidden="true"></i></h1>
</header>
<%@ include file="/WEB-INF/jspf/headerLogin.jspf"%>
<div class="w3-container w3-padding-top w3-padding-left" style="margin-top: 60px">
    <h1>Reset password</h1>
    <hr>
</div>
<div class="w3-container w3-padding w3-margin-top" style="min-height: 70%;">
    <div class="w3-card-4 w3-hide-small w3-hide-medium" style="width:50%" id="resetPasswordForm">
        <div class="w3-container w3-white">
            <h1>Please enter your new password!</h1><hr>
        </div>
        <div class="w3-container">
            <table>
                <tr>
                    <td>
                        <i class="fa fa-key fa-2x w3-margin-bottom" aria-hidden="true"></i>
                    </td>
                <form action="account" method="post">
                    <td>
                        <input id="email" class="w3-input" type="password" name="resetNewPassword" style="width:100%" placeholder="New password"/><br>
                        <input type="hidden" name="action" value="resetNewPassword">                       
                        <input type="hidden" name="resetUuid" value="${resetUuid}">                       
                    </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="w3-right-align">
                            <input class="w3-btn w3-blue-grey" type="submit" value="Submit">
                </form>
                <form action="login" method="get">
                    <input class="w3-btn w3-blue-grey" type="submit" value="Cancel"><br><br>
                </form>
                </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div id="error">${displayMessage}</div><br><br>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="w3-card-4 w3-hide-large" style="width:100%" id="resetPasswordForm">
        <div class="w3-container w3-white">
            <h1>Please enter your new password!</h1><hr>
        </div>
        <div class="w3-container">
            <table>
                <tr>
                    <td>
                        <i class="fa fa-key fa-2x w3-margin-bottom" aria-hidden="true"></i>
                    </td>
                <form action="account" method="post">
                    <td>
                        <input id="email" class="w3-input" type="text" name="resetNewPassword" style="width:100%" placeholder="New password"/><br>
                        <input type="hidden" name="action" value="resetNewPassword">                       
                        <input type="hidden" name="resetUuid" value="${resetUuid}">                       
                    </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="w3-right-align">
                            <input class="w3-btn w3-blue-grey" type="submit" value="Submit">
                </form>
                <form action="login" method="get">
                    <input class="w3-btn w3-blue-grey" type="submit" value="Cancel"><br><br>
                </form>
                </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div id="error">${displayMessage}</div><br><br>
                    </td>
                </tr>
            </table>
        </div>
    </div>

</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
