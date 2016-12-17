<%-- 
    Document   : user
    Created on : Oct 8, 2016, 10:55:32 PM
    Author     : james
--%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="w3-container w3-padding-top w3-padding-left" style="margin-top:40px">
    <h1><div id="menuTitle">Manage Users</div></h1>
    <hr>
</div>
<div class="w3-center w3-padding w3-hide-small" style="min-height: 70%">
    <table class="w3-table-all w3-hoverable w3-centered">
        <tr class="w3-blue-grey">
            <th>Username</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Password</th>
            <th>Phone Number</th>
            <th>Gender</th>
            <th>Level</th>
            <th>Delete</th>
            <th>Edit</th>
        </tr>
        <c:forEach items="${userList}"  begin="${(page-1)*ITEMSIZE}" end="${(page-1)*ITEMSIZE+(ITEMSIZE-1)}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.firstname}</td>
                <td>${user.lastname}</td>
                <td><a href="mailto:${user.email}">${user.email}</td>
                <td>${user.password}</td>
                <td>${user.phonenumber}</td>
                <td>${user.gender}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty user.roleList}">
                            Admin
                        </c:when>
                        <c:otherwise>
                            User
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form action="users" method="post">
                        <c:choose>
                            <c:when test="${not empty user.roleList}">
                                <input type="submit" class="w3-btn w3-blue-grey" value="Delete" disabled/>
                            </c:when>
                            <c:otherwise>
                                <input type="submit" class="w3-btn w3-blue-grey  w3-hover-red" value="Delete" onclick="return confirm('Do you wish to delete this user?');return false;"/>
                            </c:otherwise>
                        </c:choose>

                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="page" value="${page}">
                        <input type="hidden" name="username" value="${user.username}">
                    </form>
                </td>
                <td>
                    <form action="users" method="get">
                        <input type="submit" class="w3-btn w3-blue-grey  w3-hover-blue" value="Edit"/>
                        <input type="hidden" name="action" value="view">
                        <input type="hidden" name="page" value="${page}">
                        <input type="hidden" name="username" value="${user.username}">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <table class="w3-table w3-centered w3-hide-small">
        <tr>
            <td>
                <div class ="w3-centered">
                    <ul class="w3-pagination">
                        <c:if test="${page gt 1}">
                            <li>
                                <a href ="users?page=1">Start</a>    
                            </li>
                            <li>
                                <a href ="users?page=${page - 1}">&lt;</a>
                            </li>
                        </c:if>
                        <c:forEach var="i" begin="${startPageNumber}" end="${endPageNumber}">
                            <c:if test="${totalPageNumber ge i}">
                                <c:if test="${page ne i}">
                                    <li>
                                        <a href="<c:url value='users?page=${i}'/>">${i}</a>    
                                    </li>
                                </c:if>
                                <c:if test="${page eq i}">
                                    <li>
                                        <a class="w3-green" href="<c:url value='users?page=${i}'/>">
                                            ${i}
                                        </a>
                                    </li>
                                </c:if>
                            </c:if>
                        </c:forEach>

                        <c:if test="${page ne totalPageNumber}">
                            <li><a href ="users?page=${page + 1}">&gt;</a></li>
                            <li><a href ="users?page=${totalPageNumber}">End</a></li>
                            </c:if>
                        <hr>
                    </ul>
                    <div style="float:right">
                        <form action="users" method="get">
                            <input type="hidden" name="page" value="${page}">
                            <input type="hidden" name="action" value="addUser">
                            <input type="submit" class="w3-btn w3-green" value="Add User">
                        </form>
                    </div>
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
        <c:forEach items="${userList}"  begin="${(page-1)*ITEMSIZE}" end="${(page-1)*ITEMSIZE+(ITEMSIZE-1)}" var="user">
            <div class="w3-row">
                <a href="users?action=view&page=${page}&username=${user.username}">
                    <div class="w3-col s9 w3-container">
                        <h3>${user.username}</h3>
                        <p>${user.phonenumber}</p>
                    </div>
                </a>
                <div class="w3-col s3 w3-container">
                    <div class="w3-padding-top w3-margin-top">
                        <form action="users" method="post">
                            <button type="submit" value="Delete" class="w3-btn w3-blue-grey  w3-hover-red" onclick="return confirm('Do you wish to delete this user?');return false;">
                                <i class="fa fa-trash" aria-hidden="true"></i>
                            </button>
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="page" value="${page}">
                            <input type="hidden" name="username" value="${user.username}">
                        </form>
                    </div>
                </div>
            </div>  
            <hr>                
        </c:forEach>
        <form action="users" method="get" style='text-align: right'>
            <input type="hidden" name="page" value="${page}">
            <input type="hidden" name="action" value="addUser">
            <input type="submit" class="w3-btn w3-green" value="Add User">
        </form>
        <br>
    </div>

</div>                         
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
