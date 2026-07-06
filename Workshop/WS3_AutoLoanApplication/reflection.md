Here's your reflection formatted as a clean **Markdown (`reflect.md`) file.

```markdown
# Workshop 3 - Reflection

**Student:** Maheswaran  
**Student ID:** 048298137

## YouTube Video Walkthrough

The most significant challenge I encountered was managing the interactions between components within the MVC architecture. Tracking how data flowed between parent and child components was particularly complex. I struggled with manual dependency injection because I had to ensure dependencies were correctly passed from the `AppInitializer` to the appropriate controllers.

This became more difficult when a child component needed to interact with a service that had been provided to the parent. Passing data back to the parent, such as updating a saved user profile, required careful coordination to ensure the repository was updated and the view remained synchronized.

---

## Amortization Schedule Implementation

To ensure the accuracy and formatting of the amortization schedule, I implemented a dedicated `LoanAmortizationController` and an `AmortizationService`.

The `AmortizationService` handled the business logic by calculating the principal and interest for each payment based on the loan frequency and duration.

I used JavaFX Properties in the `AmortizationEntry` model to support property binding. Within the `LoanAmortizationController`, I implemented custom cell factories using `NumberFormat.getCurrencyInstance()` so that all financial values were displayed as properly formatted currency.

---

## Challenges and Lessons Learned

The most challenging part of this workshop was starting without a high-level design. This resulted in a monolithic `LoanController` that became difficult to maintain.

To improve the application's structure, I refactored the project by separating responsibilities and creating a dedicated `LoanAmortizationController` and corresponding view. This experience reinforced the importance of planning an application's architecture before development begins. Better planning would have significantly reduced the amount of refactoring required later in the project.

The limited time available made this process more difficult, but it ultimately reinforced the value of a well-structured MVC design.

---

## Skills and Knowledge Gained

Through this workshop, I gained valuable experience building desktop applications with JavaFX and Scene Builder.

Although I found Scene Builder less intuitive than writing CSS directly due to my background in web development, it helped me develop a better understanding of the JavaFX ecosystem.

I also strengthened my understanding of manual dependency injection. While I had previously worked with dependency injection in Angular, implementing it manually in Java required much more attention to component relationships and naming conventions.

To reduce confusion caused by similar class names, I adopted clearer and more descriptive names such as `CustomerListView`, making the application easier to navigate, understand, and maintain.

---

## Future Improvements

If I were to continue developing this application, I would implement the following enhancements:

### Persistent Database

Replace the in-memory repository with a persistent database so that user accounts, loan information, and saved interest rates remain available after the application closes.

### Vehicle Inventory Management

Develop a complete CRUD (Create, Read, Update, Delete) module for vehicle inventory management, allowing dealership staff to maintain inventory and automatically populate vehicle information into loan applications.

### Advanced Validation

Enhance the authentication system by performing real-time repository validation during the sign-up and login processes. This would verify user existence, prevent duplicate accounts, and provide immediate feedback to users.

---

## Conclusion

This workshop provided valuable hands-on experience applying the MVC architecture, manual dependency injection, JavaFX property binding, and object-oriented design principles in a real-world desktop application. It also highlighted the importance of architectural planning, maintainable code organization, and clear separation of concerns when developing larger software projects.
```
