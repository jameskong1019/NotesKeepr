<%-- 
    Document   : notes
    Created on : Oct 21, 2016, 11:14:10 PM
    Author     : james
--%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="w3-container w3-padding-top w3-padding-left" style="margin-top:40px">
    <h1>Collaborate Notes</h1>
    <hr>
</div>
<c:if test="${not empty noteList}">
    <div class="w3-padding-left w3-left" style="width:100%">
        <h2>List of All collaborate notes</h2>
    </div>
    <div class="w3-padding w3-center w3-margin-top" style="min-height: 60%;">
        <div id="responsive-table">
            <table class="w3-table-all w3-hoverable w3-centered">
                <tr class="w3-blue-grey">
                    <th style="width:10%">Note Id</th> 
                    <th>Contents</th>
                    <th style="width:15%">Created Date</th>
                    <th style="width:10%">Owner</th>
                    <th style="width:10%">Edit</th>
                </tr>
                <c:forEach items="${noteList}" begin="${(page-1)*ITEMSIZE}" end="${(page-1)*ITEMSIZE+(ITEMSIZE-1)}" var="note">
                    <fmt:formatDate value="${note.datecreated}" var="formattedDate" 
                                    type="date" pattern="MM/dd/yyyy HH:mm:ss" />
                    <tr>
                        <td style="align-items: center">${note.noteid}</td>
                        <td><c:out value="${note.contents}" default =""/></td>
                        <td>${formattedDate}</td>
                        <td>${note.owner.username}</td>
                        <td>
                            <form action="collaboratedNotes" method="get">
                                <input type="submit" class="w3-btn w3-blue-grey  w3-hover-blue" value="Edit"/>
                                <input type="hidden" name="page" value="${page}">
                                <input type="hidden" name="action" value="view">
                                <input type="hidden" name="noteID" value="${note.noteid}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
        <table class="w3-table w3-centered">
            <tr>
                <td>
                    <div class ="w3-centered">
                        <ul class="w3-pagination">
                            <c:if test="${page gt 1}">
                                <li>
                                    <a href ="collaboratedNotes?page=1">Start</a>    
                                </li>
                                <li>
                                    <a href ="collaboratedNotes?page=${page - 1}">&lt;</a>
                                </li>
                            </c:if>
                            <c:forEach var="i" begin="${startPageNumber}" end="${endPageNumber}">
                                <c:if test="${totalPageNumber ge i}">
                                    <c:if test="${page ne i}">
                                        <li>
                                            <a href="<c:url value='notes?page=${i}'/>">${i}</a>    
                                        </li>
                                    </c:if>
                                    <c:if test="${page eq i}">
                                        <li>
                                            <a class="w3-green" href="<c:url value='notes?page=${i}'/>">
                                                ${i}
                                            </a>
                                        </li>
                                    </c:if>
                                </c:if>
                            </c:forEach>

                            <c:if test="${page ne totalPageNumber}">
                                <li><a href ="collaboratedNotes?page=${page + 1}">&gt;</a></li>
                                <li><a href ="collaboratedNotes?page=${totalPageNumber}">End</a></li>
                                </c:if>
                            <hr>
                        </ul>
                    </div>
                </td>
            <tr>
        </table>
    </div>
</c:if>

<c:if test="${empty noteList}">
    <div class="w3-container w3-padding-left" style="min-height: 70%">
        <h2>You don't have collaborated Notes</h2>
        <h3>Please ask friends to add you as a collaborator :)</h3>
    </div>
</c:if>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
