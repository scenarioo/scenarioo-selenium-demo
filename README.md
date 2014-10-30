# Scenarioo Selenium Demo

This demo is a full featured testing example, that tests a demo web application (see project 'demo-my-tiny-todo') using Selenium and Scenarioo.

The example is intended as a learning example, useful for workshops and trainings.

The application under test is a small tiny todo list example application. For this example we use the version 1.4.3 which you can download
here: http://www.mytinytodo.net/. Please use this installation guide to set it up http://www.mytinytodo.net/faq.php#install.
We used lamp to set up the demo application as quickly as possible.

Preconditions:
To run this demo, you have to checkout the project 'My-Tiny-TODO-List' under https://github.com/scenarioo/demo-my-tiny-todo
and have the webapplication up and running, before running the tests (using a PHP server running on localhost).

## Deployment of Tiny Todo List ##
Your tiny-todo-list application should be deployed under http://localhost/mytinytodo/. If you like to use another path you will need to change
the url in the base webtest class TinyTodoWebTest.java.

## Scenarioo deployment ##
Download the scenarioo client from scenarioo.org and deploy it to a local tomcat (or any other servlet engine). Afterwards you can set up and configure scenarioo. More information can be found here: https://github.com/scenarioo/scenarioo/wiki/Scenarioo%20Web%20Application%20Setup.

Set your Documentation Data Directory Path directory to your checked out selenium demo directory + '/demo' (Scenarioo Webapp -> Manage -> General Settings). Example path '/home/dev/Documents/scenarioo-selenium-demo/docu'.

## Execute webtests ##
You can now execute all tests by using eclipse or gradle:
gradle test

Congratulations, you've just created your first scenarioo documentation!