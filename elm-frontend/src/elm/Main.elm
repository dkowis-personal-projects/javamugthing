module Main exposing (..)

import Html exposing (programWithFlags)
import Msgs exposing (Msg)
import Models exposing (Model, initialModel)
import Update exposing (update)
import View exposing (view)
import Commands exposing (fetchMeetingDetails)

-- Main application entrance!
main =
  Html.programWithFlags {
    init = init,
    subscriptions = subscriptions,
    view = view,
    update = update
  }

-- Flags from the javascript side
type alias Flags =
  { apiHost : String
  }

init : Flags -> (Model, Cmd Msg)
init flags =
  ({ initialModel | api = flags.apiHost }, fetchMeetingDetails flags.apiHost )

-- SUBSCRIPTIONS

subscriptions : Model -> Sub Msg
subscriptions model =
  Sub.none
