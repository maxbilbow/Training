package fjwa.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fjwa.model.Activity;
import fjwa.model.Exercise;
import fjwa.repository.ExerciseRepository;
import fjwa.repository.ExerciseRepositoryImpl;


@Service("exerciseService")
public class ExerciseServiceImpl implements ExerciseService {

	@Autowired
	private ExerciseRepository repository;
	
	@Override
	public List<Activity> findAllActivities() {
		
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity run = new Activity();
		run.setDesc("Run");
		activities.add(run);
		
		Activity bike = new Activity();
		bike.setDesc("Bike");
		activities.add(bike);
		
		Activity swim = new Activity();
		swim.setDesc("Swim");
		activities.add(swim);
		
		return activities;
	}

	@Override
	@Transactional
	public Exercise save(Exercise entity) {
		entity = repository.save(entity);
		return entity;
	}
	
}
