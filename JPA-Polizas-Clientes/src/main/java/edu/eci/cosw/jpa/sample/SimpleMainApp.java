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

import edu.eci.cosw.jpa.sample.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.text.MessageFormat;
import java.util.Date;

/**
 *
 * @author hcadavid
 */
public class SimpleMainApp {
   
    public static void main(String a[]){
        SessionFactory sf=getSessionFactory();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();

        //Cliente emp1 = (Cliente) session.load(Cliente.class, new Cliente(new ClienteId(101,"cc"),"Rey Arturo", "calle 14","314646556"));
        //System.out.println(MessageFormat.format(" ===== nombre: {0}", emp1.getNombre()));

        ClienteId clienteID = new ClienteId(101,"cc");
        Cliente emp1 = new Cliente(clienteID,"Rey Arturo", "calle 14","314646556");

        PolizaAprobadaId polizaAID = new PolizaAprobadaId(clienteID.getId(),clienteID.getTipoId(), 1);
        TipoPoliza tipoPoliza = new TipoPoliza("Poliza Type1", "Cuando rompa la espada", 100000000);

        PolizaAprobada nuevaPolizaA = new PolizaAprobada(polizaAID, emp1, tipoPoliza, new Date(), new Date());

        session.save(emp1);


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
