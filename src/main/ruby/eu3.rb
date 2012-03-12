require 'mathn'

class Integer
  def prime_factors
    factors = []
    root = Math.sqrt(self)
    Prime.new.each do |prime|
      break if prime > root
      factors.push(prime) if self.modulo(prime) === 0
    end
    factors
  end
end

#puts 13195.prime_factors
puts 600851475143.prime_factors[-1]
