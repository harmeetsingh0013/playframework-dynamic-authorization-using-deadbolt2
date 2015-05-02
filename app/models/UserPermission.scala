/**
 *
 */
package models

import be.objectify.deadbolt.core.models.Permission
import reactivemongo.bson.BSONObjectID
import play.modules.reactivemongo.json.BSONFormats._
import play.api.libs.json.Json
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * @author Harmeet SIngh(Taara)
 * @version 1.0
 */

case class UserPermission (
    val id: String,
    val value: String
) extends Permission{
    def getValue: String = value
}
