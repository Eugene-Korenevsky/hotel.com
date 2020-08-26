<%@ tag language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="choseroom">
            <p>
                <form method="POST" action="search.html">
                   <fmt:message key="date.arrive" bundle="${rs}" />
                    <input id="input" type="date" name="dayIn">
					<fmt:message key="timeIn" bundle="${rs}" />
					<input type="time" name="timeIn">
					<fmt:message key="date.leave" bundle="${rs}" />
                    <input id="input" type="date" name="dayOut">
					<fmt:message key="timeOut" bundle="${rs}" />
					<input type="time" name="timeOut">
					<fmt:message key="room.sits" bundle="${rs}" />
                    <select name="sits" size="1">
                           <option value="1">1</option> 
                           <option value="2">2</option>
                           <option value="3">3</option>
                           <option value="4">4</option> 
                        </select>
						<fmt:message key="price.to" bundle="${rs}" />
						<input id="input" type="text" name="price" </p>
                    <input id="button" type="submit" value="<fmt:message key="search.button" bundle="${rs}" />">
                </form>
                <form  action="loginForm.html">
                    <button id="button" type="submit"><fmt:message key="login.button" bundle="${rs}" /></button>
                </form>
				<form method="GET" action="cabinet.html">
            <button id="button" type="submit"><fmt:message key="cabinet.button" bundle="${rs}" /></button>
        </form>
		<form method="GET" action="index.html">
            <button id="button" type="submit"><fmt:message key="main.page" bundle="${rs}" /></button>
        </form>

</div>