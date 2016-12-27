package Exercises4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*Challenge:
 * Your company built an in-house calendar tool called HiCal. You want to add a feature to see the times in a day when everyone is available.
To do this, you’ll need to know when any team is having a meeting. In HiCal, a meeting is stored as an object of a Meeting class with integer variables startTime and endTime. These integers represent the number of 30-minute blocks past 9:00am.
 
 *For example:

  new Meeting(2, 3); // meeting from 10:00 – 10:30 am
new Meeting(6, 9); // meeting from 12:00 – 1:30 pm

Write a function mergeRanges() that takes a list of meeting time ranges and returns a list of condensed ranges.

For example, given:

  [(0, 1), (3, 5), (4, 8), (10, 12), (9, 10)]

your function would return:

  [(0, 1), (3, 8), (9, 12)]
 */


public class CalendarMerger {

	public static void main(String[] args) {
		Meeting meeting1 = new Meeting(0, 1);
		Meeting meeting2 = new Meeting(3, 5);
		Meeting meeting3 = new Meeting(4, 8);
		Meeting meeting4 = new Meeting(10, 12);
		Meeting meeting5 = new Meeting(9, 10);

		//create arrayList of meetings for testing
		ArrayList <Meeting> arrayList = new ArrayList<>();
		arrayList.add(meeting5);
		arrayList.add(meeting1);
		arrayList.add(meeting3);
		arrayList.add(meeting4);
		arrayList.add(meeting2);

		System.out.println("Solution 1:" + mergeRanges1(arrayList));
		System.out.println("Solution 2:" + mergeRanges2(arrayList));
	}

	//Solution 1 - Better
	//Time Complexity: O(nlogn)
	//Space Complexity: O(n)
	public static ArrayList<Meeting> mergeRanges1(ArrayList<Meeting> meetings){
		//sort the meetings
		Collections.sort(meetings, new Comparator<Meeting>(){
			@Override
			public int compare(Meeting meeting1, Meeting meeting2) {
				if (meeting1.startTime < meeting2.startTime){
					return -1;
				}
				if (meeting1.startTime > meeting2.startTime){
					return 1;
				}
				return 0;
				//same as:
				//return meeting1.startTime - meeting2.startTime;
			}
		});

		//change the name, now that it's sorted
		ArrayList<Meeting> sortedList = meetings;

		//create mergedArrayList and initialize it with the earliest meeting
		ArrayList<Meeting> mergedList = new ArrayList<>();
		mergedList.add(sortedList.get(0));

		Meeting currentMeeting;
		//Go through the sorted meetings
		for (int index = 1; index < sortedList.size(); index++){

			//get the latest entry from the merged Meetings list (the latest merged Meeting)
			Meeting latestMergedMeeting = mergedList.get(mergedList.size()-1);

			currentMeeting = meetings.get(index);

			if (currentMeeting.startTime <= latestMergedMeeting.endTime){
				latestMergedMeeting.endTime = Math.max(latestMergedMeeting.endTime, currentMeeting.endTime);
			}

			//no overlap, so just add the current meeting
			else{
				mergedList.add(currentMeeting);
			}
		}

		return mergedList;
	}


	//Solution 2 - using a boolean array.
	//Time Complexity: 2n + latestMeetingTime
	//Space Complexity: latestMeetingTime
	public static ArrayList<Meeting> mergeRanges2(ArrayList<Meeting> meetings){
		ArrayList<Meeting> mergedMeetingsArrayList = new ArrayList<>();
		//find the maximum value so we know what size to make the array.
		//loop through our meetings' ending times
		int maxMeetingTime = meetings.get(0).endTime;
		for (Meeting currentMeeting : meetings) {
			if(currentMeeting.endTime > maxMeetingTime){
				maxMeetingTime = currentMeeting.endTime;
			}
		}
		//create an array boolean arrayList to represent the list of merged meetings
		//each slot is false by default.
		//a true slot will represent that the upcoming half hour is occupied by a meeting.
		boolean[] mergedArray = new boolean[maxMeetingTime];

		//go through all of our meetings and fill in our merged Array
		for (Meeting currentMeeting : meetings){
			int currentStart = currentMeeting.startTime;
			int currentEnd = currentMeeting.endTime;

			for (int index = currentStart; index < currentEnd; index++) {
				mergedArray[index] = true;
			}
		}

		//now we can use our mergedArray to create the merged meetings ArrayList
		int start = -1;
		int end = -1;
		for (int index = 0; index < mergedArray.length; index++){
			boolean currentSlot = mergedArray[index];
			if(currentSlot == true){
				//if this is the starter
				if(start == -1){
					start = index;
				}
				//there is already a start:
				else{
					end = index + 1;
				}
			}
			else{
				if(start == -1){
					//move on to the next cell
				}
				else{
					//if it was a one-slot meeting, we never filled in an endtime 
					if(end == -1){
						end = start + 1;
					}
					Meeting meeting = new Meeting(start, end);
					mergedMeetingsArrayList.add(meeting);

					//re-initialize start and end
					start = -1;
					end = -1;
				}
			}
		}
		//if start is not -1, we missed recording the last meeting
		if(start != -1){
			//if it was a one-slot meeting, we never filled in an endtime 
			if(end == -1){
				end = start + 1;
			}
			Meeting meeting = new Meeting(start, end);
			mergedMeetingsArrayList.add(meeting);
		}
		return mergedMeetingsArrayList;
	}
}
