<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String message = (String) request.getAttribute("message");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<!-- pue ahí anda el mensaje, si quieres que tenga una animación tipo easy-in es a tu gusto andre -->

    <c:choose>
        <c:when test="${message != null}">
            <div class="messageAlert position-fixed bottom-0 end-0 p-3" style="background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; border-radius: 5px; z-index: 1001;">
                ${message}
            </div>
        </c:when>
        <c:when test="${errorMessage != null}">
            <div class="messageAlert position-fixed bottom-0 end-0 p-3" style="background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; border-radius: 5px; z-index: 1001;">
                ${errorMessage}
            </div>
        </c:when>
    </c:choose>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        var messageDiv = document.querySelector('.messageAlert');
        if (messageDiv) {
            setTimeout(function() {
                messageDiv.style.display = 'none';
            }, 7500);
        }
    });
</script>
