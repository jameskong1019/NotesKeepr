<%-- 
    Document   : notes
    Created on : Oct 21, 2016, 11:14:10 PM
    Author     : james
--%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<script src="${pageContext.request.contextPath}/js/scroll.js?ver=1"></script>
<div class="w3-container w3-padding-top w3-padding-left" style="margin-top:40px">
    <h1><div id="menuTitle">Shared Notes</div></h1>
    <hr>
</div>
<c:if test="${not empty noteList}">
    <div class="w3-padding w3-center w3-hide-small" style="min-height: 60%;">
        <div id="responsive-table">
            <table class="w3-table-all w3-hoverable w3-centered">
                <tr class="w3-blue-grey">
                    <th style="width:10%">No</th> 
                    <th>Title</th>
                    <th style="width:15%">Date</th>
                    <th style="width:10%">Owner</th>
                    <th style="width:10%">Edit</th>
                </tr>
                <c:forEach items="${noteList}" begin="${(page-1)*ITEMSIZE}" end="${(page-1)*ITEMSIZE+(ITEMSIZE-1)}" var="note" varStatus="status">
                    <fmt:formatDate value="${note.datecreated}" var="formattedDate" 
                                    type="date" pattern="MM/dd/yyyy HH:mm:ss" />
                    <tr>
                        <td style="align-items: center">${status.index + 1}</td>
                        <td>
                            <a href="collaboratedNotes?action=view&noteID=${note.noteid}&page=${page}" style="text-decoration: none;">
                                <div style="height:100%;width:100%">
                                    ${note.title}
                                </div>
                            </a>
                        </td>
                        <td>${formattedDate}</td>
                        <td>${note.owner.username}</td>
                        <td>
                            <form action="collaboratedNotes" method="get">
                                <input type="submit" class="w3-btn w3-blue-grey  w3-hover-blue" value="Edit"/>
                                <input type="hidden" name="page" value="${page}">
                                <input type="hidden" name="action" value="edit">
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
    <!--Small list-->
    <div class="w3-hide-large w3-hide-medium" style="min-height: 65%">
        <div class="w3-container">
            <c:forEach items="${noteList}" begin="0" end="${noteListSize}" var="note" varStatus="status">
                <fmt:formatDate value="${note.datecreated}" var="formattedDate" 
                                type="date" pattern="MM/dd/yyyy HH:mm:ss" />
                <div class="w3-row">
                    <a href="collaboratedNotes?action=view&page=${page}&noteID=${note.noteid}">
                        <div class="w3-col s9 w3-container">
                            <h3>${note.title}</h3>
                            <p>${formattedDate}</p>
                        </div>
                    </a>
                    <div class="w3-col s3 w3-container">
                        <div class="w3-padding-top w3-margin-top">
                            <form action="collaboratedNotes" method="get">
                                <button type="submit" class="w3-btn w3-blue-grey  w3-hover-blue">
                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                </button>
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="page" value="${page}">
                                <input type="hidden" name="noteID" value="${note.noteid}">
                            </form>
                        </div>
                    </div>
                </div>  
                <hr>                
            </c:forEach>
            <%--<c:if test="${noteListSize > 10}">--%>
            <!--                <div class="w3-row">
                                <div class="w3-col w3-center w3-container">
                                    <h3>Load More &nbsp; <i class="fa fa-angle-double-down" aria-hidden="true"></i></h3>
                                </div>
                            </div>-->
            <%--</c:if>--%>
        </div>
    </div>
</c:if>

<c:if test="${empty noteList}">
    <div class="w3-container w3-padding-left" style="min-height: 70%">
        <h2>You don't have shared notes</h2>
        <h3>Please ask friends to add you as a collaborator :)</h3>
    </div>
</c:if>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
