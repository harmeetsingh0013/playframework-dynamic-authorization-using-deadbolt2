/**
 *
 */
package models

import scala.collection.immutable.List
import scala.concurrent.ExecutionContext.Implicits.global
import appcontext.MongoContext
import be.objectify.deadbolt.core.models.Subject
import play.api.libs.json.Json
import play.libs.Scala
import play.modules.reactivemongo.json.BSONFormats._
import reactivemongo.bson.BSONObjectID
import reactivemongo.extensions.json.dao.JsonDao
import play.api.db.DB
import play.api.Play.current
import scala.collection.mutable.ListBuffer
/**
 * @author Harmeet SIngh(Taara)
 * @version 1.0
 */

case class User (
    val _id: BSONObjectID,
    val userName: String
) extends Subject{
  
  def getRoles: java.util.List[SecurityRole] = {
    println("getRoles >>>>> ");
    var userSecurityRoles: ListBuffer[SecurityRole] = new ListBuffer();
    DB.withConnection { conn => {
      val stmt = conn.createStatement();
      var rs = stmt.executeQuery("SELECT sr.id, sr.role_name  FROM  security_roles AS sr INNER JOIN user_security_roles AS usr WHERE  sr.id = usr.security_id AND usr.user_id = '"+this._id.stringify+"'")
      while(rs.next()){
        userSecurityRoles.+=(new SecurityRole(rs.getString(1), rs.getString(2)))
      }
    }}
    Scala.asJava(userSecurityRoles)
  }
  
  def getPermissions: java.util.List[UserPermission] = {
    println("getPermissions >>>>> ");
    var userUserPermissions: ListBuffer[UserPermission] = new ListBuffer();
    DB.withConnection { conn => {
      val stmt = conn.createStatement();
      var rs = stmt.executeQuery("SELECT up.id, up.permission_name  FROM  user_permissions AS up INNER JOIN user_user_permission AS uup WHERE  up.id = uup.permission_id AND uup.user_id = '"+this._id.stringify+"'")
      while(rs.next()){
        userUserPermissions.+=(new UserPermission(rs.getString(1), rs.getString(2)))
      }
    }}
    Scala.asJava(userUserPermissions)
  }
  
  def getIdentifier: String = userName
}

object UserFormatter{
  implicit val userFormatter = Json.format[User];
}