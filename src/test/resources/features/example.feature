Feature: As I user I want to create a calculator

  Background:
    Given Initialize driver

  @smoke
  Scenario Outline: All required fields should be filled in
    Given The user opens <link>
    When The user searches text
    When Switch to frame
    When Calculator information is filled in
    When Send filled information to estimation
    When Send email about the calculator cost information
    Then Calculator's estimated cost is the same as expected
    Then Calculator's estimated cost from received email is the same as expected
    Examples:
      | link                      |
      | https://cloud.google.com/ |