module Meetings.List exposing (..)

import Date exposing (Date)
import Html exposing (..)
import Html.Attributes exposing (class, href)
import Msgs exposing (Msg)
import Models exposing (MeetingDetails)
import RemoteData exposing (WebData)

view : WebData (List MeetingDetails) -> Html Msg
view response =
  div []
    [ myNav
    , maybeList response
    ]

maybeList : WebData (List MeetingDetails) -> Html Msg
maybeList response =
  case response of
    RemoteData.NotAsked ->
      text ""
    RemoteData.Loading ->
      text "Loading ..."
    RemoteData.Success meetingDetails ->
      list meetingDetails
    RemoteData.Failure error ->
      text (toString error)

myNav : Html Msg
myNav =
  nav [class "breadcrumb"]
    [ ol [class "breadcrumb"]
      [ li [class "breadcrumb-item active"] [ text "Meetings" ]
      ]
    ]

list : List MeetingDetails -> Html Msg
list meetingDetails =
  div [ class "list-group" ]
    (List.map detailsRow meetingDetails)

detailsRow : MeetingDetails -> Html Msg
detailsRow details =
  a [href ("#meetings/" ++ details.meetingId), class "list-group-item" ]
    [ h5 [class "mb-1"] [ text ((dateFormat details.meetup.date) ++ " " ++ details.meetup.topic) ]
    , p [class "mb-1" ] [ text ((toString (List.length details.attendees)) ++ " Attendees") ]
    ]

dateFormat : Date -> String
dateFormat date =
  (toString (Date.day date)) ++ "-" ++
    (toString (Date.month date)) ++ "-" ++
    (toString (Date.year date))