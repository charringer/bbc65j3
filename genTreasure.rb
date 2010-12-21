#!/usr/bin/env ruby
w=40
h=20
puts (0...h).map{|y|"new int[]{"+(0...w).map{|x|"%2i"%([rand(500)-400,0].max)}.join(",")+"}"}.join(",\n")
