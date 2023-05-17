grammar TemplateFormativeQuestion;

start: question;

question: matching NEWLINE
    | multiple_choice NEWLINE
    | short_answer NEWLINE
    | numerical NEWLINE
    | select_words NEWLINE
    | true_false NEWLINE
    ;




matching: MATCHING_OPT .+ (OPTION .+){4} SOLUTION (' ' .+ '|' DECIMAL ';'){4};

multiple_choice: MULTIPLE_CHOICE_OPT .+ (OPTION .+){4} SOLUTION (' ' .+ '|' DECIMAL ';'){1-4} NEWLINE ;

short_answer: SHORT_ANSWER_OPT .+ '?' SOLUTION (' ' .+ '|' DECIMAL ';')+ NEWLINE ;

numerical: NUMERICAL_OPT .+ '?' SOLUTION ' ' NUMBER '|' DECIMAL ';' NEWLINE ;

select_words: SELECT_WORDS_OPT .+ SOLUTION (' ' .+ '|' DECIMAL ';')+ NEWLINE ;

true_false: TRUE_FALSE_OPT .+ '?' SOLUTION ' ' ('True'|'False') '|' DECIMAL ';' NEWLINE ;


MATCHING_OPT : 'MQUES: ' ;
MULTIPLE_CHOICE_OPT :'MCQUES: ' ;
SHORT_ANSWER_OPT : 'SAQUES: ' ;
NUMERICAL_OPT : 'NQUES: ' ;
SELECT_WORDS_OPT : 'SWQUES: ' ;
TRUE_FALSE_OPT : 'TFQUES: ' ;

WEIGHT : 'WEIGHT' ;
OPTION : 'OPT: ' ;

SOLUTION : 'SOL:' ;

NUMBER : [1-9][0-9]* ;
DECIMAL : [0-9] '.' [0-9]{2} ;


NEWLINE : '\r'? '\n' ;