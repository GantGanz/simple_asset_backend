# Simple Asset Management System Backend

How to run :
- Create postgresql database named "simple_asset_management_system".
- Run asset.sql (https://github.com/GantGanz/simple_asset_backend/blob/main/asset.sql).
- Run SimpleassetApplication.
- Open http://localhost:8080/swagger-ui/index.html#/login-controller/login in a browser.
- Login admin:
  + username: admin@gmail.com
  + password: admin
- Login member:
  + username: member@gmail.com
  + password: member
- Front end : https://github.com/GantGanz/simple_asset_frontend.


About the Simple Asset Management System app :
- This is an app for managing and tracking companies assets.
- The assets has their own 'type' and 'status' that might change on each transaction. 
- The assets can be assigned to a place, employee (which must be registered first), and to a general type asset (must be assigned with a component type asset. ex: a RAM (component) can be assigned to a Laptop (general)). 
- An asset might have an expired date, and a 'check out' might have a specified return date. 
- An email would be sent to the new user when you create an account or to the super admin when you create an asset. (go test it by creating user with your own email)

How to use the app :

Super Admin can create stores, companies, providers, and employees. Which then can be used to create Assets.

Non Admin can check out multiple assets, and then check in half or all of the returned assets.
