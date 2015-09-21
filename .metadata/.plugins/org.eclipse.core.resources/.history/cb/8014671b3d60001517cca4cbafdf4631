package fjwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fjwa.model.Goal;
import fjwa.repository.BombRepository;
import fjwa.repository.GoalRepository;

@Service("goalService")
public class GoalServiceImpl implements GoalService {

	@Autowired
	private GoalRepository goalRepository;
	
	@Override
	public Goal save(Goal goal) {
		// TODO Auto-generated method stub
		return goalRepository.save(goal);
	}

}
