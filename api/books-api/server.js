const express = require('express');
const cors = require('cors'); // To allow frontend to access API
const app = express();
const PORT = 5000;

// Middleware
app.use(cors());
app.use(express.json());

// Sample book data
let books = [
  { id: 1, title: "The Alchemist", author: "Paulo Coelho", year: 1988 },
  { id: 2, title: "1984", author: "George Orwell", year: 1949 },
  { id: 3, title: "To Kill a Mockingbird", author: "Harper Lee", year: 1960 },
];

// Home route
app.get('/', (req, res) => res.send('Welcome to the Books API!'));

// Get all books
app.get('/api/books', (req, res) => res.json(books));

// Get a book by ID
app.get('/api/books/:id', (req, res) => {
  const book = books.find(b => b.id === parseInt(req.params.id));
  book ? res.json(book) : res.status(404).json({ message: "Book not found" });
});

// Add a new book
app.post('/api/books', (req, res) => {
  const { title, author, year } = req.body;
  const newBook = { id: books.length + 1, title, author, year };
  books.push(newBook);
  res.status(201).json(newBook);
});

// Update a book
app.put('/api/books/:id', (req, res) => {
  const book = books.find(b => b.id === parseInt(req.params.id));
  if (book) {
    const { title, author, year } = req.body;
    book.title = title || book.title;
    book.author = author || book.author;
    book.year = year || book.year;
    res.json(book);
  } else {
    res.status(404).json({ message: "Book not found" });
  }
});

// Delete a book
app.delete('/api/books/:id', (req, res) => {
  const index = books.findIndex(b => b.id === parseInt(req.params.id));
  if (index !== -1) {
    const deleted = books.splice(index, 1);
    res.json(deleted[0]);
  } else {
    res.status(404).json({ message: "Book not found" });
  }
});

app.listen(PORT, () => console.log(`Server running on http://localhost:${PORT}`));
