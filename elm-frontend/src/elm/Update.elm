module Update exposing (..)
import Commands exposing (importMeetingCmd)
import Msgs exposing (Msg)
import Models exposing (MeetingDetails, Model)
import RemoteData
import Routing exposing (parseLocation)

update : Msg -> Model -> (Model, Cmd Msg)
update msg model =
  case msg of
    Msgs.OnFetchMeetingDetails response ->
      ( {model | meetings = response }, Cmd.none)

    Msgs.OnLocationChange location ->
      let
        newRoute = parseLocation location
      in
        ( {model | route = newRoute }, Cmd.none)

    Msgs.ImportMeeting meetingDetails ->
      ( model, importMeetingCmd model.api meetingDetails)

    Msgs.OnImportMeeting (Err error) ->
      (model, Cmd.none)

    Msgs.OnImportMeeting (Ok meetingDetails) ->
      (updateMeetingDetails model meetingDetails, Cmd.none)


updateMeetingDetails : Model -> MeetingDetails -> Model
updateMeetingDetails model updatedMeeting =
  let
    pick currentMeeting =
      if updatedMeeting.meetingId == currentMeeting.meetingId then
        updatedMeeting
      else
        currentMeeting

    updateMeetingsList meetings =
      List.map pick meetings

    updatedMeetings =
      RemoteData.map updateMeetingsList model.meetings
  in
    { model | meetings = updatedMeetings }
