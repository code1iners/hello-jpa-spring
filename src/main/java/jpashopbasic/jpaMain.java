package jpashopbasic;

import jpashopbasic.domain.Address;
import jpashopbasic.domain.AddressEntity;
import jpashopbasic.domain.Member;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

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

            String query = "select m from Member m where m.name like '%kim%'";

            List<Member> members = em
                    .createQuery(query, Member.class)
                    .getResultList();

            for (Member member: members) {
                System.out.println("member = " + member);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
