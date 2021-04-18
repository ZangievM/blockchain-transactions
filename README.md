# blockchain-transactions


## Short description
Working with api was realized using `Retrofit` + `OkHttp`. For refreshing token was used `MainIntercaptor` (it implements both `Interceptor` and `Authenticator` interfaces). 
Api was separated to `MainApi` and `MainRefreshApi` to avoid dependencies cycle like: `OkHttp` -> `MainInterceptor` -> `MainApi` -> `OkHttp`. 
Main screen was realised using dynamic features (install-time). Working with WebSocket was realised using `OkHttp.WebSocket`. Par

## What was used:

* Clean architecture
* MVVM
* Dynamic features (Profile, Transactions are dynamic features)
* DI: Hilt (app) and Dagger2 (feature modules)
* Coroutines 
* LiveData (View model / fragment)
* Navigation component (+ for dynamic features)
* Retrofit + OkHttp
* Gson
* Splash screen (for startup activity)

## Tech-debt
* Tests
* Network errors distinguish and handling
* Rewrite subscribing to `Transaction` events from listeners to coroutines `StateFlows` (it was implemented using `MutableStateFlow`, but for some reason it had crashed without stacktrace. This bug was fixed in `1.4.3` version of coroutines. And it can be rewritten)
