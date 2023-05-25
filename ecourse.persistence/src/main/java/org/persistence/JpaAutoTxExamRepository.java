package org.persistence;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import org.domain.model.exam.Exam;
import repositories.ExamRepository;

import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

public class JpaAutoTxExamRepository
    extends JpaAutoTxRepository<Exam, Long, Long>
        implements ExamRepository{

    public JpaAutoTxExamRepository(String persistenceUnitName, String identityFieldName) {
        super(persistenceUnitName, identityFieldName);
    }

    public JpaAutoTxExamRepository(String persistenceUnitName, Map properties) {
        super(persistenceUnitName, properties, "id");
    }

    public JpaAutoTxExamRepository(TransactionalContext tx, String identityFieldName) {
        super(tx, identityFieldName);
    }

    @Override
    public Exam save(Exam exam) {
        return this.repo.save(exam);
    }

    @Override
    public boolean containsOfIdentity(Long id) {
        return ExamRepository.super.containsOfIdentity(id);
    }

    @Override
    public boolean contains(Exam entity) {
        return ExamRepository.super.contains(entity);
    }

    @Override
    public long size() {
        return this.repo.size();
    }

    @Override
    public void remove(Exam entity) {
        this.repo.delete(entity);
    }

    @Override
    public void removeOfIdentity(Long entityId) {
        this.repo.deleteOfIdentity(entityId);
    }

    @Override
    public void forEach(Consumer<? super Exam> action) {
        super.forEach(action);
    }

    @Override
    public Spliterator<Exam> spliterator() {
        return super.spliterator();
    }
}
