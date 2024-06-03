case class Login(username: String, password: String)

abstract class User(val name: String, val email: String, val gender: String, val phoneNumber: String, val address: String)

class Customer(_name: String, _email: String, _gender: String, _phoneNumber: String, _address: String) extends User(_name, _email, _gender, _phoneNumber, _address)

class Admin(_name: String, _email: String, _gender: String, _phoneNumber: String, _address: String) extends User(_name, _email, _gender, _phoneNumber, _address)

case class FoodCategory(category: String)

class Food(foodCategory: FoodCategory, val name: String, val price: Double, val company: String) {
  var reviews: List[(Int, String)] = Nil
  var ordersReceived: Int = 0

  def addReview(rating: Int, comment: String): Unit = {
    reviews = (rating, comment) :: reviews
  }
  def incrementOrdersReceived(): Unit = {
    ordersReceived += 1
  }


}

case class Order(id: Int, customer: Customer, company: String, food: Food, quantity: Int, totalPrice: Double)
