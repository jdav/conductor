package com.malleamus.conductor.model;

import java.util.ArrayList;

public interface Task {

		public String getDescription();
		public ArrayList<Assignment> getAssignments();
		public ArrayList<Participant> getParticipants();
}
