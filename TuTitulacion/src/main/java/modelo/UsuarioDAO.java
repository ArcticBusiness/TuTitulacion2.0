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
public class UsuarioDAO {

    private SessionFactory sessionFactory;

    /**
     *
     */
    public UsuarioDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    /**
     *
     * @param u
     */
    public void guarda(Usuario u) {
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
     * @param idUsuario
     * @return
     */
    public Usuario getUsuarioById(int idUsuario) {
        Usuario u = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Usuario where id_usuario = " + idUsuario;
            Query query = session.createQuery(hql);
            u = (Usuario) query.uniqueResult();
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
     * @param u
     */
    public void actualiza(Usuario u) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(u);
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

    public Usuario encuentra(String correo, String pass) {
        Usuario result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            //String hql = " select e from Empleado e join fetch e.trabajars where correo like '%"+correo+"%' and contrasenia = :pass" ;
            String hql = " select u from Usuario u where correo_electronico like '%" + correo + "%' and contrasenia = :pass";
            Query query = session.createQuery(hql);
            query.setParameter("pass", pass);
            //query.setParameter("correo", correo);
            result = (Usuario) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            //si hay un problema regresamos la base aun estado antes de la consulta
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //cerramos la session
            session.close();
        }
        return result;
    }
    
    /**
     *
     * @param email
     * @return
     */
    public Usuario getUsuarioByEmail(String email) {
        Usuario u = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Usuario where correoElectronico = '" + email+ "'";
            Query query = session.createQuery(hql);
            u = (Usuario) query.uniqueResult();
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
     * Regresa una lista con todos los Usuarios que esten activas en la base de datos.
     * @return 
     */
    public List<Usuario> mostrarUsuarios() {
        List<Usuario> result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql = "from Usuario";
            Query query = session.createQuery(hql);
            result = (List<Usuario>)query.list();
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
    
    /**
     * 
     * @param cvd 
     */
    public void elimina(Usuario u) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(u);
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
