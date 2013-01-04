pgl-language
============
pgl (probabilistic grammar language) is a non-Turing-Complete language that can be used to easily make and 
use probabilistic grammars.

The program takes in a .pgl file and interprets it in java. The pgl program 
will produce a string of characters based on the rules specified in the .pgl file

to compile the interpretter

    make
    
to interpret a pgl program in java:

    java parse test.pgl

The current version has no error checking of any kind and will simply produce java runtime errors. Error checking
capabilities are in the works.

an example of the language is below


    terminals{
        //comments now implemented! yay!
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
