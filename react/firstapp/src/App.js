import { useState } from "react";
import "./App.css";
import DessertsList from "./DessertsList";
import Header0 from "./Header";
import Header from "./context/Header";
import RegistrationForm from "./RegistrationForm";
import { UserProvider } from "./userContext";
import { ThemeProvider, useTheme } from "./context/ThemeContext";
import Page from "./context/Page";
const desserts = [
  {
    name: "Chocolate Cake",
    calories: 400,
    createdAt: "2022-09-01",
  },
  {
    name: "Ice Cream",
    calories: 200,
    createdAt: "2022-01-02",
  },
  {
    name: "Tiramisu",
    calories: 300,
    createdAt: "2021-10-03",
  },
  {
    name: "Cheesecake",
    calories: 600,
    createdAt: "2022-01-04",
  },
];

function App() {
  const [name, setName] = useState("");
  const [score, setScore] = useState(10);
  const [comment, setComment] = useState("");

  // Theme Context API
  const { theme } = useTheme();

  const handleSubmit = (e) => {
    e.preventDefault();
    // setName("");

    if (Number(score) <= 5 && comment.length <= 10) {
      alert("Please provide a comment explaining why the experience was poor.");
      return;
    }
    setScore(10);
    setComment("");

    console.log("Form submitted!");
  };
  return (
    <div
      className="App"
      style={{
        backgroundColor: theme === "light" ? "white" : "black",
      }}
    >
      {/* Unordered list */}
      {/* <h2>List of low calorie desserts:</h2>
      <DessertsList data={desserts} /> */}

      {/* Form */}
      {/* <form onSubmit={handleSubmit}>
        <fieldset>
          <div className="field">
            <label htmlFor="name">Name : </label>
            <input
              id="name"
              type="text"
              placeholder="Name"
              name="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </div>
          <button disabled={!name} type="submit">
            Submit
          </button>
        </fieldset>
      </form> */}

      {/* Feedback form */}
      {/* <form onSubmit={handleSubmit}>
        <fieldset>
          <h2>Feedback Form</h2>
          <div className="Field">
            <label>Score : {score} *</label>
            <input
              type="range"
              min={0}
              max={10}
              value={score}
              onChange={(e) => setScore(e.target.value)}
            />
          </div>
          <div className="Field">
            <label>Comment :</label>
            <textarea
              value={comment}
              onChange={(e) => setComment(e.target.value)}
            />
          </div>
          <button type="submit">Submit</button>
        </fieldset>
      </form> */}

      {/* Form Validation, useState hook */}
      {/* <RegistrationForm /> */}

      {/* Context API Ex:1 */}
      {/* <UserProvider>
        <Header0 />
      </UserProvider> */}

      <Header />
      <Page />
    </div>
  );
}

function Root() {
  return (
    <ThemeProvider>
      <App />
    </ThemeProvider>
  );
}

export default Root;
