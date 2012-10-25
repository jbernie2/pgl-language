public enum types {
	//typeName ({first-set},{follow-set}),
    VAR, 	//terminals and nonterminals
    NUM, 	//numbers
    LIT, 	//literals
    GEN, 	//'->' symbol
    EQ,  	//'='
    OR,  	//'|'
    LP,  	//'('
    RP,  	//')'
    LB,  	//'{'
    RB,  	//'}'
    END, 	//';'
    EOF, 	//End of file symbol
    ERR,    //Error
    
    //reserve words
    DEFINE, //define
    TDEF,   //terminals
    NTDEF   //nonterminals
}
