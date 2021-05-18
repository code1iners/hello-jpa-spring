package jpashopbasic;

import jpashopbasic.domain.inheritance.Book;
import jpashopbasic.domain.inheritance.Movie;

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
            Movie movie = new Movie();
            movie.setDirector("director");
            movie.setActor("actor");
            movie.setName("name");
            movie.setPrice(10000);

            Book book = new Book();
            book.setName("book");
            book.setAuthor("author");
            book.setPrice(8000);

            em.flush();
            em.clear();

            em.persist(movie);
            em.persist(book);

            Movie foundMovie = em.find(Movie.class, movie.getId());
            Book foundBook = em.find(Book.class, book.getId());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
