package model;

import java.io.Serializable;

import model.enumerations.*;
import model.interfaces.*;

public class ExerciseImpl implements Exercise, Serializable{
	
	private static final long serialVersionUID = 1L;
	private ExerciseType type;
	private String name;
	private String set;
	private String reps;
	private String rest;
	private String weight; //da mettere optional

	public ExerciseImpl(ExerciseType type, String name, String set, String reps, String rest, String weight) {
		this.type = type;
		this.name = name;
		this.set = set;
		this.reps = reps;
		this.rest = rest;
		this.weight = weight;
	}
	
	public ExerciseImpl(String name) {
		this.name = name;
	}

	@Override
	public ExerciseType getType() {
		return this.type;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getSet() {
		return this.set;
	}

	@Override
	public String getReps() {
		return this.reps;
	}

	@Override
	public String getRest() {
		return this.rest;
	}

	@Override
	public String getWeight() {
		return this.weight;
	}
	
	public static class ExerciseBuilder {
		private ExerciseType type;
		private String name;
		private String set;
		private String reps;
		private String rest;
		private String weight; // da mettere optional
		
		public ExerciseBuilder type(ExerciseType type) {
			this.type = type;
			return this;
		}
		
		public ExerciseBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public ExerciseBuilder set(String set) {
			this.set = set;
			return this;
		}
		
		public ExerciseBuilder reps(String reps) {
			this.reps = reps;
			return this;
		}
		
		public ExerciseBuilder rest(String rest) {
			this.rest = rest;
			return this;
		}
		
		public ExerciseBuilder weight(String weight) {
			this.weight = weight;
			return this;
		}
		
		public ExerciseImpl build() {
			return new ExerciseImpl(this.type, this.name, this.set, this.reps,this.rest, this.weight);
		}
		
	}
}
