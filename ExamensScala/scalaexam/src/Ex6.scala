/**
 * Created by pedro on 11/26/15.
 */

import scala.collection.mutable.ListBuffer

trait AComponent{
  val name:String
  def size:Int
  def filter(f:(AComponent=>Boolean)):List[AComponent]
}

case class File(val name:String,val amount:Int) extends AComponent {
  override def size = amount
  override def filter(f:(AComponent=>Boolean)):List[AComponent]={
    if (f(this)) List(this) else List()
  }
}

case class Directory(val name:String) extends AComponent{
  private var children:ListBuffer[AComponent]=new ListBuffer[AComponent]()

  def addChild(child:AComponent): Unit ={
    children+=child
  }
  def removeChild(child:AComponent):Unit={
    children-=child
  }

  override def size ={
    children.map(_.size).sum
  }

  override def filter(f:(AComponent=>Boolean)):List[AComponent]={
    val result = if (f(this)) List(this) else List()
    result ++ children.toList.flatMap(_.filter(f))
  }

}


object Ex6 extends scala.App{
  val root: Directory = new Directory("root")
  val home: Directory = new Directory("home")
  val tap: Directory = new Directory("ftap")
  val f1: File = new File("f1", 1234)
  val f2: File = new File("f2", 3445)
  val f3: File = new File("f3", 6688)
  val f4: File = new File("lp", 99999)
  root.addChild(home)
  root.addChild(f1)
  home.addChild(tap)
  home.addChild(f2)
  tap.addChild(f3)
  root.addChild(f4)

  println(root.filter(f=>f.name.startsWith("f")))



  println("-------------")

  println("Root SIZE:" + root.size)
  println("Home SIZE:" + home.size)
  println("TAP SIZE:" + tap.size)

}

