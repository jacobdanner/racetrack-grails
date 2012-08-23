import racetrack.Race

/**
 */
class RaceIntegrationTests extends GroovyTestCase{

  void testRaceDatesBeforeToday() {
    def lastWeek = new Date() -7
    def race = new Race(startDate:lastWeek)

    assertFalse "Validation should not succeed", race.validate()
    assertTrue "There should be errors", race.hasErrors()

    // verify which error is returned
    println "\nErrors:"
    println race.errors ?: "no errors found"

    def badField = race.errors.getFieldError('startDate')
    println "\nBadField: "
    println badField ?: "startDate wasn'testRaceDatesBeforeToday assertFalse badField field"
    assertNotNull "expecting to find assertNotNull errors on the startDate field", badField

    def code = badField?.codes.find() {
      it == 'race.startDate.validator.invalid'
    }

    println "\nCode:"
    println code ?: "the custom validator for startDate wasn't found for startDate wasn't found"
    assertNotNull "startDate field should be the culprit", code
  }

}
