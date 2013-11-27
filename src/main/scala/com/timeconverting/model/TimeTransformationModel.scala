package com.timeconverting.model
  //todo: Make a good model
case class TimeTransformationModel(TaskName: String, TimeStart: Types.POSIXtime, TimeStop: Types.POSIXtime) {}


type UNIXtimeInMillis = Long // type alias, just to make clear what you mean with this Long. This is not needed, but it will make your code much more readable, without having to include comments!
type UNIXtimeIntervalInMillis = Long

case class TimeInterval(start:UNIXtimeInMillis, end:UNIXtimeInMillis)

case class Task(intervals: List[TimeInterval])

class TasksTimingData(task:List[Task], snapInterval:UNIXtimeIntervalInMillis, granularity: UNIXtimeIntervalInMillis) // this object represents all timing information about tasks, and about the settings of the timing representation system. Perhaps better to separate taskstimingdata from timing-systems setting (put it in a different class).
{
}

object TasksTimingDataTransformer
{   def transform(ttdSource: TasksTimingData, ttdTarget: TasksTimingData):TasksTimingData =
{  //here comes your translation!
}
}
