package services.dBServices

import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import services.model.Student
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

import scala.concurrent.{ExecutionContext, Future}


class StudentDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig: DatabaseConfig[JdbcProfile] = dbConfigProvider.get[JdbcProfile]


  import dbConfig._
  import profile.api._


  private val students = TableQuery[Students]

  def createStudentTable: Future[Unit] = db.run(students.schema.create)


  def insert(student: Student): Future[Int] = db.run(students += student)

  def allStudents: Future[Seq[Student]] = db.run(students.result)


  private class Students(tag: Tag) extends Table[Student](tag, "Student_Table") {

    def name: Rep[String] = column[String]("name")

    def age: Rep[Int] = column[Int]("age")

    def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)

    override def * : ProvenShape[Student] = (name, age, id).mapTo[Student]
  }


}


