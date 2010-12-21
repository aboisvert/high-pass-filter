
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

// Retain only drops (negative first derivative)
// and remove noise (beta amplitude filter)
def filterDrops(x: Array[Float], beta: Float) = {
  val y = new Array[Float](x.length)
  y(0) = 0
  for (i <- 1 until x.length) {
    y(i) = if (x(i) < 0.0f && x(i) < -beta && x(i) < x(i-1)) x(i) else 0.0f
  }
  y
}

val alpha = args(0).toFloat
val beta = args(1).toFloat

val series = scala.io.Source.stdin.getLines map (_.toFloat) toArray
val filtered = highPassFilter(series, alpha)
val drops = filterDrops(filtered, beta)

drops foreach (println(_: Float))
