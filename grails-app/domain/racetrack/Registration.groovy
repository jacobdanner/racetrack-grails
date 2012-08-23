package racetrack

class Registration
{
  Date dateCreated //Note: this is a special name
  Boolean paid

 static belongsTo = [race:Race, runner:Runner]

  static mapping = {
    autoTimestamp false
  }
  static constraints = {
    race()
    runner()
    paid()
    dateCreated()
  }

  def beforeInsert = {
    // your code goes here
  }

  def beforeUpdate = {
    // your code goes here
  }

  def beforeDelete = {
    // your code goes here
  }

  def onLoad = {
    // your code goes here
  }

}
