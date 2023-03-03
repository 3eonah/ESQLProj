<?php

$conn=mysqli_connect("localhost","root","1915247",
"diet-manage");

$prev_name=mysqli_real_escape_string($conn,$_POST['prev_name']);

$filtered=array(
  'Ename'=>mysqli_real_escape_string($conn,$_POST['Ename']),
  'dept'=>mysqli_real_escape_string($conn,$_POST['dept']),
  'rank'=>mysqli_real_escape_string($conn,$_POST['rank']),
  'phone'=>mysqli_real_escape_string($conn,$_POST['phone'])
);

$sql="
  UPDATE employee SET
  Ename='{$filtered['Ename']}',
  dept='{$filtered['dept']}',
  rank='{$filtered['rank']}',
  phone='{$filtered['phone']}' WHERE Ename='{$prev_name}'
";

$result=mysqli_query($conn,$sql);
if($result==false){
  echo '데이터 수정 실패. <a href="index.php">홈으로 돌아가기</a>';
}else{
  echo '데이터 수정 성공. <a href="index.php">홈으로 돌아가기</a>';
}
 ?>
