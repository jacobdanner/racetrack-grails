package racetrack

class AdminTagLib {

  def isSessionUserAdmin = {params, body ->
    if(session?.user?.admin)
      out << body()
  }
}
