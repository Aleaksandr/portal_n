/*
 * Copyright (c) 2012 by VeriFone, Inc.
 * All Rights Reserved.
 *
 * THIS FILE CONTAINS PROPRIETARY AND CONFIDENTIAL INFORMATION
 * AND REMAINS THE UNPUBLISHED PROPERTY OF VERIFONE, INC.
 *
 * Use, disclosure, or reproduction is prohibited
 * without prior written approval from VeriFone, Inc.
 */
package util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by hirs akeaksandr on 22.05.15.
 * HibernateUtil
 */

public class HibernateUtil {

    private static Logger logger = Logger.getLogger(HibernateUtil.class);

   // private final ThreadLocal needClean = new ThreadLocal(){{set(new Boolean(false));}};

    private static HibernateUtil util = null;

    private final ThreadLocal sessions = new ThreadLocal();

    private SessionFactory sessionFactory = null;

    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            logger.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        Session session = (Session) sessions.get();
        if (session == null) {
            session = sessionFactory.openSession();
            logger.info("Session NewSession!!!");
            sessions.set(session);
        }
        logger.info("Session GetSession!!!");
        return session;
    }


    public static synchronized HibernateUtil getHibernateUtil(){
        if (util == null){
            util = new HibernateUtil();
        }
        return util;
    }

    public void cleanSession(Boolean needClean) {
        if (needClean) {
            logger.info("Session CLEAN!");
            getSession().clear();
        }
    }

}
