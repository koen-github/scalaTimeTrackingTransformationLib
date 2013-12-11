package com.timeconverting.model

import com.timeconverting.model.Types._
import com.timeconverting.model.Task
import com.timeconverting.model.TimeInterval
import java.util.Date
import java.text.SimpleDateFormat

//INPUT::
case class TimeIntervalAbstract(start:POSIXtime,end:POSIXtime)
{
   val duration =(end-start)/1000L

}
     /*
case class Duration(var durationTime: DurationInMillis){
 var sortOfTime1 = "minutes";
  def apply(sortOfTime: String){

    sortOfTime match{
      case "m"=>  this.durationTime = durationTime*60*1000
      case "h" => this.durationTime = durationTime*60*60*1000
      case "d" =>  this.durationTime = durationTime*60*60*24*1000
      case _ =>  0
    }
    }








}      */
case class Task(intervals: List[TimeIntervalAbstract])

case class TasksTimingData(task:List[Task]) // this object represents all timing information about tasks, and about the settings of the timing representation system. Perhaps better to separate taskstimingdata from timing-systems setting (put it in a different class).
{
}

case class TimingSystemSettings(snapInterval:DurationInMillis, granularity:DurationInMillis)


/*
//OUTPUT::
case class Duration(durationTime: DurationInMillis){ }
       var duratio = 0.0

       def this(sortOfTime:String, durationTime: Long){
          this(sortOfTime)
         this.duratio = sortOfTime match{
           case "m"=> durationTime*60*1000
           case "h" => durationTime*60*60*1000
           case "d" =>  durationTime*60*60*24*1000
           case _ =>  0
         };
       }
     }                                      */




object TasksTimingDataTransformer
{

  def transform(ttdSource: TasksTimingData, ttdTarget: TimingSystemSettings)//:TasksTimingData =
{  //here comes your translation!
  //Here comes my translation!
     //Map(BEGIN->END) TIME Multiply by: 1000L

  val duration  = ttdSource.task.map{x =>
    val TimingsDurations: List[POSIXtime] = x.intervals.map(x => x.duration)
   val thing:List[TimeIntervalAbstract] = x.intervals.map{
      x=>


      if(x.duration/(ttdTarget.granularity) != 1)
       {
         val minutes = Math.round(x.duration/60.0)
         val newDuration = (Math.round(minutes/ttdTarget.granularity)*ttdTarget.granularity)*60*1000L
         val snapMinutes = Math.round(new Date(x.start).getMinutes/ttdTarget.snapInterval)*10
          val almostNewTime = new Date(x.start)
         almostNewTime.setMinutes(snapMinutes) //Lol, I didn't know it was possible to change a val
         val newStartTime =  almostNewTime.getTime
         TimeIntervalAbstract(newStartTime, newStartTime+newDuration)

       }
      else
      {

        TimeIntervalAbstract(x.start,x.end )
      }
   }
  println(TimingsDurations)
  println(thing)
    //TOTAL DURATION:
    val new_thing = TimingsDurations.foldLeft(0)(_.toInt+_.toInt)
    val MiniandHoru=(new_thing/60.0/60.0).toString.split('.')
    val time1DurationHours =MiniandHoru(0)
    val time1DurationMinutes =( ("0."+MiniandHoru(1)).trim).toFloat*60
     println("Hours: "+time1DurationHours)
    println("Minutes: "+time1DurationMinutes)
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


  }     */
          */

}
}
