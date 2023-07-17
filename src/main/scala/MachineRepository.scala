class MachineRepository(val machines: List[Machine]) {
  def addStock(ms: List[String], qty: Int): IResult = {
    machines.filter(m => ms.contains(m.id)).map {
      m => {
        val oldStock = m.stockLevel
        m.stockLevel = m.stockLevel + qty
        if (oldStock < 3 && m.stockLevel >= 3)
          StockLevelOkResult(m.id)
        else Success()
      }
    }.head
  }

  def reduceStock(ms: List[String], qty: Int): IResult = {
    machines.filter(m => ms.contains(m.id)).map {
      m => {
        val oldStock = m.stockLevel
        m.stockLevel = m.stockLevel - qty
        if (oldStock > 3 && m.stockLevel <= 3)
          LowStockWarningResult(m.id)
        else Success()
      }
    }.head
  }
}