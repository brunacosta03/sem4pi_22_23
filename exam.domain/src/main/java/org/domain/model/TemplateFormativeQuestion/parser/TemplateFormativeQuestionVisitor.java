// Generated from D:/JavaProjects/sem4pi-22-23-8/exam.domain/src/main/java/org/domain/model/TemplateFormativeQuestion/parser\TemplateFormativeQuestion.g4 by ANTLR 4.12.0
package org.domain.model.TemplateFormativeQuestion.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TemplateFormativeQuestionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TemplateFormativeQuestionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TemplateFormativeQuestionParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(TemplateFormativeQuestionParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateFormativeQuestionParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(TemplateFormativeQuestionParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateFormativeQuestionParser#matching}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatching(TemplateFormativeQuestionParser.MatchingContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateFormativeQuestionParser#multiple_choice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiple_choice(TemplateFormativeQuestionParser.Multiple_choiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateFormativeQuestionParser#short_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShort_answer(TemplateFormativeQuestionParser.Short_answerContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateFormativeQuestionParser#numerical}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumerical(TemplateFormativeQuestionParser.NumericalContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateFormativeQuestionParser#select_words}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_words(TemplateFormativeQuestionParser.Select_wordsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateFormativeQuestionParser#true_false}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue_false(TemplateFormativeQuestionParser.True_falseContext ctx);
}