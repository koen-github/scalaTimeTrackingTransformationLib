package org.scatitrali

import scala.xml._

case class XMLAsProject(xmlLocation: String) {

  init()

  def init(): List[Project] = {
    val xmlAsString: String = scala.io.Source.fromFile(xmlLocation).mkString
    val xmlNodes: Node = XML.loadString(xmlAsString)
    val scopedCleared: Node = clearScope(xmlNodes)
    transformToDataModel(scopedCleared)
  }

  private def clearScope(x: Node): Node = x match {
    case e: Elem => e.copy(scope = TopScope, child = e.child.map(clearScope))
    case o => o
  }


  private def transformToDataModel(startNode: Node): List[Project] =
    {
      startNode match {
        case xml.Elem("gtt", "gtt", _, _, _, firstProject, _) => firstProject match { case <gtt:project-list>{ children @ _* }</gtt:project-list> => stripDifferentTasks(children) }
        case _ => Nil
      }
    }

  private def stripDifferentTasks(tasksLists: Seq[Node]): List[Project] =
    {
      tasksLists match {
        case Seq(xml.Elem("gtt", "project", _, _, _, tasksContents @ _*), xs @ _*) =>
          {
          List(Project((tasksContents \\ "title").text, (tasksContents \\ "desc").text, (tasksContents \\ "id").text,
            tasksContents.toList.flatMap {
              m => m match {
                case <gtt:task-list>{ tasklist @ _* }</gtt:task-list> => stripTimes(tasklist)
                case _ => Nil
              }
            })) ::: stripDifferentTasks(xs)
          }
        case Seq(x, xs @ _*) => stripDifferentTasks(xs)
        case Seq() => Nil
      }
    }

  private def stripTimes(possibleTimeIntervals: Seq[Node]): List[TaskList] =
    {
      possibleTimeIntervals match {
        case Seq(xml.Elem("gtt", "task", _, _, _, taskes @ _*), xs @ _*) =>
          List(TaskList((taskes \\ "memo").text, (taskes \\ "guid").text,
            taskes.toList.flatMap {
              x => x match {
                  case <gtt:interval-list>{ timeIntervals @ _* }</gtt:interval-list> => stripStartStop(timeIntervals)
                  case _ => Nil
                }
            })) ::: stripTimes(xs)
        case Seq(x, xs @ _*) => stripTimes(xs)
        case Seq() => Nil
      }
    }

  private def stripStartStop(nodesOfTimes: Seq[Node]): List[TimeInterval] =
    {
      nodesOfTimes match {
        case Seq(Elem("gtt", "interval", _, _, _, st @ _*), xs @ _*) => List(TimeInterval((st \\ "start").text.toLong, (st \\ "stop").text.toLong)) ::: stripStartStop(xs)
        case Seq(x, xs @ _*) => stripStartStop(xs)
        case Seq() => Nil
      }
    }
}
