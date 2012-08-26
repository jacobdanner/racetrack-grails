package racetrack

class User
{
  String login
  String password
  String role = "user"

  static constraints = {
    login(blank: false, nullable: false, unique: true)
    password(blank: false, password: true)
    role(inList: ["admin", "user"])
  }

  String toString()
  {
    login
  }

  def beforeInsert = {
    // hardly secure, but
    // this is just an example for now
    password.encodeAsSHA()

  }
  // should not be persisted back to the DB
  static transients = ['admin']

  boolean isAdmin(){
    return role == "admin"
  }

}
