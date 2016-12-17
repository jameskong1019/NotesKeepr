<%-- 
    Document   : notes
    Created on : Oct 21, 2016, 11:14:10 PM
    Author     : james
--%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<script src="js/editnote.js?ver=2"></script>
<div class="w3-container w3-padding-top w3-padding-left" style="margin-top:40px">
    <h1><div id="menuTitle">View Note</div></h1>
    <hr>
</div>
<div class="w3-padding w3-center" style="min-height: 60%;">
    <table class="w3-table w3-bordered">
        <input id="noteID" type="hidden" name="noteID" value="${selectedNote.noteid}">
        <fmt:formatDate value="${selectedNote.datecreated}" var="formattedDate" 
                        type="date" pattern="MM/dd/yyyy HH:mm:ss" />
        <tr>
            <td>
                <label>Date</label>
            </td>
            <td>
                ${formattedDate}
            </td>
        </tr>
        <tr>
            <td>
                <label>Collaborators</label>
            </td>
            <td>
                <div id="listCollaborator">
                    <c:choose>
                        <c:when test="${not empty selectedNote.userList}">
                            <c:forEach items="${selectedNote.userList}" var="user" varStatus="status">
                                ${user.username} 
                                <span type="hidden" value="${user.username}" id="usernameToRemove"/>
                                <c:if test="${!status.last}">
                                    |
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            No collaborators
                        </c:otherwise>
                    </c:choose>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <label>Title</label>
            </td>
            <td colspan='2'>
                ${selectedNote.title}
            </td>
        </tr>
        <tr>
            <td colspan='2'>
                ${selectedNote.contents}
            </td>
        </tr>
        <tr><td colspan="2" style="text-align: right; "> 
                <form action="notes" method="get">
                    <input id="action" type="hidden" name="action" value="edit">
                    <input id="page" type="hidden" name="page" value="${page}">
                    <input id="noteID" type="hidden" name="noteID" value="${selectedNote.noteid}">
                    <input id="saveButton" class="w3-btn w3-blue-grey" type="submit" value="Edit">
                </form>
                <form action="notes" method="get">
                    <input type="hidden" name="page" value="${page}">
                    <input type="hidden" name="action" value="cancel">
                    <input class="w3-btn w3-blue-grey" type="submit" value="Cancel">
                </form>
            </td></tr>
    </table>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<script>

</script>
</body>
</html>
