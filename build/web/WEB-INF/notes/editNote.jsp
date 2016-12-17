<%-- 
    Document   : notes
    Created on : Oct 21, 2016, 11:14:10 PM
    Author     : james
--%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<script src="js/editnote.js?ver=2"></script>
<div class="w3-container w3-padding-top w3-padding-left" style="margin-top:40px">
    <h1><div id="menuTitle">Edit Note</div></h1>
    <hr>
</div>
<div class="w3-padding w3-center" style="min-height: 60%;">
    <table class="w3-table w3-bordered">
        <input id="noteID" type="hidden" name="noteID" value="${selectedNote.noteid}">
        <fmt:formatDate value="${selectedNote.datecreated}" var="formattedDate" 
                        type="date" pattern="MM/dd/yyyy HH:mm:ss" />
        <tr>
            <td style="width:50%">
                <label>Date</label>
            </td>
            <td style="width:50%">
                ${formattedDate}
            </td>
        </tr>
        <tr>
            <td>
                <label>Collaborators</label>
                <button id="addCollboratorButton"><i class="fa fa-plus-square fa-lg" aria-hidden="true"></i></button>
            </td>
            <td>
                <div id="listCollaborator">
                    <c:choose>
                        <c:when test="${not empty selectedNote.userList}">
                            <c:forEach items="${selectedNote.userList}" var="user" varStatus="status">
                                ${user.username} <button id="removeCollaboratorButton" value="${user.username}">
                                    <i class="fa fa-minus-square fa-lg" aria-hidden="true"></i>
                                </button>
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
                <div id="addCollaborator" style="display: none;">
                    <input type="text" id="usernameToAdd" style="max-width: 500px" placeholder="Enter username">
                    <input type="button" class="w3-btn w3-blue-grey" id="submitCollaboratorButton"  value="Add" disabled> 
                    <input type="button" class="w3-btn w3-blue-grey" id="cancelCollaboratorButton" value="Cancel"><br>
                    <div id="checkUser"></div>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan='2'>
                <input class="w3-input" type="text" id="myNoteTitle" name="title" style="width:100%" placeholder="Title" value="${selectedNote.title}">
            </td>
        </tr>
        <tr>
            <td colspan='2'>
                <div id="myNoteContents" style="width:100%">${selectedNote.contents}</div>
            </td>
        </tr>
        <tr><td colspan="2" style="text-align: right; "> 
                <input id="action" type="hidden" name="action" value="save">
                <input id="page" type="hidden" name="page" value="${page}">
                <input id="saveButton" class="w3-btn w3-blue-grey" type="submit" value="Save">
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
