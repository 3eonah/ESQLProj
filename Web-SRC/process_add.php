<?php
$conn=mysqli_connect('localhost','root','1915247','diet-manage');
$sql="
  INSERT INTO member
  VALUES(
    '{$_POST['Mname']}',
    '{$_POST['sex']}',{$_POST['age']},'{$_POST['phone']}',
    '{$_POST['type']}','{$_POST['manager']}'
    )
";
$result=mysqli_query($conn,$sql);
if($result==false){
  echo '데이터 추가 실패.';
}else{
  echo '데이터 추가 완료.
  <a href="index.php">홈으로 돌아가기</a>';
}

 ?>
