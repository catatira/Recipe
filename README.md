# Recipe - Modern Android Stack Demo App

An Android app built with Jetpack Compose that allows users to browse and save favorite recipes, with recipe suggestions powered by ChatGPT.

**Recipe** is a demo Android application showcasing modern Android development practices and libraries. 
It allows users to browse and save recipes, with recipe suggestions powered by OpenAI.

### Key Features

*   **Browse & Save Recipes:** Explore and save your favorite recipes.
*   **OpenAI Suggestions:** Get recipe ideas powered by OpenAI.
*   **Modern UI:** Built with Jetpack Compose for a declarative UI.
*   **Offline Support:** Saved recipes are available offline, thanks to Room.

### Tech Stack Highlights

This project demonstrates the use of:
*   **Kotlin:** The entire app is written in Kotlin.
*   **Kotlin Coroutines:** For asynchronous operations.
*   **Jetpack Compose:** For building the UI.
*   **Compose Navigation:** For screen navigation.
*   **Retrofit:** For network requests to the OpenAI API.
*   **Room:** For local data persistence.
*   **MockK & Turbine:** For robust unit testing.
*   **Koin:** For dependency injection.
* **Kotlin Serialization:** For data serialization.

### OpenAI Integration

This demo integrates with OpenAI to provide recipe suggestions.

**Note:** You'll need an OpenAI API key. Add it to `local.properties` as `open-ai-key=YOUR_API_KEY`.

### Getting Started

1.  **Clone:** `git clone https://github.com/catatira/Recipe.git`
2.  **Open:** Open the project in Android Studio.
3.  **API Key:** Add your OpenAI API key to `local.properties`.
4.  **Run:** Build and run the app.

### Testing

Unit tests are included using MockK and Turbine. Run them from Android Studio's test runner.