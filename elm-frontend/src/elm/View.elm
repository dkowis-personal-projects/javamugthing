module View exposing (..)

import Html exposing (Html, div, text)
import Html.Attributes exposing (class)
import Msgs exposing (Msg)
import Models exposing (Model, MeetingId)
import Meetings.List
import Meetings.PrizeSelection
import RemoteData

--Parent container, everything starts in this
view : Model -> Html Msg
view model =
  div [class "container"] [page model]


page : Model -> Html Msg
page model =
  case model.route of
    Models.MeetingsRoute ->
      Meetings.List.view model.meetings

    Models.MeetingRoute id ->
      prizeSelectionPage model id

    Models.NotFoundRoute ->
      notFoundView

prizeSelectionPage : Model -> MeetingId -> Html Msg
prizeSelectionPage model meetingId =
  case model.meetings of
    RemoteData.NotAsked ->
      text ""
    RemoteData.Loading ->
      text "Loading ..."
    RemoteData.Success meetings ->
      let
        maybeMeeting = meetings
          |> List.filter (\meeting -> meeting.meetingId == meetingId)
          |> List.head
      in
        case maybeMeeting of
          Just meeting ->
            Meetings.PrizeSelection.view meeting
          Nothing ->
            notFoundView
    RemoteData.Failure err ->
      text (toString err)


notFoundView : Html msg
notFoundView =
  div [] [ text "Not Found!" ]