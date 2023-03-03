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
        height: auto;
        border: 1px solid black;
      }

      .footer{
        text-align: center;
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
          <a href="member.php">회원관리</a>
          <ul class="sub">
            <li><a href="allmem.php">전체 회원 보기</a></li>
            <li><a href="searchmem.php">회원 검색</a></li>
            <li><a href="addmem.php">회원 추가</a></li>
          </ul>
        </li>

        <li>
          <a href="diet.php">식단관리</a>
          <ul class="sub">
            <li><a href="#">회원 식단 관리</a></li>
            <li><a href="allfood.php">전체 영양정보 보기</a></li>
          </ul>
        </li>

        <li>
          <a href="employee.php">직원관리</a>
          <ul class="sub">
            <li><a href="#">전체 직원 보기</a></li>
            <li><a href="#">직원 정보 편집</a></li>
          </ul>
        </li>

      </ul>
    </div>

    <div class="content">
      <p></p>
      <table border="1" align="center">
        <th>고객명</th>
        <th>성별</th>
        <th>나이</th>
        <th>연락처</th>
        <th>관리타입</th>
        <th>담당자</th>
        <th></th>
        <th></th>
        <tr>
          <?php
          $conn=mysqli_connect("localhost","root","1915247",
          "diet-manage");
          $sql="SELECT * FROM member";
          $result=mysqli_query($conn,$sql);
          while($row=mysqli_fetch_array($result)){
            ?>
            <tr>
            <td><?=$row['Mname']?></td>
            <td><?=$row['sex']?></td>
            <td><?=$row['age']?></td>
            <td><?=$row['phone']?></td>
            <td><?=$row['type']?></td>
            <td><?=$row['manager']?></td>
            <td><a href="updatemem.php?Mname=<?=$row['Mname']?>">수정</a></td>
            <td>
              <form action="process_delete_mem.php" method="post"
              onsubmit="if(!confirm('삭제하시겠습니까?')){return false;}">
                <input type="hidden" name="Mname" value="<?=$row['Mname']?>">
                <input type="submit" value="삭제">
              </form>
            </td>
          </tr>
            <?php
          }
           ?>
         </tr>
      </table>
      <p></p>
    </div>
    <div class="footer">
      <a href="index.php">홈으로 돌아가기</a>
    </div>
  </body>
</html>
