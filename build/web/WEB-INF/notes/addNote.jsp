<%-- 
    Document   : notes
    Created on : Oct 21, 2016, 11:14:10 PM
    Author     : james
--%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<script src="js/addnote.js?ver=2"></script>
<div class="w3-container w3-padding-top w3-padding-left" style="margin-top:40px">
    <h1><div id="menuTitle">Add Note</div></h1>
    <hr>
</div>
<div class="w3-center" style="min-height: 60%">
    <table class="w3-table">
            <tr>
                <td>
                    <input class="w3-input" maxlength=20 type="text" id="myNoteTitle" name="title" style="width:100%" placeholder="Title">
                </td>
            </tr>
            <tr>
                <td colspan='2'>
                    <div id="myNoteContents">${selectedNote.contents}</div>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: right; "> 
                    <input type="hidden" name="action" value="add" id="action">
                    <input type="hidden" name="page" value="${page}" id="page">
                    <input class="w3-btn w3-blue-grey" type="button" id="composeButton" value="Compose Note">
        <form action="notes" method="get">
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
