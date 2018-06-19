# API notes

## Meeting details

| method| path | notes |
| --:| -- | -- |
GET | `/meetings` | List Meetings (open and closed)
GET | `/meetings/#id` | meeting details
GET | `/meetings/#id/attendees` | list people imported from meetup
POST | `/meetings/#id/attendees` | add an attendee
PUT | `/meetings/#id/attendees/#id` | update an attendee (mostly not-present, or data correction)
  


/*
   GET /meetings  - list meetings (open and closed)
   GET /meetings/{id} - meeting details
   GET /meetings/{id}/attendees - list of people imported from meetup
  POST /meetings/{id}/attendees - add an attendee
   PUT /meetings/{id}/attendees/{id} - update an attendee - not present

  POST /importMeeting -- Import a meeting from meetup

  POST /meetings/{id}/giveaway -- create the giveaway (can no longer adjust attendees)
   GET /meetings/{id}/giveaway -- current state of giveaway, who won prizes
   PUT /meetings/{id}/giveaway -- update giveaway -- complete!

   GET /meetings/{id}/giveaway/prizes -- list all prizes
  POST /meetings/{id}/giveaway/prizes -- create another prize (just for this giveaway)
   PUT /meetings/{id}/giveaway/prizes/{id} -- update a prize (just for this giveaway)
DELETE /meetings/{id}/giveaway/prizes/{id} --remove a prize (just for this giveaway)

   PUT /meetings/{id}/giveaway/prizes/{id}/attendees/{id} -- update prize winner (or did not want)
   GET /meetings/{id}/giveaway/prizes/{id}/attendees -- get list of attendees that could win this
DELETE /meetings/{id}/giveaway/prizes/{id}/attendees/{id} -- remove attendee details from prize
  POST /meetings/{id}/giveaway/prizes/{id}/attendees -- put a winner on that prize


 */
