package currency2
import currency.Euro
import currency.Account

class Currency2:
  println(currency.Euro)
  val a1: Account = Account(10, Euro)
  val a2: Account = a1 + 1.0
  val a3: Account = a1*1.0
  val a5: Account = 1.0 + a1 // le compilateur trouve la conversion Double => Account
  val a6: Account = 1.0 * a1 // mais pas la conversion Factor => Account car la méthode
  // Factor * Account se trouve dans la classe Factor
  //SOLUTION: créer une méthode Account * Account dans la classe Account

