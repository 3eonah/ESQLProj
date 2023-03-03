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
            <li><a href="#">전체 영양정보 보기</a></li>
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

      <p></p>
      <div class="content_child1">
      <table border="1" align="center" >
        <th>고객명</th>
        <th>성별</th>
        <th>나이</th>
        <th>여락처</th>
        <th>관리타입</th>
        <th>담당자</th>
        <th>정보수정</th>
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
          </tr>
            <?php
          }
           ?>
         </tr>
      </table>
      </div>
      <?php
      $escaped=array('Mname'=>'','sex'=>'','age'=>'','phone'=>'','type'=>'','manager'=>'');
    $label_submit='submit';
    $form_action='process_add.php';
    $prev_name='';
      if(isset($_GET['Mname'])){
        $name=mysqli_real_escape_string($conn,$_GET['Mname']);
        $sql="SELECT * FROM member WHERE Mname='{$name}'";
        $result=mysqli_query($conn,$sql);
        $row=mysqli_fetch_array($result);
        $escaped['Mname']=htmlspecialchars($row['Mname']);
        $escaped['sex']=htmlspecialchars($row['sex']);
        $escaped['age']=htmlspecialchars($row['age']);
        $escaped['phone']=htmlspecialchars($row['phone']);
        $escaped['type']=htmlspecialchars($row['type']);
        $escaped['manager']=htmlspecialchars($row['manager']);
        $label_submit='update';
        $form_action='process_update_mem.php';
        $prev_name='<input type="hidden" name="prev_name" value="'.$_GET['Mname'].'">';
      }

      ?>
      <div class="content_child2">
      <form class="form" action="<?=$form_action?>" method="post">
        <?=$prev_name?>
        <p><input type="text" name="Mname" placeholder="이름"
          value="<?=$escaped['Mname']?>"></p>
        <p><input type="text" name="sex" placeholder="성별"
          value="<?=$escaped['sex']?>"></p>
        <p><input type="text" name="age" placeholder="나이"
          value="<?=$escaped['age']?>"></p>
        <p><input type="text" name="phone" placeholder="연락처"
          value="<?=$escaped['phone']?>"></p>
        <p><input type="text" name="type" placeholder="관리타입"
          value="<?=$escaped['type']?>"></p>
        <p><input type="text" name="manager" placeholder="담당자"
          value="<?=$escaped['manager']?>"></p>
        <p><input type="submit" value="<?=$label_submit?>"></p>
      </form>
      </div>

  </body>
</html>
