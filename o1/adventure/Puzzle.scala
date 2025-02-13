package o1.adventure


class Puzzle(val description: String, val solution: String):
  var isSolved = false

  def solve(attempt: String): Boolean =
    if this.solution == attempt then
      this.isSolved = true
      true
    else
      false


end Puzzle
