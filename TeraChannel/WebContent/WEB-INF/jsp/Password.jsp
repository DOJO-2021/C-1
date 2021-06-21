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
				<th>メールアドレス</th>
				<td><input type="text" id="user_mail" name="user_mail" class="form-control" ></td>
				</tr>
				<tr>
				<th>新しいパスワード</th>
				<td><input type="text"id="new_pw" name="new_pw" class="form-control"></td>
				</tr>
				<th>新しいパスワードの確認</th>
				<td><input type="text"id="confirm"  name="confirm"  class="form-control"></td>
				</tr>
			</table>
			<p>
				<input type="submit" name="change" value="変更">
			</p>
		</form>
		<c:out value="${errorMessage}" />
     </div>

<script type="text/javascript">
'use strict';

    function check(){

	    //メールアドレスに値が入っているか？
	  if(document.getElementById('user_mail').value == ""){
		    alert("メールアドレスを入力してください");
			return false;
		}

	  //新しいパスワードは両方一致しているか？
	   const new_pw = document.getElementById('new_pw').value;
	   const confirm = document.getElementById('confirm').value;
	   if(new_pw !== confirm) {
           alert("パスワードが一致していません");
           return false;
        }
	   //新しいパスワードに値が入っているか？
	   if(new_pw == ""){
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