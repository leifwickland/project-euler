sum = 0
(1...1000).each do |i| 
  if (i.modulo(5) === 0 or i.modulo(3) === 0)
    sum = sum + i
  end
end
puts sum
