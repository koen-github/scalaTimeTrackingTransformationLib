package com.timeconverting.model

import com.timeconverting.model.Types._
import com.timeconverting.model.Task
import com.timeconverting.model.TimeInterval
import java.util.Date
import java.text.SimpleDateFormat


case class TimeIntervalAbstract(start:Types.UNIXtimeInMillis, end:Types.UNIXtimeInMillis)

case class Task(intervals: List[TimeIntervalAbstract])

case class TasksTimingData(task:List[Task], snapInterval:Types.UNIXtimeIntervalInMillis, granularity: Types.UNIXtimeIntervalInMillis) // this object represents all timing information about tasks, and about the settings of the timing representation system. Perhaps better to separate taskstimingdata from timing-systems setting (put it in a different class).
{
}

object TasksTimingDataTransformer
{

  def transform(ttdSource: TasksTimingData, ttdTarget: TasksTimingData):TasksTimingData =
{  //here comes your translation!
  //Here comes my translation!
     //Map(BEGIN->END) TIME Multiple by: 1000L
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

  TasksTimingData(List(Task(List(TimeIntervalAbstract(1385323207*1000L,1385325007*1000L)))),1385328607*1000L, 1385330407*1000L)  //test and not correct!!!
}
}
