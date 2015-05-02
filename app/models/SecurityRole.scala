/**
 *
 */
package models

import be.objectify.deadbolt.core.models.Role
import reactivemongo.bson.BSONObjectID
import play.api.libs.json.Json
import play.modules.reactivemongo.json.BSONFormats._

/**
 * @author Harmeet SIngh(Taara)
 * @version 1.0
 */
case class SecurityRole (
    val id: String,
    val roleName: String    
)extends Role {
  def getName = this.roleName
}