<%-- 
    Document   : notes
    Created on : Oct 21, 2016, 11:14:10 PM
    Author     : james
--%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<script src="js/editCollaborateNote.js?ver=1"></script>
<div class="w3-container w3-padding-top w3-padding-left" style="margin-top:40px">
    <h1><div id="menuTitle">Edit Shared Note</div></h1>
    <hr>
</div>
<div class="w3-container w3-padding-top w3-padding-left w3-hide-large w3-hide-medium"></div>
<div class="w3-container w3-center" style="min-height: 60%;">
    <table class="w3-table w3-bordered">
        <input type="hidden" name="noteID" id="noteID" value="${selectedNote.noteid}">
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
                <c:forEach items="${selectedNote.userList}" var="user">
                    ${user.username} |
                </c:forEach>
                ${selectedNote.owner.username}
            </td>
        </tr>
        <tr>
            <td colspan='2'>
                <input class="w3-input" type="text" id="collaboNoteTitle" name="title" style="width:100%" placeholder="Title" value="${selectedNote.title}">
            </td>
        </tr>
        <tr>
            <td colspan='2'>
                <div id="collaboNoteContents">${selectedNote.contents}</div>
            </td>
        </tr>
        <tr><td colspan="2" style="text-align: right; "> 
                <input type="hidden" name="action" value="save" id='action'>
                <input type="hidden" name="page" value="${page}" id='page'>
                <input class="w3-btn w3-blue-grey" type="button" value="Save" id='saveButton'>
                <form action="collaboratedNotes" method="get">
                    <input type="hidden" name="page" value="${page}">
                    <input type="hidden" name="action" value="cancel">
                    <input class="w3-btn w3-blue-grey" type="submit" value="Cancel">
                </form>
            </td></tr>
    </table>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
