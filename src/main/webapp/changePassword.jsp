<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="changePassword" method="POST">
    <input type="hidden" name="token" value="${token}">
    <input type="hidden" name="email" value="${email}">
    <input type="password" name="newPassword" placeholder="Nueva contraseña" required>
    <button type="submit">Cambiar</button>
</form>
</body>
</html>
