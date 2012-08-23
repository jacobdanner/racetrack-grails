package racetrack

class Race
{
  String name
  Date startDate
  String city
  String state
  // in kilometers
  BigDecimal distance
  BigDecimal cost
  Integer maxRunners = 100000

  BigDecimal inMiles(){
    return distance * 0.6214
  }

  String toString() {
    return "${name}, ${startDate.format('MM/dd/yyy')}"
  }


  static hasMany = [registrations:Registration]

  static mapping = {
    sort "startDate"
  }

  static constraints = {
    name(blank: false, maxSize: 50)
    startDate(nullable: false, validator:  {return it > new Date()})
    city()
    state(inList: ["GA","NC","SC","VA","WA"])
    distance(min: 0.0)
    cost(min: 0.0, max: 100.0)
    maxRunners(min: 1, max:100000)
  }
}
