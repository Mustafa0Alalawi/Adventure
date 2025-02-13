package o1.adventure

/** The class `Adventure` represents text adventure games. An adventure consists of a player and
  * a number of areas that make up the game world. It provides methods for playing the game one
  * turn at a time and for checking the state of the game.
  *
  * N.B. This version of the class has a lot of “hard-coded” information that pertains to a very
  * specific adventure game that involves a small trip through a twisted forest. All newly created
  * instances of class `Adventure` are identical to each other. To create other kinds of adventure
  * games, you will need to modify or replace the source code of this class. */
class Adventure:

  /** the name of the game */
  val title = "A evildead Adventure"

  private val middle      = Area("Forest", "The forest looms around you, its trees like gnarled hands reaching towards a moonless sky. The wind whispers through the leaves, carrying with it a faint, unidentifiable wail. Every step you take seems to be watched by unseen eyes.")
  private val northForest = Area("Forest", "The air grows colder here, and the trees seem to close in around you. Shadows flit just beyond your line of sight, and the ground is littered with strange, rune-like markings that you dare not touch. An unsettling laugh echoes in the distance")
  private val southForest = Area("Forest", "An oppressive silence hangs over this part of the forest. The ground is soft, almost squelchy underfoot, and you occasionally hear the sound of something large moving in the underbrush. The trees here are old, their bark twisted into grotesque shapes.")
  private val clearing    = Area("Forest Clearing", "In this small clearing, the sky is visible, but it brings no comfort – the stars are wrong, unfamiliar. The paths leading out seem to shift when not looked at directly. An ancient, crumbling statue stands here, its features eroded but its eyes unsettlingly lifelike.")
  private val tangle      = Area("Tangle of Bushes", "The bushes are thick and thorny, snagging at your clothes and skin. You hear something rustling nearby, too large to be a small animal. Occasionally, a sharp, metallic scent wafts through the air, but its source remains unseen.")
  private val parking        = Area("Parking", "The car sits forlornly under a flickering streetlight, the only island of light in a sea of darkness. The ground is littered with old, rusted parts and the shadows seem to move just at the edge of the light.")
  private val workshed  = Area("Workshed", "Solve the puzzle to pick the gas tank.")
  private val hall = Area("hall", "This hall is long and narrow, the wallpaper peeling off to reveal something dark beneath. The light bulbs flicker intermittently, casting bizarre shadows. You feel as if something is patiently waiting, just out of sight")
  private val bedroom = Area("Bedroom", "The room is cold, far colder than it should be. The bed is unmade, the sheets stained with something dark. The window is open, but no wind enters. Instead, there's a low, monotonous chanting coming from outside.")
  private val secondBedroom = Area("secound bedroom", "Pick up the keys quickly before the devil notes you!")
  private val livingroom  = Area("Livingroom", "The living room is strangely pristine, in stark contrast to the rest of the house. The furniture is antique, and the portraits on the wall seem to follow you with their eyes. A grandfather clock ticks loudly, its sound unnaturally clear.")
  private val destination = parking


  middle     .setNeighbors(Vector("north" -> northForest, "east" -> tangle, "south" -> southForest, "west" -> clearing   ))
  northForest.setNeighbors(Vector(                        "east" -> tangle, "south" -> middle,      "west" -> clearing   ))
  southForest.setNeighbors(Vector("north" -> middle,      "east" -> tangle, "south" -> hall, "west" -> clearing   ))
  clearing   .setNeighbors(Vector("north" -> northForest, "east" -> middle, "south" -> southForest, "west" -> northForest))
  tangle     .setNeighbors(Vector("north" -> northForest, "east" -> parking,   "south" -> southForest, "west" -> northForest))
  workshed .setNeighbors(Vector("north" -> livingroom))
  livingroom.setNeighbors(Vector("north" -> hall, "west" -> secondBedroom, "south" -> workshed))
  hall.setNeighbors(Vector("west" -> secondBedroom, "northwest" -> bedroom, "north" -> southForest, "south" -> livingroom))
  bedroom.setNeighbors(Vector("south" -> secondBedroom, "east" -> hall))
  secondBedroom.setNeighbors(Vector("north" -> bedroom, "east" -> hall))
  parking       .setNeighbors(Vector(                                            "west" -> tangle     ))


  hall.addItem(Item("phone", "You might need that to call the cops."))
  secondBedroom.addItem(Item("keys", "Car won't start without gas"))
  workshed.addItem(Item("gas tank", "Car won't start without the keys."))

  /** The character who is the protagonist of the adventure and whom the real-life player controls. */
  val player = Player(middle)

  /** The number of turns that have passed since the start of the game. */
  var turnCount = 0
  /** The maximum number of turns that this adventure game allows before time runs out. */
  val timeLimit = 40

  val lockedDoorPuzzle =
    Puzzle("The door is locked. It seems to require a code.\nCan you open the door using these clues. \n682: One digit is right and in its place. \n614: One digit is right but in the wrong place. \n206: Two digits are right, but both are in the wrong place. \n738: All digits are wrong. \n380: One digit is right but in the wrong place. \nNotes! use in command 'solve' then the code. ", "042")
  workshed.setPuzzle(lockedDoorPuzzle)


  /** Determines if the adventure is complete, that is, if the player has won. */
  def isComplete =
    this.player.location == this.destination && this.player.has("phone") && this.player.has("keys") && this.player.has("gas")

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver = this.isComplete || this.player.hasQuit || this.turnCount == this.timeLimit || this.player.location == this.bedroom


  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage = "Welcome to the shadows of 'A Evildead Adventure'. Your journey begins at the edge of an ominous forest, where every step could be your last. Beware, for the darkness holds secrets, and not all who wander here find their way back. Do you dare to uncover the mysteries that lurk within? The path ahead is fraught with peril, and time is not on your side. Enter if you dare, but remember, some doors, once opened, can never be closed again."

  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage =
    if this.isComplete then
      "As you speed away in the car, your heart still racing, you can't shake off the chilling feeling that something from the forest is following you. Every shadow seems to move, every noise makes you jump. Even as the forest fades into the distance, you feel a haunting presence lingering, as if the terror you escaped is never far behind."
    else if this.turnCount == this.timeLimit then
      "Time slips away like shadows at night, and with it, your last chance of escape. The air grows colder, the darkness deeper. You hear a sinister whisper behind you - it's too late. The devil has found you. Everything fades to black"
    else
      "You're dead!"


  /** Plays a turn by executing the given in-game command, such as “go west”. Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String) =
    val action = Action(command)
    val outcomeReport = action.execute(this.player)
    if outcomeReport.isDefined then
      this.turnCount += 1
    outcomeReport.getOrElse(s"""Unknown command: "$command".""")

end Adventure

