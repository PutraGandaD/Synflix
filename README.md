# Synflix
![synflix_logo](https://github.com/PutraGandaD/F-AND24001121-synrgy7-SimpleTMDBApp-ch5/assets/54593964/a284feb4-f987-411a-905e-dc4498663b49) <br>
Basic Movie Database App utilizing The Movie Database (TMDB) API, for learning android development purpose.

## Branch
- **clean-architecture** = Modularization + Clean Architecture implementation with Dependency Injection (Koin)
- **mvvm-with-di-koin** (Old/Archived/Discontinued) = MVVM (Model - View - ViewModel) Architecture implementation + Dependency Injection (Koin)

## Screenshot
![Untitled design](https://github.com/PutraGandaD/Synflix/assets/54593964/a9f506e4-63ec-41dd-becb-7ed401f4c19a)
![Untitled design (1)](https://github.com/PutraGandaD/Synflix/assets/54593964/f9ba6c65-58c7-4520-8c02-5e2afbb23be1)

## Tech Stack :
- Using TheMovieDB API (https://api.themoviedb.org)
- Built using Material Design 3 (https://m3.material.io), UI Inspired by Netflix
- Built with Clean Architecture, modularization by layer.
- With Dependency Injection (Koin)
- Single Activity Approach with multiple Fragments
- Using Android Jetpack Navigation Component for Navigation
- Using Android Jetpack DataStore Preference for saving login state (simulating login/register)
- Using RecyclerView with OnClickListener Interface
- and more...

## Architecture Overview
![1_eWl4zVmw4zKNGEF3Cba6Tw](https://github.com/PutraGandaD/Synflix/assets/54593964/4e436873-9f2a-4e6f-8a7c-022f2cb0f24b)

## Build Variant / Flavors
![Screen Shot 2024-09-05 at 3 08 49 AM](https://github.com/user-attachments/assets/1fd4601e-1631-4d29-9de7-5d7ddb152465)<br>
See this commit for details : https://github.com/PutraGandaD/Synflix/commit/ffd956bd1c78973e33f7febad7e903f68d0c38b0

## Navigation
![Screen Shot 2024-09-05 at 3 12 44 AM](https://github.com/user-attachments/assets/6cc59b18-2d73-4104-bf5a-6477230589d9)

## Unit Test
This project utilize JUnit4 for Unit Tests. Unit Test located in **app** module/layer.

## CI/CD 
This project utilize CI/CD with GitHub Actions.
Please go to Actions tab for more details on CI/CD Script

## Firebase Integrations
This project utilizing Firebase for Performance Monitoring and Custom Traces (for learning purposes). <br>
In order to build this app, you need the google-services.json file that suited the build variant/flavors of this app. <br>
For CI/CD to work, i implemented a custom way to generate google-services.json based on GitHub Secrets. <br>
See these commit for details on Firebase Integrations in this app : <br>
https://github.com/PutraGandaD/Synflix/commit/fe0afd8a5148c17c0ba95460147d9c9d337135fc <br>
https://github.com/PutraGandaD/Synflix/commit/78c364c5c3f7109a89558bb756e6641ce4b67fe7

## Dependency Used
This project already utilizing Gradle Version Catalog as recommended by Google for multi module projects.
You can check the dependency used in this project [here](https://github.com/PutraGandaD/Synflix/blob/clean-architecture/gradle/libs.versions.toml).

## WIP :
- [x] Modularization
- [x] Add features for change profile picture locally (+WorkManager for blurring image)
- [x] Build Flavoring and Build Types
- [x] Unit Test (with JUnit4)
- [x] Simple CI/CD with GitHub Actions
- [x] Migrating dependencies to Gradle Version Catalog 
- [ ] Migrating from LiveData to StateFlow
- [ ] Implement Check Internet Connection
- [ ] Implement Paging 3.0 for fetching data from API
- [ ] Add Search Movie feature
- [ ] Add "Add to Watchlist..." feature

## README Credits :
- A Definitive Guide to Clean Architecture in Android with MVVM by Sourik at Medium <br>
  https://medium.com/@gangulysourik/a-definitive-guide-to-clean-architecture-in-android-with-mvvm-d74a0533ef2c

