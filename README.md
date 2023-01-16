# Simple Asset Management Systm Backend

First time run :
(MUST DO, since the steps below is the required steps to create the Super Admin account, which is mandatory to make the app run normally, with all the provided securities)

- 1.Create postgresql database named "simple_asset_management_system".
- 2.Run all the sql code (open asset.sql in dbeaver then 'ctrl + A' and 'ctrl + enter').
- 3.Open SecurityConfig (in com.test.simpleasset.config package) :
  + 3a.Uncomment /users/** matchers.
- 4.Open UserController (in com.test.simpleasset.controller package) 
  + 4a.Comment the PreAuthorize to create super admin for the first time. (Don't comment the Post Mapping)
- 5.Open UserService (in com.test.simpleasset.service package) :
  + 5a.Uncomment principalId = 1l and comment getPrinciple.getId().
  + 5b.Uncomment the ADMIN code and Comment the NON_ADMIN.
- 6.Run SimpleassetApplication.
- 7.Open http://localhost:8080/swagger-ui/index.html#/user-controller/insert in a browser.
  + 7a.Enter the correct data !IMPORTANT (especially email, since the password would be sent there. If you already create an account with a random email, you must delete the database again and follow the instruction from the start all over again). You can input random file code and extension though, for example, 'aaa' and 'png'.
- 8.Undo the comment and uncomment steps above (3,4,5) till its like in the original state.
- 9.Follow the front end steps.
