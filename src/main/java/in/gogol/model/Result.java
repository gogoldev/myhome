package in.gogol.model;

public class Result {

	Team home;
	Team away;
	Long homeGoals;
	Long awayGoals;
	Outcome outcome;
	
	public Team getHome() {
		return home;
	}

	public void setHome(Team home) {
		this.home = home;
	}

	public Team getAway() {
		return away;
	}

	public void setAway(Team away) {
		this.away = away;
	}

	public Long getHomeGoals() {
		return homeGoals;
	}

	public void setHomeGoals(Long homeGoals) {
		this.homeGoals = homeGoals;
	}

	public Long getAwayGoals() {
		return awayGoals;
	}

	public void setAwayGoals(Long awayGoals) {
		this.awayGoals = awayGoals;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	enum Outcome{
		HOME,
		AWAY,
		DRAW
	}
}
