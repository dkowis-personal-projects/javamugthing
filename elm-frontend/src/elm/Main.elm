module Main exposing (..)

import Html exposing (programWithFlags)
import Msgs exposing (Msg)
import Models exposing (Model, initialModel)
import Update exposing (update)
import View exposing (view)
import Commands exposing (fetchMeetingDetails)
import Navigation exposing (Location)
import Routing

-- Main application entrance!
main =
  Navigation.programWithFlags Msgs.OnLocationChange
    { init = init
    , view = view
    , update = update
    , subscriptions = subscriptions
    }

type alias Flags =
  { apiHost : String
  }

init : Flags -> Location -> (Model, Cmd Msg)
init flags location =
  let
    currentRoute = Routing.parseLocation location
  in
    (initialModel flags.apiHost currentRoute, fetchMeetingDetails flags.apiHost )

-- SUBSCRIPTIONS

subscriptions : Model -> Sub Msg
subscriptions model =
  Sub.none
