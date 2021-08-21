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
        <title>자유게시판</title>
        <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" /><div class="ui middle aligned center aligned grid"></div>
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="/css/styles.css" rel="stylesheet" />
        <!--제이쿼리-->
        <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
        <style>
            a{
                color: #000000;
            }
        </style>
    </head>
    <body id="page-top">
    <div align="center">
        <h1>어서오세요
        <sec:authentication property="principal.memberVo.nickname"/>
            님</h1>
    </div>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand js-scroll-trigger" href="/"><img src="assets/img/FabinetHome2.png" alt="" /></a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"/>
                    Menu
                    <i class="fas fa-bars ml-1"></i>
                </button>
            </div>
        </nav><%--
        <!-- Masthead-->
        <header class="masthead">
            <div class="container">
                <!-- <div class="masthead-subheading">Fabinet 게시판</div> -->
                <div class="masthead-heading text-uppercase">Fabinet 게시판</div>
            </div>
        </header>--%>
        <!-- Services-->
        <div class="ui middle aligned center aligned grid">
            <div class="column">
                <!-- <h1 class="section-heading text-uppercase" align="center">게시판</h1> -->
                <br>
                <div class="ui middle aligned center aligned grid">
                    <div class="ui middle aligned center aligned grid">
                    </div>
                    <div class="ui middle aligned center aligned grid">
                        <table class="ui celled table">
                            <thead>
                                <tr>
                                    <%--<th width="120">번호</th>--%>
                                    <th width="150">제목</th>
                                    <th width="50">작성자</th>
                                    <th width="50">등록일</th>
                                </tr>
                            </thead>
                            <br>
                            <tbody id="list">
                            </tbody>
                        </table>
                    </div>
                    <div class="ui middle aligned center aligned grid" style="text-align: right">
                        <a href="/board/createBoard"><button class="btn btn-primary btn-xl text-uppercase js-scroll-trigger">게시글 작성하기</button></a>
                    </div>
                    <br>
                    <div class="ui middle aligned center aligned grid" style="text-align: right">
                        <a href="/user/logout"><button class="btn btn-primary btn-xl text-uppercase js-scroll-trigger">로그아웃</button></a>
                    </div>
                    <div class="ui error message"></div>
    
                </div>
            </div>
        </div>

    <nav><%--이거 처리하자--%>
        <ul class="pagination">
            <li>
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li>
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>

        
        <!-- Footer-->
<%--        <footer class="footer py-4">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-4 text-lg-left">Copyright © KPU Fabinet 2021</div>
                    <div class="col-lg-4 my-3 my-lg-0">
                        <a class="btn btn-dark btn-social mx-2" href="#!"><i class="fab fa-twitter"></i></a>
                        <a class="btn btn-dark btn-social mx-2" href="#!"><i class="fab fa-facebook-f"></i></a>
                        <a class="btn btn-dark btn-social mx-2" href="#!"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <div class="col-lg-4 text-lg-right">
                        <a class="mr-3" href="#!">Privacy Policy</a>
                        <a href="#!">Terms of Use</a>
                    </div>
                </div>
            </div>
        </footer>--%>

        <script>
            $(document).ready(function() {
                $.ajax({
                    type: "get",
                    url: "/list",
                    success: function(data) {
                        console.log("넘어온 값: ");
                        console.log(data);
                        let boards = data['boardVos'];
                        //for(var ele in data['boardVos']){
                        for (let i = 0; i < boards.length; i++) {
                            let board_id = boards[i]['board_id'];
                            let title = boards[i]['title'];
                            let author = boards[i]['author'];
                            let date = boards[i]['date'];
                            $("#list").append(
                                "<tr>"+
                                // "<td>"+board_id+"</td>"+
                                "<td><a href='/board/"+board_id+"'>"+ title+"</a></td>"+
                                "<td>"+author+"</td>"+
                                "<td>"+FormatToUnixtime(date)+"</td>"+
                                "</tr>"
                            );
                        }
                    }
                });

                $(document).on("click", ".view_btn", function() {
                    var b_no = $(this).parent().attr("data-id");    //이거 글번호 읽기가 안된다 어케하지
                    console.log(b_no);
                    alert(b_no);
                    $.ajax({
                        type: "get",
                        url: "/board?id="+b_no,
                        contentType : "application/json; charset=UTF-8",
                        dataType : "json",
                        data: {
                            b_no: b_no
                        },
                        success: function(data) {
                            console.log(data);
                            $("#b_title").text(data['b_title']);
                            $("#b_review").text(data['b_ownernick'] + " - " +  FormatToUnixtime(data['b_regdate']));
                            $("#b_content").text(data['b_content']);
                            $('#view_modal').modal('show');
                        },
                        error: function(error) {
                            //alert("게시글 읽기 페이지 아직 안만듬" + error);
                        }
                    });
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
        <script src="assets/mail/jqBootstrapValidation.js"></script>
        <script src="assets/mail/contact_me.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
