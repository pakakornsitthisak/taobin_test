class PublishSubscribeService extends IPublishSubscribeService {

  private var subscribers = List.empty[ISubscriber]


  override def publish(event: IEvent): Unit = {
    event match {
      case m: MachineSaleEvent => println(s"${m.eventType} : ${m.getSoldQuantity()}")
      case m: MachineRefillEvent => println(s"${m.eventType} : ${m.getRefillNumber()}")
      case m: LowStockWarningEvent => println(s"${m.eventType} : ${m.id}")
      case m: StockLevelOkEvent => println(s"${m.eventType} : ${m.id}")
      case _ =>
    }
    subscribers.map {
      s => {
        s.handle(event) match {
          case _: Success =>
          case e: Fail => println(s"Failed: ${e.message}.")
          case e: LowStockWarningResult => publish(LowStockWarningEvent(e.id))
          case e: StockLevelOkResult => publish(StockLevelOkEvent(e.id))
          case _ =>
        }

      }
    }
    event match {
      case m: MachineSaleEvent => println(s"${m.eventType} : ${m.getSoldQuantity()}")
      case m: MachineRefillEvent => println(s"${m.eventType} : ${m.getRefillNumber()}")
      case m: LowStockWarningEvent => println(s"${m.eventType} : ${m.id}")
      case m: StockLevelOkEvent => println(s"${m.eventType} : ${m.id}")
      case _ =>
    }
  }

  override def subscribe(handler: ISubscriber): Unit = {
    subscribers = subscribers.appended(handler)
  }

  override def unsubscribe(): Unit = {
    subscribers = List.empty
  }
}