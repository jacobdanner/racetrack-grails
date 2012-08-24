package racetrack

class AdminTagLib {

  def isAdmin = { body ->
    if(session?.user?.admin)
      out << body()
  }
}
