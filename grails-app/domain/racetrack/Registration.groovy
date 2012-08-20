package racetrack

class Registration
{

  String name
  Date dateOfBirth
  String gender
  String address
  String city
  String state
  String zipcode
  String email
  Date dateCreated //Note: this is a special name
  Date lastUpdated


  static mapping = {
    autoTimestamp false
  }
  static constraints = {
    name(blank: false)
    email(email: true, unique: true)
    gender(inList: ["M","F"], nullable: false)

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
