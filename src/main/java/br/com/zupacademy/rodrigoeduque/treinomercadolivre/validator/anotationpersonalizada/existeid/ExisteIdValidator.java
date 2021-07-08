package br.com.zupacademy.rodrigoeduque.treinomercadolivre.validator.anotationpersonalizada.existeid;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExisteIdValidator implements ConstraintValidator<ExisteId,Long> {

    private String atributo;
    private Class<?> classe;

    @PersistenceContext
    private EntityManager em;



    @Override
    public void initialize(ExisteId existeId) {

        atributo = existeId.atributo();
        classe = existeId.classe();

    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {

        Query query = em.createQuery("select 1 from "+classe.getName()+" where "+atributo+"=:value");
        query.setParameter("value", value);

        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <=1, "Solicite apoio ao seu DBA. Você possui mais de um ID " + classe.getName() + " que deveria ser unico" );

        if (value == null){
            return true;
        }
        return !list.isEmpty();
    }

}
