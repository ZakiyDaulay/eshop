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

## **Assignment 3 Reflection 1**
List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them. 
The code quality issue that I fixed was removing the field injections. I followed what Sonarcloud did and added constructor injections instead.I did this 
to prevent the creation of objects
in an invalid state.

Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of 
Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

I think my workflow met the definition of CI/CD. This is because my workflow succesfully does unit tests, does a code
quality check, and security analysis after every push using the scorecard, sonarcloud, and Ci yml files. The workflow also deploys after every push and builds the docker
image using Koyeb.
