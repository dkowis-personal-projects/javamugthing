# Java Metroplex Users Group Application Thing!

Useful software for the Java Metroplex Users Group.

## Run in development mode

Run the spring boot app, this is the API, listening on localhost:8080.
The java application development process is the same as all others. CORS
has to be disabled in Dev mode, because elm-reactor doesn't proxy the backend calls.

Then, cd into `elm-frontend`, run `elm-package install`, and start `elm-reactor`. That will compile and stuff your elm frontend! Finally, navigate in your browser to `http://localhost:8000/src/html/dev.html`.

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
  * show past Meetups, read-only
  * have a way to import a coming meetup
  * show open meetups
    * show import problems
    * add attendees manually
    * idempotently update again
    * start the giveaway
  * Default prizes settings (CRUD)
  * Giveaway UI
    * default prizes are pre-loaded
    * Select attendee to win
    * Note a attendee as not-present
    * Note a attendee as did-not-want
    * Complete giveaway -- all done
    * add random prize
    * remove prize
  * View previous giveaways
    * Export attendee list

* Regular UI
  * see past meetups and winners
  * see current meetup, updating live

* Notification UI for mobile. 
  * if you won, flash and vibrate!