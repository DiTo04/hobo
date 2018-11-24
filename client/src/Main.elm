port module Main exposing (main)

import Browser
import Html exposing (..)
import Html.Attributes exposing (..)
import Html.Events exposing (..)
import Json.Decode exposing (Decoder, field, string)
import Json.Encode as E

port sendAction : String -> Cmd msg

-- MAIN


main =
  Browser.element
    { init = init
    , update = update
    , subscriptions = subscriptions
    , view = view
    }



-- MODEL

type alias Model =
  { tiles: List (List Tile)
  , turn: Player
  , winner: Player
  , flag: Int
  }

type Player = X | O | Empty

type alias Tile =
  { xcoord: Int
  , ycoord: Int
  , marker: Player
  }

startMap : List (List Tile)
startMap =
  [ [ Tile -1 -1 Empty, Tile 0 -1 Empty, Tile 1 -1 Empty ]
  , [ Tile -1 0 Empty, Tile 0 0 X, Tile 1 0 Empty ]
  , [ Tile -1 1 Empty, Tile 0 1 Empty, Tile 1 1 O ]
  ]


init : Int -> (Model, Cmd Msg)
init flag =
  (Model startMap X Empty flag, Cmd.none)



-- UPDATE


type Msg
  = SendAction String
  | ChooseTile Tile Player


update : Msg -> Model -> (Model, Cmd Msg)
update msg model =
  case msg of
    SendAction str ->
      ( model, sendAction str )
    ChooseTile tile player ->
      case player of
        X -> ( model, sendAction ("X," ++ (String.fromInt tile.xcoord) ++ "," ++ (String.fromInt tile.ycoord) ) )
        O -> ( model, sendAction ("O," ++ (String.fromInt tile.xcoord) ++ "," ++ (String.fromInt tile.ycoord) ) )
        _ -> ( model, Cmd.none )
      



-- SUBSCRIPTIONS


subscriptions : Model -> Sub Msg
subscriptions model =
  Sub.none



-- VIEW


view : Model -> Html Msg
view model =
  div [ class "lol" ]
    [ 
      div [] (List.map (
        \row -> div [] (List.map (\tile -> node tile model.turn ) row)
      ) model.tiles)
    ]

node : Tile -> Player -> Html Msg
node tile player =
  case tile.marker of
    X -> span [ onClick (SendAction "X") ] [ text "X" ]
    O -> span [ onClick (SendAction "O") ] [ text "O" ]
    Empty -> span [ onClick (ChooseTile tile player) ] [ text "-" ]






-- HTTP


-- getRandomCatGif : Cmd Msg
-- getRandomCatGif =
--   Http.get
--     { url = "https://api.giphy.com/v1/gifs/random?api_key=dc6zaTOxFJmzC&tag=cat"
--     , expect = Http.expectJson GotGif gifDecoder
--     }


-- gifDecoder : Decoder String
-- gifDecoder =
--   field "data" (field "image_url" string)