package org.domain.model.TemplateFormativeQuestion.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.domain.model.*;
import org.domain.model.TemplateFormativeQuestion.TemplateFormativeQuestion;

import java.util.ArrayList;
import java.util.List;

public class FormativeQuestionBuilderListener implements TemplateFormativeQuestionListener{
    private QuestionDescription description;
    private QuestionType type;

    private List<Answer> answers = new ArrayList<>();
    private List<Solution> solutions = new ArrayList<>();


    public TemplateFormativeQuestion getResult() {
        return new TemplateFormativeQuestion(description, type, answers, solutions);
    }

    @Override
    public void enterStart(TemplateFormativeQuestionParser.StartContext ctx) {}

    @Override
    public void exitStart(TemplateFormativeQuestionParser.StartContext ctx) {}

    @Override
    public void enterQuestion(TemplateFormativeQuestionParser.QuestionContext ctx) {}

    @Override
    public void exitQuestion(TemplateFormativeQuestionParser.QuestionContext ctx) {}

    @Override
    public void enterMatching(TemplateFormativeQuestionParser.MatchingContext ctx) {
        type = QuestionType.MATCHING;

        description = new QuestionDescription(ctx.DESCRIPTION().getText());
    }

    @Override
    public void exitMatching(TemplateFormativeQuestionParser.MatchingContext ctx) {}

    @Override
    public void enterMultiple_choice(TemplateFormativeQuestionParser.Multiple_choiceContext ctx) {
        type = QuestionType.MULTIPLE_CHOICE;

        description = new QuestionDescription(ctx.DESCRIPTION().getText());
    }

    @Override
    public void exitMultiple_choice(TemplateFormativeQuestionParser.Multiple_choiceContext ctx) {}

    @Override
    public void enterShort_answer(TemplateFormativeQuestionParser.Short_answerContext ctx) {
        type = QuestionType.SHORT_ANSWER;

        description = new QuestionDescription(ctx.DESCRIPTION_WITH_QUESTION_MARK().getText());
    }

    @Override
    public void exitShort_answer(TemplateFormativeQuestionParser.Short_answerContext ctx) {}

    @Override
    public void enterNumerical(TemplateFormativeQuestionParser.NumericalContext ctx) {
        type = QuestionType.NUMERICAL;

        description = new QuestionDescription(ctx.DESCRIPTION_WITH_QUESTION_MARK().getText());
    }

    @Override
    public void exitNumerical(TemplateFormativeQuestionParser.NumericalContext ctx) {}

    @Override
    public void enterSelect_words(TemplateFormativeQuestionParser.Select_wordsContext ctx) {
        type = QuestionType.MISSING_WORDS;

        description = new QuestionDescription(ctx.DESCRIPTION().getText());
    }

    @Override
    public void exitSelect_words(TemplateFormativeQuestionParser.Select_wordsContext ctx) {}

    @Override
    public void enterTrue_false(TemplateFormativeQuestionParser.True_falseContext ctx) {
        type = QuestionType.TRUE_FALSE;

        description = new QuestionDescription(ctx.DESCRIPTION_WITH_QUESTION_MARK().getText());
    }

    @Override
    public void exitTrue_false(TemplateFormativeQuestionParser.True_falseContext ctx) {}

    @Override
    public void visitTerminal(TerminalNode terminalNode) {
        if(terminalNode.getSymbol().getType() == TemplateFormativeQuestionParser.OPTION) {
            answers.add(new Answer(terminalNode.getText()));
        }
        else if(
                terminalNode.getSymbol().getType() == TemplateFormativeQuestionParser.ANSWER_TEXT ||
                terminalNode.getSymbol().getType() == TemplateFormativeQuestionParser.NUMERICAL_ANSWER_TEXT ||
                terminalNode.getSymbol().getType() == TemplateFormativeQuestionParser.TRUE_FALSE_ANSWER_TEXT
        ) {
            String solutionText = terminalNode.getText();

            solutionText = solutionText
                    .replace(" ", "")
                    .replace(";", "");

            solutions.add(new Solution(
                    new SolutionWeight(Double.parseDouble(solutionText.split("|")[1])),
                    new SolutionDescription(solutionText.split("|")[0])
            ));

        }
    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {}

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {}

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {}
}
