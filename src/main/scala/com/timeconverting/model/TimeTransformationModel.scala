package com.timeconverting.model

import com.timeconverting.model.Types._
import com.timeconverting.model.Task
import com.timeconverting.model.TimeInterval
import java.util.Date
import java.text.SimpleDateFormat


case class TimeIntervalAbstract(start:POSIXtime,end:POSIXtime) {
  /*
  def this(sortOfTime:String) = {

    sortOfTime match{
      case "m"=>  this.start = start*60*1000; this.end = end*60*1000
      case "h" => this.start = start*60*60*1000; this.end = end*60*60*1000
      case "d" => this.start = start*60*60*24*1000; this.end = end*60*60*24*1000
      case _ =>  0

    }

  }  *///todo: work out time assignment
}

case class Task(intervals: List[TimeIntervalAbstract])

case class TasksTimingData(task:List[Task], snapInterval:POSIXtime, granularity: POSIXtime) // this object represents all timing information about tasks, and about the settings of the timing representation system. Perhaps better to separate taskstimingdata from timing-systems setting (put it in a different class).
{
}

object TasksTimingDataTransformer
{

  def transform(ttdSource: TasksTimingData, ttdTarget: String):TasksTimingData =
{  //here comes your translation!
  //Here comes my translation!
     //Map(BEGIN->END) TIME Multiply by: 1000L
  /*
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
   */
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


  }     */


}
}
