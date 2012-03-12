def fib_up_to(max)
  i1,i2 = 1,2
  while i1 < max
    yield i1
    i1, i2=i2, i1+i2
  end
end


sum = 0
fib_up_to(4000000) do |i|
  puts i
  sum = sum + i if i.modulo(2) === 0 
end
puts ''
puts sum


