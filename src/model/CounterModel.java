package model;

import java.util.ArrayList;

import entities.Counter;

public class CounterModel {

	private static CounterModel instance;

	private ArrayList<Counter> counters;

	private CounterModel() {
		counters = new ArrayList<Counter>();
	}

	public static CounterModel getInstance() {
		if (instance == null) {
			instance = new CounterModel();
		}

		return instance;
	}

	public boolean initiateNewCounter(int counterNumber) {
		for (Counter counter : counters) {
			if (counter.getCounterNumber() == counterNumber) {
				return false;
			}
		}

		Counter counter = new Counter(counterNumber);
		counters.add(counter);

		return true;
	}

	public Counter getCounter(int counterNumber) {
		for (Counter counter : counters) {
			if (counter.getCounterNumber() == counterNumber) {
				return counter;
			}
		}
		return null;
	}
}
