package controllers

import javax.inject._
import play.api.data.Form
import play.api.mvc._
import services.dBServices.StudentDao
import services.formServices.ProductFormService
import services.formServices.ProductFormService._
import services.model.Student

import scala.concurrent.ExecutionContext

@Singleton
class FormController @Inject()(studentDao: StudentDao, messagesControllerComponents: MessagesControllerComponents)(implicit executionContext: ExecutionContext,assetsFinder: AssetsFinder) extends
  MessagesAbstractController(controllerComponents = messagesControllerComponents) {


  def gotoformpage: Action[AnyContent] = Action.async{
    implicit request =>
    studentDao.allStudents.map((stds: Seq[Student]) => Ok(views.html.formpage(form, stds)))
  }



  def insertStudent(): Action[AnyContent] = Action.async {
    implicit request =>
      form.bindFromRequest().fold(
        (errorForm: Form[Data]) => studentDao.allStudents.map((stds: Seq[Student]) => Ok(views.html.formpage(errorForm, stds))),
        (data: Data) => {
          studentDao.insert(Student(data.name, data.age)).map(x => Redirect(routes.FormController.gotoformpage()))
        }
      )
  }

  def gotoLoginpage():Action[AnyContent]=Action.apply{
    implicit messagesRequest : MessagesRequest[AnyContent] =>
      Ok(views.html.login(ProductFormService.form))
  }



}


