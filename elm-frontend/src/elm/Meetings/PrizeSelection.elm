module Meetings.PrizeSelection exposing (..)

import Html exposing (..)
import Html.Attributes exposing (class, href, scope, type_)
import Msgs exposing (Msg)
import Models exposing (MeetingDetails, Prize)

view : MeetingDetails -> Html Msg
view model =
  div []
    [ myNav model
    , importDiv model
    , prizeForm model
    ]

myNav : MeetingDetails -> Html Msg
myNav model =
  nav [class "breadcrumb"]
    [ ol [class "breadcrumb"]
      [ li [class "breadcrumb-item"] [ a [href "#meetings"] [ text "Meetings" ] ]
      , li [class "breadcrumb-item active" ] [text model.meetup.topic ]
      ]
    ]


importDiv : MeetingDetails -> Html Msg
importDiv meetingDetails =
  if meetingDetails.imported then
    div [] []
  else
    div [class "alert alert-primary"] [
    p [] [text ( meetingDetails.meetup.topic ++ " needs to be imported from meetup!")]
    , button [class "btn btn-primary"] [text "IMPORT"]
    ]

prizeForm : MeetingDetails -> Html Msg
prizeForm model =
      table [class "table table-striped"] [
      thead [] [
        th [scope "col"][text "Prize"]
        , th [scope "col"][text "Winner"]
        , th [scope "col"][text "Did Not Want"]
        , th [scope "col"][text "Not Present"]
        , th [scope "col"][text "Select Winner"]
      ]
      , tbody [] (List.map (\prize -> prizeRow model.complete prize) model.prizes)
      ]


prizeRow : Bool -> Prize -> Html Msg
prizeRow completed prize =
  let
    winnerName = case prize.winner of
      Just dude ->
        dude.name
      Nothing ->
       ""
  in
    if completed then
      tr []
        [ td [] [ text prize.name ]
        , td [] [ text winnerName ]
        , td [] [ text "" ]
        , td [] [ text "" ]
        , td [] [ text "" ]
        ]
    else
      tr []
        [ td [] [ text prize.name ]
        , td [] [ text winnerName ]
        , td [] [ button [type_ "button", class "btn btn-warning"] [ text "Did Not Want" ] ]
        , td [] [ button [type_ "button", class "btn btn-danger"] [text "Not Present" ] ]
        , td [] [ button [type_ "button", class "btn btn-primary" ] [text "Choose Winner"] ]
        ]
