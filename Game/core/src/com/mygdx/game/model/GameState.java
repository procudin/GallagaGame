/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;


/*
 * Состояния игры
 */
public enum GameState {
    GAMEOVER,       /// Игра проиграна
    WIN,            /// Игра выиграна
    BOSSFIGHT,      /// Бой с боссом
    INGAME          /// Бой
}
