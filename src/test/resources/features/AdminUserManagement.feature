Feature: Admin User Management
  Scenario: Add and Delete Admin User
    Given user navigates to OrangeHRM login page
    When user enters username "Admin" and password "admin123"
    And clicks on login button
    Then user should be logged in successfully
    When user clicks on Admin menu
    And gets initial record count
    And clicks on Add button
    Then Verify Add User Is Displayed
    Then fills in the new user details
    And clicks on Save button
    Then verify record count increased by one
    When user searches for the new user
    And deletes the new user
    Then verify record count decreased by one