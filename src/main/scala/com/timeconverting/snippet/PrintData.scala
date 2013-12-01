package com.timeconverting
package snippet

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import com.timeconverting.lib._
import Helpers._
import com.scala.gnotime.read_xml
import com.timeconverting.model.{TasksTimingDataTransformer, TimeIntervalAbstract, Task, TasksTimingData}

class Printing {

  val test = read_xml.proc
  def data =
  {
   println(test)
    //in case of taskjuggler
    val times = Map(1385323207*1000L -> 1385325007*1000L,1385328607*1000L -> 1385330407*1000L,  1385319607*1000L -> 1385332207*1000L, 1387881307*1000L ->  1387882807*1000L)
   val input_data = TasksTimingData(
      List(
        Task(
          times.toList.map(x=> TimeIntervalAbstract(x._1, x._2))
        )),
     1, 60
    ) //wait, This is the transform function so this makes no sense. This must be input for the current function.
     //snapinterval: 1=align with full hours
    //granularity: 60=time parts must be 1 hour, 1 hour is 60 minutes
   println( TasksTimingDataTransformer.transform(input_data, "SpecificTarget"))
    "#list *" #> test.map{
      x=>


    "#title *" #> x.title &
     "#projectdesc *" #> x.projectDesc &
      "#another *" #> x.tasks.map{ s =>
    "#memo *" #> s.memo &
    "#guid *" #> s.guid &
   "#times *" #> s.timeIntervals2.map { a=>
   "#start *" #>  a.start  &
  "#stop *" #> a.stop

   }
      }
     }
  }
  //};
}