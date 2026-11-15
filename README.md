# sunhacks 2026

# ⚔️ Arena Analytics

**A battle simulator with a built-in balance report: Java runs 1,000 fights, Python charts the winners.**

Built for **SunHacks 2026** (Rookie category).

<!-- Add a chart screenshot here once charts/win_rate.png is committed: -->
<!-- ![Win rate per fighter](storyteller/charts/win_rate.png) -->

## 👥 Team
- **[Rory Lendzion]**: Java battle engine (`engine/`)
- **[Armita Simchi]**: Python analysis and charts (`charts/`)

## Inspiration
Every game with more than one character runs into the same argument: is it actually fair? Players debate it endlessly, but opinions aren't evidence, so we set out to answer the question with data instead of guesswork. The result is Arena Analytics, a battle simulator that lets fighters face each other hundreds of times and then tells us who really comes out on top.

## What It Does
Arena Analytics is a battle simulator with a built-in balance report. A Java engine runs automated fights between six different fighters and logs every result: the winner, the loser, how many rounds it lasted, and how much HP the winner had left. A Python analysis tool reads that log and generates three charts:

1. **Win rate per fighter**, with a 50% reference line to show who is above or below par
2. **Battle length**, a histogram of how many rounds fights last
3. **Dominance**, the average HP the winner has left, which shows who wins comfortably and who barely scrapes by

## How We Built It
The project has two parts that talk to each other through a single CSV file.

```
 ☕ Java engine  ──►  results.csv  ──►  🐍 Python analysis  ──►  📊 3 charts
 (engine/)          (one row per       (charts/charts.py)
                     battle)
```

- **Java** runs the fights and logs one row per battle: `battle_id,winner,loser,rounds,winner_hp_left`.
- **Python** reads that log, using **pandas** to count wins and losses and **matplotlib** to draw the charts.

The key statistic is the win rate:

> **win rate = W / (W + L) × 100**, where W is the number of wins and L is the number of losses.

In a perfectly balanced game every fighter would sit near 50%, so big deviations flag the characters that need rebalancing.

## What We Found
In our run of 1,000 battles between six fighters, the game was **not balanced**:

| Fighter | Win rate |
|---|---|
| 🐉 Dragon | 67.5% (won 222 of 329 battles) |
| 😈 Sun Devil | 60.6% |
| 🧹 Roomba | 56.9% |
| 🌪️ Haboob | 46.9% |
| 🥤 Vending Machine | 38.1% |
| 🌵 Cactus | 30.6% (won 95 of 310 battles) |

That's a gap of nearly 37 percentage points between the strongest and weakest fighter, far from the 50% we would expect in a fair game. The battles are random, so exact numbers shift a little each run, but the pattern stays the same.

## How to Run

<details>
<summary>☕ Step 1: Run the Java simulation</summary>

1. Put `Fighter.java` and `ProjectMain.java` (in `engine/`) in the same project in your IDE.
2. Run `ProjectMain.java`.
3. It prints `Done! Wrote 1000 battles to results.csv`.

Or from the command line:

```bash
cd engine
javac Fighter.java ProjectMain.java
java ProjectMain
```

</details>

<details>
<summary>🐍 Step 2: Run the Python analysis</summary>

```bash
cd charts
python -m pip install -r requirements.txt
python charts.py
```

It prints a stats table and saves three charts into a `charts` folder. If `results.csv` isn't in the same folder, pass its path: `python charts.py ../engine/results.csv`.

</details>

## 📁 Repo Structure
```
SunHacks2026/
├── README.md
├── engine/          ☕ Java: Fighter.java, ProjectMain.java, results.csv
└── charts/     🐍 Python: charts.py
```
See [`engine/`](engine/) for the fighter stats and Java details.

## Challenges We Ran Into
- Java and Python don't share data directly, so we had to agree on a file format. The CSV's column names (`winner`, `loser`, `rounds`, `winner_hp_left`) had to match exactly or the analysis would fail with a `KeyError`.
- Our data file was actually an Excel workbook with the wrong extension, so VS Code refused to open it as text.
- We hit `ModuleNotFoundError` because the libraries were installed for a different Python than the one running the script. That taught us why virtual environments and `python -m pip` matter.
- A few missing commas, a misspelled method name, and misplaced indentation were each enough to stop the whole script.

## 🏆 Accomplishments We're Proud Of
We built a complete pipeline that simulated 1,000 battles between six fighters, analyzed the data, and showed it visually. Our biggest accomplishment is that the data answered the question we started with: the game is not balanced, and we can show it with a program we designed ourselves. We're also proud of getting the whole thing working across two languages. A process that would have taken hours of manual testing now takes seconds.

## What We Learned
Along the way we learned how to build a data pipeline across two languages, how to aggregate with pandas and visualize with matplotlib, and how to debug environment and file-format problems rather than just code.

## What's Next
- Suggest stat changes that balance overpowered fighters automatically
- Add matchup heatmaps showing who beats whom
- Let users define their own fighters
- Wrap everything in a web dashboard that updates live as simulations run

## 🛠️ Built With
Java · Python · pandas · matplotlib
with Eclipse and Visual Studio (IDEs)
