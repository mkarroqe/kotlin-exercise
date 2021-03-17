# kotlin-exercise
An exercise in learning [Kotlin](https://kotlinlang.org/) and practicing [Test Driven Development](https://www.agilealliance.org/glossary/tdd/#q=~(infinite~false~filters~(postType~(~'page~'post~'aa_book~'aa_event_session~'aa_experience_report~'aa_glossary~'aa_research_paper~'aa_video)~tags~(~'tdd))~searchTerm~'~sort~false~sortDirection~'asc~page~1)).

## Testing
Test Cases can be found in [`src/test/kotlin/test.kt`](https://github.com/mkarroqe/kotlin-exercise/blob/main/src/test/kotlin/test.kt). Run the `RunAllTests` class to test cases for each step, or run each class individually.

`mvn test` will run `RunAllTests`.

## Requirements
### Step 1: Build an Orders Service
> **Test class**: `OrderServiceTest`

- Build a service that’s able to receive simple orders of shopping goods from the
command line
- Apples cost 60 cents and oranges cost 25 cents
- The service should be able to calculate that: `[ Apple, Apple, Orange, Apple ] => $2.05`
- Make reasonable assumptions about the inputs to your solution; for example, candidates may take a list of strings as input
- Add unit tests that validate your code

### Step 2: Simple offer
> **Test class**: `SimpleOffersTest`

- The shop decides to introduce two new offers
- Buy one get one free on Apples
- 3 for the price of 2 on Oranges
- Update your functions & unit tests accordingly 

### Step 3: Build a Customer Notification Service
> **Test class**: `NotificationTest`

- Customers complained that they don’t know if their orders made it through or not as there is no notification of success
- Build a service that listens for when orders are complete and sends a notification to the customer regarding its status and estimated delivery time
- The Mail service subscribes to events from the Orders service and publishes an appropriate event that the customer (you) is able to read from the terminal

### Step 4: Limited Stock
> **Test class**: `StockTest`

- Stock can now run out, this means that customers need to be notified that their order failed
