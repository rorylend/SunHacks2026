import os
import sys
import matplotlib.pyplot as plt
import pandas as pd

CANDIDATE_PATHS = [
    "results.csv",
    os.path.join("..", "engine", "results.csv"),
    os.path.join("..", "results.csv"),
]

def find_csv():
    if len(sys.argv) > 1:
        return sys.argv[1]
    for path in CANDIDATE_PATHS:
        if os.path.exists(path):
            return path
    print("could not find results.csv. Run the Java program first,")
    print("or pass the path: python charts.py path/to/results.csv")
    sys.exit(1)

def main():
    csv_path = find_csv()
    print("reading", csv_path)
    df = pd.read_csv(csv_path)
    os.makedirs("charts", exist_ok=True)

    wins = df["winner"].value_counts()
    losses = df["loser"].value_counts()
    stats = pd.DataFrame({"wins": wins, "losses": losses}).fillna(0)
    stats["battles"] = stats["wins"] + stats["losses"]
    stats["win_rate_pct"] = (stats["wins"] / stats["battles"] * 100).round(1)
    stats = stats.sort_values("win_rate_pct", ascending=False)
    print("\n=== Fighter stats ===")
    print(stats)
    print("\nTotal battles:", len(df))
    print("Average rounds per battle:", round(df["rounds"].mean(), 1))

    plt.figure(figsize=(8, 5))
    stats["win_rate_pct"].plot(kind="bar", color="#e8590c")
    plt.title("Win rate per fighter (%)")
    plt.ylabel("Win rate (%)")
    plt.xlabel("")
    plt.xticks(rotation=30, ha="right")
    plt.axhline(50, color="gray", linestyle="--", linewidth=1)
    plt.tight_layout()
    plt.savefig(os.path.join("charts", "win_rate.png"))

    plt.figure(figsize=(8, 5))
    plt.hist(df["rounds"], bins=20, color="#1c7ed6", edgecolor="white")
    plt.title("How many rounds do battles last?")
    plt.xlabel("Rounds")
    plt.ylabel("Number of battles")
    plt.tight_layout()
    plt.savefig(os.path.join("charts", "rounds_hist.png"))

    plt.figure(figsize=(8, 5))
    df.groupby("winner")["winner_hp_left"].mean().sort_values(ascending=False).plot(
        kind="bar", color="#2f9e44"
    )
    plt.title("Average HP left after a win (how dominant are they?)")
    plt.ylabel("Average HP left")
    plt.xlabel("")
    plt.xticks(rotation=30, ha="right")
    plt.tight_layout()
    plt.savefig(os.path.join("charts", "hp_left.png"))

    print("\nSaved 3 charts to the 'charts' folder.")
    plt.show()

if __name__ == "__main__":
    main()