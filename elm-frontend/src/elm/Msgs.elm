module Msgs exposing (..)

import Models exposing (MeetingDetails)
import RemoteData exposing (WebData)


type Msg
 = OnFetchMeetingDetails (WebData (List MeetingDetails))