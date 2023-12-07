# Initial Design
## Entities (from project blueprint and incorporated comments from TA)
Note: 
- ID (int): Unique identifier of the event [IDENTIFIER]
- Name (string): The title or name of the event
- User (UserID): The user that this event belongs to
- Location (String): The physical location or the virtual meeting link of the event
- Description (String): A description of the event
- Label (enum): Label or category associated with the event (default enum values: personal or work)
- Pinned (boolean): Indicates whether the event is pinned for quick access
- Sub-events (ArrayList of EventID): The child events of this event

*Reminders:* 
1) *Sub-events have same structure as events*
2) *Let's not deal with recurring events first (possibly imported from Google Calendar)*
3) *Event is displayed as "all-day" if StartTime and EndTime are indicated as 00:00 and 23:59 respectively*

LocalEvent (inheriting Note):
- Date (java.time.LocalDate): The date of the event
- StartTime (java.time.LocalTime): The time when the event occurs (default value: 00:00 when user didn't specify time)
- EndTime (java.time.LocalTime): The time when the event ends (default value: 23:59 when user didn't specify time)

User:
- ID (UserID): ID of user object [IDENTIFIER]
- Username (String): Username of the user used during login
- Password (byte[]): The hashed password string
- Credential (String): Credential JSON object from the user's credentials.json file they uploaded to access their Google account

## Classes and methods
Use case interactors:
- Note Input Boundary:
  - bool createNote(): create and save a note (make sure no descendants are the same)
  - bool editNote(): edit an existing note and save its changes (make sure no descendants are the same)
  - bool deleteNote(): delete an existing note
  - Note Output Data getNode(): check whether the note belongs to the user and return its corresponding Note instance when the user wants to view its details
  - ArrayList of Note Output Data getAllNotes(): get all notes to show in list or view
- Local Event Input Boundary:
  - bool createEvent(): create and save an event (make sure no sub-events are the same)
  - bool editEvent(): edit an existing event and save its changes (make sure no sub-events are the same)
  - bool deleteEvent(): delete an existing event
  - Event Output Data getEvent(): check whether the event belongs to the user and return its corresponding Event instance when the user wants to view its details
  - ArrayList of Event Output Data getAllEvents(): get all events to show in listed or calendar view
- External Event Input Boundary:
  - bool importEvent()
  - bool exportEvent()
- Local Event interactor (implementing Local Event Input Boundary):
  - bool createEvent(Event Input Data Object): create a local event
  - bool editEvent(Event Input Data Object)
  - bool deleteEvent(Event Input Data Object)
  - Event Output Data, Notification Output Data getEvent(EventID, UserID)
  - ArrayList of Event Output Data getAllEvents(UserID)
- Google Calendar Event interactor (implementing External Event Input Boundary):
  - bool importEvent(GoogleCalendarEventID): import an event from Google Calendar
  - bool exportEvent(Event Input Data Object): export an existing local event to the user's Google Calendar
  - ArrayList of Google Calendar Event Output Data getAllEvents(): get the user's events on their Google Calendar so that they can view them and choose which to import
- Notifications Input Boundary:
  - bool setOccurrences()
  - ArrayList<Java.time.localDateTime> getOccurrences()
- Notifications interactor (implementing Notifications Input Boundary):
  - bool setOccurrences(Notification Input Data Object): add, remove or edit existing occurrences
  - ArrayList<Java.time.localDateTime> getOccurrences(EventID, userID): get the list of occurrences associated with this event if it belongs to the user with userID specified
- Sign up Input Boundary:
  - bool createUser()
  - bool setCredentials()
- Sign up interactor (implementing Sign up Input Boundary):
  - bool createUser(Signup Data Input Object): create a User instance and save it in storage device
  - private bool setCredentials(Username, password, Credential): save the credentials for the user, hash the password
  - private bool isCredentialValid(Credential): check if the Credential object is valid
- Log in+out Input Boundary:
  - User Output Data logIn()
  - bool logOut()
- Log in+out interactor (implementing Log In+Out Input Boundary):
  - User Output Data logIn(Login Data Input Object): Match username with a User instance and check if password is correct
  - private bool createCalendar(Credential): create a Google Calendar instance with this OAuth Client ID
  - bool logOut(): Log out and the system should return to Login page

Data Access (depends on whether we implement it with database tables or JSON files):
- Data Access Interface:
  - Object getEntry()
  - bool addEntry()
  - bool removeEntry()
- Note Data Access Object (associated with Note entities)
- Event Data Access Object (associated with Event entities)
- User Data Access Object (associated with User and Credentials entities)
- Google Calendar Access Object

Data Input classes:
- Event/Notification input data:
  - All instance attributes in User but make them private
  - Constructor assigning values to all instance attributes
  - A method for returning values of all instance attributes
- Signup input data:
  - Private instance attributes: Username, Password, RepeatPassword, Credentials String
  - Constructor assigning these
  - A method for returning these
- Login input data:
  - Private instance attributes: Username, Password
  - Constructor assigning these
  - A method for returning these

Controllers:
- Event controller:
  - Methods to create, edit, delete, get or get all event(s) in which the corresponsing method in Local Event Input Boundary is called
- Google Calendar Event controller
- Notifications controller
- Signup controller
- Login+out controller

Output Data classes:
- Google Calendar Event output data:
  - Private instance attribute: important deatils in Google Calendar Event instance
  - Contructor to assign this
  - A method for returning the details
- LocalEvent/Notification output data:
  - Private instance attribute: all instance attributes in LocalEvent/Notification
  - Eventid and its corresponding name for associated event in Notification and sub-events in LocalEvent 
- User output data:
  - Private instance attributes: UserID, username associated with them
 
Presenters and Output Boundaries:

View models and Views (Details of each view described in README.md):
- List view
- Add view for note
- Add view for event
- Edit view for note
- Edit view for event
- Detailed view for note
- Detailed view for event
- Login view (elsie)
- Sign up view (elsie)
- Import event view (annie)
- Export event view (annie)
