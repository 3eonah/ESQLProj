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

      .content_child1{
        float: left;
        width: 50%
      }

      .content_child2{

        float:right;
        width: 50%;
        display:flex;
      }

      .form{
        margin: auto;
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
            <li><a href="allemp.php">전체 직원 보기</a></li>
            <li><a href="#">직원 정보 편집</a></li>
          </ul>
        </li>

      </ul>
    </div>

      <p></p>
      <div class="content_child1">
      <table border="1" align="center" >
        <th>사원명</th>
        <th>부서</th>
        <th>직급</th>
        <th>연락처</th>
        <th></th>
        <tr>
          <?php
          $conn=mysqli_connect("localhost","root","1915247",
          "diet-manage");
          $sql="SELECT * FROM employee";
          $result=mysqli_query($conn,$sql);
          while($row=mysqli_fetch_array($result)){
            ?>
            <tr>
              <td><?=$row['Ename']?></td>
              <td><?=$row['dept']?></td>
              <td><?=$row['rank']?></td>
              <td><?=$row['phone']?></td>
              <td><a href="updateemp.php?Ename=<?=$row['Ename']?>">수정</a></td>
          </tr>
            <?php
          }
           ?>
         </tr>
      </table>
      </div>
      <?php
      $escaped=array('Ename'=>'','dept'=>'','rank'=>'','phone'=>'');
    $label_submit='submit';
    $form_action='process_add.php';
    $prev_name='';
      if(isset($_GET['Ename'])){
        $name=mysqli_real_escape_string($conn,$_GET['Ename']);
        $sql="SELECT * FROM employee WHERE Ename='{$name}'";
        $result=mysqli_query($conn,$sql);
        $row=mysqli_fetch_array($result);
        $escaped['Ename']=htmlspecialchars($row['Ename']);
        $escaped['dept']=htmlspecialchars($row['dept']);
        $escaped['rank']=htmlspecialchars($row['rank']);
        $escaped['phone']=htmlspecialchars($row['phone']);
        $label_submit='update';
        $form_action='process_update_emp.php';
        $prev_name='<input type="hidden" name="prev_name" value="'.$_GET['Ename'].'">';
      }

      ?>
      <div class="content_child2">
      <form class="form" action="<?=$form_action?>" method="post">
        <?=$prev_name?>
        <p><input type="text" name="Ename" placeholder="이름"
          value="<?=$escaped['Ename']?>"></p>
        <p><input type="text" name="dept" placeholder="부서"
          value="<?=$escaped['dept']?>"></p>
        <p><input type="text" name="rank" placeholder="직급"
          value="<?=$escaped['rank']?>"></p>
        <p><input type="text" name="phone" placeholder="연락처"
          value="<?=$escaped['phone']?>"></p>
        <p><input type="submit" value="<?=$label_submit?>"></p>
      </form>
      </div>

  </body>
</html>
