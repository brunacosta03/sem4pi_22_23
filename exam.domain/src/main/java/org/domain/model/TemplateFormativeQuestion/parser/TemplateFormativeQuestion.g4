grammar TemplateFormativeQuestion;

start: question;

question: matching NEWLINE
    | multiple_choice NEWLINE
    | short_answer NEWLINE
    | numerical NEWLINE
    | select_words NEWLINE
    | true_false NEWLINE
    ;




matching: MATCHING_OPT DESCRIPTION (OPTION OPTION_DESCRIPTION){4} SOLUTION (SOLUTION_TEXT){4};

multiple_choice: MULTIPLE_CHOICE_OPT DESCRIPTION (OPTION OPTION_DESCRIPTION){4} SOLUTION (SOLUTION_TEXT){1-4} NEWLINE ;

short_answer: SHORT_ANSWER_OPT DESCRIPTION_WITH_QUESTION_MARK SOLUTION (SOLUTION_TEXT)+ NEWLINE ;

numerical: NUMERICAL_OPT DESCRIPTION_WITH_QUESTION_MARK SOLUTION NUMERICAL_SOLUTION_TEXT NEWLINE ;

select_words: SELECT_WORDS_OPT DESCRIPTION SOLUTION (SOLUTION_TEXT)+ NEWLINE ;

true_false: TRUE_FALSE_OPT DESCRIPTION_WITH_QUESTION_MARK SOLUTION TRUE_FALSE_SOLUTION_TEXT NEWLINE ;


// options
MATCHING_OPT : 'MQUES: ' ;
MULTIPLE_CHOICE_OPT :'MCQUES: ' ;
SHORT_ANSWER_OPT : 'SAQUES: ' ;
NUMERICAL_OPT : 'NQUES: ' ;
SELECT_WORDS_OPT : 'SWQUES: ' ;
TRUE_FALSE_OPT : 'TFQUES: ' ;

// options
OPTION : 'OPT: ' ;
OPTION_DESCRIPTION : .+ ;

// solution
SOLUTION : 'SOL:' ;

// numbers
NUMBER : [1-9][0-9]* ;
DECIMAL : [0-9] '.' [0-9]{2} ;

// newlines
NEWLINE : '\r'? '\n' ;

// answer texts

SOLUTION_TEXT : ' ' .+ '|' DECIMAL ';' ;

NUMERICAL_SOLUTION_TEXT : ' ' NUMBER '|' DECIMAL ';' ;

TRUE_FALSE_SOLUTION_TEXT : ' ' TRUE_FALSE '|' DECIMAL ';' ;
TRUE_FALSE : 'True' | 'False' ;

DESCRIPTION : .+;

DESCRIPTION_WITH_QUESTION_MARK : .+ '?';



