abstract class User(val username: String, val displayName: String, var email: String, val gender: String, var phoneNumber: String, var address: String)

case class Login(username: String, password: String)

object User {
  def register(username: String, password: String, displayName: String, email: String, gender: String, phoneNumber: String, address: String): User = {
    new User(username, displayName, email, gender, phoneNumber, address) {}
  }
}

class Customer(login: Login, displayName: String, email: String, gender: String, phoneNumber: String, address: String)
  extends User(login.username, displayName, email, gender, phoneNumber, address)

class Admin(login: Login, displayName: String, email: String, gender: String, phoneNumber: String, address: String)
  extends User(login.username, displayName, email, gender, phoneNumber, address)

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

class Food(val category: Category, val name: String, val price: Double, val company: String) {
  var reviews: List[(Int, String)] = Nil
  var ordersReceived: Int = 0

  def addReview(rating: Int, comment: String): Unit = {
    reviews = (rating, comment) :: reviews
  }

  def incrementOrdersReceived(): Unit = {
    ordersReceived += 1
  }
}

case class Order(id: Int, customer: Customer, company: String, items: List[(Food, Int)]) {
  def totalPrice: Double = {
    items.foldLeft(0.0) { case (acc, (food, quantity)) =>
      acc + food.price * quantity
    }
  }
}
