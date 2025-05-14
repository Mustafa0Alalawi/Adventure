# 👹 Evildead Adventure

Welcome to **Evildead Adventure**, a text-based horror exploration game inspired by the 2013 film *Evil Dead*. Traverse a haunted forest, investigate an eerie cabin, and uncover dark secrets as you race against time to escape with your life.


<img width="1048" alt="Screenshot 2025-05-14 at 14 26 31" src="https://github.com/user-attachments/assets/43b9c64b-06f9-4b6e-9610-4842935febce" />

---

## 🧭 Game Overview

- 🧱 Genre: Text Adventure / Puzzle
- 🎬 Theme: Inspired by *Evil Dead (2013)*
- 🎮 Goal: Explore, solve puzzles, and escape with your life

In this immersive narrative game, you awaken at the edge of a cursed forest. You must navigate the terrifying landscape, collect crucial items, solve an ominous puzzle, and avoid deadly traps. One wrong move, and it's all over.

---

## 🗺️ Map

Refer to the in-game world map (`evildead.gif`) for layout and room references.

<img width="571" alt="Screenshot 2025-05-14 at 14 27 04" src="https://github.com/user-attachments/assets/13163967-fc83-4a49-be24-e9848eb81113" />


---

## 🕹️ How to Play

- Use directional commands to move (e.g., `go north`, `go east`)
- Type `solve` followed by a code to attempt puzzle solutions
- Use `pick up [item]` to collect objects
- Use `inventory` to check what you're carrying
- Type `help` for available commands

---

## 🎯 Objective

To **win**, you must collect three key items:

| Item        | Location         |
|-------------|------------------|
| 📱 Phone     | Hall             |
| 🗝️ Keys      | Second Bedroom   |
| ⛽ Gas Tank  | Workshed (locked, requires a code) |

> 🧩 The **Workshed** is locked — you must either solve a puzzle or use:
> 
> ```text
> solve 042
> ```

Once you've collected all three items, proceed to the **Parking** area to escape.

---

## ⚠️ Danger Zones

🚫 **Bedroom**: Do **not** enter — the **devil** lurks there, and doing so ends the game instantly.

---

## Getting Started

### Requirements

- [Scala](https://www.scala-lang.org/)
- [SBT](https://www.scala-sbt.org/)

### Run the Game

```bash
git clone https://github.com/Mustafa0Alalawi/Adventure.git
cd Adventure
sbt run
