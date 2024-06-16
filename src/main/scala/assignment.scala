abstract class User(val username: String, var displayName: String, var email: String, val gender: String, var phoneNumber: String, var address: String) {
  def updateProfile(newDisplayName: String, newEmail: String, newPhoneNumber: String, newAddress: String): Unit = {
    // Update user profile details
  }

  def resetPassword(oldPassword: String, newPassword: String): Boolean = {
    // Reset the user's password and return success status
    false
  }

  def deactivateAccount(): Unit = {
    // Deactivate the user's account
  }
}

class Customer(username: String, displayName: String, email: String, gender: String, phoneNumber: String, address: String, val login: Login)
  extends User(username, displayName, email, gender, phoneNumber, address) {

  def viewOrderHistory(): List[Order] = {
    // Return a list of orders placed by the customer
    Nil
  }

  def placeOrder(order: Order): Boolean = {
    // Place a new order and return success status
    false
  }

  def addReview(food: Food, rating: Int, comment: String): Unit = {
    // Add a review for the specified food item
  }
}

class Admin(username: String, displayName: String, email: String, gender: String, phoneNumber: String, address: String, val login: Login)
  extends User(username, displayName, email, gender, phoneNumber, address) {

  def manageUsers(): Unit = {
    // Logic to manage users (add, remove, update)
  }

  def manageFoodItems(): Unit = {
    // Logic to manage food items (add, remove, update)
  }

  def viewAllOrders(): List[Order] = {
    // Return a list of all orders in the system
    Nil
  }
}

class DeliveryPerson(username: String, displayName: String, email: String, gender: String, phoneNumber: String, address: String, val login: Login)
  extends User(username, displayName, email, gender, phoneNumber, address) {
  var isAvailable: Boolean = true
  var currentOrder: Option[Order] = None

  def assignOrder(order: Order): Boolean = {
    if (isAvailable) {
      currentOrder = _
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

class Restaurant(username: String, displayName: String, email: String, gender: String, phoneNumber: String, address: String, var menu: List[Food], val login: Login)
  extends User(username, displayName, email, gender, phoneNumber, address) {
  var orders: List[Order] = Nil

  def addOrder(order: Order): Unit = {
    orders = order :: orders
  }
}

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
      case "deliveryperson" => new DeliveryPerson(username, displayName, email, gender, phoneNumber, address, Login(username, password))
      case "restaurant" => new Restaurant(username, displayName, email, gender, phoneNumber, address, Nil, Login(username, password))
    }
  }
}

case class Category(category: String, description:String)

class Food(var category: Category, var name: String, var price: Double, val restaurant: Restaurant) {
  var reviews: List[(Customer, Int, String)] = Nil
  var ordersReceived: Int = 0

  def addReview(customer:Customer, rating: Int, comment: String): Unit = {
    reviews = (customer, rating, comment) :: reviews
  }

  def incrementOrdersReceived(): Unit = {
    // display the number of times customer ordered this food
    ordersReceived += 1
  }
}

trait Discount {
  var percentage: Double

  def calculateDiscount(price: Double): Double = {
    price * percentage / 100
  }

  def applyDiscount(price: Double): Double = {
    price - calculateDiscount(price)
  }
}

case class Order(id: Int, customer: Customer, restaurant: Restaurant, items: List[(Food, Int)], percentage: Double) extends Discount {
  // Calculate total price by summing up item prices and applying discount
  def totalPrice: Double = {
    // Calculate subtotal by summing item prices
    // Apply discount to subtotal
    0.00
  }
}

case class Payment(order: Order, amount: Double, paymentMethod: String, var isPaid: Boolean = false) {
  def makePayment(): Unit = {
    isPaid = true
  }
}