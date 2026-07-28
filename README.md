# DevLog — Coding Session Tracker

**Author:** Joshua Kligman

## Project Description

DevLog is a JavaFX desktop application for logging and reviewing individual coding sessions. Rather than tracking hours alone, it records what actually happened in each session: the project and language, when the session ran and how long it lasted, the feature being worked on, notes, bugs encountered, bugs fixed, bugs still open, and a self-assigned productivity rating.

Sessions are stored in a local SQLite database and persist between launches.

---

## Features

- **Full CRUD** — create, view, update, and delete coding sessions
- **Live session timer** — start a timer when you begin coding; stopping it fills in the start time, end time, and duration automatically
- **Manual entry** — sessions can also be logged after the fact by typing times and duration directly
- **Session table** — all logged sessions in one view; clicking a row loads it into the form for editing
- **Summary statistics** — total hours, distinct projects, most-used language, and total open bugs, recalculated after every change
- **Input validation** — required fields, numeric duration, and productivity range are checked before anything reaches the database, with clear error dialogs
- **Exception handling** — database errors and invalid input are caught and reported rather than crashing the application
- **Delete confirmation** — destructive actions require confirmation
- **Custom styling** — external CSS stylesheet for a consistent visual design

---

## Instructions for Running the Project

**Requirements**

- JDK 21 or newer
- Internet connection on first build (Maven downloads JavaFX and the SQLite driver automatically)

Nothing else needs to be installed. JavaFX and SQLite are handled by Maven, and the included Maven Wrapper means a separate Maven installation is not required.

**Option 1 — command line**

```
git clone <repository-url>
cd DevLog
mvnw javafx:run
```

On macOS or Linux, use `./mvnw javafx:run`.

**Option 2 — IntelliJ IDEA**

1. Open the project folder in IntelliJ
2. Wait for Maven to finish importing dependencies
3. Run `Launcher.java`

> Run `Launcher`, not `Main`. Launching a class that extends `Application` directly from the classpath produces a "JavaFX runtime components are missing" error.

The database file `devlog.db` is created automatically in the project root on first launch if it does not already exist.
