# Java Engine: Battle Simulator

The Java half of **Arena Analytics**. It runs **1,000 random battles** between six fighters and saves every result to `results.csv`. The Python side (chart file) reads that file and charts it.

## Files

| File | What it does |
|---|---|
| `Fighter.java` | The blueprint for one fighter: stats plus attack, dodge, and reset behavior |
| `ProjectMain.java` | The driver: builds the fighters, runs the battles, writes the CSV |
| `results.csv` | The output (created when you run the program) |

## The Fighters

| Fighter | HP | Attack | Dodge |
|---|---|---|---|
| 🌵 Cactus | 65 | 5–9 | 10% |
| 🥤 Vending Machine | 85 | 4–8 | 0% |
| 🐉 Dragon | 75 | 5–10 | 5% |
| 😈 Sun Devil | 70 | 4–9 | 15% |
| 🧹 Roomba | 55 | 4–8 | 40% |
| 🌪️ Haboob | 55 | 6–10 | 20% |

## How It Works

**1. `Fighter` holds the stats and the rules.** Each fighter has a name, max HP, current HP, an attack range, and a dodge chance. The fields are `private`, and other code uses getters and methods:

- `rollAttack(rand)`: picks a random damage number between the fighter's min and max attack
- `takeDamage(damage, rand)`: rolls for a dodge; if the fighter doesn't dodge, HP goes down (never below 0)
- `isAlive()`: true while HP is above 0
- `reset()`: restores full HP before each new battle

**2. `ProjectMain` runs the program.** The `main` method:

1. Builds an `ArrayList` of the six fighters
2. Opens `results.csv` and writes the header row
3. Loops 1,000 times: picks two different fighters at random and calls `runBattle`
4. Writes each returned line to the file and prints a "Done!" message

**3. `runBattle` plays one fight.**

1. Reset both fighters to full HP
2. A coin flip decides who attacks first
3. Loop: the attacker rolls damage, the defender takes it, the round counter goes up, and the two swap roles
4. Stop when someone's HP hits 0
5. Return one CSV line: `id,winner,loser,rounds,winner_hp_left`

## Output Format

```
battle_id,winner,loser,rounds,winner_hp_left
1,Roomba,Haboob,20,26
2,Dragon,Cactus,21,2
```

| Column | Meaning |
|---|---|
| `battle_id` | Battle number, 1 to 1,000 |
| `winner` / `loser` | Fighter names |
| `rounds` | How many attacks it took to finish the fight |
| `winner_hp_left` | HP the winner had left (a low number means a close fight) |

⚠️ The Python code depends on these exact column names. If you change them, update `charts.py` too.

## How to Run

<details>
<summary>▶️ In Eclipse (or any Java IDE)</summary>

1. Put `Fighter.java` and `ProjectMain.java` in the same project.
2. Run `ProjectMain.java`.
3. The console prints `Done! Wrote 1000 battles to results.csv`.
4. Refresh the project (F5) to see `results.csv`.

</details>

<details>
<summary>⌨️ From the command line</summary>

```bash
javac Fighter.java ProjectMain.java
java ProjectMain
```

</details>

Rerun the program afterward, since each run **overwrites** `results.csv` with new random battles.

## 🛠️ Java Concepts Used

- Classes and objects (a `Fighter` blueprint and a driver class)
- Encapsulation: private fields with getters
- Constructors and the `this` keyword
- `ArrayList` of objects
- `Random` for damage rolls, dodges, and pairings
- `for` and `while` loops
- Static methods
- File output with `FileWriter` and `try/catch`

## 📚 What We Learned (Java)

- How to share data with another language by agreeing on a file format
- How small bugs hide in plain sight: a misspelled parameter name left every fighter's minimum attack at 0, and a repeated variable in an `if` block meant the "coin flip" never changed who attacked first
