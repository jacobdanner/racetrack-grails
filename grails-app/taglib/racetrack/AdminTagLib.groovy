package racetrack

class AdminTagLib {

  def isAdmin = {params, body ->
    if(session?.user?.admin)
      out << body()
  }
}
