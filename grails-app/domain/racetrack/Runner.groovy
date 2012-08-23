package racetrack

class Runner
{

  static constraints = {
    firstName(blank: false)
    lastName(blank: false)
    dateOfBirth()
    gender(inList: ["M", "F"], nullable: false)
    address()
    city()
    state()
    zipcode()
    email(email: true, unique: true)
  }

  static hasMany = [registration: Registration]

  String firstName
  String lastName
  Date dateOfBirth
  String gender
  String address
  String city
  String state
  String zipcode
  String email

  String toString()
  {
    "${lastName}, ${firstName} (${email})"
  }
}
