package pl.sdacademy.main;

import pl.sdacademy.database.dao.NfcTagDao;
import pl.sdacademy.database.dao.RunDao;
import pl.sdacademy.database.dao.RunMemberDao;
import pl.sdacademy.database.daoimpl.NfcTagDaoImpl;
import pl.sdacademy.database.daoimpl.RunDaoImpl;
import pl.sdacademy.database.daoimpl.RunMemberDaoImpl;
import pl.sdacademy.database.entity.NfcTag;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.entity.RunMember;
import pl.sdacademy.database.utils.HibernateUtils;

public class Main {
    public static void main(String[] args) {
//        oneToManySelectTest();
       manyToManyInsetTest();


    }

    static void manyToManyInsetTest() {
        RunMemberDao runMemberDao = new RunMemberDaoImpl();
        NfcTagDao nfcTagDao = new NfcTagDaoImpl();

        RunMember member1 = new RunMember();
        RunMember member2 = new RunMember();

        //------------tworzenie uczestników
        member1.setName("Antek");
        member1.setStartNumber(1);

        member2.setName("Robert");
        member2.setStartNumber(2);

        runMemberDao.save(member1);
        runMemberDao.save(member2);

        //---------------------------

        //-------tworzenie tagów
        NfcTag tag1 = new NfcTag();
        NfcTag tag2 = new NfcTag();

        tag1.setSerial("tag-1");        //dopisujemy mu użytkowników
        tag1.getMembers().add(member1); //dowiązanie do uczestników
        tag1.getMembers().add(member2);

        tag2.setSerial("tag-2");
        tag2.getMembers().add(member1);
        tag2.getMembers().add(member2);

        nfcTagDao.save(tag1);
        nfcTagDao.save(tag2);


    }


    static void oneToManySelectTest() {
        RunDao runDao = new RunDaoImpl();

        Run run = runDao.findById(55l);

        System.out.println("Nazwa: " + run.getName());
        System.out.println("Ilość uczestników: " + run.getMembers().size());
    }


    static void oneToManyInsertTest() {
        RunDao runDao = new RunDaoImpl();
        RunMemberDao runMemberDao = new RunMemberDaoImpl();

        Run run = new Run();
        run.setName("Warszawska piątka");
        run.setMembersLimit(100);

        runDao.save(run);

        for (int i = 0; i < 4; i++) {
            RunMember runMember = new RunMember();
            runMember.setName("uczestnik " +i);
            runMember.setStartNumber(i);
            runMember.setRun(run);
            runMemberDao.save(runMember);
        }
    }




}
