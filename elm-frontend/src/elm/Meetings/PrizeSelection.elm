module Meetings.PrizeSelection exposing (..)

import Html exposing (..)
import Html.Attributes exposing (class, href)
import Msgs exposing (Msg)
import Models exposing (MeetingDetails)

view : MeetingDetails -> Html Msg
view model =
  div []
    [ nav model
    , prizeForm model
    ]

nav : MeetingDetails -> Html Msg
nav model =
  div [ ]
    [ div [] [ h1 [] [
     a [href "#meetings"] [text "Meetings"]
     , text (" > " ++ model.meetup.topic)
     ] ] ]

prizeForm : MeetingDetails -> Html Msg
prizeForm model =
  div []
   (List.map (\prize -> p [] [text prize.name]) model.prizes)