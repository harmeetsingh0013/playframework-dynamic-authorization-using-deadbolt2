/**
 *
 */
package handlers

import be.objectify.deadbolt.scala.DeadboltHandler
import be.objectify.deadbolt.scala.DynamicResourceHandler
import play.api.mvc.Request
import play.api.db.DB
import play.api.Play.current

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
class MyDynamicResourceHandler extends DynamicResourceHandler{

     def isAllowed[A](name: String, meta: String, handler: DeadboltHandler, request: Request[A]) = {
        MyDynamicResourceHandler.handlers(name).isAllowed(name, meta, handler, request)
    }
     
     def checkPermission[A](permissionValue: String, deadboltHandler: DeadboltHandler, request: Request[A]) = {
       println(" MyDynamicResourceHandler checkPermission")
       false
     }
     
    object MyDynamicResourceHandler {
      val handlers: Map[String, DynamicResourceHandler] =
        Map(
           "group_one" -> new DynamicResourceHandler() {
             def isAllowed[A](name: String, meta: String, deadboltHandler: DeadboltHandler, request: Request[A]) ={
               if (!request.headers.get("userId").isEmpty){
                 val userId = request.headers.get("userId").get;
                 DB.withConnection { conn => {
                 val stmt = conn.createStatement();
                 var rs = stmt.executeQuery("SELECT COUNT(user_id) FROM group_one WHERE user_id = '"+userId+"'")
                 if (rs.next())  if (rs.getInt(1) != 0) true else false else false 
                }}
               }else false
             }

             def checkPermission[A](permissionValue: String, deadboltHandler: DeadboltHandler, request: Request[A]) = {
               println("INNER MyDynamicResourceHandler checkPermission");
               false
             }
         },
         "group_two" -> new DynamicResourceHandler() {
             def isAllowed[A](name: String, meta: String, deadboltHandler: DeadboltHandler, request: Request[A]) ={
               if (!request.headers.get("userId").isEmpty){
                 val userId = request.headers.get("userId").get;
                 DB.withConnection { conn => {
                 val stmt = conn.createStatement();
                 var rs = stmt.executeQuery("SELECT COUNT(user_id) FROM group_two WHERE user_id = '"+userId+"'")
                 if (rs.next())  if (rs.getInt(1) != 0) true else false else false 
                }}
               }else false
             }

             def checkPermission[A](permissionValue: String, deadboltHandler: DeadboltHandler, request: Request[A]) = {
               println("INNER MyDynamicResourceHandler checkPermission");
               false
             }
         }
       )
    }
}