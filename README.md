# Play-Framework Dynamic Authorization Using Deadbolt-2

In this sample application we are using **Deadbolt-2** for maintaining dynamic **Authorization** using **Play-Framework 2.3.x**, **H2 Database** and **ReactiveMongo-Extensions**. We are using **Deadbolt-2** for secure our controllers with dynaimc authorization. The **Deadbolt-2.3.2** version not supported reactive-mongo, so we maintain permission using **JDBC** and rest of data maintain in **Mongodb** using **ReactiveMongo**. Our RDBMS tables structure and sample data are declared in **conf/evolutions/default** directory. Our mongodb collection structure as below:    

**NOTE: We are facing problem with Deadbolt-2.3.3 version. [Please access this link.](http://stackoverflow.com/questions/30002646/playframework-with-deadbolt2-overriding-method-getsubject-in-trait-deadbolthand)**

```sh
`users` Collection: 
{
    "_id" : ObjectId("553e3c982c00002d00c4fd12"),
    "userName" : "james"
}

```
**NOTE: Detail about mongodb database please access /conf/application.conf file.**
Following are the references, that i used for creating my sample application. 

* [Play-Frmework 2.3.x](https://www.playframework.com/documentation/2.3.x/Highlights23)
* [ReactiveMongo-Extensions](https://github.com/ReactiveMongo/ReactiveMongo-Extensions)
* [Deadbolt Guide](https://github.com/schaloner/deadbolt-2-guide)
* [Deadbolt Example](https://github.com/schaloner/deadbolt-2-scala-examples)
* [Database Evolutions](https://www.playframework.com/documentation/2.1.x/Evolutions)

In this sample, we cover deadbolt 3 stratergies as **Dynamic**, **Restrict** and **Pattern**. Others are not cover, but they are also important. Deadbolt-2 is really a great authorization mechanism with Play-Framework. The brief explanation for our samples as below: 
* **Dynamic**: For Dynamic authorization we extends **DynamicResourceHandler** class and override its methods **isAllowed** and **checkPermission**. With this we create resources dynamiclly and provide authorization access at run time. In our application we can use multiple Custom-DynamicResourceHandler according to our requirements or we can use one DynamicResourceHandler with Inner classes, as we do in our sample application. 
* **Restrict**: In this we authorised our controllers based on User roles. 
* **Pattern**: This is another good option provide by Deadbolt for set our authorization based on Equality or Regular Expressions. 

We can also secure our scala templates with Deadbolt-2, But we are not cover secure templates in our sample. The securing templates is same as secure our controllers. For detail description [Click on this link](http://deadbolt-2-scala.herokuapp.com/#controller-Pattern). We are using chrome POSTMAN client for access our controllers. 

>First time, when we run this sample application, access **http://localhost:9000** url, because this create our tables in h2-database automatically. But we need to create our database using Play-Framework h2-browser command. For more detail access **Database Evolutions** in above references. 

>For detail description, Please follow Deadbolt-2 **Guide** and **Example**, That we mention in references.
