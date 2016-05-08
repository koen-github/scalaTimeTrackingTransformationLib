import org.scatitrali.XMLAsProject

object ConvertGnotimeToTaskjuggler {
  def main(args: Array[String]): Unit = {
    println("=====================================================")
    println("===================ScaTiTralI========================")
    println("This will convert the following file into an taskjuggler project: example1.xml")
    val pathName: String = "src/main/resources/example1.xml"
    val convertedProject: XMLAsProject = XMLAsProject(pathName)
    println("=====================================================")
    println(convertedProject)

  }
}

/***

OLD-CODE:

package com.gnotime.pros

import java.util.Date
import com.gnotime.model.Types._
import java.text.SimpleDateFormat

object Transform2TJ {
  //val times is from ONE TASK INSIDE A PROJECT
  val times: Map[POSIXtime, POSIXtime] = Map(1385323207*1000L -> 1385325007*1000L,1385328607*1000L -> 1385330407*1000L,  1385319607*1000L -> 1385332207*1000L, 1387881307*1000L ->  1387882807*1000L)
 val duration  = times.map{x =>
  val thing:POSIXtime = (x._2 - x._1)
  val Duration = new Date(thing);

  val time1DurationHours = Duration.getHours -1 ;
  val time1DurationMinutes = Duration.getMinutes

  time1DurationHours*60 + time1DurationMinutes

}
 val durationInMinutes = duration.foldLeft(0)(_ + _)
 val durationInHours = Math.round(durationInMinutes/60f) // do we need to round it to one full hour?
  val startDateHour = new SimpleDateFormat("yyyy-MM-dd-HH:00").format(new Date(times.head._1))   //taskjuggler start Date and Round Hour
   //taskjuggler: booking task like this:
  //2013-11-24-21:00 +durationInHours + "h"
  //example: booking test 2013-11-24-21:00 +5h
}




ANOTHER OLD CODE:


package com.timeconverting.model

import com.timeconverting.model.Types._
import com.timeconverting.model.Task
import com.timeconverting.model.TimeInterval
import java.util.Date
import java.text.SimpleDateFormat

//INPUT::
case class TimeIntervalAbstract(start: POSIXtime, end: POSIXtime) {
  val duration: Long = (end - start)

}

object TimeIntervalAbstract {
  def apply(start: POSIXtime, end: POSIXtime, sortOfTime: String): TimeIntervalAbstract =
    {
      sortOfTime match {
        case "m" ⇒ new TimeIntervalAbstract(start * 60 * 1000, end * 60 * 1000)
        case "h" ⇒ new TimeIntervalAbstract(start * 60 * 60 * 1000, end * 60 * 60 * 1000)
        case "d" ⇒ new TimeIntervalAbstract(start * 60 * 60 * 24 * 1000, end * 60 * 60 * 24 * 1000)
        case _ ⇒ new TimeIntervalAbstract(0, 0)

      }

    }

}
case class Task(intervals: List[TimeIntervalAbstract])

case class TasksTimingData(task: List[Task]) // this object represents all timing information about tasks, and about the settings of the timing representation system. Perhaps better to separate taskstimingdata from timing-systems setting (put it in a different class).
{
}

case class TimingSystemSettings(snapInterval: DurationInMillis, granularity: DurationInMillis)

object TasksTimingDataTransformer {
  def transform(ttdSource: TasksTimingData, ttdTarget: TimingSystemSettings) //:TasksTimingData =
  { //here comes your translation!
    //Here comes my translation!
    //Map(BEGIN->END) TIME Multiply by: 1000L

    val duration = ttdSource.task.map { interVals ⇒
      val TimingsDurations: List[POSIXtime] = interVals.intervals.map(_.duration)
      val thing: List[TimeIntervalAbstract] = interVals.intervals.map { times ⇒
        //  println(x.duration.toDouble/ttdTarget.granularity.toDouble)
        /*
        val newDuration = Math.round(times.duration / ttdTarget.granularity.toDouble) * ttdTarget.granularity
        //val snapMinutes = Math.round(new Date(x.start).getMinutes/ttdTarget.snapInterval)*10
        val endie = Math.round(times.end - (ttdTarget.granularity.toDouble / 2))
        val startie = endie - newDuration
        TimeIntervalAbstract(startie, endie)


        val newDivider = (if (times.duration < ttdTarget.granularity) {
          val test = (ttdTarget.granularity - (times.duration % ttdTarget.granularity)) + times.duration
          times.duration + test
        } else {
          if ((ttdTarget.granularity / 2) <= (times.duration % ttdTarget.granularity)) {

            times.duration + (times.duration % ttdTarget.granularity)
          } else {
            times.duration - (times.duration % ttdTarget.granularity)
          }
        })

        val newDuration2 = (newDivider / ttdTarget.granularity) * ttdTarget.granularity
        //val snapMinutes = Math.round(new Date(x.start).getMinutes/ttdTarget.snapInterval)*10
        val endie2 = times.end - (ttdTarget.granularity / 2)

        val startie2 = endie2 - newDuration2

        TimeIntervalAbstract(startie2, endie2)

      }
      println(TimingsDurations)
      println(thing)
      //TOTAL DURATION:
      // val new_thing = TimingsDurations.foldLeft(0)(_.toInt+_.toInt)
      // val MiniandHoru=(new_thing/60.0/60.0).toString.split('.')
      // val time1DurationHours =MiniandHoru(0)
      //  val time1DurationMinutes =( ("0."+MiniandHoru(1)).trim).toFloat*60
      //   println("Hours: "+time1DurationHours)
      //  println("Minutes: "+time1DurationMinutes)
    }
    //println(duration);
    /*
  val durationInMinutes = duration.foldLeft(0)(_ + _)
  val durationInHours = Math.round(durationInMinutes/60f) // do we need to round it to one full hour?
val startDateHour = new SimpleDateFormat("yyyy-MM-dd-HH:00").format(new Date(times.head._1))
   val snapInterval = new Date(times.head._1).getHours//taskjuggler start Date and Round Hour
  //taskjuggler: booking task like this:
  //2013-11-24-21:00 +durationInHours + "h"
  //example: booking test 2013-11-24-21:00 +5h
  TasksTimingData(
    List(
      Task(
        times.toList.map(x=> TimeIntervalAbstract(x._1, x._2))
          )),
    snapInterval, durationInHours
  ) //wait, This is the transform function so this makes no sense. This must be input for the current function.
  /*
  ttdTarget match {
    case "SpecificTarget" =>  val duration  = ttdSource.task.map{x =>
      val thing:List[POSIXtime] = x.intervals.map{
        x=>
          x.end-x.start.toLong
      }
    val new_thing = thing.foldLeft(0)(_.toInt+_.toInt)
      val Duration = new Date(new_thing);
      val time1DurationHours = Duration.getHours -1 ;
      val time1DurationMinutes = Duration.getMinutes
      time1DurationHours*60 + time1DurationMinutes
    }
    case _ => TasksTimingData(List(Task(List(TimeIntervalAbstract(0,0)))),0,0)
  }


  }
}







package com.timeconverting
package snippet

import scala.xml.{ NodeSeq, Text }
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import com.timeconverting.lib._
import Helpers._
import com.scala.gnotime.read_xml
import com.timeconverting.model._
import com.timeconverting.model.TimeIntervalAbstract
import com.timeconverting.model.TasksTimingData
import com.timeconverting.model.Task

class Printing {

  val test = read_xml.proc
  def data =
    {
      println(test)
      //in case of taskjuggler
      //SCENARIO 1:       (Lots of roundy times)
      // val times = Map(1385323207*1000L -> 1385325007*1000L,1385328607*1000L -> 1385330407*1000L,  1385319607*1000L -> 1385332207*1000L, 1387881307*1000L ->  1387882807*1000L)
      //SCEARIO 2: (half and uneven times)
      val times =
        Map(
          1386512266 * 1000L -> 1386515866 * 1000L,
          1386530026 * 1000L -> 1386533146 * 1000L,
          1386536686 * 1000L -> 1386538726 * 1000L,
          1386531946 * 1000L -> 1386534766 * 1000L)
      val input_data_old = TasksTimingData(
        List(
          Task(
            times.toList.map(utn ⇒ TimeIntervalAbstract(utn._1, utn._2)))))

      val input_data = TasksTimingData(List(
        Task(List(

          TimeIntervalAbstract(0, 3),
          TimeIntervalAbstract(14, 18),
          TimeIntervalAbstract(21, 29)))))
      //wait, This is the transform function so this makes no sense. This must be input for the current function.
      //snapinterval: 1=align with full hours
      //granularity: 60=time parts must be 1 hour, 1 hour is 60 minutes
      println(TasksTimingDataTransformer.transform(input_data, TimingSystemSettings(60, 5)))

      "#list *" #> test.map {
        oihtml ⇒

          "#title *" #> oihtml.title &
            "#projectdesc *" #> oihtml.projectDesc &
            "#another *" #> oihtml.tasks.map { inHtml ⇒
              "#memo *" #> inHtml.memo &
                "#guid *" #> inHtml.guid &
                "#times *" #> inHtml.timeIntervals2.map { times ⇒
                  "#start *" #> times.start &
                    "#stop *" #> times.stop

                }
            }
      }
    }
  //};
  /*
  BEFORE:
/INPUT:
 Map(
1385323207*1000L -> 1385325007*1000L,
1385328607*1000L -> 1385330407*1000L,
1385319607*1000L -> 1385332207*1000L,
1387881307*1000L ->  1387882807*1000L
)
//GRANULARITY 25
List(
TimeIntervalAbstract(1385323207000,1385324707000),
TimeIntervalAbstract(1385328607000,1385330107000),
TimeIntervalAbstract(1385319607000,1385331607000),
TimeIntervalAbstract(1387881307000,1387882807000
))
//GRANULARITY 15
List(
TimeIntervalAbstract(1385323207000,1385325007000),
TimeIntervalAbstract(1385328607000,1385330407000),
TimeIntervalAbstract(1385319607000,1385332207000),
TimeIntervalAbstract(1387881307000,1387882207000))
/NEXT SCENARIO:
 Map(
1386512266*1000L -> 1386515866*1000L,
1386530026*1000L -> 1386533146*1000L,
1386536686*1000L -> 1386538726*1000L,
)
   */
}

*/
