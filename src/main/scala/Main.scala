import models.Machine
import repos.MachineRepository
import services.{IPublishSubscribeService, PublishSubscribeService}
import subscribers.{MachineRefillSubscriber, MachineSaleSubscriber}
import utils.Helper

object Main extends App {
    val machines: List[Machine] = List(
      new Machine("001"), new Machine("002"), new Machine("003")
    )

    val machineRepositiry = new MachineRepository(machines)
    val saleSubscriber = new MachineSaleSubscriber(machines.map(_.id), machineRepositiry)
    val refillSubscriber = new MachineRefillSubscriber(machines.map(_.id), machineRepositiry)

    val pubSubService: IPublishSubscribeService = new PublishSubscribeService()

    List(saleSubscriber, refillSubscriber).foreach {
      s => pubSubService.subscribe(s)
    }

    val events = (1 to 100).map(i => Helper.generateEvent())

    events.map(e => {
        println("--------------------------------------------------")
        pubSubService.publish(e)
        machines.map(m => {
            println(s"${m.id} : ${m.stockLevel}")
        })
        println("--------------------------------------------------")

    })
}