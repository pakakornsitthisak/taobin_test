trait ISubscriber {
  def handle(event: IEvent): IResult
}