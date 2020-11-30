package com.rsh.tkachenko.microservice.AppToDo;




import com.rsh.tkachenko.microservice.AppToDo.daos.ToDoDao;
import com.rsh.tkachenko.microservice.AppToDo.daos.UserDao;

import com.rsh.tkachenko.microservice.AppToDo.entities.ToDo;
import com.rsh.tkachenko.microservice.AppToDo.entities.User;
import com.rsh.tkachenko.microservice.AppToDo.utilities.EncryptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

//@ComponentScan(basePackages={"com.rsh.tkachenko.microservice"})
//@EntityScan("com.rsh.tkachenko.microservice")
//@EnableJpaRepositories("com.rsh.repository.tkachenko.microservice")
@SpringBootApplication
public class AppToDoApplication implements CommandLineRunner {

	@Autowired
	UserDao userDao;

	@Autowired
	ToDoDao toDoDao;

	@Autowired
	EncryptionUtils encryptionUtils;

	//private static final Logger log = LoggerFactory.getLogger(AppToDoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AppToDoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//...

		//log.info("lets fill H2in-memory database");
		String encryptedPwd;
		encryptedPwd = encryptionUtils.encrypt("Test01");
		userDao.save(new User("nick@gmail.com", "Nikolay", encryptedPwd));

		encryptedPwd = encryptionUtils.encrypt("Test02");
		userDao.save(new User("michel@gmail.com", "Micjail", encryptedPwd));

		encryptedPwd = encryptionUtils.encrypt("Test03");
		userDao.save(new User("marina@gmail.com", "Marina", encryptedPwd));


		toDoDao.save( new ToDo(1, "Learn Microservices", new Date(), "high", "nick@gmail.com"));
		toDoDao.save( new ToDo(null, "Learn Spring boot", null, "low", "nick@gmail.com"));

		toDoDao.save( new ToDo(3, "Feed Animals", new Date(), "high", "michel@gmail.com"));
		toDoDao.save( new ToDo(null, "Go to take Jim", null, "low", "michel@gmail.com"));

		toDoDao.save( new ToDo(5, "By a new Car", new Date(), "high", "marina@gmail.com"));
		toDoDao.save( new ToDo(null, "Go to the Gim", null, "low", "marina@gmail.com"));

		//log.info("we'he finished to fill our database");

	}
}
