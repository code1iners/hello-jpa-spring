package jpashopbasic;

import jpashopbasic.domain.Address;
import jpashopbasic.domain.Member;
import jpashopbasic.domain.Period;
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
            member.setName("member1");;
            member.setHomeAddress(new Address("city", "street", "zipcode"));
            member.setWorkPeriod(new Period());

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
