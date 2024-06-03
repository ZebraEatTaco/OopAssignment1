//https://www.freeprojectz.com/paid-projects/nodejs-react-mongo/online-food-ordering-system

case class Login(username:String, password:String)

abstract class User(val name:String, val email:String, val gender:String, val phoneNumber: Int, val address:String)

class Customer(_name:String,_email:String,_gender:String,_phoneNumber:Int,_address:String) extends User(_name,_email,_gender,_phoneNumber,_address)

class Admin(_name:String,_email:String,_gender:String,_phoneNumber:Int,_address:String) extends User(_name,_email,_gender,_phoneNumber,_address)

case class FoodCategory(Category: String)

class Food(var foodCategory: FoodCategory)