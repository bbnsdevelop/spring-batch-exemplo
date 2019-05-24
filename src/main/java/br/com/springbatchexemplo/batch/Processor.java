package br.com.springbatchexemplo.batch;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import br.com.springbatchexemplo.model.User;

@Component
public class Processor implements ItemProcessor<User, User> {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final Map<String, String> DEPT_NAMES = new HashMap<>();
	
	public Processor() {
		DEPT_NAMES.put("001", "Technology");
		DEPT_NAMES.put("002", "Operations");
		DEPT_NAMES.put("003", "Accounts");
	}

	@Override
	public User process(User user) throws Exception {
		String deptCode = user.getDept();
		String dept = DEPT_NAMES.get(deptCode);
		user.setDept(dept);
		logger.info("Convert from {} to {}", deptCode, dept);
		return user;
	}

}
