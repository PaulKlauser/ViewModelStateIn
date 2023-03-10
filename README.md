A demonstration of `viewModelScope` still being active while the associated
view is no longer visible, in this case, on the back stack.

`stateIn()`'s `started` parameter allows you to expose a StateFlow from the ViewModel
without worrying about unintended collections while on the back stack by using
`SharingStarted.WhileSubscribed()`. This will stop the collection of the upstream
Flow when the view has stopped collecting the ViewModel's Flow, which will occur when
it is placed on the back stack.

I still generally recommend avoiding `stateIn()` as much as possible, however I also
recognize that Compose's `Flow#collectAsState()` requires an initial value. Granted,
so does `stateIn()`, so 🤷. I haven't worked with the tools enough IRL to know if 
defaulting to "empty" initial values causes flickering, or if the value is conflated
and it's a non-issue. With the toy example of integers, it appears to be a 
non-issue.
