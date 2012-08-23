import racetrack.Runner

class BootStrap {

    def init = { servletContext ->
      def jane = new Runner(firstName:"Jane",
                lastName: "Doe",
                dateOfBirth: (new Date() - 365*30),
      gender: "F",
      address: "1234 Foo Rd.",
      city: "fooVille",
      state: "FO",
      zipcode:"12345",
      email: "foo@example.com")

      jane.save()
      if(jane.hasErrors())
      {
        println jane.errors
      }
    }
    def destroy = {
    }
}
