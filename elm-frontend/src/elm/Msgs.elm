module Msgs exposing (..)

import Http
import Models exposing (MeetingDetails)
import Navigation exposing (Location)
import RemoteData exposing (WebData)


type Msg
 = OnFetchMeetingDetails (WebData (List MeetingDetails))
 | OnLocationChange Location
 | OnMeetingImport (Result Http.Error MeetingDetails) --TODO: maybe use WebData, because it gives me a loading thing