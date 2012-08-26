<%@ page import="racetrack.User" %>
<!doctype html>
<html>
<head>
  <meta name="layout" content="main">
  <title>Login</title>
</head>

<body>
<div class="body">
  <h1>Login</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:form action="authenticate" method="post">
    <div class="dialog">
      <table>
        <tbody>
        <tr class="prop">
          <td valign="top" class="name">
            <label for="login"><g:message code="user.login.label" default="Login" /></label>
          </td>
          <td valign="top">
            <input type="text" id="login" name="login"/>
          </td>
        </tr>
        <tr class="prop">
          <td valign="top" class="name">
            <label for="password"><g:message code="user.password.label" default="Password" /></label>
          </td>
          <td valign="top">
            <input type="password"
                   id="password" name="password"/>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <div class="buttons">
      <span class="btn-group">
        <input type="submit" class="btn btn-small" value="Login"/>
      </span>
    </div>
  </g:form>
</div>

</body>
</html>
