<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Web and MySQL 1915247</title>
    <style>
      .header{
        text-align: center;
        padding: 10px 20px;
        height: 80px;
      }
      .header a{
        color: black;
      }

      ul{
        list-style-type: none;
      }

      a{
        text-decoration: none;
      }

      /* navigate css */
      #nav{
        background: #5EE177;
        height: 50px;
      }

      #nav ul{
        width: auto;
        margin: 0 auto;
      }

      #nav .menu >li{
        float: left;
        width: 32%;
        height: 30px;
        text-align: center;
        background: #5EE177;
        position: relative;
        padding: 10px 0px;
          color:#fff;
      }

      /* menu:hover */
      #nav .menu > li:hover .sub{
        display: block;
      }

      #nav .menu >li a{
        color:#fff;
        display: block;

      }

      #nav > .menu >li:hover{
        background: #1E9E36;
      }

      #nav .menu .sub{
        position: absolute;
        top:50px;
        left:0;
        width: 100%;
        display: none;
      }

      #nav .menu .sub li{
        background: #62CB76;
        color:#fff;
      }

      #nav .menu .sub li a:hover{
        background: #1E9E36;
      }

      .content{
        height: 500px;
        border: 1px solid black;
      }


    </style>
  </head>
  <body>
    <div class="header">
      <h1><a href="index.php">Eat Healty, Live Lively</a></h1>
    </div>
    <div id="nav">
      <ul class="menu">
        <li>
          회원관리
          <ul class="sub">
            <li><a href="allmem.php">전체 회원 보기</a></li>
            <li><a href="searchmem.php">회원 검색</a></li>
            <li><a href="addmem.php">회원 추가</a></li>
          </ul>
        </li>

        <li>
          식단관리
          <ul class="sub">
            <li><a href="manae.php">회원 식단 조회</a></li>
            <li><a href="allfood.php">전체 영양정보 보기</a></li>
          </ul>
        </li>

        <li>
          직원관리
          <ul class="sub">
            <li><a href="allemp.php">전체 직원 보기</a></li>
            <li><a href="#">직원 정보 편집</a></li>
          </ul>
        </li>

      </ul>
    </div>
    <div class="content" align="center">
      <h2>회원 식단 관리</h2>
      <form class="form" action="process_search_diet.php" method="post">
        <p><input type="text" name="Mname" placeholder="이름"></p>
        <p><input type="text" name="phone" placeholder="연락처"></p>
        <p><input type="submit" value="검색"></p>
      </form>

    </div>
  </body>
</html>
