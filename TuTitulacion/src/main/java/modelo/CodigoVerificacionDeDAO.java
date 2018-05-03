/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author miguel
 */
public class CodigoVerificacionDeDAO {

    private SessionFactory sessionFactory;

    /**
     *
     */
    public CodigoVerificacionDeDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    /**
     *
     * @param u
     */
    public void guarda(CodigoVerificacionDe u) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(u);
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

    /**
     * 
     * @param fk_usuario
     * @param codigo
     * @return 
     */
    public CodigoVerificacionDe getVerificacion(int fk_usuario, String codigo) {
        CodigoVerificacionDe cvd = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from CodigoVerificacionDe where codigoVerificacion = '" + codigo + "' and fkUsuario = '" + fk_usuario + "'";
            Query query = session.createQuery(hql);
            cvd = (CodigoVerificacionDe) query.uniqueResult();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cvd;
    }

    /**
     * 
     * @param cvd 
     */
    public void elimina(CodigoVerificacionDe cvd) {
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
