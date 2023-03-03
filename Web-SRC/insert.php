<?php
//MysQl 서버에 접속
$conn=mysqli_connect('localhost','root','1915247','diet-manage');
//sql query 
$sql="
  INSERT INTO nutrition_info
  VALUES('고구마',186,38.2,10,3.6,4)
";
mysqli_query($conn,$sql);
 ?>
