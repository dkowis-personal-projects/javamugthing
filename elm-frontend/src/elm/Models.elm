module Models exposing (..)

import Date exposing (Date, now)
import RemoteData exposing (WebData)

type alias Model =
  { api: String
  , meetings : WebData (List MeetingDetails)
  , route : Route
  }


initialModel : String -> Route -> Model
initialModel api route =
  { api = api
  , meetings = RemoteData.Loading
  , route = route
  }

--TODO: how do I handle unauthorized and authorized parts?
type Route
  = MeetingsRoute
  | MeetingRoute MeetingId
  | NotFoundRoute



type alias MeetupId = String
type alias MeetingId = String

type alias MeetupEvent =
  { id : MeetupId
  , date : Date
  , topic : String
  }

type alias Attendee =
  { email : String
  , name : String
  , company : String
  , present : Bool
  }

type alias PrizeId = Int

type alias Prize =
  { id : PrizeId
  , name : String
  , winner : Maybe Attendee
  }

type alias MeetingDetails =
  { meetingId : MeetingId
  , meetup : MeetupEvent
  , attendees : List Attendee
  , prizes : List Prize
  , complete : Bool
  , imported : Bool
  }