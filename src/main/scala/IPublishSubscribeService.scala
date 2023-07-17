trait IPublishSubscribeService {
  def publish(event: IEvent): Unit
  def subscribe(handler: ISubscriber): Unit
  def unsubscribe(): Unit
}