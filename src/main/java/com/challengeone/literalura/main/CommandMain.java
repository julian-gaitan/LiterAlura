package com.challengeone.literalura.main;

import com.challengeone.literalura.entity.TestEnt;
import com.challengeone.literalura.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CommandMain {

    public void start(TestRepository testRepository) {
        System.out.println("HELlo");
        TestEnt ent = new TestEnt();
        ent.setAbc("Yes Yes Yes !!!");
        testRepository.save(ent);
    }
}
