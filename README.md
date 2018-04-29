# Java Metroplex Users Group Application Thing!

Useful software for the Java Metroplex Users Group.

## Run in development mode

Run the spring boot app, listening on localhost:8080.
The java application development process is the same as all others. CORS
has to be disabled in Dev mode, because elm-reactor doesn't proxy the backend calls.

cd into `elm-frontend` and start `elm-reactor`

navigate in your browser to `src/html/dev.html`

That will compile and stuff your elm frontend!

## TODO

* A better name
    * Meetuper
    * JaMeetup
    * MeetupManager
    * MeetupArchitect
    * Meetupgineer
    * Mugger
    * Meetups
    * MeetupMarketer
    * LetsMeetup
* Admin UI
  * View Meetup Events (according to meetup.com api)
    * add attendee that didn't get to RSVP on meetup
    * pre-load meetup attendees
    * update meetup attendees
    * 
  * Start a giveaway thing
    * Select attendee
    * Note a attendee as not-present
    * Note a attendee as did-not-want
    * Complete giveaway
  * View previous giveaways
    * Export attendee list

