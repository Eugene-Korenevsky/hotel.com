<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>

 <fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/> 
<u:html>
    <div id="body">
	<u:choseroom/>
        <table id="table">
            <tbody id="tbody">
              <tr>
<td><fmt:message key="${ message }" bundle="${rs}" /></td>
<td><c:out value="${ user.name }"  /></td>
<td><c:out value="${ user.surname }" /></td>
<td><c:out value="${ user.role }" /></td>
<td><c:if test="${user.role eq 'administration'}">
    <form method="GET" action="orders.html">
            <button type="submit"><fmt:message key="order.button" bundle="${rs}" /></button>
        </form>
		<br/>
		<form action="reserves.html" method="GET">
            <button type="submit"><fmt:message key="reserve.button" bundle="${rs}" /></button>
			</form>
			<br/>
		<form action="rooms.html" method"GET">
            <button type="submit"><fmt:message key="room.button" bundle="${rs}" /></button>
        </form>
    </c:if></td>
</tr>  
            </tbody>
        </table>
    </div>
	 
    </div>

</u:html>