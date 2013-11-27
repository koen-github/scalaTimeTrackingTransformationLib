package com.timeconverting.pros

import java.util.Date
import com.timeconverting.model.Types._
import java.text.SimpleDateFormat

object Trans2Time {
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
