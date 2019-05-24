package br.com.springbatchexemplo.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.springbatchexemplo.model.User;
import br.com.springbatchexemplo.repository.UserRepository;

@Component
public class DBWriter implements ItemWriter<User> {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void write(List<? extends User> users) throws Exception {
		logger.info("Data Saved for Users -> {}", users);
		userRepository.save(users);
		
	}

}
