class MachineSaleSubscriber(val machines: List[String], val machineRepository: MachineRepository) extends ISubscriber {
  override def handle(event: IEvent): IResult = event match {
    case e: MachineSaleEvent =>
      try {
        machineRepository.reduceStock(machines.filter(e => e == event.machineId), e.getSoldQuantity())
      } catch {
        case e: Exception =>
          Fail(e.getMessage)
      }
    case _ => Success()
  }
}