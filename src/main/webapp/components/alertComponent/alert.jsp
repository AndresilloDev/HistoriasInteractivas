<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String message;
    String errorMessage;
    session = request.getSession();
    if ((String) session.getAttribute("message") == null) {
        message = (String) request.getAttribute("message");
    } else {
        message = (String) session.getAttribute("message");
    }
    if ((String) session.getAttribute("errorMessage") == null) {
        errorMessage = (String) request.getAttribute("errorMessage");
    } else {
        errorMessage = (String) session.getAttribute("errorMessage");
    }
%>
<head>
    <link rel="stylesheet" type="text/css" href="components/alertComponent/alert.css">
</head>
<c:choose>
    <c:when test="${message != null}">
        <div class="messageAlert">
            <div>${message}</div>
            <div class="progressBar">
                <div class="progress"></div>
            </div>
        </div>
    </c:when>
    <c:when test="${errorMessage != null}">
        <div class="messageAlert">
            <div>${errorMessage}</div>
            <div class="progressBar">
                <div class="progress"></div>
            </div>
        </div>
    </c:when>
</c:choose>
<%
    session.removeAttribute("message");
    session.removeAttribute("errorMessage");
%>
<script src="components/alertComponent/alert.js"></script>