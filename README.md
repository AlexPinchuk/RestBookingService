# RestBookingServer

# Task
Your employer has an existing system for employees to submit booking requests for meetings in the boardroom. Your employer has now asked you to implement a system for processing batches of booking requests. Current system is based on text messages, but you need to do it using JSON

Your processing system must receive input (and sent output) message not as text, but in JSON format. Below you see the data as row text (how it’s used in a current system), so please create a correspondent JSON structure yourself. 

Old system input:

	  0900 1730 
	  2011-03-17 10:17:06 EMP001 
	  2011-03-21 09:00 2 
	  2011-03-16 12:34:56 EMP002 
	  2011-03-21 09:00 2
	  2011-03-16 09:28:23 EMP003 
	  2011-03-22 14:00 2 
	  2011-03-17 11:23:45 EMP004 
	  2011-03-22 16:00 1 
	  2011-03-15 17:29:12 EMP005 
	  2011-03-21 16:00 3

Old system output:

	  2011-03-21 
	  09:00 11:00 EMP002 
	  2011-03-22 
	  14:00 16:00 EMP003 
	  16:00 17:00 EMP004

# Input
A sample input using JSON string:

	{
		"officeTimetable" : {
			"officeStartTime" : "0900",
			"officeFinishTime" : "1730"		
		},

		"bookingRequests" : [
			{
				"requestSubmissionTime" : "2011-03-17 10:17:06",
				"employeeId" : "EMP001",
				"meetingStartTime" : "2011-03-21 09:00",
				"meetingDuration" : "2"
			},

			{
				"requestSubmissionTime" : "2011-03-16 12:34:56",
				"employeeId" : "EMP002",
				"meetingStartTime" : "2011-03-21 09:00",
				"meetingDuration" : "2"
			},

			{
				"requestSubmissionTime" : "2011-03-16 09:28:23",
				"employeeId" : "EMP003",
				"meetingStartTime" : "2011-03-22 14:00",
				"meetingDuration" : "2"
			},

			{
				"requestSubmissionTime" : "2011-03-17 11:23:45",
				"employeeId" : "EMP004",
				"meetingStartTime" : "2011-03-22 16:00",
				"meetingDuration" : "1"
			},

			{
				"requestSubmissionTime" : "2011-03-15 17:29:12",
				"employeeId" : "EMP005",
				"meetingStartTime" : "2011-03-21 16:00",
				"meetingDuration" : "3"
			}
		]	
	}

# Output
The system provides a successful booking calendar as output, with bookings being grouped chronologically by day.
For the sample input displayed above, the system must provide the following JSON string as output:

	{
		"2011-03-21" : [
			{
				"employeeId" : "EMP002"
				"meetingStartTime" : "09:00",
				"meetingFinishTime" : "11:00"
			}
		],


		"2011-03-22" : [
			{
				"employeeId" : "EMP003"
				"meetingStartTime" : "14:00",
				"meetingFinishTime" : "16:00"
			},

			{
				"employeeId" : "EMP004"
				"meetingStartTime" : "16:00",
				"meetingFinishTime" : "17:00"
			}
		]		
	}
	
# Request Information

	Method: POST
	Media Type: application/json
	Resource URL: //RestBookingService/services/bookingService/schedule
