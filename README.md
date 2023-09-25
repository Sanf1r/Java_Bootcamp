# Java_Bootcamp
Java Educational Projects

# Character Occurences

Program for counting a character occurrences in a text. 

The program will display the results in a histogram. This chart will show 10 most frequently occurring characters in descending order. 

If letters are encountered the same number of times, they should be sorted in a lexicographic order.

Each character may occur in text a great number of times. For that reason, the chart should be scalable. The maximum height of the displayed chart is 10, and the minimum is 0. 

Input data for the program is a string with a single "\n" character at the end (thus, a single long string can be used as input).

It is assumed that each input character can be contained in a char variable (Unicode BMP; for example, the code of letter "S" is 0053, maximum code value is 65535).

The maximum number of character occurrences is 999.

Example of program operation:

```
$ java Program

-> AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO42

 36
  #  35
  #   #
  #   #  27
  #   #   #
  #   #   #
  #   #   #
  #   #   #  14  12
  #   #   #   #   #   9
  #   #   #   #   #   #   7   4
  #   #   #   #   #   #   #   #   2   2
  D   A   S   W   L   K   O   T   E   R
```
# Money Transfer System

An internal money transfer system is an integral part of many corporate applications. 

Task is to automate a business process associated with transfers of certain amounts between participants of our system.

Each system user can transfer a certain amount to another user. We need to make sure that even if we lose the history of incoming and outgoing transfers for a specific user, we shall still be able to recover this information.

Inside the system, all money transactions are stored in the form of debit/credit pairs.

To recover the connection within such pairs, identifiers of each transaction should be used.

A transfer entry may obviously be lost in such a complex system—it may not be recorded for one of the users (to emulate and debug such a situation, a developer needs to be able to remove the transfer data from one of users individually). Since such situations are realistic, functionality is required for displaying all "unacknowledged transfers" (transactions recorded for one user only) and resolving such issues.

As a result, there is a functioning application with a console menu.

Menu functionality implemented in the respective class with a link field to TransactionsService.

Each menu item must be accompanied by the number of the command entered by a user to call an action.

The application support two launch modes—production (standard mode) and dev (where transfer information for a specific user can be removed by user ID, and a function that checks the validity of all transfers can be run). 

If an exception is thrown, a message containing information about the error shall appear, and user shall be provided an ability to enter valid data.

<img width="446" alt="Снимок экрана 2023-09-25 в 17 35 05" src="https://github.com/Sanf1r/Java_Bootcamp/assets/100280376/890391b6-20a1-4c34-a441-96f63aec28e9">

