case class Login(username: String, password: String) {
  def status(_username: String, _password: String): Boolean = {
    _username == username && _password == password
  }
}

abstract class User(val login: Login, val displayName: String, var email: String, val gender: String, var phoneNumber: String, var address: String)

class Customer(login: Login, displayName: String, email: String, gender: String, phoneNumber: String, address: String) extends User(login, displayName, email, gender, phoneNumber, address)

class Admin(login: Login, displayName: String, email: String, gender: String, phoneNumber: String, address: String) extends User(login, displayName, email, gender, phoneNumber, address)

case class Category(category: String)

trait Discount {
  val percentage: Double

  def calculateDiscount(price: Double): Double = {
    price * percentage / 100
  }

  def applyDiscount(price: Double): Double = {
    price - calculateDiscount(price)
  }
}

class Food(val category: Category, val name: String, val price: Double, val company: String) extends Discount {
  var reviews: List[(Int, String)] = Nil
  var ordersReceived: Int = 0

  def addReview(rating: Int, comment: String): Unit = {
    reviews = (rating, comment) :: reviews
  }

  def incrementOrdersReceived(): Unit = {
    ordersReceived += 1
  }

  def finalPrice: Double = {
    applyDiscount(price)
  }
}

case class Order(id: Int, customer: Customer, company: String, food: Food, quantity: Int) {
  def totalPrice: Double = {
    food.finalPrice * quantity
  }
}
