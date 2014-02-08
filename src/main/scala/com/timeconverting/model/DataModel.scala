package com.timeconverting.model

object Types {
  type POSIXtime = Long // POSIXtime: see http://en.wikipedia.org/wiki/Unix_time
  type DurationInMillis = Long

}

import Types._

case class Project(title: String, // in timeconverting.xml: <title>
    projectDesc: String,
    id: String, // in timeconverting.xml: <id>
    tasks: List[TaskList] // in timeconverting.xml: <project-list>
    ) {
}

case class TaskList(memo: String, // in timeconverting.xml: <id>
    guid: String, // in timeconverting.xml: <title>
    timeIntervals2: List[TimeInterval] // in timeconverting.xml: <task-list> (not certain yet, better verify) list of time intervals on which this project was active tracked on TOP level (so excluding time intervals occupied by the subprojects).
    ) {
}

case class TimeInterval(start: POSIXtime, stop: POSIXtime) {
}