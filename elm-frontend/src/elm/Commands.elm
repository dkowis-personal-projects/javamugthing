module Commands exposing (..)

import Date exposing (Date)
import Http
import Json.Decode as Decode exposing (nullable)
import Json.Decode.Pipeline exposing (decode, hardcoded, optional, required)
import Json.Decode.Extra as JsonExtra
import Msgs exposing (Msg)
import Models exposing (MeetingDetails, MeetupEvent, Attendee, Prize)
import RemoteData exposing (WebData)

fetchMeetingDetails : String -> Cmd Msg
fetchMeetingDetails apiBase =
  Http.get (apiBase ++ "/api/meetings") meetingsDecoder
    |> RemoteData.sendRequest
    |> Cmd.map Msgs.OnFetchMeetingDetails


meetingsDecoder : Decode.Decoder (List MeetingDetails)
meetingsDecoder =
  Decode.list meetingDetailDecoder

meetingDetailDecoder : Decode.Decoder MeetingDetails
meetingDetailDecoder =
  decode MeetingDetails
    |> required "meetingId" Decode.string
    |> required "meetup" meetupDecoder
    |> required "attendees" attendeesDecoder
    |> required "prizes" prizesDecoder
    |> required "complete" Decode.bool
    |> required "imported" Decode.bool
    |> required "imported" importedDecoder

--Custom decoder for the Nothing or Just (RemoteData.NotAsked)
importedDecoder : Decode.Decoder (Maybe (WebData Bool))
importedDecoder =
  let
    convert: Bool -> Decode.Decoder (Maybe (WebData Bool))
    convert raw =
      if raw then
        Decode.succeed Nothing
      else
        Decode.succeed (Just RemoteData.NotAsked)
  in
    Decode.bool |> Decode.andThen convert


meetupDecoder : Decode.Decoder MeetupEvent
meetupDecoder =
  decode MeetupEvent
    |> required "id" Decode.string
    |> required "date" JsonExtra.date
    |> required "topic" Decode.string

attendeesDecoder : Decode.Decoder (List Attendee)
attendeesDecoder =
  Decode.list attendeeDecoder

attendeeDecoder : Decode.Decoder Attendee
attendeeDecoder =
  decode Attendee
    |> required "email" Decode.string
    |> required "name" Decode.string
    |> required "company" Decode.string
    |> required "present" Decode.bool

prizesDecoder : Decode.Decoder (List Prize)
prizesDecoder =
  Decode.list prizeDecoder

prizeDecoder : Decode.Decoder Prize
prizeDecoder =
  decode Prize
    |> required "id" Decode.int
    |> required "name" Decode.string
    |> required "winner" (nullable attendeeDecoder)

-- Meeting commands
importMeetingRequest : String -> MeetingDetails -> Http.Request MeetingDetails
importMeetingRequest baseUrl meetingDetails =
  Http.request
    { body = Http.emptyBody
    , expect = Http.expectJson meetingDetailDecoder
    , headers = []
    , method = "POST"
    , timeout = Nothing
    , url = baseUrl ++ "/api/meetings/" ++ meetingDetails.meetingId ++ "/import"
    , withCredentials = False
    }

importMeetingCmd : String -> MeetingDetails -> Cmd Msg
importMeetingCmd baseUrl meetingDetails =
  importMeetingRequest baseUrl meetingDetails
   |> Http.send Msgs.OnImportMeeting