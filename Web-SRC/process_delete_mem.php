<?php

$conn=mysqli_connect("localhost","root","1915247",
"diet-manage");

$filtered=array(
  'Mname'=>mysqli_real_escape_string($conn,$_POST['Mname'])
);

$sql="
  DELETE FROM member WHERE
  Mname='{$filtered['Mname']}'

";

$result=mysqli_query($conn,$sql);
if($result==false){
  echo '데이터 삭제 실패. <a href="index.php">홈으로 돌아가기</a>';
}else{
  echo '데이터 삭제 성공. <a href="index.php">홈으로 돌아가기</a>';
}
 ?>
