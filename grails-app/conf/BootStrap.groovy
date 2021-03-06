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

        def admin = new User(login: "admin",
            password: "admin".encodeAsSHA(),
            role: "admin")
        admin.save()
        if (admin.hasErrors())
        {
          println admin.errors
        }

        def jdoe = new User(login: "jdoe",
            password: "jdoe".encodeAsSHA(),
            role: "user")

        jdoe.save()
        if (jdoe.hasErrors())
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
            paid: false,
            runner: jane,
            race: trot
        )

        reg.save()
        if (reg.hasErrors())
        {
          //reg.errors.each { println "${it}" }
          println reg.errors
        }

        def burner = new Race(
            name: "Barn Burner",
            startDate: (new Date() + 120),
            city: "Cary",
            state: "NC",
            distance: 10.0,
            cost: 15.0,
            maxRunners: 350
        )
        burner.save()
        if (burner.hasErrors())
        {
          println burner.errors
        }

        def chase = new Race(
            name: "Race for the Chase",
            startDate: (new Date() + 150),
            city: "Duck",
            state: "NC",
            distance: 5.0,
            cost: 25.0,
            maxRunners: 350
        )
        chase.save()
        if (chase.hasErrors())
        {
          println chase.errors
        }

        break

      case "production":
        // seed production data here
        break
    }
  }
  def destroy = {
  }
}
