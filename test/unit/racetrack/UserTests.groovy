package racetrack



import grails.test.mixin.*
import org.junit.*
import grails.test.GrailsUnitTestCase

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserTests {//extends GrailsUnitTestCase{

  // old unit tests method with mocking
  void testSimpleConstraints2(){
    // metaprogram methods for constraints evaluation onto
    // the Class
    mockForConstraintsTests(User)
    def user = new User(login: "someone",
        password: "blah".encodeAsSHA(),
        role: "SuperUser")
    // incorrect role
    assertFalse user.validate()

    // verifies inList validation failed for role
    assertEquals "inList", user.errors["role"]
  }

    void testSimpleConstraints() {
      def user = new User(login:  "someone",
          password: "blah".encodeAsSHA(),
          role: "SuperUser")

      // role is not correct
      assertFalse user.validate()
    }

  void testUniqueConstraint() {
    def jdoe = new User(login: "jdoe")
    def admin = new User(login: "admin")
    // creates mod table
    mockDomain(User, [jdoe, admin])

    // verify uniqueness constraint
    def badUser = new User(login:  "jdoe")
    badUser.save()
    assertEquals 2, User.count()
    assertEquals "unique", badUser.errors["login"]

    def goodUser = new User(login: "good",password: "password".encodeAsSHA(),role:"user")
    goodUser.save()
    assertEquals 3, User.count()
    assertNotNull User.findByLoginAndPassword("good", "password".encodeAsSHA())
  }


}
