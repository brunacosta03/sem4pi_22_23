grammar TemplateFormativeQuestion;

start: question;

question: matching NEWLINE
    | multiple_choice NEWLINE
    | short_answer NEWLINE
    | numerical NEWLINE
    | select_words NEWLINE
    | true_false NEWLINE
    ;


matching: ;
multiple_choice: ;
short_answer: ;

numerical: NUMERICAL_OPT .+ '?' SOLUTION WEIGHT DECIMAL NEWLINE ;

true_false: TRUE_FALSE_OPT .+ '?' SOLUTION ('True'|'False') WEIGHT (DECIMAL|NUMBER) OPTION NEWLINE ;

select_words: ;







MATCHING_OPT : 'MQUES:' ;
MULTIPLE_CHOICE_OPT :'MCQUES:' ;
SHORT_ANSWER_OPT : 'SAQUES:' ;
NUMERICAL_OPT : 'NQUES:' ;
SELECT_WORDS_OPT : 'SWQUES:' ;
TRUE_FALSE_OPT : 'TFQUES:' ;

WEIGHT : 'WEIGHT' ;
OPTION : 'OPTION' ;
SOLUTION : 'SOL:'  ;



NUMBER : [1-9][0-9]* ;

DECIMAL : '0.' NUMBER ;


NEWLINE : '\r'? '\n' ;