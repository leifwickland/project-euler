import java.util.{Calendar,GregorianCalendar}

object project19 extends util.Project {
  def description = "How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?"

  def solve(args: Array[String]) {
    val firstOfMonthSundays = for (year <- 1901.to(2000); month <- 0.to(11) if isSunday(year, month, 1)) yield (year, month, 1)
    printf("Found %d first of month Sundays.\n", firstOfMonthSundays.length)
  }

  def isSunday(year: Int, month: Int, day: Int) = Calendar.SUNDAY == new GregorianCalendar(year, month, day).get(Calendar.DAY_OF_WEEK)

  override def test {
    verify(false, isSunday(2011, 3, 1))
    verify(true, isSunday(2011, 4, 1))
    verify(true, isSunday(1899, 0, 1))
    verify(false, isSunday(1900, 0, 1))
    verify(false, isSunday(1901, 0, 1))
  }
}
