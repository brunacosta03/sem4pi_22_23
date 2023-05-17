package org.domain.model.TemplateFormativeQuestion.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.domain.model.TemplateFormativeQuestion.TemplateFormativeQuestion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class TemplateFormativeQuestionEvaluator {
    private static final String FILE_NAME = "question.txt";

    public static TemplateFormativeQuestion evaluate(String toParse) throws IOException {
        WriteToFile(toParse);
        FileInputStream fis = new FileInputStream(new File("question.txt"));
        TemplateFormativeQuestionLexer lexer = new TemplateFormativeQuestionLexer(new ANTLRInputStream(fis));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TemplateFormativeQuestionParser parser = new TemplateFormativeQuestionParser(tokens);
        parser.addErrorListener(new ErrorListener());
        ParseTree tree = parser.start();

        return null;
    }




    public static void WriteToFile(String toParse) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_NAME);

        fileWriter.write(toParse);

        fileWriter.close();
    }

    public TemplateFormativeQuestion parseWithListener(ParseTree tree){
        ParseTreeWalker walker = new ParseTreeWalker();
        FormativeQuestionBuilderListener listener = new FormativeQuestionBuilderListener();
        walker.walk(listener, tree);

        return listener.getResult();
    }
}
