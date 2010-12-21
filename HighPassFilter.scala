
import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import java.io._

def readSeries(filename: String) = {
  Source.fromFile(filename).getLines map { _.toFloat } toArray
}

// See Wikipedia article "High-pass filter"
// http://en.wikipedia.org/wiki/High-pass_filter
def highPassFilter(x: IndexedSeq[Float], alpha: Float) = {
  val y = new Array[Float](x.length)
  y(0) = x(0)
  for (i <- 1 until x.length) {
    y(i) = alpha * (y(i-1) + x(i) - x(i-1))
  }
  y
}

def writeSeries(filename: String, points: Array[Float]) {
  val out = new PrintStream(new FileOutputStream(filename))
  try {
    points foreach { out.println(_) }
  } finally {
    out.close()
  }
}

val filename = args(0)
val alpha = args(1).toFloat

val points = readSeries(filename)
val filtered = highPassFilter(points, alpha)

writeSeries(filename + ".out", filtered)

