package org.bootstrappers;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.EmailAddress;
import org.domain.model.exam.Exam;
import org.domain.model.exam.ResolutionSection;
import org.domain.model.examtemplate.domain.ExamTemplate;
import org.domain.model.examtemplate.domain.ExamTitle;
import org.persistence.PersistenceContext;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.repositories.UserRepository;
import repositories.ExamRepository;
import repositories.ExamTemplateRepository;

import java.util.List;

public class ExamsBootstrapperBase {

    private final ExamRepository examRepo =
            PersistenceContext.repositories().exams();
    private final ExamTemplateRepository examTemplateRepo =
            PersistenceContext.repositories().examTemplates();
    private final UserRepository userRepo =
            PersistenceContext.repositories().users();


    public Exam addExam(ExamTitle title, EmailAddress email, List<ResolutionSection> sections, Double grade){
        try{
            User user = userRepo.findUserByEmail(email).get();

            //ExamTemplate examTemplate = examTemplateRepo.findByTitle(title).get();

            Exam exam = examRepo.save(new Exam(null, user, sections, grade));

            System.out.println("[+] Exam added");

            return exam;
        }catch (final IntegrityViolationException
                      | ConcurrencyException e){
            System.out.println("EXAM ALREADY EXISTS");
            return null;
        }
    }
}
