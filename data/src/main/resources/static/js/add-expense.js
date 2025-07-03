document.getElementById('expenseForm').addEventListener('submit', function (e) {
  e.preventDefault();

  const formData = new FormData(this);
  const expense = {
    description: formData.get('description'),
    category: formData.get('category'),
    amount: parseFloat(formData.get('amount')),
    date: formData.get('date'),
  };

  fetch('http://localhost:8080/api/expenses', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(expense)
  })
    .then(res => {
      if (!res.ok) throw new Error('Failed to add expense');
      return res.json();
    })
    .then(data => {
      window.location.href = 'index.html';
    })
    .catch(err => {
      alert('Error: ' + err.message);
    });
});
