package racetrack



import org.junit.*
import grails.test.mixin.*
import org.codehaus.groovy.grails.plugins.codecs.Base64Codec

@TestFor(UserController)
@Mock(User)
class UserControllerTests
{

  /*void setUp() {
    super.setUp()
    String.metaClass.encodeAsBase64 = { ->
      Base64Codec.encode(delegate)
    }
    String.metaClass.encodeAsSHA = { ->
      SHACodec.encode(delegate)
    }
  }*/

  def populateValidParams(params)
  {
    assert params != null
    // TODO: Populate valid properties like...
    //params["name"] = 'someValidName'
  }

  void testIndex()
  {
    controller.index()
    assert "/user/list" == response.redirectedUrl
  }

  void testList()
  {

    def model = controller.list()

    assert model.userInstanceList.size() == 0
    assert model.userInstanceTotal == 0
  }

  void testCreate()
  {
    def model = controller.create()

    assert model.userInstance != null
  }

  void testSave()
  {
    controller.save()

    assert model.userInstance != null
    assert view == '/user/create'

    response.reset()

    populateValidParams(params)
    controller.save()

    assert response.redirectedUrl == '/user/show/1'
    assert controller.flash.message != null
    assert User.count() == 1
  }

  void testShow()
  {
    controller.show()

    assert flash.message != null
    assert response.redirectedUrl == '/user/list'


    populateValidParams(params)
    def user = new User(params)

    assert user.save() != null

    params.id = user.id

    def model = controller.show()

    assert model.userInstance == user
  }

  void testShow2()
  {
    def jdoe = new User(login: "jdoe")
    def suziq = new User(login: "suziq")
    mockDomain(User, [jdoe, suziq])

    controller.params.identity = 2
    def map = controller.show()
    assertEquals "suziq", map.userInstance.login
  }

  void testAuthenticate()
  {
    def jdoe = new User(login: "jdoe",
        password: "password".encodeAsSHA())
    mockDomain(User, [jdoe])

    controller.params.login = "jdoe"
    controller.params.password="password"
    controller.authenticate()
    assertNotNull controller.session.user
    assertEquals "jdoe", controller.session.user.login

    controller.params.password = "foo"
    controller.authenticate()
    assertTrue controller.flash.message.startsWith("Sorry, jdoe")
  }



  void testEdit()
  {
    controller.edit()

    assert flash.message != null
    assert response.redirectedUrl == '/user/list'


    populateValidParams(params)
    def user = new User(params)

    assert user.save() != null

    params.id = user.id

    def model = controller.edit()

    assert model.userInstance == user
  }

  void testUpdate()
  {
    controller.update()

    assert flash.message != null
    assert response.redirectedUrl == '/user/list'

    response.reset()


    populateValidParams(params)
    def user = new User(params)

    assert user.save() != null

    // test invalid parameters in update
    params.id = user.id
    //TODO: add invalid values to params object

    controller.update()

    assert view == "/user/edit"
    assert model.userInstance != null

    user.clearErrors()

    populateValidParams(params)
    controller.update()

    assert response.redirectedUrl == "/user/show/$user.id"
    assert flash.message != null

    //test outdated version number
    response.reset()
    user.clearErrors()

    populateValidParams(params)
    params.id = user.id
    params.version = -1
    controller.update()

    assert view == "/user/edit"
    assert model.userInstance != null
    assert model.userInstance.errors.getFieldError('version')
    assert flash.message != null
  }

  void testDelete()
  {
    controller.delete()
    assert flash.message != null
    assert response.redirectedUrl == '/user/list'

    response.reset()

    populateValidParams(params)
    def user = new User(params)

    assert user.save() != null
    assert User.count() == 1

    params.id = user.id

    controller.delete()

    assert User.count() == 0
    assert User.get(user.id) == null
    assert response.redirectedUrl == '/user/list'
  }
}
