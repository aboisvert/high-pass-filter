#!/bin/sh
series=$1
alpha=$2
scala HighPassFilter.scala $series $alpha
gnuplot <<END
  plot [0:500] "$series" using 1 title "original" smooth csplines, \
    "$series.out" using 1 title "high pass filter" smooth csplines
  pause 30
END

