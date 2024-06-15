abstract class User(val username: String, var displayName: String, var email: String, val gender: String, var phoneNumber: String, var address: String)

case class Login(username: String, password: String) {
  def login():Boolean={
    //return login status
    false
  }
}

object User {
  def register(userType: String, username: String, password: String, displayName: String, email: String, gender: String, phoneNumber: String, address: String): User = {
    userType.toLowerCase match {
      case "customer" => new Customer(username, displayName, email, gender, phoneNumber, address, Login(username, password))
      case "admin" => new Admin(username, displayName, email, gender, phoneNumber, address, Login(username, password))
    }
  }
}

class Customer(username: String, displayName: String, email: String, gender: String, phoneNumber: String, address: String, val login: Login)
  extends User(username, displayName, email, gender, phoneNumber, address)

class Admin(username: String, displayName: String, email: String, gender: String, phoneNumber: String, address: String, val login: Login)
  extends User(username, displayName, email, gender, phoneNumber, address)

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
    // display the number of times customer ordered this food
    ordersReceived += 1
  }
}

case class Order(id: Int, customer: Customer, restaurant: Restaurant, items: List[(Food, Int)]) extends Discount {
  val percentage: Double = 10.0  // Example discount percentage

  def totalPrice: Double = {
    var total = 0.0
    for ((food, quantity) <- items) {
      total += food.price * quantity
    }
    applyDiscount(total)
  }
}

class Restaurant(val name: String, val address: String, val menu: List[Food]) {
  var orders: List[Order] = Nil

  def addOrder(order: Order): Unit = {
    orders = order :: orders
  }
}

class DeliveryPerson(val name: String, var phoneNumber: String, var isAvailable: Boolean = true) {
  var currentOrder: Order = _

  def assignOrder(order: Order): Boolean = {
    if (isAvailable) {
      currentOrder = order
      isAvailable = false
      true
    } else {
      false
    }
  }

  def completeOrder(): Unit = {
    currentOrder = null
    isAvailable = true
  }
}

case class Payment(order: Order, amount: Double, paymentMethod: String, var isPaid: Boolean = false) {
  def makePayment(): Unit = {
    isPaid = true
  }
}
