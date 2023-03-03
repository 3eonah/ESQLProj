<?php

$conn=mysqli_connect("localhost","root","1915247",
"diet-manage");

$prev_name=mysqli_real_escape_string($conn,$_POST['prev_name']);

$filtered=array(
  'Mname'=>mysqli_real_escape_string($conn,$_POST['Mname']),
  'sex'=>mysqli_real_escape_string($conn,$_POST['sex']),
  'age'=>mysqli_real_escape_string($conn,$_POST['age']),
  'phone'=>mysqli_real_escape_string($conn,$_POST['phone']),
  'type'=>mysqli_real_escape_string($conn,$_POST['type']),
  'manager'=>mysqli_real_escape_string($conn,$_POST['manager'])
);

$sql="
  UPDATE member SET
  Mname='{$filtered['Mname']}',
  sex='{$filtered['sex']}',
  age={$filtered['age']},
  phone='{$filtered['phone']}',
  type='{$filtered['type']}',
  manager='{$filtered['manager']}'
  WHERE Mname='{$prev_name}';
";

$result=mysqli_query($conn,$sql);
if($result==false){
  echo '데이터 수정 실패. <a href="index.php">홈으로 돌아가기</a>';
}else{
  echo '데이터 수정 성공. <a href="index.php">홈으로 돌아가기</a>';
}
 ?>
