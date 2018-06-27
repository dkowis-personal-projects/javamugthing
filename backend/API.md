# API notes

Trying to design the API a bit, so I can get enough working.

## Authenticated Admin API

### Meeting details

| method| path | notes |
| --:| -- | -- |
GET | `/meetings` | List Meetings (open and closed)
GET | `/meetings/#id` | meeting details
GET | `/meetings/#id/attendees` | list people imported from meetup
POST | `/meetings/#id/attendees` | add an attendee
PUT | `/meetings/#id/attendees/#id` | update an attendee (mostly not-present, or data correction)
  

### import meeting

| method| path | notes |
| --:| -- | -- |
POST | `/importMeeting` | import a meeting from meetup

### giveaway

| method| path | notes |
| --:| -- | -- |
POST | `/meetings/#id/giveaway` | create the giveaway, can no longer add attendees
GET | `/meetings/#id/giveaway` | current state of this meeting's giveaway
PUT | `/meetings/#id/giveaway` | update the giveaway (mostly to complete)

### prizes in a giveaway

| method| path | notes |
| --:| -- | -- |
GET | `/meetings/#id/giveaway/prizes` | list all prizes
POST | `/meetings/#id/giveaway/prizes` | add another prize (only for this giveaway)
PUT | `/meetings/#id/giveaway/prizes/#id` | update a prize, only for this giveaway
DELETE | `/meetings/#id/giveaway/prizes/#id` | remove a prize, only for this giveaway

### winners of prizes in a giveaway
| method| path | notes |
| --:| -- | -- |
GET | `/meetings/#id/giveaway/prizes/#id/attendees` | list attendees of this meeting that can win this prize
POST | `/meetings/#id/giveaway/prizes/#id/attendees` | Command to win, remove, didnotwant, or notpresent


## Unauthenticated user API

### meeting details
| method| path | notes |
| --:| -- | -- |
GET | `/meetings/` | List meetings, open and closed
GET | `/meetings/#id` | meeting details


### watch current meeting
| method| path | notes |
| --:| -- | -- |
GET | `/meetings/#id/giveaway` | should update details as they happen
GET | `/meetings/#id/giveaway/#email` | provide a live notification if you win a thing!

Will need websockets and payloads in those to make it happen