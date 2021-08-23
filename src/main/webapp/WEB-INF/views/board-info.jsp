<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        <script>
            function like_button() {
                $.ajax({
                    type: "POST",
                    url: "/like/"+board_id,
                    success: function (response) {
                        window.location.reload();
                    }
                })
            }

            function dislike_button() {
                $.ajax({
                    type: "POST",
                    url: "/dislike/"+board_id,
                    success: function (response) {
                        window.location.reload();
                    }
                })
            }

            function update_comment(comment_id) {
                $.ajax({
                    type: "PUT",
                    url: "/comment/"+comment_id,
                    success: function (response) {
                        window.location.reload();
                    }
                })
            }

            function delete_comment(comment_id) {
                $.ajax({
                    type: "DELETE",
                    url: "/comment/"+comment_id,
                    success: function (response) {
                        window.location.reload();
                    }
                })
            }

            function update_board(board_id) {
                location.href = "/update/"+board_id;
            }

            function delete_board(board_id) {
                $.ajax({
                    type: "DELETE",
                    url: "/list/"+board_id,
                    success: function (response) {
                        location.href = "/";
                    }
                })
            }
        </script>
    </head>
    <body id="page-top">

        <!-- 시작 -->
        <br><br><br>
        <div class="container">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4" id="title"></h1>
                                <div id="isupdated"></div>
                                <div align="left">
                                    <div style="white-space : pre-wrap;height: 100%" id="author">글쓴이: </div>
                                    <div style="white-space : pre-wrap;height: 100%" id="date">작성일: </div>
                                    <div style="white-space : pre-wrap;height: 100%" id="like_count">좋아요: </div>
                                    <div style="white-space : pre-wrap;height: 100%" id="dislike_count">싫어요: </div>
                                </div>
                            </div>
                            <button type="button" onclick="like_button();" class="btn btn-primary">좋아요</button>
                            <button type="button" onclick="dislike_button()" class="btn btn-secondary">싫어요</button>
                            <div>
                                <div style="margin-top: 10px" id="update_button"/>
                            </div>
                            <form class="user" >
                                <br>
                                    <div class="form-group" >
                                        <li class="list-group-item">
                                            <div >
                                                <div id="content" style="white-space : pre-wrap; margin-bottom: 150px"></div>
                                            </div>
                                        </li>
                                    </div>
                            </form>

                            <div class="ui middle aligned center aligned grid">
                                <table class="ui celled table">
                                    <thead>
                                    <tr>
                                        <th width="80"></th>
                                        <th width="150"></th>
                                        <th width="100"></th>
                                        <th width="30"></th>
                                        <th width="30"></th>
                                    </tr>
                                    </thead>
                                    <br>
                                    <hr>
                                    <form class="user">
                                        <div class="form-group">
                                            <textarea class="form-control" style="resize: vertical;" id="comment_input" placeholder="댓글을 입력하세요" rows="3"></textarea>
                                            <p class="help-block text-danger"></p>
                                        </div>
                                        <div class="btn btn-primary btn-user btn-block" id = "submitBoard">댓글달기</div>
                                    </form>
                                    <tbody id="comment">
                                    </tbody>
                                </table>
                            </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            let current_user_id = <sec:authentication property="principal.memberVo.member_id"/>;
            let board_id = "${id}";
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
                        let author_nickname = board['author_nickname'];
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
                        if(updated){
                            $("#isupdated").append("(수정됨)");
                        }
                        console.log("if문 시작");
                        console.log(author);
                        if(author == author_nickname){
                            console.log("if문 들어옴");
                            $("#update_button").append(
                                "<tr>"+
                                "<td><button type='button' onclick="+"update_board("+board_id+"); class='btn btn-info'>"+'수정하기'+"</button></td>"+
                                "<td><button type='button' onclick="+"delete_board("+board_id+"); class='btn btn-danger'>"+'삭제하기'+"</button></td>"+
                                "</tr>"
                            );
                        }
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
                            let comment_id = comments[i]['comment_id'];
                            let member_nickname = comments[i]['member_nickname'];
                            let member_id = comments[i]['member_id'];
                            let content = comments[i]['content'];
                            let date = comments[i]['date'];
                            let updated = comments[i]['updated'];
                            console.log(member_id);
                            if(member_id == current_user_id){
                                $("#comment").append(
                                    "<tr>"+
                                    "<td>"+member_nickname+"</td>"+
                                    "<td>"+content+"</td>"+
                                    "<td>"+FormatToUnixtime(date)+"</td>"+
                                    "<td><button type='button' onclick="+"update_comment("+comment_id+"); class='btn btn-info'>"+'수정하기'+"</button></td>"+
                                    "<td><button type='button' onclick="+"delete_comment("+comment_id+"); class='btn btn-danger'>"+'삭제하기'+"</button></td>"+
                                    "</tr>"
                                );
                            }
                            else {
                                $("#comment").append(
                                    "<tr>" +
                                    "<td>" + member_nickname + "</td>" +
                                    "<td>" + content + "</td>" +
                                    "<td>" + FormatToUnixtime(date) + "</td>" +
                                    "</tr>"
                                );
                            }
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
        <script>
            let current_board_id = "${id}";
            $(document).ready(function(){
                $("#submitBoard").click(function(){
                    var json = {
                        board_id : current_board_id,
                        content : $("#comment_input").val()
                    };

                    for(var str in json){
                        if(json[str].length == 0){
                            alert($("#" + str).attr("placeholder") + "를 입력해주세요.");
                            $("#" + str).focus();
                            return;
                        }
                    }

                    $.ajax({
                        type : "POST",
                        url : "/comment",
                        data : JSON.stringify(json),
                        contentType: 'application/json',
                        success : function(data) {
                            console.log(data);
                            //alert('등록되었습니다.');
                            window.location.reload();
                        },
                        error: function (error) {
                            alert("오류 발생" + error);
                        }
                    });
                });
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
