package subscribers

import models.{IEvent, IResult}

trait ISubscriber {
  def handle(event: IEvent): IResult
}