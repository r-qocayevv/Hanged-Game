# 🪓 Hanged – Multiplayer Hangman Game

### 🎮 Overview

**Hanged** is a real-time multiplayer Hangman game built with **Jetpack Compose**, **Clean
Architecture**, and **Firebase**.  
Players can create or join rooms, play together in real-time via **WebSocket (Socket.IO)**, and
track their game history and leaderboard position.

---

### 🧩 App Thumbnail
<img src="screenshots/hanged_thumbnail.png"/>

---

## 📱 App Screenshots

| Splash | Login | Register |
|--------|--------|----------|
| <img src="screenshots/splash_screen.png" width="250"/> | <img src="screenshots/login_screen.png" width="250"/> | <img src="screenshots/register_screen.png" width="250"/> |

| Home                                                 | Game (not started)                                   | Game (started)                                        |
|------------------------------------------------------|------------------------------------------------------|-------------------------------------------------------|
| <img src="screenshots/home_screen.png" width="250"/> | <img src="screenshots/game_screen.png" width="250"/> | <img src="screenshots/game_screen2.png" width="250"/> |

| Room Status Guide                                          | Players                                          | Game History                                          |
|------------------------------------------------------------|--------------------------------------------------|-------------------------------------------------------|
| <img src="screenshots/room_status_guide.png" width="250"/> | <img src="screenshots/players.png" width="250"/> | <img src="screenshots/game_history.png" width="250"/> |

| Leaderboard                                          |
|------------------------------------------------------|
| <img src="screenshots/leaderboard.png" width="250"/> |

---

## ⚙️ Tech Stack

| Category                 | Technologies                              |
|--------------------------|-------------------------------------------|
| **Language**             | Kotlin                                    |
| **Architecture**         | Clean Architecture + MVI                  |
| **UI**                   | Jetpack Compose, Material Design 3        |
| **Backend**              | Firebase (Auth, Firestore, Storage)       |
| **Realtime**             | WebSocket (Socket.IO)                     |
| **Dependency Injection** | Hilt                                      |
| **Local Storage**        | DataStore                                 |
| **Networking**           | Retrofit                                  |
| **Coroutines / Flow**    | For async operations and state management |
| **Animations**           | Lottie Animations                         |

---

## 🚀 Features

- 👥 **Multiplayer gameplay** via WebSocket
- 🔐 **Firebase Authentication** (Email/Password)
- 💬 **Real-time communication** between players
- 🏆 **Leaderboard and history tracking**
- ⚡ **Clean Architecture + MVI** ensures scalability and maintainability


