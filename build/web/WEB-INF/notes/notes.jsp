<%-- 
    Document   : notes
    Created on : Oct 21, 2016, 11:14:10 PM
    Author     : james
--%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<script src="${pageContext.request.contextPath}/js/scroll.js?ver=1"></script>
<div class="w3-container w3-padding-top w3-padding-left" style="margin-top:40px">
    <h1><div id="menuTitle">My Notes</div></h1>
    <hr>
</div>
<c:if test="${not empty noteList}">
    <div class="w3-center w3-padding w3-hide-small" style="min-height: 70%;">
        <div id="responsive-table">
            <table class="w3-table-all w3-hoverable w3-centered">
                <tr class="w3-blue-grey">
                    <th style="width:10%">No</th> 
                    <th>Title</th>
                    <th style="width:30%">Date</th>
                    <th style="width:10%">Delete</th>
                    <th style="width:10%">Edit</th>
                </tr>
                <c:forEach items="${noteList}" begin="${(page-1)*ITEMSIZE}" end="${(page-1)*ITEMSIZE+(ITEMSIZE-1)}" var="note" varStatus="status">
                    <fmt:formatDate value="${note.datecreated}" var="formattedDate" 
                                    type="date" pattern="MM/dd/yyyy HH:mm:ss" />
                    <tr>
                        <td style="align-items: center">${status.index + 1}</td>
                        <td>
                            <a href="notes?action=view&noteID=${note.noteid}&page=${page}" style="text-decoration: none;">
                                <div style="height:100%;width:100%">
                                    ${note.title}
                                </div>
                            </a>
                        </td>
                        <td>${formattedDate}</td>
                        <td>
                            <form action="notes" method="post">
                                <input type="submit" value="Delete" class="w3-btn w3-blue-grey  w3-hover-red" onclick="return confirm('Do you wish to delete this note?');return false;"/>
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="page" value="${page}">
                                <input type="hidden" name="noteID" value="${note.noteid}">
                            </form>
                        </td>
                        <td>
                            <form action="notes" method="get">
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
        <div class="w3-hide-small" style="float:right">
            <form action="notes" method="get">
                <input type="hidden" name="page" value="${page}">
                <input type="hidden" name="action" value="addNote">
                <input type="submit"  class="w3-btn w3-green" value="Compose Note">
            </form>
        </div>
        <br><br>
        <table class="w3-table w3-centered w3-hide-small">
            <tr>
                <td>
                    <div class ="w3-centered">
                        <ul class="w3-pagination">
                            <c:if test="${page gt 1}">
                                <li>
                                    <a href ="notes?page=1">Start</a>    
                                </li>
                                <li>
                                    <a href ="notes?page=${page - 1}">&lt;</a>
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
                                <li><a href ="notes?page=${page + 1}">&gt;</a></li>
                                <li><a href ="notes?page=${totalPageNumber}">End</a></li>
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
        <!--<ul class="w3-ul w3-card-4">-->
        <!--                <li class="w3-blue-grey">
                            <span class="w3-small w3-left">List of notes</span><br>
                        </li>-->
        <div class="w3-container">
            <c:forEach items="${noteList}" begin="0" end="${noteListSize}" var="note" varStatus="status">
                <fmt:formatDate value="${note.datecreated}" var="formattedDate" 
                                type="date" pattern="MM/dd/yyyy HH:mm:ss" />
                <div class="w3-row">
                    <a href="notes?action=view&page=${page}&noteID=${note.noteid}">
                        <div class="w3-col s9 w3-container">
                            <h3>${note.title}</h3>
                            <p>${formattedDate}</p>
                        </div>
                    </a>
                    <div class="w3-col s3 w3-container">
                        <div class="w3-padding-top w3-margin-top">
                            <form action="notes" method="post">
                                <button type="submit" value="Delete" class="w3-btn w3-blue-grey  w3-hover-red" onclick="return confirm('Do you wish to delete this note?');return false;">
                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                </button>
                                <input type="hidden" name="action" value="delete">
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
            <div class="w3-hide-large" style="float:right">
                <a class="w3-btn-floating-large w3-green w3-xxlarge"  id="fixedComposeButton" href="notes?page=${page}&action=addNote" >+</a>
            </div>
        </div>
    </div>
</c:if>
<!--<script>
    var load = 0;
    var text = "";
    var i;
    var test;
    var data;
    $(document).ready(function () {
        $("#loadMore").click(function () {

    <c:set var="loadNum" value="${0}"/>

    <c:set var="begin" value="${0}"/>
    <c:set var="end" value="${0}"/>

            var notes = new Array();

    <c:forEach items="${noteList}" var="note">
            var note = new Object();
        <fmt:formatDate value="${note.datecreated}" var="formattedDate" type="date" pattern="MM/dd/yyyy HH:mm:ss" />

            note.noteid = '${note.noteid}';
            note.contents = '${note.contents}';
            note.formattedDate = '${formattedDate}';

            notes.push(note);

    </c:forEach>

            for (var i = 10 * load + 10; i < 10 * load + 20; i++) {

                if (i < notes.length) {
                    text += "<li class=\"w3-white\">";
                    text += "<span class=\"w3-margin-right w3-padding-top w3-right\">";
                    text += "<form action=\"notes\" method=\"get\">";
                    text += "<button type=\"submit\" class=\"w3-btn w3-blue-grey\">";
                    text += "<i class=\"fa fa-pencil\"></i>";
                    text += "</button>";
                    text += "<input type=\"hidden\" name=\"page\" value=\"" +${page} + "\">";
                    text += "<input type=\"hidden\" name=\"action\" value=\"view\">";
                    text += "<input type=\"hidden\" name=\"noteID\" value=\"" + notes[i].noteid + "\">"
                    text += "</form>";
                    text += "</span>";
                    text += "<span class=\"w3-margin-right w3-padding-top w3-right\">";
                    text += "<form action=\"notes\" method=\"post\">";
                    text += "<button type=\"submit\" class=\"w3-btn w3-blue-grey\" onclick=\"return confirm('Do you wish to delete this note?');return false;\">";
                    text += "<i class=\"fa fa-trash\" aria-hidden=\"true\"></i>";
                    text += "</button>";

                    text += "<input type=\"hidden\" name=\"action\" value=\"delete\">"
                    text += "<input type=\"hidden\" name=\"page\" value=\"" +${page} + "\">";
                    text += "<input type=\"hidden\" name=\"noteID\" value=\"" + notes[i].noteid + "\">";
                    text += "</form>";
                    text += "</span>";
                    text += "<span class=\"w3-xlarge w3-left\">" + notes[i].contents + "</span><br><br>";
                    text += "<span class=\"w3-small w3-left\">" + notes[i].formattedDate + "</span><br>";
                    text += "</li>";
                } else
                {
                    $("#loadMore").hide();
                    break;
                }

            }

            load++;
            $("#loadList").html(text);
        });
    });
</script>-->

<c:if test="${empty noteList}">
    <div class="w3-container w3-padding" style="min-height: 70%;">
        <h2>Please compose note</h2>
        <br>
        <form action="notes" method="get">
            <input type="hidden" name="page" value="${page}"/>
            <input type="hidden" name="action" value="addNote"/>
            <input class="w3-btn w3-green"type="submit" value="Compose Note"/>
        </form>         
    </div>
</c:if>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
