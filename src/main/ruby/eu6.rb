require 'mathn'
i = 0
Prime.new.each {|j|
  i+= 1
  puts "#{i}: #{j}"
  if (i == 10001)
    exit
  end
}

