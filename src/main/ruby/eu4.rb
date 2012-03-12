class String
  def palindrome?
    self === self.reverse
  end
end

palindromes = []
(100..999).each do |x|
  (100..x).each do |y|
    z = x * y
    if z.to_s.palindrome?
      palindromes.push(z)
    end
  end
end
puts palindromes.sort[-1]
