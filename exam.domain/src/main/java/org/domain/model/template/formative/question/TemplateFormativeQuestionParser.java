// Generated from D:/JavaProjects/TemplateFormativeQuestion/src/main/java/org\TemplateFormativeQuestion.g4 by ANTLR 4.12.0
package org.domain.model.template.formative.question;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class TemplateFormativeQuestionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, ID_OPTION=3, ID_SOLUTION=4, MATCHING=5, MULTIPLE_CHOICE=6, 
		SHORT_ANSWER=7, NUMERICAL=8, SELECT_WORDS=9, TRUE_FALSE=10, SOLUTION_TEXT=11, 
		TRUE_FALSE_SOLUTION_TEXT=12, SOL_DESCRIPTION=13, TRUE_FALSE_SOL_DESCRIPTION=14, 
		DESCRIPTION=15, NUMBER=16, NEWLINE=17, DECIMAL=18;
	public static final int
		RULE_start = 0, RULE_quest = 1, RULE_matching_quest = 2, RULE_multiple_choice_quest = 3, 
		RULE_short_answer_quest = 4, RULE_numerical_quest = 5, RULE_select_words_quest = 6, 
		RULE_true_false_quest = 7, RULE_create_option = 8, RULE_create_solution = 9, 
		RULE_create_true_false_solution = 10, RULE_create_numerical_solution = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "quest", "matching_quest", "multiple_choice_quest", "short_answer_quest", 
			"numerical_quest", "select_words_quest", "true_false_quest", "create_option", 
			"create_solution", "create_true_false_solution", "create_numerical_solution"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "' '", "';'", "'OPT'", "'SOL'", "'MQUES'", "'MCQUES'", "'SAQUES'", 
			"'NQUES'", "'SWQUES'", "'TFQUES'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "ID_OPTION", "ID_SOLUTION", "MATCHING", "MULTIPLE_CHOICE", 
			"SHORT_ANSWER", "NUMERICAL", "SELECT_WORDS", "TRUE_FALSE", "SOLUTION_TEXT", 
			"TRUE_FALSE_SOLUTION_TEXT", "SOL_DESCRIPTION", "TRUE_FALSE_SOL_DESCRIPTION", 
			"DESCRIPTION", "NUMBER", "NEWLINE", "DECIMAL"
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
		public QuestContext quest() {
			return getRuleContext(QuestContext.class,0);
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
			setState(24);
			quest();
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
	public static class QuestContext extends ParserRuleContext {
		public Matching_questContext matching_quest() {
			return getRuleContext(Matching_questContext.class,0);
		}
		public Multiple_choice_questContext multiple_choice_quest() {
			return getRuleContext(Multiple_choice_questContext.class,0);
		}
		public Short_answer_questContext short_answer_quest() {
			return getRuleContext(Short_answer_questContext.class,0);
		}
		public Numerical_questContext numerical_quest() {
			return getRuleContext(Numerical_questContext.class,0);
		}
		public Select_words_questContext select_words_quest() {
			return getRuleContext(Select_words_questContext.class,0);
		}
		public True_false_questContext true_false_quest() {
			return getRuleContext(True_false_questContext.class,0);
		}
		public QuestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterQuest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitQuest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitQuest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestContext quest() throws RecognitionException {
		QuestContext _localctx = new QuestContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_quest);
		try {
			setState(32);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MATCHING:
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				matching_quest();
				}
				break;
			case MULTIPLE_CHOICE:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				multiple_choice_quest();
				}
				break;
			case SHORT_ANSWER:
				enterOuterAlt(_localctx, 3);
				{
				setState(28);
				short_answer_quest();
				}
				break;
			case NUMERICAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(29);
				numerical_quest();
				}
				break;
			case SELECT_WORDS:
				enterOuterAlt(_localctx, 5);
				{
				setState(30);
				select_words_quest();
				}
				break;
			case TRUE_FALSE:
				enterOuterAlt(_localctx, 6);
				{
				setState(31);
				true_false_quest();
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
	public static class Matching_questContext extends ParserRuleContext {
		public TerminalNode MATCHING() { return getToken(TemplateFormativeQuestionParser.MATCHING, 0); }
		public TerminalNode DESCRIPTION() { return getToken(TemplateFormativeQuestionParser.DESCRIPTION, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(TemplateFormativeQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TemplateFormativeQuestionParser.NEWLINE, i);
		}
		public List<Create_optionContext> create_option() {
			return getRuleContexts(Create_optionContext.class);
		}
		public Create_optionContext create_option(int i) {
			return getRuleContext(Create_optionContext.class,i);
		}
		public List<Create_solutionContext> create_solution() {
			return getRuleContexts(Create_solutionContext.class);
		}
		public Create_solutionContext create_solution(int i) {
			return getRuleContext(Create_solutionContext.class,i);
		}
		public Matching_questContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching_quest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterMatching_quest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitMatching_quest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitMatching_quest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Matching_questContext matching_quest() throws RecognitionException {
		Matching_questContext _localctx = new Matching_questContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_matching_quest);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(MATCHING);
			setState(38);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(35);
					match(T__0);
					}
					} 
				}
				setState(40);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(41);
			match(DESCRIPTION);
			setState(45);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(42);
					match(T__0);
					}
					} 
				}
				setState(47);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(48);
			match(T__1);
			setState(52);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(49);
					match(NEWLINE);
					}
					} 
				}
				setState(54);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(56); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(55);
				create_option();
				}
				}
				setState(58); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID_OPTION );
			setState(61); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(60);
				create_solution();
				}
				}
				setState(63); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID_SOLUTION );
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
	public static class Multiple_choice_questContext extends ParserRuleContext {
		public TerminalNode MULTIPLE_CHOICE() { return getToken(TemplateFormativeQuestionParser.MULTIPLE_CHOICE, 0); }
		public TerminalNode DESCRIPTION() { return getToken(TemplateFormativeQuestionParser.DESCRIPTION, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(TemplateFormativeQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TemplateFormativeQuestionParser.NEWLINE, i);
		}
		public List<Create_optionContext> create_option() {
			return getRuleContexts(Create_optionContext.class);
		}
		public Create_optionContext create_option(int i) {
			return getRuleContext(Create_optionContext.class,i);
		}
		public List<Create_solutionContext> create_solution() {
			return getRuleContexts(Create_solutionContext.class);
		}
		public Create_solutionContext create_solution(int i) {
			return getRuleContext(Create_solutionContext.class,i);
		}
		public Multiple_choice_questContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_choice_quest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterMultiple_choice_quest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitMultiple_choice_quest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitMultiple_choice_quest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiple_choice_questContext multiple_choice_quest() throws RecognitionException {
		Multiple_choice_questContext _localctx = new Multiple_choice_questContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_multiple_choice_quest);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(MULTIPLE_CHOICE);
			setState(69);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(66);
					match(T__0);
					}
					} 
				}
				setState(71);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(72);
			match(DESCRIPTION);
			setState(76);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(73);
					match(T__0);
					}
					} 
				}
				setState(78);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(79);
			match(T__1);
			setState(83);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(80);
					match(NEWLINE);
					}
					} 
				}
				setState(85);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(87); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(86);
				create_option();
				}
				}
				setState(89); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID_OPTION );
			setState(92); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(91);
				create_solution();
				}
				}
				setState(94); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID_SOLUTION );
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
	public static class Short_answer_questContext extends ParserRuleContext {
		public TerminalNode SHORT_ANSWER() { return getToken(TemplateFormativeQuestionParser.SHORT_ANSWER, 0); }
		public TerminalNode DESCRIPTION() { return getToken(TemplateFormativeQuestionParser.DESCRIPTION, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(TemplateFormativeQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TemplateFormativeQuestionParser.NEWLINE, i);
		}
		public List<Create_solutionContext> create_solution() {
			return getRuleContexts(Create_solutionContext.class);
		}
		public Create_solutionContext create_solution(int i) {
			return getRuleContext(Create_solutionContext.class,i);
		}
		public Short_answer_questContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_short_answer_quest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterShort_answer_quest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitShort_answer_quest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitShort_answer_quest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Short_answer_questContext short_answer_quest() throws RecognitionException {
		Short_answer_questContext _localctx = new Short_answer_questContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_short_answer_quest);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(SHORT_ANSWER);
			setState(100);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(97);
					match(T__0);
					}
					} 
				}
				setState(102);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(103);
			match(DESCRIPTION);
			setState(107);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(104);
					match(T__0);
					}
					} 
				}
				setState(109);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			setState(110);
			match(T__1);
			setState(114);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(111);
					match(NEWLINE);
					}
					} 
				}
				setState(116);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			setState(118); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(117);
				create_solution();
				}
				}
				setState(120); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID_SOLUTION );
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
	public static class Numerical_questContext extends ParserRuleContext {
		public TerminalNode NUMERICAL() { return getToken(TemplateFormativeQuestionParser.NUMERICAL, 0); }
		public TerminalNode DESCRIPTION() { return getToken(TemplateFormativeQuestionParser.DESCRIPTION, 0); }
		public Create_solutionContext create_solution() {
			return getRuleContext(Create_solutionContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(TemplateFormativeQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TemplateFormativeQuestionParser.NEWLINE, i);
		}
		public Numerical_questContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numerical_quest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterNumerical_quest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitNumerical_quest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitNumerical_quest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numerical_questContext numerical_quest() throws RecognitionException {
		Numerical_questContext _localctx = new Numerical_questContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_numerical_quest);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(NUMERICAL);
			setState(126);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(123);
					match(T__0);
					}
					} 
				}
				setState(128);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			setState(129);
			match(DESCRIPTION);
			setState(133);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(130);
					match(T__0);
					}
					} 
				}
				setState(135);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			setState(136);
			match(T__1);
			setState(140);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(137);
					match(NEWLINE);
					}
					} 
				}
				setState(142);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			setState(143);
			create_solution();
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
	public static class Select_words_questContext extends ParserRuleContext {
		public TerminalNode SELECT_WORDS() { return getToken(TemplateFormativeQuestionParser.SELECT_WORDS, 0); }
		public TerminalNode DESCRIPTION() { return getToken(TemplateFormativeQuestionParser.DESCRIPTION, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(TemplateFormativeQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TemplateFormativeQuestionParser.NEWLINE, i);
		}
		public List<Create_solutionContext> create_solution() {
			return getRuleContexts(Create_solutionContext.class);
		}
		public Create_solutionContext create_solution(int i) {
			return getRuleContext(Create_solutionContext.class,i);
		}
		public Select_words_questContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_words_quest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterSelect_words_quest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitSelect_words_quest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitSelect_words_quest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_words_questContext select_words_quest() throws RecognitionException {
		Select_words_questContext _localctx = new Select_words_questContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_select_words_quest);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(SELECT_WORDS);
			setState(149);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(146);
					match(T__0);
					}
					} 
				}
				setState(151);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			setState(152);
			match(DESCRIPTION);
			setState(156);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(153);
					match(T__0);
					}
					} 
				}
				setState(158);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			setState(159);
			match(T__1);
			setState(163);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(160);
					match(NEWLINE);
					}
					} 
				}
				setState(165);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			setState(167); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(166);
				create_solution();
				}
				}
				setState(169); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID_SOLUTION );
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
	public static class True_false_questContext extends ParserRuleContext {
		public TerminalNode TRUE_FALSE() { return getToken(TemplateFormativeQuestionParser.TRUE_FALSE, 0); }
		public TerminalNode DESCRIPTION() { return getToken(TemplateFormativeQuestionParser.DESCRIPTION, 0); }
		public Create_true_false_solutionContext create_true_false_solution() {
			return getRuleContext(Create_true_false_solutionContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(TemplateFormativeQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TemplateFormativeQuestionParser.NEWLINE, i);
		}
		public True_false_questContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_true_false_quest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterTrue_false_quest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitTrue_false_quest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitTrue_false_quest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final True_false_questContext true_false_quest() throws RecognitionException {
		True_false_questContext _localctx = new True_false_questContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_true_false_quest);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(TRUE_FALSE);
			setState(175);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(172);
					match(T__0);
					}
					} 
				}
				setState(177);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			setState(178);
			match(DESCRIPTION);
			setState(182);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(179);
					match(T__0);
					}
					} 
				}
				setState(184);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			setState(185);
			match(T__1);
			setState(189);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(186);
					match(NEWLINE);
					}
					} 
				}
				setState(191);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			setState(192);
			create_true_false_solution();
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
	public static class Create_optionContext extends ParserRuleContext {
		public TerminalNode ID_OPTION() { return getToken(TemplateFormativeQuestionParser.ID_OPTION, 0); }
		public TerminalNode DESCRIPTION() { return getToken(TemplateFormativeQuestionParser.DESCRIPTION, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(TemplateFormativeQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TemplateFormativeQuestionParser.NEWLINE, i);
		}
		public Create_optionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterCreate_option(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitCreate_option(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitCreate_option(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_optionContext create_option() throws RecognitionException {
		Create_optionContext _localctx = new Create_optionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_create_option);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(ID_OPTION);
			setState(198);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(195);
					match(T__0);
					}
					} 
				}
				setState(200);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			setState(201);
			match(DESCRIPTION);
			setState(205);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(202);
					match(T__0);
					}
					} 
				}
				setState(207);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			setState(208);
			match(T__1);
			setState(212);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(209);
					match(NEWLINE);
					}
					} 
				}
				setState(214);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
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
	public static class Create_solutionContext extends ParserRuleContext {
		public TerminalNode ID_SOLUTION() { return getToken(TemplateFormativeQuestionParser.ID_SOLUTION, 0); }
		public TerminalNode SOLUTION_TEXT() { return getToken(TemplateFormativeQuestionParser.SOLUTION_TEXT, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(TemplateFormativeQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TemplateFormativeQuestionParser.NEWLINE, i);
		}
		public Create_solutionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_solution; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterCreate_solution(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitCreate_solution(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitCreate_solution(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_solutionContext create_solution() throws RecognitionException {
		Create_solutionContext _localctx = new Create_solutionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_create_solution);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(ID_SOLUTION);
			setState(219);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(216);
					match(T__0);
					}
					} 
				}
				setState(221);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			setState(222);
			match(SOLUTION_TEXT);
			setState(226);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(223);
					match(T__0);
					}
					} 
				}
				setState(228);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			setState(229);
			match(T__1);
			setState(233);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(230);
					match(NEWLINE);
					}
					} 
				}
				setState(235);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
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
	public static class Create_true_false_solutionContext extends ParserRuleContext {
		public TerminalNode ID_SOLUTION() { return getToken(TemplateFormativeQuestionParser.ID_SOLUTION, 0); }
		public TerminalNode TRUE_FALSE_SOLUTION_TEXT() { return getToken(TemplateFormativeQuestionParser.TRUE_FALSE_SOLUTION_TEXT, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(TemplateFormativeQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TemplateFormativeQuestionParser.NEWLINE, i);
		}
		public Create_true_false_solutionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_true_false_solution; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterCreate_true_false_solution(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitCreate_true_false_solution(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitCreate_true_false_solution(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_true_false_solutionContext create_true_false_solution() throws RecognitionException {
		Create_true_false_solutionContext _localctx = new Create_true_false_solutionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_create_true_false_solution);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			match(ID_SOLUTION);
			setState(240);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(237);
					match(T__0);
					}
					} 
				}
				setState(242);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}
			setState(243);
			match(TRUE_FALSE_SOLUTION_TEXT);
			setState(247);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(244);
					match(T__0);
					}
					} 
				}
				setState(249);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			setState(250);
			match(T__1);
			setState(254);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(251);
					match(NEWLINE);
					}
					} 
				}
				setState(256);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
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
	public static class Create_numerical_solutionContext extends ParserRuleContext {
		public TerminalNode ID_SOLUTION() { return getToken(TemplateFormativeQuestionParser.ID_SOLUTION, 0); }
		public TerminalNode NUMBER() { return getToken(TemplateFormativeQuestionParser.NUMBER, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(TemplateFormativeQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TemplateFormativeQuestionParser.NEWLINE, i);
		}
		public Create_numerical_solutionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_numerical_solution; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).enterCreate_numerical_solution(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateFormativeQuestionListener ) ((TemplateFormativeQuestionListener)listener).exitCreate_numerical_solution(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateFormativeQuestionVisitor ) return ((TemplateFormativeQuestionVisitor<? extends T>)visitor).visitCreate_numerical_solution(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_numerical_solutionContext create_numerical_solution() throws RecognitionException {
		Create_numerical_solutionContext _localctx = new Create_numerical_solutionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_create_numerical_solution);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(ID_SOLUTION);
			setState(261);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(258);
					match(T__0);
					}
					} 
				}
				setState(263);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			setState(264);
			match(NUMBER);
			setState(268);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(265);
					match(T__0);
					}
					} 
				}
				setState(270);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			}
			setState(271);
			match(T__1);
			setState(275);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(272);
					match(NEWLINE);
					}
					} 
				}
				setState(277);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
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

	public static final String _serializedATN =
		"\u0004\u0001\u0012\u0117\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001!\b\u0001\u0001\u0002\u0001\u0002"+
		"\u0005\u0002%\b\u0002\n\u0002\f\u0002(\t\u0002\u0001\u0002\u0001\u0002"+
		"\u0005\u0002,\b\u0002\n\u0002\f\u0002/\t\u0002\u0001\u0002\u0001\u0002"+
		"\u0005\u00023\b\u0002\n\u0002\f\u00026\t\u0002\u0001\u0002\u0004\u0002"+
		"9\b\u0002\u000b\u0002\f\u0002:\u0001\u0002\u0004\u0002>\b\u0002\u000b"+
		"\u0002\f\u0002?\u0001\u0003\u0001\u0003\u0005\u0003D\b\u0003\n\u0003\f"+
		"\u0003G\t\u0003\u0001\u0003\u0001\u0003\u0005\u0003K\b\u0003\n\u0003\f"+
		"\u0003N\t\u0003\u0001\u0003\u0001\u0003\u0005\u0003R\b\u0003\n\u0003\f"+
		"\u0003U\t\u0003\u0001\u0003\u0004\u0003X\b\u0003\u000b\u0003\f\u0003Y"+
		"\u0001\u0003\u0004\u0003]\b\u0003\u000b\u0003\f\u0003^\u0001\u0004\u0001"+
		"\u0004\u0005\u0004c\b\u0004\n\u0004\f\u0004f\t\u0004\u0001\u0004\u0001"+
		"\u0004\u0005\u0004j\b\u0004\n\u0004\f\u0004m\t\u0004\u0001\u0004\u0001"+
		"\u0004\u0005\u0004q\b\u0004\n\u0004\f\u0004t\t\u0004\u0001\u0004\u0004"+
		"\u0004w\b\u0004\u000b\u0004\f\u0004x\u0001\u0005\u0001\u0005\u0005\u0005"+
		"}\b\u0005\n\u0005\f\u0005\u0080\t\u0005\u0001\u0005\u0001\u0005\u0005"+
		"\u0005\u0084\b\u0005\n\u0005\f\u0005\u0087\t\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u0005\u008b\b\u0005\n\u0005\f\u0005\u008e\t\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0005\u0006\u0094\b\u0006\n\u0006\f\u0006"+
		"\u0097\t\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u009b\b\u0006\n\u0006"+
		"\f\u0006\u009e\t\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u00a2\b\u0006"+
		"\n\u0006\f\u0006\u00a5\t\u0006\u0001\u0006\u0004\u0006\u00a8\b\u0006\u000b"+
		"\u0006\f\u0006\u00a9\u0001\u0007\u0001\u0007\u0005\u0007\u00ae\b\u0007"+
		"\n\u0007\f\u0007\u00b1\t\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u00b5"+
		"\b\u0007\n\u0007\f\u0007\u00b8\t\u0007\u0001\u0007\u0001\u0007\u0005\u0007"+
		"\u00bc\b\u0007\n\u0007\f\u0007\u00bf\t\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0005\b\u00c5\b\b\n\b\f\b\u00c8\t\b\u0001\b\u0001\b\u0005\b"+
		"\u00cc\b\b\n\b\f\b\u00cf\t\b\u0001\b\u0001\b\u0005\b\u00d3\b\b\n\b\f\b"+
		"\u00d6\t\b\u0001\t\u0001\t\u0005\t\u00da\b\t\n\t\f\t\u00dd\t\t\u0001\t"+
		"\u0001\t\u0005\t\u00e1\b\t\n\t\f\t\u00e4\t\t\u0001\t\u0001\t\u0005\t\u00e8"+
		"\b\t\n\t\f\t\u00eb\t\t\u0001\n\u0001\n\u0005\n\u00ef\b\n\n\n\f\n\u00f2"+
		"\t\n\u0001\n\u0001\n\u0005\n\u00f6\b\n\n\n\f\n\u00f9\t\n\u0001\n\u0001"+
		"\n\u0005\n\u00fd\b\n\n\n\f\n\u0100\t\n\u0001\u000b\u0001\u000b\u0005\u000b"+
		"\u0104\b\u000b\n\u000b\f\u000b\u0107\t\u000b\u0001\u000b\u0001\u000b\u0005"+
		"\u000b\u010b\b\u000b\n\u000b\f\u000b\u010e\t\u000b\u0001\u000b\u0001\u000b"+
		"\u0005\u000b\u0112\b\u000b\n\u000b\f\u000b\u0115\t\u000b\u0001\u000b\u001e"+
		"&-4ELSdkr~\u0085\u008c\u0095\u009c\u00a3\u00af\u00b6\u00bd\u00c6\u00cd"+
		"\u00d4\u00db\u00e2\u00e9\u00f0\u00f7\u00fe\u0105\u010c\u0113\u0000\f\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0000\u0000\u0133"+
		"\u0000\u0018\u0001\u0000\u0000\u0000\u0002 \u0001\u0000\u0000\u0000\u0004"+
		"\"\u0001\u0000\u0000\u0000\u0006A\u0001\u0000\u0000\u0000\b`\u0001\u0000"+
		"\u0000\u0000\nz\u0001\u0000\u0000\u0000\f\u0091\u0001\u0000\u0000\u0000"+
		"\u000e\u00ab\u0001\u0000\u0000\u0000\u0010\u00c2\u0001\u0000\u0000\u0000"+
		"\u0012\u00d7\u0001\u0000\u0000\u0000\u0014\u00ec\u0001\u0000\u0000\u0000"+
		"\u0016\u0101\u0001\u0000\u0000\u0000\u0018\u0019\u0003\u0002\u0001\u0000"+
		"\u0019\u0001\u0001\u0000\u0000\u0000\u001a!\u0003\u0004\u0002\u0000\u001b"+
		"!\u0003\u0006\u0003\u0000\u001c!\u0003\b\u0004\u0000\u001d!\u0003\n\u0005"+
		"\u0000\u001e!\u0003\f\u0006\u0000\u001f!\u0003\u000e\u0007\u0000 \u001a"+
		"\u0001\u0000\u0000\u0000 \u001b\u0001\u0000\u0000\u0000 \u001c\u0001\u0000"+
		"\u0000\u0000 \u001d\u0001\u0000\u0000\u0000 \u001e\u0001\u0000\u0000\u0000"+
		" \u001f\u0001\u0000\u0000\u0000!\u0003\u0001\u0000\u0000\u0000\"&\u0005"+
		"\u0005\u0000\u0000#%\u0005\u0001\u0000\u0000$#\u0001\u0000\u0000\u0000"+
		"%(\u0001\u0000\u0000\u0000&\'\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000"+
		"\u0000\')\u0001\u0000\u0000\u0000(&\u0001\u0000\u0000\u0000)-\u0005\u000f"+
		"\u0000\u0000*,\u0005\u0001\u0000\u0000+*\u0001\u0000\u0000\u0000,/\u0001"+
		"\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000"+
		".0\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u000004\u0005\u0002\u0000"+
		"\u000013\u0005\u0011\u0000\u000021\u0001\u0000\u0000\u000036\u0001\u0000"+
		"\u0000\u000045\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u000058\u0001"+
		"\u0000\u0000\u000064\u0001\u0000\u0000\u000079\u0003\u0010\b\u000087\u0001"+
		"\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:8\u0001\u0000\u0000\u0000"+
		":;\u0001\u0000\u0000\u0000;=\u0001\u0000\u0000\u0000<>\u0003\u0012\t\u0000"+
		"=<\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000"+
		"\u0000?@\u0001\u0000\u0000\u0000@\u0005\u0001\u0000\u0000\u0000AE\u0005"+
		"\u0006\u0000\u0000BD\u0005\u0001\u0000\u0000CB\u0001\u0000\u0000\u0000"+
		"DG\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000"+
		"\u0000FH\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000HL\u0005\u000f"+
		"\u0000\u0000IK\u0005\u0001\u0000\u0000JI\u0001\u0000\u0000\u0000KN\u0001"+
		"\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000"+
		"MO\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000OS\u0005\u0002\u0000"+
		"\u0000PR\u0005\u0011\u0000\u0000QP\u0001\u0000\u0000\u0000RU\u0001\u0000"+
		"\u0000\u0000ST\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000TW\u0001"+
		"\u0000\u0000\u0000US\u0001\u0000\u0000\u0000VX\u0003\u0010\b\u0000WV\u0001"+
		"\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000YW\u0001\u0000\u0000\u0000"+
		"YZ\u0001\u0000\u0000\u0000Z\\\u0001\u0000\u0000\u0000[]\u0003\u0012\t"+
		"\u0000\\[\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^\\\u0001\u0000"+
		"\u0000\u0000^_\u0001\u0000\u0000\u0000_\u0007\u0001\u0000\u0000\u0000"+
		"`d\u0005\u0007\u0000\u0000ac\u0005\u0001\u0000\u0000ba\u0001\u0000\u0000"+
		"\u0000cf\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000db\u0001\u0000"+
		"\u0000\u0000eg\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000gk\u0005"+
		"\u000f\u0000\u0000hj\u0005\u0001\u0000\u0000ih\u0001\u0000\u0000\u0000"+
		"jm\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000"+
		"\u0000ln\u0001\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000nr\u0005\u0002"+
		"\u0000\u0000oq\u0005\u0011\u0000\u0000po\u0001\u0000\u0000\u0000qt\u0001"+
		"\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000"+
		"sv\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000uw\u0003\u0012\t\u0000"+
		"vu\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000"+
		"\u0000xy\u0001\u0000\u0000\u0000y\t\u0001\u0000\u0000\u0000z~\u0005\b"+
		"\u0000\u0000{}\u0005\u0001\u0000\u0000|{\u0001\u0000\u0000\u0000}\u0080"+
		"\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000~|\u0001\u0000"+
		"\u0000\u0000\u007f\u0081\u0001\u0000\u0000\u0000\u0080~\u0001\u0000\u0000"+
		"\u0000\u0081\u0085\u0005\u000f\u0000\u0000\u0082\u0084\u0005\u0001\u0000"+
		"\u0000\u0083\u0082\u0001\u0000\u0000\u0000\u0084\u0087\u0001\u0000\u0000"+
		"\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0085\u0083\u0001\u0000\u0000"+
		"\u0000\u0086\u0088\u0001\u0000\u0000\u0000\u0087\u0085\u0001\u0000\u0000"+
		"\u0000\u0088\u008c\u0005\u0002\u0000\u0000\u0089\u008b\u0005\u0011\u0000"+
		"\u0000\u008a\u0089\u0001\u0000\u0000\u0000\u008b\u008e\u0001\u0000\u0000"+
		"\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000"+
		"\u0000\u008d\u008f\u0001\u0000\u0000\u0000\u008e\u008c\u0001\u0000\u0000"+
		"\u0000\u008f\u0090\u0003\u0012\t\u0000\u0090\u000b\u0001\u0000\u0000\u0000"+
		"\u0091\u0095\u0005\t\u0000\u0000\u0092\u0094\u0005\u0001\u0000\u0000\u0093"+
		"\u0092\u0001\u0000\u0000\u0000\u0094\u0097\u0001\u0000\u0000\u0000\u0095"+
		"\u0096\u0001\u0000\u0000\u0000\u0095\u0093\u0001\u0000\u0000\u0000\u0096"+
		"\u0098\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000\u0000\u0000\u0098"+
		"\u009c\u0005\u000f\u0000\u0000\u0099\u009b\u0005\u0001\u0000\u0000\u009a"+
		"\u0099\u0001\u0000\u0000\u0000\u009b\u009e\u0001\u0000\u0000\u0000\u009c"+
		"\u009d\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009d"+
		"\u009f\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009f"+
		"\u00a3\u0005\u0002\u0000\u0000\u00a0\u00a2\u0005\u0011\u0000\u0000\u00a1"+
		"\u00a0\u0001\u0000\u0000\u0000\u00a2\u00a5\u0001\u0000\u0000\u0000\u00a3"+
		"\u00a4\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000\u00a4"+
		"\u00a7\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a8\u0003\u0012\t\u0000\u00a7\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a9"+
		"\u0001\u0000\u0000\u0000\u00a9\u00a7\u0001\u0000\u0000\u0000\u00a9\u00aa"+
		"\u0001\u0000\u0000\u0000\u00aa\r\u0001\u0000\u0000\u0000\u00ab\u00af\u0005"+
		"\n\u0000\u0000\u00ac\u00ae\u0005\u0001\u0000\u0000\u00ad\u00ac\u0001\u0000"+
		"\u0000\u0000\u00ae\u00b1\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000"+
		"\u0000\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00b0\u00b2\u0001\u0000"+
		"\u0000\u0000\u00b1\u00af\u0001\u0000\u0000\u0000\u00b2\u00b6\u0005\u000f"+
		"\u0000\u0000\u00b3\u00b5\u0005\u0001\u0000\u0000\u00b4\u00b3\u0001\u0000"+
		"\u0000\u0000\u00b5\u00b8\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000"+
		"\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b7\u00b9\u0001\u0000"+
		"\u0000\u0000\u00b8\u00b6\u0001\u0000\u0000\u0000\u00b9\u00bd\u0005\u0002"+
		"\u0000\u0000\u00ba\u00bc\u0005\u0011\u0000\u0000\u00bb\u00ba\u0001\u0000"+
		"\u0000\u0000\u00bc\u00bf\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000"+
		"\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00be\u00c0\u0001\u0000"+
		"\u0000\u0000\u00bf\u00bd\u0001\u0000\u0000\u0000\u00c0\u00c1\u0003\u0014"+
		"\n\u0000\u00c1\u000f\u0001\u0000\u0000\u0000\u00c2\u00c6\u0005\u0003\u0000"+
		"\u0000\u00c3\u00c5\u0005\u0001\u0000\u0000\u00c4\u00c3\u0001\u0000\u0000"+
		"\u0000\u00c5\u00c8\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000"+
		"\u0000\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c7\u00c9\u0001\u0000\u0000"+
		"\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9\u00cd\u0005\u000f\u0000"+
		"\u0000\u00ca\u00cc\u0005\u0001\u0000\u0000\u00cb\u00ca\u0001\u0000\u0000"+
		"\u0000\u00cc\u00cf\u0001\u0000\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000"+
		"\u0000\u00cd\u00cb\u0001\u0000\u0000\u0000\u00ce\u00d0\u0001\u0000\u0000"+
		"\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000\u00d0\u00d4\u0005\u0002\u0000"+
		"\u0000\u00d1\u00d3\u0005\u0011\u0000\u0000\u00d2\u00d1\u0001\u0000\u0000"+
		"\u0000\u00d3\u00d6\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000"+
		"\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d5\u0011\u0001\u0000\u0000"+
		"\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d7\u00db\u0005\u0004\u0000"+
		"\u0000\u00d8\u00da\u0005\u0001\u0000\u0000\u00d9\u00d8\u0001\u0000\u0000"+
		"\u0000\u00da\u00dd\u0001\u0000\u0000\u0000\u00db\u00dc\u0001\u0000\u0000"+
		"\u0000\u00db\u00d9\u0001\u0000\u0000\u0000\u00dc\u00de\u0001\u0000\u0000"+
		"\u0000\u00dd\u00db\u0001\u0000\u0000\u0000\u00de\u00e2\u0005\u000b\u0000"+
		"\u0000\u00df\u00e1\u0005\u0001\u0000\u0000\u00e0\u00df\u0001\u0000\u0000"+
		"\u0000\u00e1\u00e4\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000"+
		"\u0000\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e3\u00e5\u0001\u0000\u0000"+
		"\u0000\u00e4\u00e2\u0001\u0000\u0000\u0000\u00e5\u00e9\u0005\u0002\u0000"+
		"\u0000\u00e6\u00e8\u0005\u0011\u0000\u0000\u00e7\u00e6\u0001\u0000\u0000"+
		"\u0000\u00e8\u00eb\u0001\u0000\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000"+
		"\u0000\u00e9\u00e7\u0001\u0000\u0000\u0000\u00ea\u0013\u0001\u0000\u0000"+
		"\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00ec\u00f0\u0005\u0004\u0000"+
		"\u0000\u00ed\u00ef\u0005\u0001\u0000\u0000\u00ee\u00ed\u0001\u0000\u0000"+
		"\u0000\u00ef\u00f2\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000"+
		"\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000\u00f1\u00f3\u0001\u0000\u0000"+
		"\u0000\u00f2\u00f0\u0001\u0000\u0000\u0000\u00f3\u00f7\u0005\f\u0000\u0000"+
		"\u00f4\u00f6\u0005\u0001\u0000\u0000\u00f5\u00f4\u0001\u0000\u0000\u0000"+
		"\u00f6\u00f9\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000"+
		"\u00f7\u00f5\u0001\u0000\u0000\u0000\u00f8\u00fa\u0001\u0000\u0000\u0000"+
		"\u00f9\u00f7\u0001\u0000\u0000\u0000\u00fa\u00fe\u0005\u0002\u0000\u0000"+
		"\u00fb\u00fd\u0005\u0011\u0000\u0000\u00fc\u00fb\u0001\u0000\u0000\u0000"+
		"\u00fd\u0100\u0001\u0000\u0000\u0000\u00fe\u00ff\u0001\u0000\u0000\u0000"+
		"\u00fe\u00fc\u0001\u0000\u0000\u0000\u00ff\u0015\u0001\u0000\u0000\u0000"+
		"\u0100\u00fe\u0001\u0000\u0000\u0000\u0101\u0105\u0005\u0004\u0000\u0000"+
		"\u0102\u0104\u0005\u0001\u0000\u0000\u0103\u0102\u0001\u0000\u0000\u0000"+
		"\u0104\u0107\u0001\u0000\u0000\u0000\u0105\u0106\u0001\u0000\u0000\u0000"+
		"\u0105\u0103\u0001\u0000\u0000\u0000\u0106\u0108\u0001\u0000\u0000\u0000"+
		"\u0107\u0105\u0001\u0000\u0000\u0000\u0108\u010c\u0005\u0010\u0000\u0000"+
		"\u0109\u010b\u0005\u0001\u0000\u0000\u010a\u0109\u0001\u0000\u0000\u0000"+
		"\u010b\u010e\u0001\u0000\u0000\u0000\u010c\u010d\u0001\u0000\u0000\u0000"+
		"\u010c\u010a\u0001\u0000\u0000\u0000\u010d\u010f\u0001\u0000\u0000\u0000"+
		"\u010e\u010c\u0001\u0000\u0000\u0000\u010f\u0113\u0005\u0002\u0000\u0000"+
		"\u0110\u0112\u0005\u0011\u0000\u0000\u0111\u0110\u0001\u0000\u0000\u0000"+
		"\u0112\u0115\u0001\u0000\u0000\u0000\u0113\u0114\u0001\u0000\u0000\u0000"+
		"\u0113\u0111\u0001\u0000\u0000\u0000\u0114\u0017\u0001\u0000\u0000\u0000"+
		"\u0115\u0113\u0001\u0000\u0000\u0000% &-4:?ELSY^dkrx~\u0085\u008c\u0095"+
		"\u009c\u00a3\u00a9\u00af\u00b6\u00bd\u00c6\u00cd\u00d4\u00db\u00e2\u00e9"+
		"\u00f0\u00f7\u00fe\u0105\u010c\u0113";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}