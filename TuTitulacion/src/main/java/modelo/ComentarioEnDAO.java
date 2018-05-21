/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ComentarioEnDAO {

    private SessionFactory sessionFactory;

    public ComentarioEnDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void guarda(ComentarioEn ce) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(ce);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<ComentarioEn> mostrarComentariosDe(int idPregunta) {
        List<ComentarioEn> resultado = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql = "from ComentarioEn where pregunta = '" + idPregunta + "'";
            Query query = session.createQuery(hql);
            resultado = (List<ComentarioEn>) query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return resultado;
    }
    
     /**
     *
     * @param email
     * @return
     */
    public ComentarioEn getComentarioEnByIdUsuario(int id) {
        ComentarioEn u = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from ComentarioEn as pde where pde.usuario.idUsuario = " + id;
            Query query = session.createQuery(hql);
            u = (ComentarioEn) query.uniqueResult();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return u;
    }

    /**
     *
     * @param cvd
     */
    public void elimina(ComentarioEn cvd) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(cvd);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
