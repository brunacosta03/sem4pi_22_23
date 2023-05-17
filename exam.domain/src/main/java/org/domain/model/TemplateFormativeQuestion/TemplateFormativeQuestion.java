package org.domain.model.TemplateFormativeQuestion;

import org.domain.model.Answer;
import org.domain.model.QuestionDescription;
import org.domain.model.QuestionType;
import org.domain.model.Solution;

import javax.persistence.*;
import java.util.List;

@Entity
public class TemplateFormativeQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private final QuestionDescription description;
    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    private final QuestionType type;

    @ElementCollection
    @CollectionTable(name = "t_answers")
    private final List<Answer> answers;

    @ElementCollection
    @CollectionTable(name = "t_solutions")
    private final List<Solution> solutions;

    public TemplateFormativeQuestion(
            QuestionDescription description,
            QuestionType type,
            List<Answer> answers,
            List<Solution> solutions
    ) {
        this.description = description;
        this.type = type;
        this.answers = answers;
        this.solutions = solutions;
    }

    public TemplateFormativeQuestion() {
        // for ORM
        this.description = null;
        this.type = null;
        this.answers = null;
        this.solutions = null;
    }
}
