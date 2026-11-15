# sunhacks 2026

# Battle Simulator: Java Engine

The Java half of our SunHacks 2026 project runs **1,000 random battles** between silly fighters and saves every result to `results.csv`. The Python half reads that file and turns it into charts.

## 🎮 What It Does

Six fighters are paired off at random. They take turns attacking until one runs out of HP.

| Fighter | HP | Attack | Dodge |
|---|---|---|---|
| 🌵 Cactus | 65 | 5–9 | 10% |
| 🥤 Vending Machine | 85 | 4–8 | 0% |
| 🐉 Dragon | 75 | 5–10 | 5% |
| 😈 Sun Devil | 70 | 4–9 | 15% |
| 🧹 Roomba | 55 | 4–8 | 40% |
| 🌪️ Haboob | 55 | 6–10 | 20% |

Every battle is randomized: who attacks first, how much damage each hit does, and whether a hit gets dodged.

## 🧩 How the Code Is Organized

- **`Fighter.java`**: a class that holds one fighter's stats and behavior (`rollAttack`, `takeDamage`, `reset`, `isAlive`)
- **`ProjectMain.java`**: the driver. It builds the fighters, runs the battles in a loop, and writes the CSV file

## 📄 Output Format

Each battle becomes one line in `results.csv`:

```
battle_id,winner,loser,rounds,winner_hp_left
1,Roomba,Haboob,20,26
2,Dragon,Cactus,21,2
```

This file is the only thing our Java and Python code share, so both sides agree on these column names.

## 🚀 How to Run

<details>
<summary>▶️ Run it in Eclipse (or any Java IDE)</summary>

1. Put `Fighter.java` and `ProjectMain.java` in the same project.
2. Run `ProjectMain.java`.
3. The console prints `Done! Wrote 1000 battles to results.csv`.
4. Refresh the project to see `results.csv`.

</details>

<details>
<summary>⌨️ Run it from the command line</summary>

```bash
javac Fighter.java ProjectMain.java
java ProjectMain
```

</details>

Then run `python charts.py` in the `storyteller` folder to see the charts.

## 📊 Example Result

Results change every run because the battles are random. In one run, the Dragon won about 77% of its fights, the Roomba about 56% (its 40% dodge chance makes up for low attack), and the Vending Machine about 34%.

## 🛠️ Concepts Used

- Classes and objects (a `Fighter` blueprint and a driver class)
- Encapsulation (private fields with getters)
- `ArrayList` of objects
- `Random` for damage rolls, dodges, and pairings
- `while` and `for` loops
- File output with `FileWriter` and `try/catch`
- Static methods

## 📚 What We Learned

- How to split one project between two languages by agreeing on a shared file format
- How to transfer content between Eclipse and Visual Studio (different IDEs)
- How to track down small bugs like typos in variable names and a swapped variable in an `if` block
- How to use GitHub so two people can work in the same repo without overwriting each other
