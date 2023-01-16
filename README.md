# Simple Asset Management Systm Backend

First time run (MUST DO):

- Create postgresql database named "simple_asset_management_system".
- Run all the sql code (open asset.sql in dbeaver then 'ctrl + A' and 'ctrl + enter').
- Open SecurityConfig (in com.test.simpleasset.config package) :
  + Uncomment /users/** matchers.
- Open UserController (in com.test.simpleasset.controller package) 
  + Comment the PreAuthorize to create super admin for the first time.
- Open UserService (in com.test.simpleasset.service package) :
  + Uncomment principalId = 1l and comment getPrinciple.getId().
  + Uncomment the ADMIN code and Comment the NON_ADMIN.
- Run SimpleassetApplication.
- Open http://localhost:8080/swagger-ui/index.html#/user-controller/insert in a browser.
  + Enter the correct data (especially email, since the password would be sent there. You can input random file code and extension, for example 'aaa' and 'png').
- Undo the comment and uncomment steps above (3,4,5) till its like in the original state.
- Run front end (ngserve).

Explanation :
The steps above is the required steps to create Super Admin account, which is mandatory to make the app run normally (with all the provided securities).
