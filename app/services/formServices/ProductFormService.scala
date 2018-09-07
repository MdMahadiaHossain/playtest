package services.formServices

import play.api.data.Form
import play.api.data.Forms._

object ProductFormService {

  case class Data(name: String, age:Int)

  def form:Form[Data]=Form.apply{
    mapping(
      "name"-> nonEmptyText.verifying("avoid script tags", cons => !cons.contains('>')),
      "age" -> number(min = 18,max = 64)
    )(Data.apply)(Data.unapply)

  }

}
