// Generated from D:/JavaProjects/sem4pi-22-23-8/exam.domain/src/main/java/org/domain/model/TemplateFormativeQuestion/parser\TemplateFormativeQuestion.g4 by ANTLR 4.12.0
package org.domain.model.TemplateFormativeQuestion.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class TemplateFormativeQuestionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MATCHING_OPT=1, MULTIPLE_CHOICE_OPT=2, SHORT_ANSWER_OPT=3, NUMERICAL_OPT=4, 
		SELECT_WORDS_OPT=5, TRUE_FALSE_OPT=6, OPTION=7, OPTION_DESCRIPTION=8, 
		SOLUTION=9, NUMBER=10, DECIMAL=11, NEWLINE=12, ANSWER_TEXT=13, NUMERICAL_ANSWER_TEXT=14, 
		TRUE_FALSE_ANSWER_TEXT=15, TRUE_FALSE=16, DESCRIPTION=17, DESCRIPTION_WITH_QUESTION_MARK=18;
	public static final int
		RULE_start = 0, RULE_question = 1, RULE_matching = 2, RULE_multiple_choice = 3, 
		RULE_short_answer = 4, RULE_numerical = 5, RULE_select_words = 6, RULE_true_false = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "question", "matching", "multiple_choice", "short_answer", "numerical", 
			"select_words", "true_false"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'MQUES: '", "'MCQUES: '", "'SAQUES: '", "'NQUES: '", "'SWQUES: '", 
			"'TFQUES: '", "'OPT: '", null, "'SOL:'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "MATCHING_OPT", "MULTIPLE_CHOICE_OPT", "SHORT_ANSWER_OPT", "NUMERICAL_OPT", 
			"SELECT_WORDS_OPT", "TRUE_FALSE_OPT", "OPTION", "OPTION_DESCRIPTION", 
			"SOLUTION", "NUMBER", "DECIMAL", "NEWLINE", "ANSWER_TEXT", "NUMERICAL_ANSWER_TEXT", 
			"TRUE_FALSE_ANSWER_TEXT", "TRUE_FALSE", "DESCRIPTION", "DESCRIPTION_WITH_QUESTION_MARK"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TemplateFormativeQuestion.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TemplateFormativeQuestionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			question();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionContext extends ParserRuleContext {
		public MatchingContext matching() {
			return getRuleContext(MatchingContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(TemplateFormativeQuestionParser.NEWLINE, 0); }
		public Multiple_choiceContext multiple_choice() {
			return getRuleContext(Multiple_choiceContext.class,0);
		}
		public Short_answerContext short_answer() {
			return getRuleContext(Short_answerContext.class,0);
		}
		public NumericalContext numerical() {
			return getRuleContext(NumericalContext.class,0);
		}
		public Select_wordsContext select_words() {
			return getRuleContext(Select_wordsContext.class,0);
		}
		public True_falseContext true_false() {
			return getRuleContext(True_falseContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_question);
		try {
			setState(36);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MATCHING_OPT:
				enterOuterAlt(_localctx, 1);
				{
				setState(18);
				matching();
				setState(19);
				match(NEWLINE);
				}
				break;
			case MULTIPLE_CHOICE_OPT:
				enterOuterAlt(_localctx, 2);
				{
				setState(21);
				multiple_choice();
				setState(22);
				match(NEWLINE);
				}
				break;
			case SHORT_ANSWER_OPT:
				enterOuterAlt(_localctx, 3);
				{
				setState(24);
				short_answer();
				setState(25);
				match(NEWLINE);
				}
				break;
			case NUMERICAL_OPT:
				enterOuterAlt(_localctx, 4);
				{
				setState(27);
				numerical();
				setState(28);
				match(NEWLINE);
				}
				break;
			case SELECT_WORDS_OPT:
				enterOuterAlt(_localctx, 5);
				{
				setState(30);
				select_words();
				setState(31);
				match(NEWLINE);
				}
				break;
			case TRUE_FALSE_OPT:
				enterOuterAlt(_localctx, 6);
				{
				setState(33);
				true_false();
				setState(34);
				match(NEWLINE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MatchingContext extends ParserRuleContext {
		public TerminalNode MATCHING_OPT() { return getToken(TemplateFormativeQuestionParser.MATCHING_OPT, 0); }
		public TerminalNode DESCRIPTION() { return getToken(TemplateFormativeQuestionParser.DESCRIPTION, 0); }
		public TerminalNode SOLUTION() { return getToken(TemplateFormativeQuestionParser.SOLUTION, 0); }
		public TerminalNode OPTION() { return getToken(TemplateFormativeQuestionParser.OPTION, 0); }
		public TerminalNode OPTION_DESCRIPTION() { return getToken(TemplateFormativeQuestionParser.OPTION_DESCRIPTION, 0); }
		public TerminalNode ANSWER_TEXT() { return getToken(TemplateFormativeQuestionParser.ANSWER_TEXT, 0); }
		public MatchingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterMatching(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitMatching(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitMatching(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingContext matching() throws RecognitionException {
		MatchingContext _localctx = new MatchingContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_matching);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(MATCHING_OPT);
			setState(39);
			match(DESCRIPTION);
			{
			setState(40);
			match(OPTION);
			setState(41);
			match(OPTION_DESCRIPTION);
			}
			setState(44);
			match(SOLUTION);
			{
			setState(45);
			match(ANSWER_TEXT);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Multiple_choiceContext extends ParserRuleContext {
		public TerminalNode MULTIPLE_CHOICE_OPT() { return getToken(TemplateFormativeQuestionParser.MULTIPLE_CHOICE_OPT, 0); }
		public TerminalNode DESCRIPTION() { return getToken(TemplateFormativeQuestionParser.DESCRIPTION, 0); }
		public TerminalNode SOLUTION() { return getToken(TemplateFormativeQuestionParser.SOLUTION, 0); }
		public TerminalNode NEWLINE() { return getToken(TemplateFormativeQuestionParser.NEWLINE, 0); }
		public TerminalNode OPTION() { return getToken(TemplateFormativeQuestionParser.OPTION, 0); }
		public TerminalNode OPTION_DESCRIPTION() { return getToken(TemplateFormativeQuestionParser.OPTION_DESCRIPTION, 0); }
		public TerminalNode ANSWER_TEXT() { return getToken(TemplateFormativeQuestionParser.ANSWER_TEXT, 0); }
		public Multiple_choiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_choice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterMultiple_choice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitMultiple_choice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitMultiple_choice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiple_choiceContext multiple_choice() throws RecognitionException {
		Multiple_choiceContext _localctx = new Multiple_choiceContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_multiple_choice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(MULTIPLE_CHOICE_OPT);
			setState(49);
			match(DESCRIPTION);
			{
			setState(50);
			match(OPTION);
			setState(51);
			match(OPTION_DESCRIPTION);
			}
			setState(54);
			match(SOLUTION);
			{
			setState(55);
			match(ANSWER_TEXT);
			}
			setState(57);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Short_answerContext extends ParserRuleContext {
		public TerminalNode SHORT_ANSWER_OPT() { return getToken(TemplateFormativeQuestionParser.SHORT_ANSWER_OPT, 0); }
		public TerminalNode DESCRIPTION_WITH_QUESTION_MARK() { return getToken(TemplateFormativeQuestionParser.DESCRIPTION_WITH_QUESTION_MARK, 0); }
		public TerminalNode SOLUTION() { return getToken(TemplateFormativeQuestionParser.SOLUTION, 0); }
		public TerminalNode NEWLINE() { return getToken(TemplateFormativeQuestionParser.NEWLINE, 0); }
		public List<TerminalNode> ANSWER_TEXT() { return getTokens(TemplateFormativeQuestionParser.ANSWER_TEXT); }
		public TerminalNode ANSWER_TEXT(int i) {
			return getToken(TemplateFormativeQuestionParser.ANSWER_TEXT, i);
		}
		public Short_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_short_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterShort_answer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitShort_answer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitShort_answer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Short_answerContext short_answer() throws RecognitionException {
		Short_answerContext _localctx = new Short_answerContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_short_answer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(SHORT_ANSWER_OPT);
			setState(60);
			match(DESCRIPTION_WITH_QUESTION_MARK);
			setState(61);
			match(SOLUTION);
			setState(63); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(62);
				match(ANSWER_TEXT);
				}
				}
				setState(65); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ANSWER_TEXT );
			setState(67);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumericalContext extends ParserRuleContext {
		public TerminalNode NUMERICAL_OPT() { return getToken(TemplateFormativeQuestionParser.NUMERICAL_OPT, 0); }
		public TerminalNode DESCRIPTION_WITH_QUESTION_MARK() { return getToken(TemplateFormativeQuestionParser.DESCRIPTION_WITH_QUESTION_MARK, 0); }
		public TerminalNode SOLUTION() { return getToken(TemplateFormativeQuestionParser.SOLUTION, 0); }
		public TerminalNode NUMERICAL_ANSWER_TEXT() { return getToken(TemplateFormativeQuestionParser.NUMERICAL_ANSWER_TEXT, 0); }
		public TerminalNode NEWLINE() { return getToken(TemplateFormativeQuestionParser.NEWLINE, 0); }
		public NumericalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numerical; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterNumerical(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitNumerical(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitNumerical(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericalContext numerical() throws RecognitionException {
		NumericalContext _localctx = new NumericalContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_numerical);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(NUMERICAL_OPT);
			setState(70);
			match(DESCRIPTION_WITH_QUESTION_MARK);
			setState(71);
			match(SOLUTION);
			setState(72);
			match(NUMERICAL_ANSWER_TEXT);
			setState(73);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Select_wordsContext extends ParserRuleContext {
		public TerminalNode SELECT_WORDS_OPT() { return getToken(TemplateFormativeQuestionParser.SELECT_WORDS_OPT, 0); }
		public TerminalNode DESCRIPTION() { return getToken(TemplateFormativeQuestionParser.DESCRIPTION, 0); }
		public TerminalNode SOLUTION() { return getToken(TemplateFormativeQuestionParser.SOLUTION, 0); }
		public TerminalNode NEWLINE() { return getToken(TemplateFormativeQuestionParser.NEWLINE, 0); }
		public List<TerminalNode> ANSWER_TEXT() { return getTokens(TemplateFormativeQuestionParser.ANSWER_TEXT); }
		public TerminalNode ANSWER_TEXT(int i) {
			return getToken(TemplateFormativeQuestionParser.ANSWER_TEXT, i);
		}
		public Select_wordsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_words; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterSelect_words(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitSelect_words(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitSelect_words(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_wordsContext select_words() throws RecognitionException {
		Select_wordsContext _localctx = new Select_wordsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_select_words);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(SELECT_WORDS_OPT);
			setState(76);
			match(DESCRIPTION);
			setState(77);
			match(SOLUTION);
			setState(79); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(78);
				match(ANSWER_TEXT);
				}
				}
				setState(81); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ANSWER_TEXT );
			setState(83);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class True_falseContext extends ParserRuleContext {
		public TerminalNode TRUE_FALSE_OPT() { return getToken(TemplateFormativeQuestionParser.TRUE_FALSE_OPT, 0); }
		public TerminalNode DESCRIPTION_WITH_QUESTION_MARK() { return getToken(TemplateFormativeQuestionParser.DESCRIPTION_WITH_QUESTION_MARK, 0); }
		public TerminalNode SOLUTION() { return getToken(TemplateFormativeQuestionParser.SOLUTION, 0); }
		public TerminalNode TRUE_FALSE_ANSWER_TEXT() { return getToken(TemplateFormativeQuestionParser.TRUE_FALSE_ANSWER_TEXT, 0); }
		public TerminalNode NEWLINE() { return getToken(TemplateFormativeQuestionParser.NEWLINE, 0); }
		public True_falseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_true_false; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterTrue_false(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitTrue_false(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitTrue_false(this);
			else return visitor.visitChildren(this);
		}
	}

	public final True_falseContext true_false() throws RecognitionException {
		True_falseContext _localctx = new True_falseContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_true_false);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(TRUE_FALSE_OPT);
			setState(86);
			match(DESCRIPTION_WITH_QUESTION_MARK);
			setState(87);
			match(SOLUTION);
			setState(88);
			match(TRUE_FALSE_ANSWER_TEXT);
			setState(89);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0012\\\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001%\b\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004\u0004@\b"+
		"\u0004\u000b\u0004\f\u0004A\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0004\u0006P\b\u0006\u000b\u0006\f\u0006"+
		"Q\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0000\u0000\b\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0000\u0000Z\u0000\u0010\u0001\u0000\u0000\u0000\u0002$\u0001"+
		"\u0000\u0000\u0000\u0004&\u0001\u0000\u0000\u0000\u00060\u0001\u0000\u0000"+
		"\u0000\b;\u0001\u0000\u0000\u0000\nE\u0001\u0000\u0000\u0000\fK\u0001"+
		"\u0000\u0000\u0000\u000eU\u0001\u0000\u0000\u0000\u0010\u0011\u0003\u0002"+
		"\u0001\u0000\u0011\u0001\u0001\u0000\u0000\u0000\u0012\u0013\u0003\u0004"+
		"\u0002\u0000\u0013\u0014\u0005\f\u0000\u0000\u0014%\u0001\u0000\u0000"+
		"\u0000\u0015\u0016\u0003\u0006\u0003\u0000\u0016\u0017\u0005\f\u0000\u0000"+
		"\u0017%\u0001\u0000\u0000\u0000\u0018\u0019\u0003\b\u0004\u0000\u0019"+
		"\u001a\u0005\f\u0000\u0000\u001a%\u0001\u0000\u0000\u0000\u001b\u001c"+
		"\u0003\n\u0005\u0000\u001c\u001d\u0005\f\u0000\u0000\u001d%\u0001\u0000"+
		"\u0000\u0000\u001e\u001f\u0003\f\u0006\u0000\u001f \u0005\f\u0000\u0000"+
		" %\u0001\u0000\u0000\u0000!\"\u0003\u000e\u0007\u0000\"#\u0005\f\u0000"+
		"\u0000#%\u0001\u0000\u0000\u0000$\u0012\u0001\u0000\u0000\u0000$\u0015"+
		"\u0001\u0000\u0000\u0000$\u0018\u0001\u0000\u0000\u0000$\u001b\u0001\u0000"+
		"\u0000\u0000$\u001e\u0001\u0000\u0000\u0000$!\u0001\u0000\u0000\u0000"+
		"%\u0003\u0001\u0000\u0000\u0000&\'\u0005\u0001\u0000\u0000\'(\u0005\u0011"+
		"\u0000\u0000()\u0005\u0007\u0000\u0000)*\u0005\b\u0000\u0000*+\u0001\u0000"+
		"\u0000\u0000+,\u0006\u0002\uffff\uffff\u0000,-\u0005\t\u0000\u0000-.\u0005"+
		"\r\u0000\u0000./\u0006\u0002\uffff\uffff\u0000/\u0005\u0001\u0000\u0000"+
		"\u000001\u0005\u0002\u0000\u000012\u0005\u0011\u0000\u000023\u0005\u0007"+
		"\u0000\u000034\u0005\b\u0000\u000045\u0001\u0000\u0000\u000056\u0006\u0003"+
		"\uffff\uffff\u000067\u0005\t\u0000\u000078\u0005\r\u0000\u000089\u0006"+
		"\u0003\uffff\uffff\u00009:\u0005\f\u0000\u0000:\u0007\u0001\u0000\u0000"+
		"\u0000;<\u0005\u0003\u0000\u0000<=\u0005\u0012\u0000\u0000=?\u0005\t\u0000"+
		"\u0000>@\u0005\r\u0000\u0000?>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000"+
		"\u0000A?\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000BC\u0001\u0000"+
		"\u0000\u0000CD\u0005\f\u0000\u0000D\t\u0001\u0000\u0000\u0000EF\u0005"+
		"\u0004\u0000\u0000FG\u0005\u0012\u0000\u0000GH\u0005\t\u0000\u0000HI\u0005"+
		"\u000e\u0000\u0000IJ\u0005\f\u0000\u0000J\u000b\u0001\u0000\u0000\u0000"+
		"KL\u0005\u0005\u0000\u0000LM\u0005\u0011\u0000\u0000MO\u0005\t\u0000\u0000"+
		"NP\u0005\r\u0000\u0000ON\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000"+
		"QO\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000"+
		"\u0000ST\u0005\f\u0000\u0000T\r\u0001\u0000\u0000\u0000UV\u0005\u0006"+
		"\u0000\u0000VW\u0005\u0012\u0000\u0000WX\u0005\t\u0000\u0000XY\u0005\u000f"+
		"\u0000\u0000YZ\u0005\f\u0000\u0000Z\u000f\u0001\u0000\u0000\u0000\u0003"+
		"$AQ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}