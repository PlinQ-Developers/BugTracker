<!-- GETTING STARTED -->
# Getting Started
A bug tracker app that allows users to submit, track and monitor bugs. MVVM architecture, JIRA board, Kotlin, Room DB, Retrofit, Glide.

The Bug Tracker App is an Android application that allows users to report bugs and suspicious activities they might encounter while using the application. The app enables users to check the status of their bug report and contact support via email if they need further assistance.

Follow the following instructions to set up your project locally.
To get a local copy up and running follow these simple example steps.

# Prerequisites

This project is publicly hosted on GitHub. Clone the source code to download the project onto your computer then unzip the file. Open the project folder using Android Studio to access the project files

* Android 8.1 Oreo

  ```sh
  Please ensure that you run the project on an Android 8.1 device. You can however change the buildSdk to target the device you are testing on.
  ```

# Installation

_Below is an example of how you can instruct your audience on installing and setting up your app. This template doesn't rely on any external dependencies or services._

1. The mock API is hosted here [Mocky API](https://run.mocky.io/v3/b215398c-d15a-49ed-99f1-876af7deb740)
2. Clone the repo
   ```sh
   git clone https://github.com/PlinQ-Developers/BugTracker.git
   ```

3. Switch to  `testing branch`
   ```sh
   Branch = testing
   ```

4. Launch app 
   ```sh
   Build your android app
   ```


<!-- Features -->
# Features

- Report a bug: Users can submit a bug report that includes the bug title, bug description, date reported, and severity of the bug.
- Upload screenshots: Users can choose to upload screenshots of the issue they are experiencing while using the app.
- Track bug status: Users can monitor the status of their bug report and receive notifications when the issue is resolved.
- Contact support: Users can reach out to the app support team for further assistance by referencing their bug report ID.



<!-- App Architecture -->
# Architecture

The Bug Tracker App is built using the Model-View-ViewModel (MVVM) architecture pattern, which separates the presentation layer from the business logic layer. This architecture ensures that the app's UI remains responsive even when accessing and manipulating data from the backend.


<!-- ROADMAP -->
# Roadmap

- [x] Implemented System Design
- [x] Implemented Business UseCases
- [x] Implemented Filter on Bug Lists
- [x] Implemented View Bug Item
- [x] Added Local Room Database
    - [x] Read
    - [x] Write
    - [ ] Delete
    - [x] Update
- [x] Implemented Network calls


##Contributors

The Bug Tracker App was developed by Olel Nashon, with contributions from other members of the PlinQ Developers development team


<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information..
