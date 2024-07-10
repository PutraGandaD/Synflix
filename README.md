# Synflix
![synflix_logo](https://github.com/PutraGandaD/F-AND24001121-synrgy7-SimpleTMDBApp-ch5/assets/54593964/a284feb4-f987-411a-905e-dc4498663b49) <br>
Basic Movie Database App utilizing The Movie Database (TMDB) API, for learning android development purpose.

## Branch
- **clean-architecture** = Modularization + Clean Architecture implementation with Dependency Injection (Koin)
- **mvvm-with-di-koin** = MVVM (Model - View - ViewModel) Architecture implementation + Dependency Injection (Koin)

## Screenshot
![Untitled design](https://github.com/PutraGandaD/Synflix/assets/54593964/a9f506e4-63ec-41dd-becb-7ed401f4c19a)
![Untitled design (1)](https://github.com/PutraGandaD/Synflix/assets/54593964/f9ba6c65-58c7-4520-8c02-5e2afbb23be1)

## Tech Stack :
- Built using Material Design 3 (https://m3.material.io), UI Inspired by Netflix
- Built with Clean Architecture (kind of)
- With Dependency Injection (Koin)
- Single Activity Approach with multiple Fragments
- Using TheMovieDB API (https://api.themoviedb.org)
- Using Android Jetpack Navigation Component for Navigation
- Using Android Jetpack Preference DataStore for saving login state (simulating login/register)
- Using RecyclerView with OnClickListener Interface
- and more...

## Architecture Overview
![1_eWl4zVmw4zKNGEF3Cba6Tw](https://github.com/PutraGandaD/Synflix/assets/54593964/4e436873-9f2a-4e6f-8a7c-022f2cb0f24b)

## Build Variant / Flavors
![Screen Shot 2024-07-11 at 2 23 25 AM](https://github.com/PutraGandaD/Synflix/assets/54593964/3e8bce58-b888-4c7d-b2b9-a5de7399f6fb)

## 3rd party library featured in this app :
- Facebook Shimmer Library (https://github.com/facebookarchive/shimmer-android)
- Retrofit
- Glide
- Koin

## WIP :
- [x] Modularization
- [x] Add features for change profile picture locally (+WorkManager for blurring image)
- [x] Build Flavoring and Build Types
- [x] Unit Test (with JUnit4)
- [x] Simple CI/CD with GitHub Actions
- [ ] Migrating from LiveData to StateFlow 
- [ ] Implement Paging 3.0 for fetching data from API
- [ ] Add Search Movie feature
- [ ] Add "Add to Watchlist..." feature

## README Credits :
- A Definitive Guide to Clean Architecture in Android with MVVM by Sourik at Medium <br>
  https://medium.com/@gangulysourik/a-definitive-guide-to-clean-architecture-in-android-with-mvvm-d74a0533ef2c

