package com.hd.hdmp;

import com.hd.hdmp.common.util.PasswordHelper;
import com.hd.hdmp.entity.StaffinfoEntity;
import com.hd.hdmp.repository.StaffinfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HdmpApplicationTests {

	@Autowired
	StaffinfoRepository staffinfoRepository;
	@Test
	public void contextLoads() {
		PasswordHelper passwordHelper = new PasswordHelper();
		StaffinfoEntity staffinfoEntity = new StaffinfoEntity();
		staffinfoEntity.setUsername("admin");
		staffinfoEntity.setPassword("123");
		passwordHelper.encrptPassword(staffinfoEntity);
		staffinfoRepository.save(staffinfoEntity);
	}

}
