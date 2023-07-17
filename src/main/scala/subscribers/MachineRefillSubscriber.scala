package subscribers

import models._
import repos.MachineRepository

class MachineRefillSubscriber(val machines: List[String], val machineRepository: MachineRepository) extends ISubscriber {
  override def handle(event: IEvent): IResult = event match {
    case e: MachineRefillEvent =>
      try {
        machineRepository.addStock(machines.filter(e => e == event.machineId), e.getRefillNumber())
      } catch {
        case e: Exception =>
          Fail(e.getMessage)
      }
    case _ => Success()
  }
}