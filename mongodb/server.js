const express = require("express");
const mongoose = require("mongoose");
const bodyParser = require("body-parser");
const cors = require("cors");

const app = express();
app.use(bodyParser.json());
app.use(cors());
app.use(express.static("public"));


// Connect to MongoDB Atlas
mongoose.connect(
  "mongodb+srv://saranya:sansaran@cluster0.anypy8q.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0",
  { useNewUrlParser: true, useUnifiedTopology: true }
)
.then(() => console.log("MongoDB Connected"))
.catch(err => console.log(err));

// Schema
const noteSchema = new mongoose.Schema({
  title: String,
  content: String
});

const Note = mongoose.model("Note", noteSchema);

// Routes

// CREATE
app.post("/notes", async (req, res) => {
  const note = new Note(req.body);
  await note.save();
  res.send(note);
});

// READ ALL
app.get("/notes", async (req, res) => {
  const notes = await Note.find();
  res.send(notes);
});

// UPDATE
app.put("/notes/:id", async (req, res) => {
  const note = await Note.findByIdAndUpdate(req.params.id, req.body, { new: true });
  res.send(note);
});

// DELETE
app.delete("/notes/:id", async (req, res) => {
  await Note.findByIdAndDelete(req.params.id);
  res.send({ message: "Deleted successfully" });
});

app.listen(5000, () => console.log("Server running on port 5000"));
