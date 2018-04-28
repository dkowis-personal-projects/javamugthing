module Msgs exposing (..)

import Models exposing (MeetingDetails)
import Navigation exposing (Location)
import RemoteData exposing (WebData)


type Msg
 = OnFetchMeetingDetails (WebData (List MeetingDetails))
 | OnLocationChange Location