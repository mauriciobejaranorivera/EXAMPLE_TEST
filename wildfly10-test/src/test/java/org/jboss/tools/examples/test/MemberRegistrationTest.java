/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.tools.examples.test;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.tools.examples.model.Member;
import org.jboss.tools.examples.model.Rol;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mbr.dao.DataAccessObjectJpa;
import com.mbr.dao.MemberDao;
import com.mbr.dao.RolDao;
import com.mbr.interfaces.dao.IMemberDao;
import com.mbr.interfaces.dao.IRolDao;

@RunWith(Arquillian.class)
public class MemberRegistrationTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                //.addClasses(Member.class, MemberRegistration.class, Resources.class)
        		.addClasses(Member.class, IMemberDao.class, MemberDao.class,DataAccessObjectJpa.class,IRolDao.class,RolDao.class,Rol.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml");
    }

    
    //@Inject MemberRegistration memberRegistration;
    
    @Inject IMemberDao memberDao;

//    @Inject
//    Logger log;

    @Test
    public void testRegister() throws Exception {
        Member newMember = new Member();
        newMember.setName("Jane Doe");
        newMember.setEmail("jane@mailinator.com");
        newMember.setPhoneNumber("2125551234");
        //memberRegistration.register(newMember);
        memberDao.registrar(newMember);
        assertNotNull(newMember.getId());
        //log.info(newMember.getName() + " was persisted with id " + newMember.getId());
        System.out.println(newMember.getName() + " was persisted with id " + newMember.getId());
    }

}
