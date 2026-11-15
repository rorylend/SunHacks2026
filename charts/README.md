# Python Analysis: Balance Report

The Python half of **Arena Analytics**. It reads the battle log made by the Java engine (see [`../engine`](../engine)) and turns it into stats and charts that show which fighters are overpowered or underpowered.

## Files

| File | What it does |
|---|---|
| `charts.py` | Reads `results.csv`, prints a stats table, and saves three charts |
| `requirements.txt` | The Python libraries to install (`pandas`, `matplotlib`) |
| `charts/` | Created when you run the script; holds the chart images |

## How It Works

`charts.py` runs in four steps:

1. **Find the data.** It looks for `results.csv` in its own folder, then in `../engine/`. You can also pass a path: `python charts.py path/to/results.csv`.
2. **Load it with pandas.** `pd.read_csv` turns the file into a table (a DataFrame), one row per battle.
3. **Compute fighter stats.** It counts each fighter's wins and losses, then calculates the win rate:

   > **win rate = W / (W + L) × 100**

   In a perfectly balanced game every fighter sits near 50%, so big gaps point to characters that need rebalancing.
4. **Draw and save three charts** with matplotlib.

## The Three Charts

| Chart | File | What it shows |
|---|---|---|
| **Win rate per fighter** | `charts/win_rate.png` | Each fighter's win rate, with a 50% reference line to show who is above or below par |
| **Battle length** | `charts/rounds_hist.png` | A histogram of how many rounds fights last |
| **Dominance** | `charts/hp_left.png` | The average HP the winner has left, which shows who wins comfortably and who barely scrapes by |

It also prints the stats table and the average number of rounds per battle in the terminal.

## Input It Expects

A CSV file with these columns, written by the Java program:

```
battle_id,winner,loser,rounds,winner_hp_left
1,Roomba,Haboob,20,26
2,Dragon,Cactus,21,2
```

⚠️ The column names have to match exactly, or the script fails with a `KeyError`.

## How to Run

1. **Get `results.csv`.** Run the Java program first (see [`../engine`](../engine)) and make sure the file is in this folder or in `../engine/`.
2. **Open a terminal in this folder** (`charts`).
3. **Install the libraries** (first time only):

   ```bash
   python -m pip install -r requirements.txt
   ```

4. **Run it:**

   ```bash
   python charts.py
   ```

Chart windows pop up, and the images are saved in the `charts` folder. Close each window to continue.

<details>
<summary>🧪 Optional: use a virtual environment</summary>

A virtual environment keeps this project's libraries separate from the rest of your computer.

```bash
python -m venv venv
venv\Scripts\activate          # Windows
source venv/bin/activate       # Mac/Linux
python -m pip install -r requirements.txt
```

</details>

<details>
<summary>Troubleshooting</summary>

- **`ModuleNotFoundError: No module named 'pandas'`**: the libraries were installed for a different Python. Use `python -m pip install -r requirements.txt` so they match the Python running the script.
- **"Could not find results.csv"**: run the Java program first, or pass the path: `python charts.py ../engine/results.csv`.
- **`KeyError`**: the CSV's column names don't match the list above.
- **VS Code won't open the CSV as text**: check that the file is a real CSV and not an Excel workbook renamed to `.csv`.

</details>

## Python Concepts and Libraries Used

- **pandas**: loading the CSV, counting with `value_counts`, grouping with `groupby`
- **matplotlib**: bar charts, a histogram, and saving images
- File paths with `os` and command-line arguments with `sys`
- Functions and a `main()` entry point

## What We Learned Using Python

- How to aggregate data with pandas and visualize it with matplotlib
- How to debug environment problems, such as libraries installed for the wrong Python, and file-format problems, rather than just code
