module Meetings.PrizeSelection exposing (..)

import Html exposing (..)
import Html.Attributes exposing (class, disabled, href, scope, type_)
import Html.Events exposing (onClick)
import Msgs exposing (Msg)
import Models exposing (MeetingDetails, Prize)
import RemoteData

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


--TODO: this method has so much copypasta!
importDiv : MeetingDetails -> Html Msg
importDiv meetingDetails =
  case meetingDetails.importing of
    Nothing ->
      div [] []
    Just RemoteData.NotAsked ->
      div [class "alert alert-primary"] [
      p [] [text ( meetingDetails.meetup.topic ++ " needs to be imported from meetup!")]
      , button [class "btn btn-primary", onClick (Msgs.ImportMeeting meetingDetails) ] [text "IMPORT"] --TODO: could reuse the component!
      ]
    Just RemoteData.Loading ->
      div [class "alert alert-primary"] [
      p [] [text ( meetingDetails.meetup.topic ++ " needs to be imported from meetup!")]
      , button [class "btn btn-primary", onClick (Msgs.ImportMeeting meetingDetails), disabled True ] [text "LOADING"] --TODO: could reuse the component!
      ]
    Just (RemoteData.Success value) ->
      -- TODO: need to reload the meeting
      div [class "alert alert-primary"] [
      p [] [text ( meetingDetails.meetup.topic ++ " I need to reload the data!")]
      , button [class "btn btn-primary", onClick (Msgs.ImportMeeting meetingDetails), disabled True ] [text "OOPS"] --TODO: could reuse the component!
      ]
    Just (RemoteData.Failure err) ->
      -- TODO: whoopsie?
      div [class "alert alert-primary"] [
      p [] [text ( meetingDetails.meetup.topic ++ " didn't work! " ++ (toString err))]
      , button [class "btn btn-primary", onClick (Msgs.ImportMeeting meetingDetails), disabled True ] [text "CRAP"] --TODO: could reuse the component!
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
