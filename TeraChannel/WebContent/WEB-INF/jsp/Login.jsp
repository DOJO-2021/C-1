<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TERAKO</title>
<img src="image//teraco.jpg" alt="logo">
</head>
<body>
	<div class="wrapper">
		<div id="app">
			<nav class="navbar navbar-expand-md navbar-light bg-white shadow-sm">
				<div class="container">
					<a href="https://dojo-teraco.seplus.jp" class="navbar-brand"
						style="padding-bottom: 0px;"><img src="/img/teraco-logo.png"
						style="width: 100px;"></a>
					<button type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation" class="navbar-toggler">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div id="navbarSupportedContent" class="collapse navbar-collapse">
						<ul class="navbar-nav ml-auto"></ul>
					</div>
				</div>
			</nav>
			<main class="py-4">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-md-8">
							<div class="card">
								<div class="card-header">ログイン</div>
								<div class="card-body">
									<form method="POST"
										action="https://dojo-teraco.seplus.jp/login">
										<input type="hidden" name="_token"
											value="xd8ZVqL5EuxtFCcl2iJgHCSg3uTcTQ49gCdwbj6B">
										<div class="form-group row">
											<label for="email"
												class="col-md-4 col-form-label text-md-right">メールアドレス</label>
											<div class="col-md-6">
												<input id="email" type="email" name="email" value=""
													required="required" autocomplete="email"
													autofocus="autofocus" class="form-control ">
											</div>
										</div>
										<div class="form-group row">
											<label for="password"
												class="col-md-4 col-form-label text-md-right">パスワード</label>
											<div class="col-md-6">
												<input id="password" type="password" name="password"
													required="required" autocomplete="current-password"
													class="form-control ">
											</div>
										</div>
										<div class="form-group row mb-0">
											<div class="col-md-8 offset-md-4">
												<button type="submit" class="btn btn-primary">ログイン
												</button>
												<a href="https://dojo-teraco.seplus.jp/password/reset"
													class="btn btn-link"> パスワードを忘れた方はこちら </a>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
		<footer>
			<p>
				<img src="/img/dojo-logo.png" style="width: 100px"><br>
				Copyright(C) 2021 SEplus.Co.,Ltd. All rights reserved.<br>
				本サイトの掲載記事、写真、イラスト、問題コンテンツの無断転載を禁じます。記載されているロゴ、システム名、製品名は各社及び商標権者の登録商標あるいは商標です。
			</p>
		</footer>
	</div>


</body>



</html>