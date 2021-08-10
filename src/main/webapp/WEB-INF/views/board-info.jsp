<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>게시글보기</title>
        <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" /><div class="ui middle aligned center aligned grid"></div>
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="../css/styles.css" rel="stylesheet" />
        <!--제이쿼리-->
        <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    </head>
    <body id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand js-scroll-trigger" href="#page-top"><img src="../assets/img/navbar-logo.svg" alt="" /></a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"/>
            </div>
        </nav>

        <!-- 시작 -->
        <br><br><br>
        <div class="container">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4" id="title"></h1>
                                <div align="left">
                                    <div style="white-space : pre-wrap;height: 100%" id="author">글쓴이: </div>
                                    <div style="white-space : pre-wrap;height: 100%" id="date">작성일: </div>
                                    <div style="white-space : pre-wrap;height: 100%" id="like_count">좋아요: </div>
                                    <div style="white-space : pre-wrap;height: 100%" id="dislike_count">싫어요: </div>
                                </div>
                            </div>

                            <form class="user" >
                                <br>
                                    <div class="form-group">
                                        <li class="list-group-item">
                                            <div >
                                                <div id="content" style="white-space : pre-wrap;height: 100%"></div>
                                            </div>
                                        </li>
                                    </div>
                            </form>


                            <div class="ui middle aligned center aligned grid">
                                <table class="ui celled table">
                                    <thead>
                                    <tr>
                                        <th width="80"></th>
                                        <th width="250"></th>
                                        <th width="100"></th>
                                        <th width="50"></th>
                                    </tr>
                                    </thead>
                                    <br>
                                    <tbody id="comment">
                                    </tbody>
                                </table>
                            </div>
                            <%--<div class="form-group">
                                <li class="list-group-item">
                                    <div>
                                        <div id="comment" style="white-space : pre-wrap;height: 100%"></div>
                                    </div>
                                </li>
                            </div>--%>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $(document).ready(function() {
                var id = "${id}";
                console.log(id+"번 게시글 불러오기");
                $.ajax({
                    type: "get",
                    url: "/list/"+id,
                    success: function(data) {
                        console.log("넘어온 값: ");
                        console.log(data);
                        let board = data;
                        let board_id = board['board_id'];
                        let title = board['title'];
                        let content = board['content'];
                        let author = board['author'];
                        let date = board['date'];
                        let like_count = board['like_count'];
                        let dislike_count = board['dislike_count'];
                        let updated = board['updated'];
                        $("#title").append(title);
                        $("#author").append(author);
                        $("#date").append(FormatToUnixtime(date));
                        $("#like_count").append(like_count);
                        $("#dislike_count").append(dislike_count);
                        $("#content").append(content);
                    }
                });

                $.ajax({
                    type: "get",
                    url: "/comment/"+id,
                    success: function(data) {
                        console.log("넘어온 값: ");
                        console.log(data);
                        let comments = data;
                        for (let i = 0; i < comments.length; i++) {
                            let member_nickname = comments[i]['member_nickname'];
                            let content = comments[i]['content'];
                            let date = comments[i]['date'];
                            let updated = comments[i]['updated'];
                            console.log(member_nickname);
                            console.log(content);
                            console.log(date);
                            $("#comment").append(
                                "<tr>"+
                                "<td>"+member_nickname+"</td>"+
                                "<td>"+content+"</td>"+
                                "<td>"+FormatToUnixtime(date)+"</td>"+
                                "</tr>"
                            );
                        }
                    }
                });

                function FormatToUnixtime(unixtime) {
                    var u = new Date(unixtime);
                    console.log("u: " + u);
                    return u.getUTCFullYear() +
                        '-' + ('0' + (u.getMonth()+1)).slice(-2) +
                        '-' + ('0' + u.getDate()).slice(-2) +
                        ' ' + ('0' + u.getHours()).slice(-2) +
                        ':' + ('0' + u.getMinutes()).slice(-2)
                };
            });
        </script>

        <!-- Bootstrap core JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Third party plugin JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <!-- Contact form JS-->
        <script src="../assets/mail/jqBootstrapValidation.js"></script>
        <script src="../assets/mail/contact_me.js"></script>
        <!-- Core theme JS-->
        <script src="../js/scripts.js"></script>
    </body>
</html>
