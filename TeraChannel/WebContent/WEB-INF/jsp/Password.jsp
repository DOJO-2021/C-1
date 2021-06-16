<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>TERAKO Password</title>
<link rel="stylesheet" href="css/Password.css">
</head>


<body>
	<div class=div>
		<img src="image/teraco.jpg">

		<h2>パスワード変更</h2>

		<p>新しいパスワードを入力してください


			<form  method="POST" action="/TeraChannel/PasswordServlet" onsubmit="return check();">
			<table  align="center">
			    <tr>
				<th>前のパスワード</th>
				<td><input type="text" id="password" name="password" class="form-control" ></td>
				</tr>
				<tr>
				<th>新しいパスワード</th>
				<td><input type="text"id="new_pass" name="new_pass" class="form-control"></td>
				</tr>
				<th>新しいパスワードの確認</th>
				<td><input type="text"id="confirm"  name="confirm"  class="form-control"></td>
				</tr>
			</table>
			<p>
				<input type="submit" name="change" value="変更">
			</p>
		</form>
     </div>

<script type="text/javascript">
'use strict';

    function check(){

	    //前のパスワードに値が入っているか？
	  if(document.getElementById('password').value == ""){
		    alert("前のパスワードを入力してください");
			return false;
		}

	  //新しいパスワードは両方一致しているか？
	   const new_pass = document.getElementById('new_pass').value;
	   const confirm = document.getElementById('confirm').value;
	   if(new_pass !== confirm) {
           alert("パスワードが一致していません");
           return false;
        }
	   //新しいパスワードに値が入っているか？
	   if(new_pass == ""){
		    alert("パスワードを入力してください");
			return false;
		}
	   if(confirm == ""){
		    alert("パスワードを入力してください");
			return false;
       }
    }


</script>
</body>
</html>