/**
 *
 */
package appcontext

import scala.concurrent.ExecutionContext.Implicits.global

import reactivemongo.api.DB
import reactivemongo.api.MongoDriver

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
object MongoContext {

  val mongoDriver = new MongoDriver;
  val connection = mongoDriver.connection(List("localhost"));
  def db: DB = connection.db("deadbolt2")
}