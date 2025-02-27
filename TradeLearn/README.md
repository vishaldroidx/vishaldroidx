# TradeLearn - Virtual Trading Learning App

TradeLearn is a virtual trading learning app designed to teach students about the stock market and trading concepts without any financial risk. It integrates real-time market data APIs and allows teachers/admins to create student accounts and assign virtual balance for trading practice.

## Features

- User Registration & Login (Admin/Teacher Controlled)
- Dashboard with virtual balance, trending stocks, and charts
- Live Trading Module with real-time market data
- Learning & Educational Content
- Leaderboard & Rewards System
- Admin Panel for user management
- Settings & Profile Management

## Tech Stack

- Frontend: Android (Kotlin/Jetpack Compose)
- Backend: Firebase
- Real-time APIs: Alpha Vantage, Yahoo Finance
- Deployment: Firebase Cloud Messaging (FCM)

## Project Structure

The project follows the standard Android architecture with MVVM pattern:
- `app/src/main/java/com/techwarezen/tradelearn/` - Kotlin source files
- `app/src/main/res/` - Resource files (layouts, drawables, etc.)
- `app/src/main/AndroidManifest.xml` - App manifest

## Getting Started

1. Clone the repository
2. Open the project in Android Studio
3. Connect Firebase (instructions in documentation)
4. Build and run the app