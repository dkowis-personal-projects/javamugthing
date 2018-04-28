module Models exposing (..)

import Date exposing (Date, now)

type alias Model =
  { api: String
  , meetings : List MeetingDetails
  }


initialModel : Model
initialModel =
  { api = ""
  , meetings = [
    MeetingDetails "1" (MeetupEvent "1" (Date.fromTime 1522891417000) "Sample Meetup")
      [ (Attendee "david@kow.is" "David Kowis" "Southwest Airlines" True)
      , (Attendee "jorge@landiv.ar" "Jorge Landivar" "DataDog" True)
      ]
      [ (Prize 1 "$50 to amazon" Nothing) ]
      False
  ]
  }


type alias MeetupId = String

type alias MeetupEvent =
  { id : MeetupId
  , date : Date
  , topic : String
  }

type alias Attendee =
  { email : String
  , name : String
  , company : String
  , isPresent : Bool
  }

type alias PrizeId = Int

type alias Prize =
  { id : PrizeId
  , name : String
  , winner : Maybe Attendee
  }

type alias MeetingDetails =
  { meetingId : String
  , meetup : MeetupEvent
  , attendees : List Attendee
  , prizes : List Prize
  , isComplete : Bool
  }