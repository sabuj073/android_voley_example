<?php
$con = mysqli_connect("localhost","root","","demo");
	mysqli_set_charset($con,"utf8")
$name = $_POST['u_name'];
$number = $_POST['phoneno'];

mysqli_query($con,"INSERT into save values('{$name}','{$number}')");

 ?>