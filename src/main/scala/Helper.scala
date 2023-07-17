object Helper {
  private def randomMachine= () => {
    val random = Math.random() * 3
    val machineId = if (random < 1) "001"
    else if (random < 2) "002"
    else "003"
    machineId
  }

  def generateEvent = () => {
    val random = Math.random()
    if (random < 0.9) {
      val saleQty = if (Math.random() < 0.5) 1 else 2
      MachineSaleEvent(saleQty, randomMachine())
    } else {
      val refillQty = if (Math.random() < 0.5) 3 else 5
      MachineRefillEvent(refillQty, randomMachine())
    }
  }
}