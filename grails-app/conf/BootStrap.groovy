import racetrack.Runner
import grails.util.GrailsUtil
import racetrack.Race
import racetrack.Registration
import racetrack.User

class BootStrap
{

  def init = { servletContext ->
    switch (GrailsUtil.environment)
    {
      case "development":

        def admin = new User(login:"admin",
            password:"wordpass",
            role: "admin")
        admin.save()
        if(admin.hasErrors())
        {
          println admin.errors
        }

        def jdoe = new User(login: "jdoe",
            password: "password",
            role: "user")

        jdoe.save()
        if(jdoe.hasErrors())
        {
          println jdoe.errors
        }

        def jane = new Runner(firstName: "Jane",
            lastName: "Doe",
            dateOfBirth: (new Date() - 365 * 30),
            gender: "F",
            address: "1234 Foo Rd.",
            city: "fooVille",
            state: "FO",
            zipcode: "12345",
            email: "foo@example.com")

        jane.save()
        if (jane.hasErrors())
        {
          println jane.errors
        }

        def trot = new Race(
            name: "Turkey Trot",
            startDate: (new Date() + 90),
            city: "Bar",
            state: "NC",
            distance: 5.0,
            cost: 20.0,
            maxRunners: 350
        )
        trot.save()
        if (trot.hasErrors())
        {
          println trot.errors
        }

        def reg = new Registration(
            paid:false,
            runner:jane,
            race:trot
        )

        reg.save()
        if(reg.hasErrors())
        {
          //reg.errors.each { println "${it}" }
          println reg.errors
        }
        break

      case "production" : break
    }
  }
  def destroy = {
  }
}
