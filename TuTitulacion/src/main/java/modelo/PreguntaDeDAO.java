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

/**
 *
 * @author miguel
 */
public class PreguntaDeDAO {
    private SessionFactory sessionFactory;

    /**
     *
     */
    public PreguntaDeDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    /**
     * 
     * @param p 
     */
    public void guarda(PreguntaDe p){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.persist(p);
            tx.commit();
        }catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
    
     /**
     * Regresa una lista con todos las Preguntas que estan activas en la base de datos.
     * @return 
     */
    public List<PreguntaDe> mostrarPreguntas() {
        List<PreguntaDe> result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql = "from PreguntaDe";
            Query query = session.createQuery(hql);
            result = (List<PreguntaDe>)query.list();
            tx.commit();
        }
        catch (Exception e) {
            //si hay un problema regresamos la base aun estado antes de la consulta
            if (tx!=null){
                tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
            //cerramos la session
            session.close();
        }
        return result;
    }
    
    
}
