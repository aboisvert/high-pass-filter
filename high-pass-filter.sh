#!/bin/sh

series=$1
alpha=$2
cutoff=$3

scala HighPassFilter.scala $alpha $cutoff < $series > filtered.out

gnuplot <<END
  plot [0:600] "$series" using 1 title "original" with lines, \
    "filtered.out" using 1 title "high pass filter" with lines

  set terminal push
  set terminal svg
  set output "filtered.svg"
  replot
  set output
  set terminal pop

  pause 30
END

