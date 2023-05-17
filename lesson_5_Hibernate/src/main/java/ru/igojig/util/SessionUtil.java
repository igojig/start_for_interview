package ru.igojig.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.igojig.entity.Student;

public class SessionUtil {
    private static SessionFactory sessionFactory;

    public static void init(){
        if(sessionFactory==null){
            sessionFactory= new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        }

    }

    public static Session getSession(){
        if(sessionFactory==null){
            getSession();
        }
        return sessionFactory.getCurrentSession();
    }

    public static void close(){
        if(sessionFactory!=null){
            System.out.println("Close session");
            sessionFactory.close();
        }
    }

}
