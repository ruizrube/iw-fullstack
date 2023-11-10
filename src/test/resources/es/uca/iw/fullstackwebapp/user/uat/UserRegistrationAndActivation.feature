Feature: Register and Activate user 
  As a user, I want to sign up on the web platform 

  Scenario: A person registers on the app and tries to activate their user
    When The user "student1" with email "student1@iw.uca.es" and password "password" registers on the app 
      And The user "student1" introduces their email "student1@iw.uca.es" and a verification code to activate
    Then The user gets a message with the text "Congrats. The user has been activated"
    
  Scenario: A non-registered person tries to activate their user
    When The user "student2" introduces their email "student2@iw.uca.es" and a verification code to activate 
    Then The user gets a message with the text "Ups. The user could not be activated"
    
   
