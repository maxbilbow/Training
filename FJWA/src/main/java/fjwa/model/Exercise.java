package fjwa.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@NamedQueries( {
	@NamedQuery(
			name=Exercise.FIND_ALL_EXERCISES,
			query="Select e from Exercise e"
			)
})
public class Exercise {
	public static final String 
	FIND_ALL_EXERCISES = "findAllExercises",
	CLASS = "fjwa.model.Exercise";
	
	@Id
	@GeneratedValue
	private long id;
	
	@Range(min = 1, max = 120)
	private int minutes;

	@NotNull
	private String activity;

	@ManyToOne
	private Goal goal;
	
	public String getActivity() {
		return activity;
	}
	
	/**
	 * @return the goal
	 */
	public Goal getGoal() {
		return goal;
	}

	public long getId() {
		return id;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	/**
	 * @param goal the goal to set
	 */
	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
}
