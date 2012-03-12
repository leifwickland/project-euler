require 'mathn'
class Integer
  def factorial
    total = self
    n = self
    while n > 1
      total *= (n - 1)
      n -= 1
    end
    total
  end

  def evenly_div_by_all_less_equal_to(x)

    i = 2
    while i < x
      return false if self.modulo(i) != 0 
      i += 1
    end
    return true
  end
end

puts 2520.evenly_div_by_all_less_equal_to(10)


puts 10.factorial
primesLessThan10 = [2,3,5,7]
puts primesLessThan10.inject { |product,i| product * i}
puts 20.factorial
primes = [2, 3, 5, 7, 11, 13, 17, 19]
puts primes.inject { |product,i| product * i}
factors = [ 20, 19, 18, 17, 16, 15, 14, 13, 12, 11 ] 
puts factors.inject { |product,i| product * i}
