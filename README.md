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

# File Menager

Utility handling the files. The application display information about the files, folder content and size, and provide moving/renaming functionality. In essence, the application emulates a command line of Unix-like systems.

The program accept as an argument the absolute path to the folder where we start to work, and support the following commands:

`mv` WHAT WHERE – enables to transfer or rename a file if WHERE contains a file name without a path.

`ls` – displays the current folder contents (file and subfolder names and sizes in KB)

`cd FOLDER_NAME` – changes the current directory

<img width="585" alt="Снимок экрана 2023-09-25 в 17 46 13" src="https://github.com/Sanf1r/Java_Bootcamp/assets/100280376/bedb917f-626b-43f7-8338-53567e8df2af">

# Multithreading Download

Let's assume that we need to download a list of files from a network. Some files are downloaded faster, while others are slower.

To implement this functionality, we can obviously use multithreaded downloading where each thread loads a specific file. But what should we do if there are too many files? A large number of threads cannot be run at the same time. Therefore, many of them will be waiting.

In addition, we should bear in mind that continuously creating and completing threads is a very costly operation we should avoid. It makes more sense to start N threads at once and, when either of them finishes downloading the file, it can take on the next file in the queue.

We need to create files_urls.txt file (file name shall be explicitly specified in program code) where you specify a list of URLs of files to be downloaded. 

Example of the program operation:
```
$ java Program.java --threadsCount=3
Thread-1 start download file number 1
Thread-2 start download file number 2
Thread-1 finish download file number 1
Thread-1 start download file number 3
Thread-3 start download file number 4
Thread-1 finish download file number 3
Thread-2 finish download file number 2
Thread-1 start download file number 5
Thread-3 finish download file number 4
Thread-1 finish download file number 5
```

# First Work With Maven

Project to learn how to use Maven build tool.

External libraries:
- JCommander for the command line. 
- JCDP or JColor for using colored output

Example of program operation:

`$ mvn clean package`
`$ java -jar target/program-1.0-SNAPSHOT.jar`













