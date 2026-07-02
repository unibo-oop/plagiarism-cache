package model;

import java.util.Optional;

public class TimerImpl extends Timer implements ObservableTimer{
	private Optional<TimerObserver> observer = Optional.empty();
	public TimerImpl(long a,long b) {
		super(a,b);
	}
	public TimerImpl(long a,long b,TimerObserver obs) {
		super(a,b);
		addObserver(obs);
	}
	@Override
	protected void onTick() {
		notifyObservers();
	}

	@Override
	protected void onFinish() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addObserver(TimerObserver observer) {
		if(!this.observer.isPresent()) {
			this.observer = Optional.of(observer);
		}
	}

	@Override
	public void notifyObservers() {
		observer.ifPresent(x -> x.update(getRemainingTime()));
	}

}
