package racetrack

class LoginTagLib {

  def loggedIn() {
    return request.getSession(false) && session.user
  }

  def isAdmin = {
    return loggedIn() && session.user?.isAdmin()
  }

  def loginControl = {
    // TODO: let's get some nicer formatting
    if(loggedIn() == true)
    {
      out << "Hello ${session.user} "
      out << """[${link(action:"logout",controller:"user"){"Logout"}}]"""
    } else {
      out << """[${link(action: "login",controller: "user"){"Login"}}]"""
    }
  }

  def loginFormHeader = {
    // basic twitter bootstrap navbar header form
    """<form method="post" class=\"navbar-form pull-right\">\n" +
    "   <input class=\"span2\" type=\"text\" placeholder=\"Email\">\n"
    "   <input class=\"span2\" type=\"password\" placeholder=\"Password\">\n" +
    "  <button type=\"submit\" class=\"btn\">Sign in</button>\n" +
    "</form>"""
  }



}
