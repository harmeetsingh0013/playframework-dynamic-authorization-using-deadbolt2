/**
 *
 */
package handlers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._

import appcontext.MongoContext
import be.objectify.deadbolt.core.models.Subject
import be.objectify.deadbolt.scala.DeadboltHandler
import be.objectify.deadbolt.scala.DynamicResourceHandler
import models.User
import models.UserFormatter._
import play.api.mvc.Request
import play.modules.reactivemongo.json.BSONFormats._
import reactivemongo.bson.BSONObjectID
import reactivemongo.extensions.json.dao.JsonDao
import play.api.mvc.Request
import play.api.mvc.Result
import play.api.mvc.Results

/**
 * @author Harmeet SIngh(Taara)
 * @version 1.0
 */


class MyDeadboltHandler(dynamicResourceHandler: Option[DynamicResourceHandler] = None) extends DeadboltHandler{
  
  class UserDao extends JsonDao[User, BSONObjectID](MongoContext.db, "users")
  var userDao = new UserDao()
  
  def beforeAuthCheck[A](request: Request[A]) = None
  
  override def getDynamicResourceHandler[A](request: Request[A]): Option[DynamicResourceHandler] = {
    if (dynamicResourceHandler.isDefined) dynamicResourceHandler
    else Some(new MyDynamicResourceHandler())
  }
  
  override def getSubject[A](request: Request[A]): Future[Option[Subject]] = {
    if(!request.headers.get("userId").isEmpty){
      val userId = request.headers.get("userId").get;
      userDao.findById(BSONObjectID.apply(userId));
    }else{
      println("Else Method Start getSubject");
      Future(Option.empty);
    }
  }
  
  def onAuthFailure[A](request: Request[A]): Future[Result] = {
     Future {Results.BadRequest}
  }
}