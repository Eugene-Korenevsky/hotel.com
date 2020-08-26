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
               <c:forEach var="elem" items="${result}" varStatus="status">
               <tr>
                    <th><fmt:message key="room.number" bundle="${rs}" /></th>
                    <th><fmt:message key="room.class.message" bundle="${rs}" /></th>
                    <th><fmt:message key="room.price" bundle="${rs}" /></th>
                    <th><fmt:message key="room.sits" bundle="${rs}" /></th>
					<th></th>
                    <th></th>
					<th></th>
                </tr>
<tr id="row">
<td ><c:out value="${ elem.number }"  /></td>
<td ><c:out value="${ elem.comfort }" /></td>
<td ><c:out value="${ elem.price }" /></td>
<td ><c:out value="${ elem.sits }" /></td>
<c:choose>
 <c:when test="${user.role eq 'administration'}">
    <td><form  action="findRoom.html" method="GET" >
					<button id="button" value="${ elem.id }" name="id" type="submit"><fmt:message key="more.button" bundle="${rs}" /></button>
                </form></td>
<td><form  action="createRoomForm.html">
					<button id="button"  type="submit"><fmt:message key="add.room.button" bundle="${rs}" /></button>
                </form></td>
<td><form  action="deleteRoom.html" method="POST" >
					<button id="button" value="${ elem.id }" name="id" type="submit"><fmt:message key="delete.room.button" bundle="${rs}" /></button>
                </form></td>
				 </c:when>
 <c:when test="${user.role eq 'user'}">
<td><form  action="findRoom.html" method="GET" >
					<button id="button" value="${ elem.id }" name="id" type="submit"><fmt:message key="more.button" bundle="${rs}" /></button>
                </form></td>			
				
<td><form  action="makeOrder.html" method="GET" >
                    <p><fmt:message key="date.arrive" bundle="${rs}" /><input type="date" name="dayIn" </p>
					<p><fmt:message key="timeIn" bundle="${rs}" /><input type="time" name="timeIn" </p>
                    <p><fmt:message key="date.leave" bundle="${rs}" /><input type="date" name="dayOut" </p>
					<p><fmt:message key="timeOut" bundle="${rs}" /><input type="time" name="timeOut" </p>
					<button id="button" value="${ elem.id }" name="roomId" type="submit"><fmt:message key="make.order.button" bundle="${rs}" /></button>
					
                </form></td>
 </c:when>
	<c:otherwise>
	   <td><form  action="findRoom.html" method="GET" >
					<button id="button" value="${ elem.id }" name="id" type="submit"><fmt:message key="more.button" bundle="${rs}" /></button>
                </form></td>
	</c:otherwise>
	</c:choose></td>
</tr>
</c:forEach> 
                
            </tbody>



        </table>
		
    </div>

</body>
</u:html>