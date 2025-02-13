package o1.adventure

/** The class `Action` represents actions that a player may take in a text adventure game.
  * `Action` objects are constructed on the basis of textual commands and are, in effect,
  * parsers for such commands. An action object is immutable after creation.
  * @param input  a textual in-game command such as “go east” or “rest” */
class Action(input: String):

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim

  /** Causes the given player to take the action represented by this object, assuming
    * that the command was understood. Returns a description of what happened as a result
    * of the action (such as “You go west.”). The description is returned in an `Option`
    * wrapper; if the command was not recognized, `None` is returned. */
  def execute(actor: Player) = this.verb match
    case "go"        => Some(actor.go(this.modifiers))
    case "rest"      => Some(actor.rest())
    case "help"     => Some("Winning the Game:\nTo win, you need to collect three items: a phone, keys, and a gas tank, and then escape in the car at the parking area. \nCommands:\n\ngo: Use this command followed by a direction (north, south, east, west) to move between areas.\nrest: Allows your character to rest, useful for passing time or regaining strength.\nquit: Exit the game.\ninventory: Check the items you are currently carrying.\nget: Use this to pick up items you find.\ndrop: If you need to free up space or leave an item behind, use this command.\nsolve: This command is particularly useful in the workshed to solve the puzzle and access the gas tank.")
    case "quit"      => Some(actor.quit())
    case "inventory" => Some(actor.inventory)
    case "get"       =>
      val cuurentLocation = actor.location
      cuurentLocation.getPuzzle match
        case Some(puzzle) if !puzzle.isSolved =>
          Some("You need to solve the puzzle first.")
        case _ => Some(actor.get(this.modifiers))
    case "drop"      => Some(actor.drop(this.modifiers))
    case "examine"   => Some(actor.examine(this.modifiers))
    case "solve"      =>
      val currentPuzzle = actor.location.getPuzzle
      currentPuzzle match
      case Some(puzzle) => Some(actor.solvePuzzle(this.modifiers, puzzle))
      case None         => Some("There is no puzzle to solve here.")

    case other       => None

  /** Returns a textual description of the action object, for debugging purposes. */
  override def toString = s"$verb (modifiers: $modifiers)"

end Action

