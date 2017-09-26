# Concordance

### Explanation
Concordance is a program which will take a String document written in English and output an alphabetical list of the words which form the document along with the number of occurrences of each word and the sentence indexes of each word's appearances.

#### Assumptions
* Sentences are written in English
* Sentences begin with capital letters
* A sentence ends with a period, question mark, or exclamation point and not any other type of punctuation e.g. colon, semicolon.

### Deficiencies
The program has a pretty gaping hole when it comes to abbreviated words followed by proper nouns "e.g. Encyclopedia Britannica".

### To Do List
* An option to read the document from a file
* Unit tests

### Executing the Program
Open the Concordance.java file, edit the "testString" local variable in the main() method, compile the program and execute it with no arguments.
I know it's really terrible that the user needs to edit the source code in order to run the program but I didn't want to use any of my scarce time struggling with Java I/O and instead spend my time tackling the problem itself. The meat of the program is all in the concordance(String document) method.

#### Amout of Time Spent
~3 hours of active coding and testing and a few more hours of passive background thinking while driving (don't code and drive!)

#### Outside sources
Regex for extracting words (with some massaging): https://stackoverflow.com/questions/26320622/what-is-the-java-regex-to-extract-words-and-abbreviations