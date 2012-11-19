pgl-language
============
pgl (probabilistic grammar language) is a non-Turing-Complete language that can be used to easily make and 
use probabilistic grammars.

The program takes in a .pgl file and produces a .java file, the java file can then be compiled and run which 
will produce a random string based on the rules specified in the .pgl file

to compile the compiler
    make
    
to compile a pgl program:
    java parse test.pgl
    javac test.java
    
to run a pgl program
    java test

The current version has no error checking of any kind and will simply produce errors when the java file is compiled

an example of the language is below


    terminals{
        a = "x";
        b = "y";
        c = "z";
    }
    nonterminals{
        A -> a (30) | B C (20) | A B C (50);
        B -> A C b (50) | b c (50);
        C -> c(100);
    }


terminals are the strings that are produced and nonterminals are can produce any number of terminals and nonterminals
the number following the production (10) is the probability of that production being chosen.