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
                    
                    <th><fmt:message key="room.number" bundle="${rs}" /></th>
                    <th><fmt:message key="room.class.message" bundle="${rs}" /></th>
                    <th><fmt:message key="room.price" bundle="${rs}" /></th>
                    <th><fmt:message key="room.sits" bundle="${rs}" /></th>
                    <th></th>
                </tr>
<tr>

<td><c:out value="${ room.number }"  /></td>
<td><c:out value="${ room.comfort }" /></td>
<td><c:out value="${ room.price }" /></td>
<td><c:out value="${ room.sits }" /></td>
<td>
<c:if test="${user.role eq 'administration'}">
           <form  action="changeRoomForm.html" >
					<button id="button" value="${ room.id }" name="roomId" type="submit"><fmt:message key="change.room.button" bundle="${rs}" /></button>
                </form>
				</c:if>
</td>
<td>
<c:choose>
<c:when test="${room.id eq '28'}">
   <img src="images/1.jpg" >
</c:when>
<c:when test="${room.id eq '18'}">
   <img src="images/2.jpg" >
</c:when>
<c:when test="${room.id eq '19'}">
   <img src="images/3.jpg" >
</c:when>
<c:when test="${room.id eq '29'}">
   <img src="images/4.jpg" >
</c:when>
<c:when test="${room.id eq '31'}">
   <img src="images/5.jpg" >
</c:when>
<c:otherwise>
<img src="images/6.jpg" >
</c:otherwise>
</c:choose>
</td>
</tr>
                
            </tbody>



        </table>
    </div>

</u:html>