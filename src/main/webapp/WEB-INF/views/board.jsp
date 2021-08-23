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
        <div class="ui middle aligned center aligned grid">
            <div class="column">
                <br>
                <div class="ui middle aligned center aligned grid">
                    <div class="ui middle aligned center aligned grid">
                    </div>
                    <div class="ui middle aligned center aligned grid">
                        <table class="ui celled table">
                            <thead>
                                <tr>
                                    <th width="120">번호</th>
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

                    <div style="display: flex; justify-content: center;">
                            <ul class="pagination" id="pagenation_bar">
                            </ul>
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

        <script>
            function FormatToUnixtime(unixtime) {
                var u = new Date(unixtime);
                console.log("u: " + u);
                return u.getUTCFullYear() +
                    '-' + ('0' + (u.getMonth()+1)).slice(-2) +
                    '-' + ('0' + u.getDate()).slice(-2) +
                    ' ' + ('0' + u.getHours()).slice(-2) +
                    ':' + ('0' + u.getMinutes()).slice(-2)
            };
        </script>
        <script>
            function PageCall(num) {
                $.ajax({
                    type: "get",
                    url: "/list?page=" + num,
                    success: function (data) {
                        console.log("넘어온 값");
                        console.log(data);

                        let count = data['count'];
                        let currentPageNum = data['currentPageNum'];
                        let startPageNum = data['startPageNum'];
                        let endPageNum = data['endPageNum'];
                        let next = data['next'];
                        let prev = data['prev'];
                        let boards = data['boardVos'];

                        $("#list").empty();

                        if (boards.length == 0) {
                            $("#list").append("<td colspan=20 style='padding:30px;'>데이터가 없습니다.</td>");
                        } else {
                            for (let i = 0; i < boards.length; i++) {
                                let board_id = boards[i]['board_id'];
                                let title = boards[i]['title'];
                                let author = boards[i]['author'];
                                let date = boards[i]['date'];
                                $("#list").append(
                                    "<tr>" +
                                    "<td>" + (count - i - ((currentPageNum-1)*10)) + "</td>" +
                                    "<td><a href='/board/" + board_id + "'>" + title + "</a></td>" +
                                    "<td>" + author + "</td>" +
                                    "<td>" + FormatToUnixtime(date) + "</td>" +
                                    "</tr>"
                                );
                            }
                        }
                        console.log("currentPageNum: "+currentPageNum);
                        console.log("startPageNum: "+startPageNum);
                        console.log("endPageNum: "+endPageNum);
                        $("#pagenation_bar").empty();  //페이징에 필요한 객체내부를 비워준다.

                        //첫페이지 돌아가기 버튼
                        if (currentPageNum != 1) {            // 페이지가 1페이지가 아니면
                            $("#pagenation_bar").append("<li><button onclick="+"GoPage("+1+")>맨앞으로</button></li>");        //첫페이지로가는버튼 활성화
                        } else {
                            $("#pagenation_bar").append("<li><button>맨앞으로</button></li>");        //첫페이지로가는버튼 비활성화
                        }

                        //11페이지부터 활성화 버튼
                        if (prev) {            // 앞쪽으로 페이지 넘길 수 있다면
                            $("#pagenation_bar").append("<li><button onclick="+"GoPage("+((currentPageNum-10) - (currentPageNum%10) + 1)+")>앞으로</button></li>");        //첫페이지로가는버튼 활성화
                        } else {
                            $("#pagenation_bar").append("<li><button>앞으로</button></li>");        //첫페이지로가는버튼 비활성화
                        }

                        //페이지 번호 출력
                        for (var i = startPageNum; i <= endPageNum; i++) {        //시작페이지부터 종료페이지까지 반복문
                            if (currentPageNum == i) {                            //현재페이지가 반복중인 페이지와 같다면
                                $("#pagenation_bar").append("<li><a><strong>" + i + "</strong></a></li>");    //버튼 비활성화
                            } else {
                                $("#pagenation_bar").append("<li><a onclick="+"GoPage("+i+")>" + i + "</a></li>"); //버튼 활성화
                            }
                        }

                        if (next) {            // 뒷쪽으로 페이지 넘길 수 있다면
                            $("#pagenation_bar").append("<li><button onclick="+"GoPage("+((currentPageNum+10) - (currentPageNum%10) + 1) +")>뒤로</a></li>");        //첫페이지로가는버튼 활성화
                        } else {
                            $("#pagenation_bar").append("<li><button>뒤로</button></li>");        //첫페이지로가는버튼 비활성화
                        }

                        //맨 뒷페이지로 가는 버튼튼
                       if (currentPageNum < endPageNum) {                //현재페이지가 전체페이지보다 작을때
                            $("#pagenation_bar").append("<li><button onclick="+"GoPage("+(Math.ceil(count/10))+")>맨뒤로</button></li>");    //마지막페이지로 가기 버튼 활성화
                        } else {
                            $("#pagenation_bar").append("<li><button>맨뒤로</button></li>");        //마지막페이지로 가기 버튼 비활성화
                        }
                    }
                });
            }

            function GoPage(num){
                PageCall(num);
            }

        </script>

        <script>
            let num = 1;
            $(document).ready(function() {
                PageCall(1);

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
