# Selenium Java Automation: OrangeHRM User Management

## Project Overview
This project automates a sequence of steps on the OrangeHRM demo website (https://opensource-demo.orangehrmlive.com/) using Selenium and Java with cucumber. The test script simulates adding a new user, verifying the number of records, searching for the user, and finally deleting the user.



## Test Case Details
The automated test script performs the following sequence of steps:

1. **Navigate to the Login Page**
   - URL: `https://opensource-demo.orangehrmlive.com/`

2. **Login with Admin Credentials**
   - Username: `Admin`
   - Password: `admin123`

3. **Navigate to the Admin Tab**
   - Click on the "Admin" tab on the left-hand side menu.

4. **Get and Verify Number of Records Found**
   - Extract the number of records displayed in the Admin user management table.

5. **Add a New User**
   - Click on the "Add" button.
   - Fill in the required information for a new user.
   - Click on the "Save" button.

6. **Verify Record Count Increment**
   - Verify that the number of records has increased by one.

7. **Search and Delete the New User**
   - Search for the new user based on the username.
   - Delete the new user.
   - Verify that the number of records has decreased by one.

## Running the Tests
   - Execute the test class directly from your IDE or use the Maven command:
     ```sh
     mvn clean test
     ``
