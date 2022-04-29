package scala

import currency.{Account, Factor, Currency}


extension (a: Account) {
  def *(factor:Factor):Account = factor * a.amount
  def toFactor:Factor = new Factor(a.amount)
}
extension (d: Double) {
  def *(a:Account):Account = d * a.amount
}
extension (d: Double) {
  def ++(a:Double):Account = new Account(d+d, Currency.locale)
}
