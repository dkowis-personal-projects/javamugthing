module Models exposing (..)

import Date exposing (Date)

type alias Model =
  { api: String
  , meetings : List MeetingDetails
  }


initialModel : Model
initialModel =
  { api = ""
  , meetings = []
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