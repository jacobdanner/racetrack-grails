package racetrack

import org.springframework.dao.DataIntegrityViolationException

class UserController
{

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  // called before each action is invoked
  // & is a method pointer
  def beforeInterceptor = [action:this.&auth,
      // could also use only here
      except:['login', 'logout', 'authenticate']]

  def auth() {
    if (!session.user)
    {
      redirect(controller: "user", action: "login")
      return false
    }

    if (!session.user.admin)
    {
      flash.message = "Tsk tsk - admins only"
      redirect(controller: "race", action: "list")
      return false
    }
  }

  // method is private - can only be called by other methods
  def debug(){
    println "DEBUG: ${actionUri} called."
    println "DEBUG: ${params}"
  }

  def login = {}

  // closure - exposed to the end user
  def logout = {
    flash.message = "Goodbye ${session.user}"
    session.user = null;
    redirect(action: "login")
  }

  def authenticate = {
    // GORM findBy goodness here
    // and/or to query several fields,
    // nice metaprogramming feature
    // grails.org/DomainClass+Dynamic+Methods
    def user = User.findByLoginAndPassword(params.login,
        params.password.encodeAsSHA())

    if (user)
    {
      session.user = user
      flash.message = "Hello ${user.login}!"
      // redirect admin user to admin/index page
      if (user.admin)
      {
        redirect(controller: "admin", action: "index")
      } else {
        redirect(controller: "race", action: "list")
      }
    } else
    {
      flash.message = "Sorry, ${params.login}. Please try again."
      redirect(action: "login")
    }
  }

  def index()
  {
    redirect(action: "list", params: params)
  }

  def list()
  {
    params.max = Math.min(params.max ? params.int('max') : 10, 100)
    [userInstanceList: User.list(params), userInstanceTotal: User.count()]
  }

  def create()
  {
    [userInstance: new User(params)]
  }

  def save()
  {
    def userInstance = new User(params)
    if (!userInstance.save(flush: true))
    {
      render(view: "login", model: [userInstance: userInstance])
      return
    }

    flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
    redirect(action: "show", id: userInstance.id)
  }

  def show()
  {
    def userInstance = User.get(params.id)
    if (!userInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
      redirect(action: "list")
      return
    }

    [userInstance: userInstance]
  }

  def edit()
  {
    def userInstance = User.get(params.id)
    if (!userInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
      redirect(action: "list")
      return
    }

    [userInstance: userInstance]
  }

  def update()
  {
    def userInstance = User.get(params.id)
    if (!userInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
      redirect(action: "list")
      return
    }

    if (params.version)
    {
      def version = params.version.toLong()
      if (userInstance.version > version)
      {
        userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
            [message(code: 'user.label', default: 'User')] as Object[],
            "Another user has updated this User while you were editing")
        render(view: "edit", model: [userInstance: userInstance])
        return
      }
    }

    userInstance.properties = params

    if (!userInstance.save(flush: true))
    {
      render(view: "edit", model: [userInstance: userInstance])
      return
    }

    flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
    redirect(action: "show", id: userInstance.id)
  }

  def delete()
  {
    def userInstance = User.get(params.id)
    if (!userInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
      redirect(action: "list")
      return
    }

    try
    {
      userInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e)
    {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
      redirect(action: "show", id: params.id)
    }
  }
}
