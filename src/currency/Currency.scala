package currency

import currency.Currency.locale

import java.awt.geom.CubicCurve2D
import java.util.Currency
import scala.annotation.targetName

/*
final class Currency {
  type Currency = Value
  val Euro, Dollar, Yen = Value
}*/

sealed trait Currency:
  val name: String
  val symbol: String
  val code: String
  override def toString: String = {
    this.name + " " +this.symbol +" "+this.code
  }

object Currency:
  val euroExchangeRate:Map[Currency, Double]= Map(Euro -> 1, Dollar -> 1.15850, Yen -> 131.676)
  val dollarExchangeRate:Map[Currency, Double] = Map(Euro -> 0.863187, Dollar -> 1, Yen -> 113.648)
  val YenExchangeRate:Map[Currency, Double] = Map(Euro -> 0.00759441, Dollar -> 0.00879910, Yen -> 1)
  val exchangeRate:Map[Currency, Map[Currency, Double]] = Map(Dollar ->dollarExchangeRate, Euro -> euroExchangeRate, Yen -> YenExchangeRate)
  val locale:Currency = Euro


object Euro extends Currency :
  val name = "Euro"
  val symbol = "€"
  val code = "EUR"

object Dollar extends Currency :
  val name = "Dollar"
  val symbol = "$"
  val code = "USD"

final class Account(d:Double, c:Currency):
  val currency:Currency = c
  val amount:Double = d
  override def toString: String =
     this.amount + " " +this.currency.code
  def apply(d:Double, c:Currency): Account =
    new Account(d,c)

  def +(that:Account):Account =
     new Account(this.amount+
      that.amount*Currency.exchangeRate(that.currency)(this.currency), this.currency)

object Account:
  implicit def double2Locale(d: Double): Account =
     new Account(d, locale)


object Yen extends Currency:
  val name = "Yen"
  val symbol = "¥"
  val code = "JPY"

class Factor(d:Double):
  def *(account:Account):Account =
     Account(this.d*
      account.amount, account.currency)

object Factor:
  implicit def double2Factor(d:Double): Factor =
     Factor(d)



object test:
  def main(args: Array[String]): Unit =
    println(currency.Euro)
    println(Currency.exchangeRate(Yen)(Dollar))
    val a1 = Account(10, Euro)
    println(a1)
    val a2 = Account(10, Dollar) + a1
    println(a2)
    val a3:Account = Account.double2Locale(22.50) //conversion explicit
    println(a3.getClass)
    println(a3)
    val a4:Account = 22.50 //conversion implicit
    println(a4 + 12.50)
    println(12.50+a4)
    val f:Factor = Factor(1)
    a1 * 0.1
    0.1 * a1
    f * a1



