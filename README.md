## **Assignment 1 Reflection 1**

I created two features in the application, the edit and delete features. Now when a user creates a product there will be an "actions" column in 
the product list page, where the user can press a delete or edit button. When the user clicks delete, a window will appear asking the user to confirm
his deletion and if they confirm it, the product will be removed from the product list. when they press the edit button it will redirect them back
to the create product page with the name and quantity of the product already written from before, and they will then be able to change the content 
of the product. I think I followed the clean code principles relatively well. All of my code is straightforward and simple as I gave all the functions simple names 
such as editproduct,findall,createproductpost,etc. I also added some comments to try to explain what methods I used for the functions. I can still improve the code
by Handling errors properly such as adding an error 404 page if a product or page doesn't exist. I could've used some method to ensure that the each fields
in the create product page are filled in. 

## **Assignment 1 Reflection 2**
Creating unit tests gives me a feeling of assurance that my program is working correctly, as the unit tests are a great way for me to check
if logic of my program is correct. But getting 100% on our unit tests doesn't automatically mean that our code is perfect as it only means that the
code we wrote was executed properly.

A new functional test suite would introduce duplications of code because it will follow the same setup and variables as existing tests 
which can make future updates more difficult. What we should do is to use a similar setup logic to make it more reusable. 

## **Assignment 2 Reflection 1**
List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them. 
The code quality issue that I fixed was removing the field injections. I followed what Sonarcloud did and added constructor injections instead.I did this 
to prevent the creation of objects
in an invalid state.

Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of 
Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

I think my workflow met the definition of CI/CD. This is because my workflow successfully does unit tests, does a code
quality check, and security analysis after every push using the scorecard, sonarcloud, and Ci yml files. The workflow also deploys after every push and builds the docker
image using Koyeb.

## **Assignment 3 Reflection 1**
I implemented several principles. I implemented SRP by separating Controller into two files
(car controller and product controller). I implemented OCP by making CarServiceImpl.java  open to extension
to make it possible to  add additional service without changing the core functionality. I implemented LSP by making sure 
the implementation classes doesn't affect the correctness of the application. I implemented ISP by creating specific interfaces
in the car to make sure that unnecessary methods are implemented.I implemented DIP by modifying the car controller to depend on concrete 
implementations and to depend on abstractions.

Advantages of using SOLID:
1. OCP makes it easier for us to extend the functionalities of a code. An example would be creating an application that can
send information to users by email. Later on we can add support for other platforms (SMS)
2. LSP makes sure that we can extend the functionality of the subclasses without affecting the logic of the code. Creating a code that depends
on abstraction will make it possible the code be reusable in other aspects of the application. 
3. SRP makes sure that every class is useful and has its own specific use, to make it easier to fix errors. If the delete service
is broken, we only need to modify the deleteservice instead of other classes.

Disadvantages of not using SOLID:
1. Not implementing SRP can make it more difficult to try to update or fix a code since the organization
of the classes is not good
2. Not implementing OCP means that implementing new features would have to modify existing classes that have no bugs.
3. Not implementing DIP would mean that classes depend on concrete implementations which can make unit testing more difficult.

## **Assignment 4 Reflection 1**

Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.
You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.
