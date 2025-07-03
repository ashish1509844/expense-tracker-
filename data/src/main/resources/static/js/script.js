fetch('http://localhost:8080/api/expenses')
  .then(res => res.json())
  .then(data => {
    const tableBody = document.querySelector('#expenseTable tbody');
    const categories = {};

    // Clear existing rows
    tableBody.innerHTML = '';

    data.forEach(expense => {
      const row = document.createElement('tr');
      row.innerHTML = `
      <td data-label="Description">${expense.description}</td>
      <td data-label="Category">${expense.category}</td>
      <td data-label="Amount">₹${parseFloat(expense.amount).toFixed(2)}</td>
      <td data-label="Date">${new Date(expense.date).toLocaleDateString()}</td>
      `;
      tableBody.appendChild(row);

      // Aggregate for chart
      if (categories[expense.category]) {
        categories[expense.category] += expense.amount;
      } else {
        categories[expense.category] = expense.amount;
      }
    });

    // Draw pie chart
    const ctx = document.getElementById('expenseChart').getContext('2d');
    new Chart(ctx, {
      type: 'pie',
      data: {
        labels: Object.keys(categories),
        datasets: [{
          label: 'Expenses by Category',
          data: Object.values(categories),
          backgroundColor: ['#f87171', '#60a5fa', '#34d399', '#facc15']
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'bottom'
          },
          title: {
            display: true,
            text: 'Monthly Expense Distribution'
          }
        }
      }
    });
  })
  .catch(err => {
    console.error('Failed to load expenses:', err);
  });
