let books = [
    { id: 1, title: '1984', author: 'George Orwell', genre: 'Dystopian', lateFee: 1, depositPrice: 20 },
    { id: 2, title: 'To Kill a Mockingbird', author: 'Harper Lee', genre: 'Fiction', lateFee: 0.75, depositPrice: 15 },
    { id: 3, title: 'The Great Gatsby', author: 'F. Scott Fitzgerald', genre: 'Classic', lateFee: 0.9, depositPrice: 18 }
  ];
  let readers = [];
  let loans = [];
  
  document.addEventListener('DOMContentLoaded', () => {
    showPage('home');
    displayBooks();
    displayReaders();
  });
  
  function showPage(pageId) {
    const pages = document.querySelectorAll('.page');
    pages.forEach(page => {
      page.classList.remove('active');
    });
    document.getElementById(pageId).classList.add('active');
  }
  
  function showAddLoanForm(bookTitle) {
    document.getElementById('bookTitle').value = bookTitle;
    document.getElementById('addLoanForm').style.display = 'block';
  }
  
  function closeForm(formId) {
    document.getElementById(formId).style.display = 'none';
  }
  
  function displayBooks() {
    const bookList = document.getElementById('bookList');
    bookList.innerHTML = '';
    books.forEach(book => {
      const isBorrowed = loans.some(loan => loan.bookTitle === book.title && !loan.isReturned);
      const li = document.createElement('li');
      li.innerHTML = `${book.title} by ${book.author} - Late Fee: $${book.lateFee}/day, Deposit: $${book.depositPrice} ${isBorrowed ? '(Borrowed)' : ''} <button ${isBorrowed ? 'disabled' : ''} onclick="showAddLoanForm('${book.title}')">Borrow</button>`;
      bookList.appendChild(li);
    });
  }
  
  function addLoan() {
    const readerName = document.getElementById('loanReaderName').value;
    const readerId = document.getElementById('loanReaderId').value;
    const bookTitle = document.getElementById('bookTitle').value;
    const issueDate = document.getElementById('issueDate').value;
    const returnDate = document.getElementById('returnDate').value;
  
    const book = books.find(book => book.title === bookTitle);
    const reader = readers.find(r => r.name === readerName && r.subscriptionId === readerId);
  
    if (!reader || !isSubscriptionValid(reader)) {
      alert('Subscription is invalid or reader does not exist.');
      return;
    }
  
    const discount = reader.category === 'minor' ? 0.25 : reader.category === 'military' ? 0.5 : 1;
  
    const loan = {
      id: loans.length + 1,
      bookTitle,
      readerName,
      readerId,
      issueDate,
      returnDate,
      rentalPrice: calculateRentalPrice(book.id, issueDate, returnDate) * discount,
      lateFee: book.lateFee,
      depositPrice: book.depositPrice,
      isReturned: false
    };
  
    loans.push(loan);
    displayLoans();
    closeForm('addLoanForm');
  }
  
  function returnBook() {
    const bookTitle = document.getElementById('returnBookTitle').value;
    const readerName = document.getElementById('returnReaderName').value;
    const returnDate = document.getElementById('returnDate').value;
    const damage = document.getElementById('damage').value;
  
    const loan = loans.find(loan => loan.bookTitle === bookTitle && loan.readerName === readerName && !loan.isReturned);
    if (loan) {
      const actualReturnDate = new Date(returnDate);
      const expectedReturnDate = new Date(loan.returnDate);
      let extraCharge = 0;
  
      if (actualReturnDate > expectedReturnDate) {
        const daysLate = (actualReturnDate - expectedReturnDate) / (1000 * 60 * 60 * 24);
        extraCharge += daysLate * loan.lateFee;
      }
  
      if (damage === 'minor') {
        extraCharge += loan.depositPrice * 0.1;
      } else if (damage === 'major') {
        extraCharge += loan.depositPrice * 0.75;
      }
  
      loan.isReturned = true;
      document.getElementById('returnCost').innerText = `Total extra charge: $${extraCharge.toFixed(2)}`;
      alert(`Return successful. Extra charge: $${extraCharge.toFixed(2)}`);
    } else {
      alert('Loan not found or book already returned.');
    }
    displayBooks();
    displayLoans();
  }
  
  function calculateRentalPrice(bookId, issueDate, returnDate) {
    const book = books.find(book => book.id === parseInt(bookId));
    const daysRented = (new Date(returnDate) - new Date(issueDate)) / (1000 * 60 * 60 * 24);
    return book.rentalPrice * daysRented;
  }
  
  function purchaseSubscription() {
    const readerName = document.getElementById('subscriptionReaderName').value;
    const readerId = document.getElementById('subscriptionReaderId').value;
    const subscriptionType = document.getElementById('subscriptionType').value;
  
    const reader = readers.find(r => r.name === readerName && r.id === readerId);
    if (!reader) {
      alert('Reader not found.');
      return;
    }
  
    const currentDate = new Date();
    let expiryDate;
    if (subscriptionType === 'monthly') {
      expiryDate = new Date(currentDate.setMonth(currentDate.getMonth() + 1));
    } else {
      expiryDate = new Date(currentDate.setFullYear(currentDate.getFullYear() + 1));
    }
  
    reader.subscriptionExpiry = expiryDate;
    reader.subscriptionId = generateUniqueId();
    document.getElementById('subscriptionStatus').innerText = `Subscription purchased successfully. Expires on ${expiryDate.toDateString()}. Your ID is ${reader.subscriptionId}`;
    alert(`Subscription purchased successfully. Your ID is ${reader.subscriptionId}`);
    displayReaders();
  }
  
  function generateUniqueId() {
    const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    let result = '';
    for (let i = 0; i < 5; i++) {
      result += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return result;
  }
  
  function isSubscriptionValid(reader) {
    if (!reader.subscriptionExpiry) {
      return false;
    }
    const currentDate = new Date();
    return currentDate <= new Date(reader.subscriptionExpiry);
  }
  
  function registerReader() {
    const name = document.getElementById('registerName').value;
    const address = document.getElementById('registerAddress').value;
    const phone = document.getElementById('registerPhone').value;
    const category = document.getElementById('registerCategory').value;
  
    const id = readers.length + 1;
  
    const reader = {
      id,
      name,
      address,
      phone,
      category
    };
  
    readers.push(reader);
    document.getElementById('registerStatus').innerText = `Reader registered successfully. Your ID is ${id}`;
    alert(`Reader registered successfully. Your ID is ${id}`);
    displayReaders();
  }
  
  function displayReaders() {
    const readerList = document.getElementById('readerList');
    readerList.innerHTML = '';
    readers.forEach(reader => {
      const li = document.createElement('li');
      li.textContent = `${reader.name} (ID: ${reader.id}), ${reader.category}, Subscription Expires: ${reader.subscriptionExpiry ? new Date(reader.subscriptionExpiry).toDateString() : 'None'}, Subscription ID: ${reader.subscriptionId || 'None'}`;
      readerList.appendChild(li);
    });
  }
  
  function displayLoans() {
    const loanList = document.getElementById('loanList');
    loanList.innerHTML = '';
    loans.forEach(loan => {
      const li = document.createElement('li');
      li.textContent = `Book: ${loan.bookTitle}, Reader: ${loan.readerName}, Due: ${loan.returnDate}, Rental Price: $${loan.rentalPrice.toFixed(2)}`;
      loanList.appendChild(li);
    });
  }
  
  function generateBookReport() {
    const reportContent = document.getElementById('bookReportContent');
    reportContent.innerHTML = `<ul>${books.map(book => `<li>${book.title} by ${book.author} - Genre: ${book.genre}</li>`).join('')}</ul>`;
  }
  
  function generateFinancialReport() {
    const totalIncome = loans.reduce((total, loan) => total + loan.rentalPrice, 0);
    const totalLateFees = loans.reduce((total, loan) => total + (loan.isReturned ? loan.lateFee : 0), 0);
    const reportContent = document.getElementById('financialReportContent');
    reportContent.innerHTML = `<p>Total Income from Rentals: $${totalIncome.toFixed(2)}</p><p>Total Late Fees: $${totalLateFees.toFixed(2)}</p>`;
  }

  document.addEventListener('DOMContentLoaded', () => {
  fetchBooks();
  fetchReaders();
});

  function fetchBooks() {
  fetch('http://localhost:8080/books')
      .then(response => response.json())
      .then(data => {
        const bookTable = document.getElementById('bookTable').querySelector('tbody');
        bookTable.innerHTML = '';
        data.forEach(book => {
          const row = bookTable.insertRow();
          row.insertCell(0).textContent = book.id;
          row.insertCell(1).textContent = book.title;
          row.insertCell(2).textContent = book.author;
          row.insertCell(3).textContent = book.genre;
          row.insertCell(4).textContent = book.lateFee;
          row.insertCell(5).textContent = book.depositPrice;
        });
      })
      .catch(error => console.error('Error:', error));
}

  function fetchReaders() {
  fetch('http://localhost:8080/readers')
      .then(response => response.json())
      .then(data => {
        const readerTable = document.getElementById('readerTable').querySelector('tbody');
        readerTable.innerHTML = '';
        data.forEach(reader => {
          const row = readerTable.insertRow();
          row.insertCell(0).textContent = reader.id;
          row.insertCell(1).textContent = reader.name;
          row.insertCell(2).textContent = reader.address;
          row.insertCell(3).textContent = reader.phone;
          row.insertCell(4).textContent = reader.category;
          row.insertCell(5).textContent = reader.subscriptionExpiry;
          row.insertCell(6).textContent = reader.subscriptionId;
        });
      })
      .catch(error => console.error('Error:', error));
}
  