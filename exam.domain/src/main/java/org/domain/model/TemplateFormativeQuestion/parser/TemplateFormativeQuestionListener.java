// Generated from D:/JavaProjects/sem4pi-22-23-8/exam.domain/src/main/java/org/domain/model/TemplateFormativeQuestion/parser\TemplateFormativeQuestion.g4 by ANTLR 4.12.0
package org.domain.model.TemplateFormativeQuestion.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TemplateFormativeQuestionParser}.
 */
public interface TemplateFormativeQuestionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TemplateFormativeQuestionParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(TemplateFormativeQuestionParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateFormativeQuestionParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(TemplateFormativeQuestionParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateFormativeQuestionParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(TemplateFormativeQuestionParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateFormativeQuestionParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(TemplateFormativeQuestionParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateFormativeQuestionParser#matching}.
	 * @param ctx the parse tree
	 */
	void enterMatching(TemplateFormativeQuestionParser.MatchingContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateFormativeQuestionParser#matching}.
	 * @param ctx the parse tree
	 */
	void exitMatching(TemplateFormativeQuestionParser.MatchingContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateFormativeQuestionParser#multiple_choice}.
	 * @param ctx the parse tree
	 */
	void enterMultiple_choice(TemplateFormativeQuestionParser.Multiple_choiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateFormativeQuestionParser#multiple_choice}.
	 * @param ctx the parse tree
	 */
	void exitMultiple_choice(TemplateFormativeQuestionParser.Multiple_choiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateFormativeQuestionParser#short_answer}.
	 * @param ctx the parse tree
	 */
	void enterShort_answer(TemplateFormativeQuestionParser.Short_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateFormativeQuestionParser#short_answer}.
	 * @param ctx the parse tree
	 */
	void exitShort_answer(TemplateFormativeQuestionParser.Short_answerContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateFormativeQuestionParser#numerical}.
	 * @param ctx the parse tree
	 */
	void enterNumerical(TemplateFormativeQuestionParser.NumericalContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateFormativeQuestionParser#numerical}.
	 * @param ctx the parse tree
	 */
	void exitNumerical(TemplateFormativeQuestionParser.NumericalContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateFormativeQuestionParser#select_words}.
	 * @param ctx the parse tree
	 */
	void enterSelect_words(TemplateFormativeQuestionParser.Select_wordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateFormativeQuestionParser#select_words}.
	 * @param ctx the parse tree
	 */
	void exitSelect_words(TemplateFormativeQuestionParser.Select_wordsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateFormativeQuestionParser#true_false}.
	 * @param ctx the parse tree
	 */
	void enterTrue_false(TemplateFormativeQuestionParser.True_falseContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateFormativeQuestionParser#true_false}.
	 * @param ctx the parse tree
	 */
	void exitTrue_false(TemplateFormativeQuestionParser.True_falseContext ctx);
}