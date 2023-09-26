# Java_Bootcamp
Java Educational Projects

All projects done using Java 1.8.

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

# File Manager

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

# Testing With Java

Project to get used to JUnit 5 and Mockito tools.

* Testing of simple class.

Implemented NumberWorker class that contains the following functionality:
```java
public boolean isPrime(int number) {
  ...
}

```
This method determines if a number is prime and returns true/false for all natural (positive integer) numbers. For negative numbers, as well as 0 and 1, the program shall throw an unchecked exception. IllegalNumberException.
```java
public int digitsSum(int number) {
  ...
}
```

This method returns the sum of digits of a source number.

NumberWorkerTest class that implements the module testing logic. Methods of NumberWorkerTest class shall check the correct operation of NumberWorker methods for various input data:
1. isPrimeForPrimes method to check isPrime using prime numbers (at least three)
2. isPrimeForNotPrimes method to check isPrime using composite numbers (at least three)
3. isPrimeForIncorrectNumbers method to check isPrime using incorrect numbers (at least three)
4. a method to check digitsSum using a set of at least 10 numbers

* Testing of DBMS

Do not use a heavy DBMS (like PostgreSQL) to implement integration testing of components that interact with the database. It is best to create a lightweight in-memory database with prearranged data.  

Implemented DataSource creation mechanism for HSQL DBMS. To do so, connect spring-jdbc and hsqldb dependencies to the project. Prepare schema.sql and data.sql files where you will describe product table structure and test data (at least five).

Product table structure:
- identifier
- name
- price

There also EmbeddedDataSourceTest class. In this class, implement init() method marked with @BeforeEach annotation. In this class, implement functionality to create DataSource using EmbeddedDataBaseBuilder (a class in spring-jdbc library). Implement a simple test method to check the return value of getConnection() method created by DataSource (this value must not be null).

Also implemented ProductsRepository/ProductsRepositoryJdbcImpl interface/class pair with the following methods:

```java
List<Product> findAll()

Optional<Product> findById(Long id)

void update(Product product)

void save(Product product)

void delete(Long id)
```
ProductsRepositoryJdbcImplTest class containing methods checking repository functionality using the in-memory database mentioned in the previous exercise. In this class, you should prepare in advance model objects that will be used for comparison in all tests.

* Testing with Mockito

An important rule for module tests:  an individual system component shall be tested without calling its dependencies' functionality. This approach allows developers to create and test components independently, as well as postpone the implementation of specific application parts.

Now you need to implement the business logic layer represented by UsersServiceImpl class. This class contains a user authentication logic. It also has a dependency on UsersRepository interface (in this task, you do not need to implement this interface).

UsersRepository interface (that you have described) shall contain the following methods:
```java
User findByLogin(String login);
void update(User user);
```
It is assumed that findByLogin method returns a Userobject found via login, or throws EntityNotFoundException if no user is found with the login specified. Update method throws a similar exception when updating a user that does not exist in the database.

User entity shall contain the following fields:
- Identifier
- Login
- Password
- Authentication success status (true - authenticated, false - not authenticated)

In turn, UsersServiceImpl class calls these methods inside the authentication function:
```java
boolean authenticate(String login, String password)
```

This method:
1.	Checks if a user has been authenticated in the system using this login. If authentication was performed, AlreadyAuthenticatedException must be thrown.
2.	The user with this login is retrieved from UsersRepository.
3.	If the retrieved user password matches the specified password, the method sets the status of the authentication success for the user, updates its information in the database and returns true. If passwords mismatch, the method returns false.

Goal is to:
1.	Create UsersRepository interface
2.	Create UsersServiceImpl class and authenticate method
3.	Create a module test for UsersServiceImpl class

Since objective is to check correct operation of authenticate method independently of UsersRepository component, i use mock object and stubs of findByLogin and update methods (see Mockito library).

Authenticate method shall be checked for three cases:
1.	Correct login/password (check calling update method using verify instruction of Mockito library)
2.	Incorrect login
3.	Incorrect password

<img width="558" alt="Снимок экрана 2023-09-25 в 18 16 24" src="https://github.com/Sanf1r/Java_Bootcamp/assets/100280376/d791a4af-b3a5-4287-afc0-99ae5ace3645">

# Java Spring

Spring Framework is an integral part of the most Java-based corporate systems. This framework makes it much easier to configure applications and relate components to each other. Due to this, a developer can fully focus on business logic implementation.

Spring operation principle is fully based on DI/IoC patterns.

The central concept in Spring framework is a bean (component) that represents an object inside an ApplicationContext container. The container also creates links between beans.

There are several ways to configure beans:
1. Using an xml.file.
2. Using a Java configuration (configuring with annotations).
3. Combined configuration.

XML configuration allows to change application's behavior without a reassembly. In turn, Java configuration makes code more developer-friendly.

* With xml.file

Let's implement a loosely-coupled system comprised of a set of components (beans) and compliant with IoC/DI principles.

Let's assume there is Printer interface designed to display a specific message.

This class has two implementations: PrinterWithDateTimeImpl and PrinterWithPrefixImpl. The first class outputs messages by specifying output date/time using LocalDateTime, while the second class can be used to set a text prefix for a message.

In turn, both Printer implementations have a dependency on Renderer interface that sends messages onto the console. Renderer also has two implementations: RendererStandardImpl (outputs a message via standard System.out) and RendererErrImpl (outputs messages via System.err).

Renderer also has a dependency on PreProcessor interface that pre-processes messages. Implementation of PreProcessorToUpperImpl translates all letters into upper case, while implementation of PreProcessorToLower translates all letters into lower case.

An example of code using these classes in a standard way:
```java
public class Main {
   public static void main(String[] args) {
       PreProcessor preProcessor = new PreProcessorToUpperImpl();
       Renderer renderer = new RendererErrImpl(preProcessor);
       PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
       printer.setPrefix("Prefix");
       printer.print("Hello!");
   }
}
```
Running this code will deliver the following result:

```
PREFIX HELLO!
```

Using these components with Spring looks as follows:
```java
public class Main {
   public static void main(String[] args) {
       ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
       Printer printer = context.getBean(“printerWithPrefix”, Printer.class);
       printer.print("Hello!");
   }
}
```

* With Annotation

Now configure Spring-application configuration mechanisms using annotations. To do so, use the configuration class marked as @Configuration. Inside this class, you need to describe beans for connecting to DataSource DB using @Bean annotation. As in the previous task, connection data shall be located inside db.properties file. You also need to make sure context.xml is not present.

Also implement UsersService/UsersServiceImpl interface/class pair with a dependency on UsersRepository declared in it. Insertion of correct repository bean shall be implemented using @Autowired annotation (similarly, you need to bind DataSource inside repositories). Collisions in automatic binding shall be resolved with @Qualifier annotation.

Beans for UsersService and UsersRepository shall be defined using @Component annotation.

In UsersServiceImpl, implement String signUp(String email)method that registers a new user and saves its details in DB. This method returns a temporary password assigned to the user by the system (this information should also be saved in the database).

To check if your service works correctly, implemented an integration test for UsersServiceImp using an in-memory database (H2 or HSQLDB). The context configuration for test environment (DataSource for in-memory database) shall be described in a separate TestApplicatoinConfig class. This test shall check if a temporary password has been returned in signUp method.
















