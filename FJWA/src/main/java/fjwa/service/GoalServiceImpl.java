package fjwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fjwa.model.Goal;
import fjwa.repository.GoalRepository;

@Service("goalService")
public class GoalServiceImpl implements GoalService {

	@Autowired
	private GoalRepository goalRepository;
	
	@Override
	@Transactional
	public Goal save(Goal goal) {
		return goalRepository.save(goal);
	}

}
