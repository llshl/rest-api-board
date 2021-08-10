<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet" />
    <meta charset="UTF-8">
    <title>로그인 페이지2</title>
</head>
<body>
<div id="login-form">
    <div id="login-title" align="center">KPU inside</div>
    <!--여기에 카카오 api의 REST API키를 넣어줌, client_id=[여기에넣음]-->
    <button id="login-kakao-btn" onclick="location.href='https://kauth.kakao.com/oauth/authorize?client_id=adaa819f986ba4a7936765612d3a3caf&redirect_uri=http://localhost:8080/user/kakao/callback&response_type=code'">
        카카오로 로그인하기
    </button>
    <button id="login-id-btn" onclick="location.href='/user/signup'">
        회원 가입하기
    </button>
    <c:if test = "${loginError == true}">
        <div class="alert alert-danger" role="alert">로그인에 실패하였습니다.</div>
    </c:if>
    <form action="/user/login" method="post">
        <div class="login-id-label">아이디</div>
        <input type="text" name="username" class="login-input-box">

        <div class="login-id-label">비밀번호</div>
        <input type="password" name="password" class="login-input-box">

        <button id="login-id-submit">로그인</button>
    </form>
</div>
</body>
</html>