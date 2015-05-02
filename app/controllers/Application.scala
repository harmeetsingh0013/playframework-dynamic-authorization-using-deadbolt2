package controllers

import play.api._
import play.api.mvc._
import handlers.MyDeadboltHandler
import models.UserPermission
import reactivemongo.bson.BSONObjectID
import models.SecurityRole
import models.User
import models.UserFormatter._
import play.modules.reactivemongo.json.BSONFormats._
import reactivemongo.extensions.json.dao.JsonDao
import appcontext.MongoContext
import scala.concurrent.ExecutionContext.Implicits.global
import be.objectify.deadbolt.scala.DeadboltActions
import scala.concurrent.Future
import be.objectify.deadbolt.core.PatternType

object Application extends Controller with DeadboltActions{

  class UserDao extends JsonDao[User, BSONObjectID](MongoContext.db, "users")
  var userDao = new UserDao()
  
  def index = Action {
    Ok(views.html.index(" application is ready."))
  }

  def group_one = Dynamic("group_one", "", new MyDeadboltHandler) {
                   Action.async {
                     Future.successful(Ok("Group One Done"))
                   }
                 }
  def group_two = Dynamic("group_two", "", new MyDeadboltHandler) {
                   Action.async {
                     Future.successful(Ok("Group Two Done"))
                   }
                 }
  def restrictOne = Restrict(Array("ADMIN"), new MyDeadboltHandler) {
                      Action.async {
                        Future.successful(Ok("Restriction Handler"))
                      }
                    }
  
  def pattern_one = Pattern("CH.*", PatternType.REGEX, new MyDeadboltHandler) {
                   Action.async {
                        println("In pattern one controller");
                        Future.successful(Ok("Patterns"))
                      }
                 }
  
  def pattern_two = Pattern("VIDEO", PatternType.EQUALITY, new MyDeadboltHandler) {
                   Action.async {
                        println("In pattern two controller");
                        Future.successful(Ok("Patterns"))
                      }
                 }
}