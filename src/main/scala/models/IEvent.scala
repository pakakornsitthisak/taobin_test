package models

trait IEvent {
  def eventType: String
  def machineId: String
}

case class MachineRefillEvent(
                               refill: Int,
                               id: String
                             ) extends IEvent {
  override def eventType: String = "refill"

  override def machineId: String = this.id

  def getRefillNumber = () => this.refill

}

case class MachineSaleEvent(
                             sold: Int,
                             id: String
                           ) extends IEvent {
  override def eventType: String = "sale"

  override def machineId: String = this.id

  def getSoldQuantity = () => this.sold
}

case class LowStockWarningEvent(id: String) extends IEvent {
  override def eventType: String = "los_stock_waning"

  override def machineId: String = this.id
}

case class StockLevelOkEvent(id: String) extends IEvent {
  override def eventType: String = "stock_level_ok_event"

  override def machineId: String = this.id
}