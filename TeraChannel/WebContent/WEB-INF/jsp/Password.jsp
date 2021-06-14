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


		<form method="POST" action="/TeraChannel/LoginServlet">
			<table  align="center">
			    <tr>
				<th>前のパスワード</th>
				<td><input type="password" name="password" class="form-control" ></td>
				</tr>
				<tr>
				<th>新しいパスワード</th>
				<td><input type="newpass" name="new_pass" class="form-control"></td>
				</tr>
				<th>新しいパスワードの確認</th>
				<td><input type="confirm" name="confirm" class="form-control"></td>
				</tr>
			</table>
			<p>
				<input type="submit" name="change" value="変更">
			</p>
		</form>
     </div>

     <script>
'use strict';
/*
 パスワード変更
 Node.js express
 */

const Router = require('express');

module.export = connection => {
  const router = Router();

  router.get('/', (req, res, next) => {
    res.render('password_change/index', {
      name: req.session.user.name,
      type: req.session.user.type
    });
  });

  router.post('/', (req, res, next) => {
    const password = req.body.password;
    const new_pass = req.body.new_pass;
    const confirm = req.body.confirm;


    // passwordセッション
    if (password !== req.session.password) {
      res.render('pass_change', {
        title: 'パスワード変更',
        pass: 'パスワードが間違っています'
      });
      return;
    }
    if (new_pass !== confirm) {
      res.render('pass_change', {
        title: 'パスワード変更',
        pass: '新しいパスワードが新旧で異なります'
      });
      return;
    }
    // エラー差し込み箇所

    // DBの書き換え
    const query = 'UPDATE user SET password = ? WHERE id = ?';
    connection.query(query, [pass_change, req.session.user.id], err => {
      if (err) {
        res.render('pass_change', {
          title: 'パスワード変更',
          pass: 'エラーが発生しましたので管理者にお問い合わせください'
        });
        return;
      }
      res.render('pass_change', {
        title: 'パスワード変更',
        pass: 'パスワードの変更が完了しました'
      });
    });
  });

  return router;
}


</script>

</body>
</html>