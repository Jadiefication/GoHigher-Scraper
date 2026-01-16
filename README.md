# GoHigher-Scraper

A Discord bot used for scraping the [podporatalentu.cz](https://podporatalentu.cz) website to get info about upcoming activities.

## 🚀 Overview

GoHigher-Scraper is designed to monitor and extract information about talent support activities and deliver updates directly to Discord.

## 🛠️ Tech Stack

- **Language:** [Kotlin](https://kotlinlang.org/) (JVM)
- **Framework:** [JDA (Java Discord API)](https://github.com/discord-jda/JDA)
- **Build Tool:** [Gradle (Kotlin DSL)](https://gradle.org/)
- **JDK Version:** 24

## 📋 Requirements

- **Java Development Kit (JDK) 24** or higher.
- **Discord Bot Token** (Create one on the [Discord Developer Portal](https://discord.com/developers/applications)).

## ⚙️ Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/jadiefication/GoHigher-Scraper.git
   cd GoHigher-Scraper
   ```

2. **Configure Environment Variables:**
   Create a `.env` file in the root directory (or set them in your environment):
   ```env
   DISCORD_TOKEN=your_token_here
   # TODO: Add other necessary configuration variables
   ```

3. **Build the project:**
   ```bash
   ./gradlew build
   ```

## 🏃 Running the Bot

To run the bot in development mode:
```bash
./gradlew run
```

*Note: The `run` task may need to be explicitly configured in `build.gradle.kts` if not already present.*

## 📜 Available Scripts

- `./gradlew build` - Compiles and builds the project.
- `./gradlew run` - Executes the application.
- `./gradlew test` - Runs the test suite.
- `./gradlew clean` - Cleans the build directory.

## 📂 Project Structure

```text
.
├── gradle/                  # Gradle wrapper files
├── src/
│   ├── main/
│   │   ├── kotlin/          # Source code
│   │   │   └── io/jadiefication/Main.kt  # Entry point
│   │   └── resources/       # Application resources
│   └── test/
│       └── kotlin/          # Unit tests
├── build.gradle.kts         # Build configuration
├── settings.gradle.kts      # Project settings
└── LICENSE                  # MIT License
```

## 🧪 Testing

Run tests using Gradle:
```bash
./gradlew test
```
*Current status: Basic JUnit 5 integration is set up.*

## 📝 TODOs

- [ ] Implement `podporatalentu.cz` scraping logic.
- [ ] Implement Discord command handlers using JDA.
- [ ] Configure `application` plugin in `build.gradle.kts` for easier execution.
- [ ] Add comprehensive logging.
- [ ] Set up GitHub Actions for CI/CD.

## 📄 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.
