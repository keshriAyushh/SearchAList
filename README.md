This sample project demonstrates a simple search functionality to search a list.

Tech used - 
1) Kotlin
2) Jetpack Compose
3) Koin for Dependency Injection
4) MVI pattern
5) Coroutines and Flows

The search criteria demonstrated in this example - 

1) Search only with initials
2) Search with characters in the first and last name
3) If the term length is 2, it could match the first character of the first and last names in order.
The above filters are only applied after separating the strings if a space is encountered.
