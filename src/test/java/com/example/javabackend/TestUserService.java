package com.example.javabackend;

import com.example.javabackend.entity.User;
import com.example.javabackend.exception.BaseException;
import com.example.javabackend.exception.UserException;
import com.example.javabackend.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserService userService;

	@Order(1)
	@Test
	void testcreate() throws BaseException {
		User user = userService.create(
				TestCreateData.email,
				TestCreateData.password,
				TestCreateData.name
		);

		//check not null
		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getId());

		// check equals
		Assertions.assertEquals(TestCreateData.email, user.getEmail());

		boolean isMatched = userService.matchPassword(TestCreateData.password, user.getPassword());
		Assertions.assertTrue(isMatched);

		Assertions.assertEquals(TestCreateData.name, user.getName());

	}

	@Order(2)
	@Test
	void testupdate() throws UserException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		User updatedUser = userService.updateName(user.getId(), TestUpdateData.name);

		Assertions.assertNotNull(updatedUser);
		Assertions.assertEquals(TestUpdateData.name, updatedUser.getName());
	}

	@Order(3)
	@Test
	void testdalete() {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		userService.deleteById(user.getId());

		Optional<User> optDelete = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(optDelete.isEmpty());
	}


//dsdsdsds
	interface TestCreateData {

		String email = "joe@test.com";

		String password = "123456";

		String name = "Joe";
	}

	interface TestUpdateData {

		String email = "joe2@test.com";

		String password = "1234567";

		String name = "Joe2";
	}


}
