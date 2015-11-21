<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <p>${mensagem}</p>
        <form action="login" method="POST">
            <input type="hidden" name="action" value="doLogin" />
            Username: <input type="text" name="username" value="${login}" /><br/>
            Password: <input type="password" name="password" /><br/>
            <input type="submit" value="Login" />
        </form>
    </body>
</html>
