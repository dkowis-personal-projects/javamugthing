module Main exposing (..)

import Html exposing (programWithFlags)
import Msgs exposing (Msg)
import Models exposing (Model, initialModel)
import Update exposing (update)
import View exposing (view)

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
  ({ initialModel | api = flags.apiHost }, Cmd.none)

-- SUBSCRIPTIONS

subscriptions : Model -> Sub Msg
subscriptions model =
  Sub.none
