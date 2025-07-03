# File: charting/expenses_chart.py
import matplotlib.pyplot as plt
import json

# Load data from JSON file
with open('expenses_data.json', 'r') as file:
    data = json.load(file)

# Group expenses by category
category_totals = {}
for item in data:
    category = item['category']
    amount = float(item['amount'])
    category_totals[category] = category_totals.get(category, 0) + amount

# Prepare data for pie chart
labels = list(category_totals.keys())
amounts = list(category_totals.values())

# Plot pie chart
plt.figure(figsize=(6, 6))
plt.pie(amounts, labels=labels, autopct='%1.1f%%', startangle=140)
plt.title('Monthly Expense Distribution')
plt.tight_layout()

# Save chart to file
plt.savefig('monthly_expense_chart.png')
plt.show()
