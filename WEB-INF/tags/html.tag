<%@ tag language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style>
   #body {
    background: #c7b39b url(images/main.jpg) no-repeat  ; /* Цвет фона и путь к файлу */
    color: #fff; /* Цвет текста */
   }
  </style>
 <title>the best hostel in the word</title>
    <meta charset="UTF-8">
    <link href="css/main.css" type="text/css" rel="stylesheet" />
</head>
<header>
    <div id="head">
        <p id="text">welcome to our website</p>
    </div>
</header>
<body>
<jsp:doBody/>
</body>
<footer>
    <div id="foot">
        <p id="text"></p>
    </div>
</footer>