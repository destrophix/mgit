# mgit

# Installation
### Prerequisites
- Java
- kotlin sdk

Step 1: Clone this repo.  
Step 2: cd into the mgit directory.  
Step 3: Run `kotlinc mgit.kt -include-runtime -d mgit.jar`  
Step 4: Add folowing line to your ~/.bashrc file.
```
    alias mgit="java -jar ~/Desktop/mgit/mgit.jar"
```
Step 5: Source your .bashrc with ```source ~/.bashrc``` command.

# Usage
Commands:
- init: Creates a hidden directory .mgit where all the internal working takes place.
- hash-object: It takes a file as an argument and creates a blob object for it inside ./.mgit/objects/
- cat-file: It takes the sha-1 value for the object and outputs its content.

# Development
If you plan to modify the source code. Below is one handy shorcut for compiling your files.
Add following function to your bashrc.
```
# run kotlin file
    kr(){
        filename=${1%.*}
        kotlinc ${filename}.kt -include-runtime -d ${filename}.jar
        shift 1
        java -jar ${filename}.jar $@
     }
```     
Note: Don't provide it the extension .kt with your file. It automatically adds it.
So the usage is ```kt mgit```.
