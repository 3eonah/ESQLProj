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
            <li><a href="manage.php">회원 식단 조회</a></li>
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
      <table border="1" align="center">
        <th>고객명</th>
        <th>시간</th>
        <th>음식</th>
        <th>수량</th>
        <th>열량</th>
        <th>탄수화물</th>
        <th>당</th>
        <th>단백질</th>
        <th>지방</th>
        <tr>
          <?php
          $conn=mysqli_connect("localhost","root","1915247",
          "diet-manage");

          $sql="SELECT m.Mname, d.time, d.qty, N.*
          FROM member m
          JOIN member_diet d ON m.Mname=d.Mname
          JOIN nutrition_info N ON d.foodname=N.foodname
          WHERE m.Mname='{$_POST['Mname']}'
          ";
          $result=mysqli_query($conn,$sql);
          while($row=mysqli_fetch_array($result)){
            ?>
            <tr>
            <td><?=$row['Mname']?></td>
            <td><?=$row['time']?></td>
            <td><?=$row['foodname']?></td>
            <td><?=$row['qty']?></td>
            <td><?=$row['kcal']?></td>
            <td><?=$row['carb']?></td>
            <td><?=$row['sugar']?></td>
            <td><?=$row['protein']?></td>
            <td><?=$row['fat']?></td>
            </tr>
            <?php
          }
           ?>
         </tr>
      </table>
    </div>
  </body>
</html>
