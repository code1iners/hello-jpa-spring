package jpashopbasic;

import jpashopbasic.domain.Member;
import jpashopbasic.domain.inheritance.Book;
import jpashopbasic.domain.inheritance.Movie;
import org.hibernate.Hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        test(emf);

        emf.close();
    }

    /**
     * <h2>For test</h2>
     */
    private static void test(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setName("name");
            em.persist(member);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member.getId());
            // note. check is initialized ref.
            boolean isLoaded = emf.getPersistenceUnitUtil().isLoaded(refMember);
            System.out.println("isLoaded = " + isLoaded);
            // note. forced initialization ref.
            Hibernate.initialize(refMember);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
