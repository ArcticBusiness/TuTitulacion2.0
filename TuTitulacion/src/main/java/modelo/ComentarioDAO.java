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
public class ComentarioDAO {
    private SessionFactory sessionFactory;
    Session session;

    public ComentarioDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
   
    public void guarda(Comentario c){
        System.out.println("Guardando Comentario en base de datos");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.persist(c);
            tx.commit();
        }catch (Exception e){
            if(tx != null){
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
    public List<Comentario> mostrarComentarios() {
        List<Comentario> result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql = "from Comentario";
            Query query = session.createQuery(hql);
            result = (List<Comentario>)query.list();
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