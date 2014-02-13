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
