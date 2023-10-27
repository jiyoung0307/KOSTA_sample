<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="URF-8">
    <title></title>
</head>
<body>
<h2>Hello World!</h2>
<hr>
현재 날짜와 시간은 <%= java.time.LocalDateTime.now() %> 입니다.
<hr>
메시지 : ${msg}
</body>
</html>