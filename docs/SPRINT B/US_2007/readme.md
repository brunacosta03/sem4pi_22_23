# US 2007

## 1. Context

In Sprint B client wants us to develop add/update exam questions to a repository of exam questions to be used in automatic formative exams. The team must also create a structure that validates the creation of questions that will be inserted into the repository.

## 2. Requirements

As Teacher I want to add/update exam questions to a repository of exam questions to be used in automatic formative exams

This includes only the specification of single questions to be used only in automatic formative exams, not on regular exams.
The support for exams (its design, feedback and grading) must follow specific technical requirements, specified in LPROG. 
The ANTLR tool should be used (https://www.antlr.org/).

## 3. Analysis

**Question Types:**

* Matching - A list of sub-questions is provided, along with a list of answers. The respondent must "match" the correct answers with each question.

* Multiple Choice - With the Multiple Choice question type you can create single-answer and multiple-answer questions and weight individual answers

* Short Answer - In response to a question, the respondent types a word or phrase. There
may be several possible correct answers, with different grades. Answers may or may not
be sensitive to case.

* Numerical - From the student perspective, a numerical question looks just like a short-answer question. The difference is that numerical answers are allowed to have an accepted error. This allows a continuous range of answers to be se

* Select Missing Words - Students select a missing word or phrase from a dropdown
menu. Items may be grouped and used more than once.

* True/False - In response to a question, the respondent selects from two options: True
or False.


## 4. Grammar definitions

|     CODE    |      MEANING       |
|:-----------:|:------------------:|
|  MQUES:     | Matching question  |
|  MCQUES:    | Multiple Choice question  |
|  SAQUES:    | Short Answer question  |
|  NQUES:     | Numerical question  |
|  SWQUES:    | Select missing words question  |
|  TFQUES:    | True/False question  |
|  OPT:    | Option number      |
|  SOL:       | Question solution  |

**Matching question**

The team decided that the question options should appear after the ":".
Should There must 4 options after the ":". To match the question options there must always be 4 match options.
The solution must have the same number of ";" and "-" that the options of the question. That way all the question options will have a match answer.

    MQUES: Identify the type of these creatures: Ant; Cow; Dog; Sparrow;
    OPT: Insect
    OPT: Mammal
    OPT: Mammal
    OPT: Bird
    SOL: Ant-Insect|0.25; Cow-Mammal|0.25; Dog-Mammal|0.25; Bird-Sparrow|0.25;

**Multiple Choice**

The team decided that each multiplie choice should always have 4 options. The number of solutions must always be presented followed by a ";". This way there must be the same number of weight after "|" in solution.

    MCQUES: Choose all animals that have 4 paws
    OPT: Ant
    OPT: Cow
    OPT: Dog
    OPT: Sparrow
    SOL: Cow|0.25; Dog|0.25;

**Short Answer**

The team decided that short answer question should always have a "?" in final of question. The possible solutions must always be separated by a ";". This way there must be the same number of weight after "|" in solution.

    SAQUES: What is a rabbit?
    SOL: animal|0.25; mammal|0.50; vertebrate|0.50;

**Numerical**

The team decided that short answer question should always have a "?" in final of question. The possible solution must be a number (integer or decimal). There is only one solution. This way there must be the same number of weight after "|" in solution.

    NQUES: How many paws does a dog have?
    SOL: 4|0.25;

**Select Missing Words**

The team decided that the missing words should appear between "[]" so that the student knows which word he needs to fill. There must be the same number of "[OPTION]" then options available for the student to fill in the blanks. In turn, each option placed in the correct place will have its score. The solution must be placed in order of missing fields.

    SWQUES: On a farm there are different types of animals. Namely the [OPT]. He serves to take care of the other [OPT]. No animal needs to move away from the [OPT].
    OPT: animals
    OPT: dog
    OPT: place
    SOL: dog|0.50; animals|0.50; place|0.50;

**True/False**

The team decided that true/false question should always have a "?" in final of question. The question only have 1 solution that can be "True" or "False".

    TFQUES: Does the dog have 4 paws?
    SOL: True|1.00;
    

## 5. Implementation


```java

````



## 6. Integration/Demonstration


```txt

```