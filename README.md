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
