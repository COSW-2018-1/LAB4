/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cosw.jpa.sample;

import edu.eci.cosw.jpa.sample.model.Curso;
import edu.eci.cosw.jpa.sample.model.Estudiante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author hcadavid
 */
public class SimpleMainApp {
   
    public static void main(String a[]){
        SessionFactory sf=getSessionFactory();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();

        // datos para la transaccion
        Estudiante est1 = new Estudiante(1111111110,"AAA Sergio");
        Estudiante est2 = new Estudiante(1111111112,"AAA Manuel");

        Curso cur1 = new Curso(911111110,"curso 110", "curso finan1");
        Curso cur2 = new Curso(911111112,"curso 120", "curso finan2");


        // SE DEBE AGREGAR LOS CURSOS A CADA USUARIO Y LUEGO GUARDAR, NO HACE FALTA GUARDAR ESTUDIANTES EN CADA CURSO

        est1.addCurso(cur1);
        est1.addCurso(cur2);

        est2.addCurso(cur1);
        est2.addCurso(cur2);

        // GUARDAR DATOS EN LA BD
        session.saveOrUpdate(cur1);
        session.saveOrUpdate(cur2);
        session.saveOrUpdate(est1);
        session.saveOrUpdate(est2);


        tx.commit(); 
        session.close();
        sf.close();
    }

    public static SessionFactory getSessionFactory() {
        // loads configuration and mappings
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry
                = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        // builds a session factory from the service registry
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

}
