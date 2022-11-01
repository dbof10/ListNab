
# Architecture
Unidirectional data flow in a function f(x) = y. Input is click, scroll, tap, hover with some specific 
params. ViewModel acts a function in a formula above. It's where all business happens. After processing,
data is emitted as an output to the UI layer. View can be android, ios, web. 
With the same input we can always expect the same output

![](architecture.png)

# Project structure

| Module    | Description         |
|-----------|---------------------|
| home      | main feature        |  
| common    | common utility code | 
| resources | common assets       | 
| core-component | core architecture      | 
| app | ultimate module compile everything to final binary      | 

# Run unit test
`./gradlew testDebugUnitTest`

Assignment test mainly locates in app module

# Build
`./gradlew assembleDebug`
`./gradlew assembleRelease`

# Check list
- [x] The application is a simple Android application which is written by Java/Kotlin.

- [x] The application is able to retrieve the weather information from OpenWeatherMapsAPI.

- [x] The application is able to allow user to input the searching term.

- [x] The application is able to proceed searching with a condition of the search term length
   must be from 3 characters or above.

- [x] The application is able to render the searched results as a list of weather items.

- [x] The application is able to support caching mechanism so as to prevent the app from
   generating a bunch of API requests.

- [x] The application is able to manage caching mechanism & lifecycle.

- [x] The application is able to handle failures.

- [ ] The application is able to support the disability to scale large text for who can't see the text
   clearly.

- [x] The application is able to support the disability to read out the text using VoiceOver
   controls.
