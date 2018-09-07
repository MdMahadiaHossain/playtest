package controllers

import javax.inject._
import play.api.mvc._
import services.dBServices.StudentDao

import scala.concurrent.ExecutionContext


@Singleton
class HomeController @Inject()(studentDao: StudentDao, cc: ControllerComponents)(implicit assetsFinder: AssetsFinder, executionContext: ExecutionContext)
  extends AbstractController(cc) {


  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def createTable: Action[AnyContent] = Action.async {

    studentDao.createStudentTable.map(x => Ok("table created"))
  }


  // got to onepage
  def onepage: Action[AnyContent] = Action.apply {
    Ok(views.html.onepage(assetsFinder))
  }


  // action for ajax call
  def ajxCall(): Action[AnyContent] = Action {
    println("->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ajax request coming")
    Ok("Hello  mahadi, i am here")
  }

  def check(): Action[AnyContent] = Action.apply {
    Redirect(call = routes.HomeController.onepage())
  }

}

