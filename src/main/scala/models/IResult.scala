package models

trait IResult

case class Fail(message: String) extends IResult

case class Success() extends IResult

case class LowStockWarningResult(id: String) extends IResult

case class StockLevelOkResult(id: String) extends IResult