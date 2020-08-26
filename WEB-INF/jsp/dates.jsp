
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>

<fmt:setBundle basename = "ResourceBundle.Global" var="rs" scope="application"/>

<u:html>

    <div id="body">
	<u:choseroom/>
        <div id="inputtext">
            <form method="POST" action="confirmOrder.html">
                        <p><input id="loginButton" type="submit" value="<fmt:message key="make.order.button" bundle="${rs}" />"></p>
            </form>
           <p><fmt:message key="message.order.cost" bundle="${rs}" /><c:out value="${ price }" /></p>
        </div>
    </div>


</u:html>