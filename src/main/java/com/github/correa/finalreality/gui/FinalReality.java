package com.github.correa.finalreality.gui;

import com.github.correa.finalreality.controller.GameController;
import com.github.correa.finalreality.enums.*;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.IPlayerCharacter;
import com.github.correa.finalreality.model.weapon.IWeapon;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Main entry point for the application.
 *
 * The application consist in a series of choice box and buttons
 * the first ones being the player choices, and the other works
 * as set buttons, then we have different scenes for each enemy an
 * the player characters, also we have the waiting scene and both
 * players win scene and enemies win scene.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic
 */
public class FinalReality extends Application {

  private static final String RESOURCE_PATH = "src/main/resources/";
  private final GameController controller = new GameController();
  private IWeapon weapon;
  private CharacterType characterClass;
  private HashMap<Stats, Integer> finalStats;

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Entry point of the application.
   *
   * @param primaryStage
   *     the primary stage for this application, onto which the application scene can be set.
   *     Applications may create other stages, if needed, but they will not be primary stages.
   */
  @Override
  public void start(Stage primaryStage) throws FileNotFoundException {
    Platform.setImplicitExit(true);
    configureGame(primaryStage);
    primaryStage.show();
  }

  /**
   * First scene of the game, here we can set the basic configurations
   * for the game.
   */
  private void configureGame(Stage stage) throws FileNotFoundException {
    var configRoot = new Group();
    var partySizeRoot = new Group();
    var enemiesRoot = new Group();
    var createRoot = new Group();

    readImage("config_background.jpg", 0, 0, configRoot);
    stage.setTitle("Final reality : Setting Game");
    stage.setResizable(false);

    var text1 = createRectangleText(
        "Initial configurations",
        600, 30,
        Color.DODGERBLUE, configRoot);

    uniformScale(text1, 3);

    // Selecting Party size.
    var party = new ArrayList<String>();
    party.add("1");
    party.add("2");
    party.add("3");
    party.add("4");

    var partyChoice = createPartyChoiceBox(
        "Party Size: ",
        200, 100, party,
        Color.LIGHTGREEN, partySizeRoot);
    var setButton = createButton(
        "Set Size", 270, 100, partySizeRoot);

    var classes = new ArrayList<String>();
    for (var playerClass : CharacterType.values()) {
      classes.add(String.valueOf(playerClass));
    }
    classes.remove(String.valueOf(CharacterType.ENEMY));

    // Setting Party Size.
    setButton.setOnAction(event -> {
      if (controller.getPartySize() > 0) {
        configRoot.getChildren().remove(partySizeRoot);

        var partyLabel = createRectangleText(
            "Party Size: " + controller.getPartySize(), 200, 100, Color.LIGHTGREEN, configRoot);
        uniformScale(partyLabel, 2);

        var textField = createLabeledTextField(
            "Name:   ", 200, 200, Color.LIGHTGREEN, createRoot);

        createClassChoiceBox(
            200, 230, classes, Color.LIGHTGREEN, createRoot);

        //Randomizing stats for the player character.
        var randomizeStats = createButton(
            "Randomize Stats", 150, 400, createRoot);
        randomizeStats.setOnAction(event1 -> {
          finalStats = controller.getRandomStats();
          displayBasicStats(
              finalStats, 150,
              260, Color.LIGHTGREEN, createRoot);
        });

        //Create a character button.
        var createCharacterButton = createButton(
            "Create Character", 270, 400, createRoot);
        createCharacterButton.setOnAction(event2 -> {
          var field = (TextField) textField.getChildren().get(1);
          controller.createPlayerCharacter(
              characterClass, field.getText(),
              finalStats.get(Stats.MAX_HP),
              finalStats.get(Stats.DEF),
              finalStats.get(Stats.MAX_MP));
          displayPlayerParty(400, 100, Color.LIMEGREEN, configRoot);

          // Start Game Button.
          if (controller.getPlayerCharacters().size() == controller.getPartySize()) {
            configRoot.getChildren().remove(createRoot);
            var vsText = createRectangleText(
                "V/S ", 670, 360, Color.CRIMSON, configRoot);
            uniformScale(vsText, 3);

            //Randomize enemies and their stats.
            var randomizeEnemies = createButton(
                "Randomize Enemies", 615, 450, configRoot);
            randomizeEnemies.setOnAction(event4 -> {

              if (enemiesRoot.getChildren().size() > 0) {
                enemiesRoot.getChildren().remove(0);

              }
              controller.cleanEnemyParty();
              List<Integer> range = IntStream.rangeClosed(
                  0, controller.generateRandom(0, 3)).boxed().collect(Collectors.toList());
              for (var enemy : range) {
                var randomStats =
                    controller.getRandomStats();
                controller.createEnemyCharacter(
                    String.valueOf(EnemyName.values()[
                        controller.generateRandom(
                            0, EnemyName.values().length - 1)]),
                    randomStats.get(Stats.MAX_HP),
                    randomStats.get(Stats.DMG),
                    randomStats.get(Stats.DEF),
                    randomStats.get(Stats.WEIGHT));
              }

              displayEnemyParty(
                  900, 50, Color.TOMATO, enemiesRoot);

              //Start game button.
              var startButton = createButton(
                  "Start Game", 1100, 650, configRoot);
              uniformScale(startButton, 2);
              startButton.setOnAction(event3 -> {

                var randomStats0 =
                    controller.getRandomStats();
                controller.createWeapon(
                    WeaponType.AXE, "Stormbreaker",
                    randomStats0.get(Stats.DMG),
                    randomStats0.get(Stats.WEIGHT),
                    randomStats0.get(Stats.DMG));

                var randomStats1 =
                    controller.getRandomStats();
                controller.createWeapon(
                    WeaponType.SWORD, "Frostmourne",
                    randomStats1.get(Stats.DMG),
                    randomStats1.get(Stats.WEIGHT),
                    randomStats1.get(Stats.DMG));

                var randomStats2 =
                    controller.getRandomStats();
                controller.createWeapon(
                    WeaponType.KNIFE, "Chucky's Knife",
                    randomStats2.get(Stats.DMG),
                    randomStats2.get(Stats.WEIGHT),
                    randomStats2.get(Stats.DMG));

                var randomStats3 =
                    controller.getRandomStats();
                controller.createWeapon(
                    WeaponType.BOW, "Legolas Bow",
                    randomStats3.get(Stats.DMG),
                    randomStats3.get(Stats.WEIGHT),
                    randomStats3.get(Stats.DMG));

                var randomStats4 =
                    controller.getRandomStats();
                controller.createWeapon(
                    WeaponType.STAFF, "Elder Wand",
                    randomStats4.get(Stats.DMG),
                    randomStats4.get(Stats.WEIGHT),
                    randomStats4.get(Stats.DMG));
                controller.tryToStartGame();
                controller.tryToBeginTurn();
                if (controller.getCharacterPlaying()
                    .getInfo().get(Stats.CHARACTER_TYPE)
                    .equals(String.valueOf(CharacterType.ENEMY))) {
                  try {
                    inEnemyTurn(stage);
                  } catch (FileNotFoundException e) {
                    e.printStackTrace();
                  }
                } else {
                  try {
                    inPlayerTurn(stage);
                  } catch (FileNotFoundException e) {
                    e.printStackTrace();
                  }
                }
              });
            });
          }
        });
      }
    });

    uniformScale(setButton, 1.3);
    uniformScale(partyChoice, 1.3);

    configRoot.getChildren().add(createRoot);
    configRoot.getChildren().add(enemiesRoot);
    configRoot.getChildren().add(partySizeRoot);
    createScene(stage, configRoot);
  }

  /**
   * This is the scene when a player character is in his turn.
   */
  private void inPlayerTurn(Stage stage) throws FileNotFoundException {
    var inGameRoot = new Group();
    var turnRoot = new Group();
    var equipStateRoot = new Group();
    var attackStateRoot = new Group();
    var endTurnStateRoot = new Group();
    readImage("game_background.jpg", 0, 0, inGameRoot);
    stage.setTitle("Final reality : Player Playing");
    displayCharacterPlaying(
        controller.getCharacterPlaying(), 600,
        75, Color.AQUA, turnRoot);

    createWeaponChoiceBox(
        525, 400, controller.getWeaponsString(),
        Color.AQUA, equipStateRoot);

    displayPlayerParty(
        200, 50, Color.AQUAMARINE, turnRoot);

    displayEnemyParty(
        900, 50, Color.INDIANRED, turnRoot);

    //Sets the selected weapon as equipped weapon.
    var setWeaponButton = createButton(
        "Set Weapon", 600, 450, equipStateRoot);
    setWeaponButton.setOnAction(event -> {
      var character = (IPlayerCharacter) controller.getCharacterPlaying();
      controller.tryEquip(character, weapon);
      if (character.getEquippedWeapon() != null) {
        turnRoot.getChildren().remove(equipStateRoot);

        var enemyArray = new ArrayList<String>();
        for (var enemy : controller.getEnemies()){
          enemyArray.add(enemy.toString());
        }
        createObjectiveChoiceBox(
            550, 400, enemyArray,
            Color.AQUA, attackStateRoot);
        displayEquippedWeapon(600, 250, Color.ALICEBLUE, turnRoot);

        //Attacks the selected enemy.
        var attackButton = createButton(
            "Attack", 600, 450, attackStateRoot);
        attackButton.setOnAction(event1 -> {
          if (controller.getObjective() != null){
            controller.tryToAttack(
                controller.getCharacterPlaying(),
                controller.getObjective());
            turnRoot.getChildren().remove(attackStateRoot);

            //Ends the game if the win conditions are fulfilled.
            if (controller.getState().equals(String.valueOf(States.END_GAME_STATE))){
              try {
                endGame(stage);
              } catch (FileNotFoundException e) {
                e.printStackTrace();
              }
            }
            else {
              controller.tryToGoToEndTurn();
            }
            //Displays the attack recap.
            var info = controller.getObjective().getInfo();
            createRectangleText(
                info.get(Stats.NAME) + " new HP -> " +
                    info.get(Stats.HP) + "     ",
                575, 450, Color.LIGHTPINK, endTurnStateRoot);

            //Ends the current character's turn.
            var endTurnButton =
                createButton("End Turn", 600, 500, endTurnStateRoot);
            endTurnButton.setOnAction(event2 -> {
              turnRoot.getChildren().remove(endTurnButton);

              try {
                checkWhereToGo(stage);
              } catch (FileNotFoundException e) {
                e.printStackTrace();
              }
            });
          }
        });
      }
      else {
        createRectangleText(
            "You can't equip that weapon   ", 575, 400,
            Color.CRIMSON, equipStateRoot);
      }
    });

    turnRoot.getChildren().add(endTurnStateRoot);
    turnRoot.getChildren().add(attackStateRoot);
    turnRoot.getChildren().add(equipStateRoot);
    inGameRoot.getChildren().add(turnRoot);
    createScene(stage, inGameRoot);
  }

  /**
   * This is the scene when a enemy character is in his turn.
   */
  private void inEnemyTurn(Stage stage) throws FileNotFoundException {
    var inGameRoot = new Group();
    var turnRoot = new Group();
    var attackRoot = new Group();
    var endTurnRoot = new Group();

    readImage("game_background.jpg", 0, 0, inGameRoot);
    stage.setTitle("Final reality : Enemy Playing");

    displayCharacterPlaying(
        controller.getCharacterPlaying(), 600,
        75, Color.AQUA, turnRoot);

    displayPlayerParty(
        200, 50, Color.AQUAMARINE, turnRoot);

    displayEnemyParty(
        900, 50, Color.INDIANRED, turnRoot);

    //The enemy selects the target.
    controller.tryToGoToSelectingTarget();
    controller.setObjective(
        controller.getPlayerCharacters().get(
            controller.generateRandom(
                0, (controller.getPlayerCharacters()).size()-1)));
    controller.tryToAttack(
        controller.getCharacterPlaying(), controller.getObjective());

    //Displays the recap of the enemy attack.
    var nextButton1 = createButton(
        "Next", 600, 350, attackRoot);
    nextButton1.setOnAction(event -> {
      turnRoot.getChildren().remove(attackRoot);
      var info = controller.getObjective().getInfo();
      createRectangleText(
          info.get(Stats.NAME) + " new HP -> " +
              info.get(Stats.HP) + "     ",
          575, 300, Color.LIGHTPINK, turnRoot);

      //Finish the enemy's turn.
      var nextButton2 = createButton(
          "Next", 600, 350, endTurnRoot);
      nextButton2.setOnAction(event1 -> {
        turnRoot.getChildren().remove(endTurnRoot);
        if (controller.getState().equals(String.valueOf(States.END_GAME_STATE))){
          try {
            endGame(stage);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        }
        else {
          controller.tryToGoToEndTurn();
          try {
            checkWhereToGo(stage);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        }
        turnRoot.getChildren().remove(endTurnRoot);
      });
    });

    turnRoot.getChildren().add(endTurnRoot);
    turnRoot.getChildren().add(attackRoot);
    inGameRoot.getChildren().add(turnRoot);
    createScene(stage, inGameRoot);
  }

  /**
   * This is the scene when the queue is empty.
   */
  private void waitingGame(Stage stage) throws FileNotFoundException {
    var waitingRoot = new Group();
    readImage("waiting.jpg", 0, 0, waitingRoot);

    //Checks if the controller is in waiting state.
    if (controller.getState()
        .equals(String.valueOf(States.WAITING_STATE))) {
      createRectangleText("All characters are waiting to play   ",
          600, 300, Color.GOLD, waitingRoot);
      waitingAnimator(stage);
    }

    createScene(stage, waitingRoot);
  }

  /**
   * The animation when the queue is empty
   */
  private void waitingAnimator(Stage stage) {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        if (controller.getState()
            .equals(String.valueOf(States.BEGIN_TURN_STATE))) {
          controller.tryToBeginTurn();
          if (controller.getCharacterPlaying()
              .getInfo().get(Stats.CHARACTER_TYPE)
              .equals(String.valueOf(CharacterType.ENEMY))){
            try {
              inEnemyTurn(stage);
            } catch (FileNotFoundException e) {
              e.printStackTrace();
            }
          }
          else {
            try {
              inPlayerTurn(stage);
            } catch (FileNotFoundException e) {
              e.printStackTrace();
            }
          }
          }
        }
    };
    timer.start();
  }

  /**
   * The end game scene for the player and the enemies.
   */
  private void endGame(Stage stage) throws FileNotFoundException {
    var endGameRoot = new Group();

    //End game scene when the players win.
    if (controller.getObjective().getInfo().get(Stats.CHARACTER_TYPE)
        .equals(String.valueOf(CharacterType.ENEMY))){
      readImage("players_win.jpg", 0, 0, endGameRoot);
      var players = createRectangleText(
          "You Win    ", 630,
          600, Color.FORESTGREEN, endGameRoot);
      uniformScale(players, 4);
    }

    //End game scene when the enemies win.
    else {
      readImage("enemies_win.jpg", 0, 0, endGameRoot);
      var enemies = createRectangleText(
          "You Lost  ", 630,
          600, Color.CRIMSON, endGameRoot);
      var text = (Label) enemies.getChildren().get(1);
      text.setFont(Font.font("Chiller", 20));
      text.setLayoutY(595);
      uniformScale(enemies, 4);
    }

    createScene(stage, endGameRoot);
  }

  /**
   * Checks if the gui goes to wait scene or the next turn.
   */
  private void checkWhereToGo(Stage stage) throws FileNotFoundException {
    controller.tryToEndTurn();
    if (controller.getQueue().isEmpty()){
      waitingGame(stage);
    }
    else {
      controller.tryToBeginTurn();
      if (controller.getCharacterPlaying()
          .getInfo().get(Stats.CHARACTER_TYPE)
          .equals(String.valueOf(CharacterType.ENEMY))){
        inEnemyTurn(stage);
      }
      else {
        inPlayerTurn(stage);
      }
    }
  }

  /**
   * Displays the information of the player character equipped weapon.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param fill Color of the rectangle.
   * @param mainRoot Main root of the scene.
   */
  private void displayEquippedWeapon(
      double X, double Y,
      Color fill, Group mainRoot) {
    var root = new Group();
    var title = createRectangleText(
        "Weapon Equipped:     ", X,
        Y-40, fill, root);
    uniformScale(title, 1.5);
    var info = weapon.getInfo();
    createRectangleText(
        Stats.NAME + ": " + info.get(Stats.NAME) + "      ",
        X, Y , fill, root);
    createRectangleText(
        Stats.WEAPON_TYPE +
            ": " + info.get(Stats.WEAPON_TYPE) + "         ",
        X + 10, Y + 30, fill, root);
    createRectangleText(
        Stats.DMG + ": " + info.get(Stats.DMG) + "   ",
        X + 10, Y + 60 , fill, root);
    createRectangleText(
        Stats.WEIGHT + ": " + info.get(Stats.WEIGHT) + "   ",
        X + 10, Y + 90 , fill, root);
    mainRoot.getChildren().add(root);
  }


  /**
   * Displays the information of the character.
   * @param character The character that will be displayed.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param fill Color of the rectangle.
   * @param mainRoot Main root of the scene.
   */
  private void displayCharacterPlaying(
      ICharacter character,
      double X, double Y,
      Color fill, Group mainRoot) {
    var root = new Group();
    var title = createRectangleText(
        "Character Playing: ", X,
        Y-50, Color.DARKGOLDENROD, root);
    uniformScale(title, 2);
    var info = character.getInfo();
    createRectangleText(
        Stats.NAME + ": " + info.get(Stats.NAME) + "      ",
        X, Y , fill, root);
    createRectangleText(
        Stats.CHARACTER_TYPE +
            ": " + info.get(Stats.CHARACTER_TYPE) + "         ",
        X + 10, Y + 25, fill, root);
    createRectangleText(
        Stats.HP + ": " + info.get(Stats.HP) + "/" + info.get(Stats.MAX_HP) + "   ",
        X + 10, Y + 50 , fill, root);
    createRectangleText(
        Stats.DEF + ": " + info.get(Stats.DEF) + "   ",
        X + 10, Y + 75 , fill, root);
    mainRoot.getChildren().add(root);
  }


  /**
   * Displays the information of the enemies party.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param fill Color of the rectangle.
   * @param mainRoot Main root of the scene.
   */
  private void displayEnemyParty(
      double X, double Y,
      Color fill, Group mainRoot) {
    var root = new Group();
    var enemiesStats =
        controller.getEnemiesInfo();
    var posYIter = 0;
    for (var enemy : enemiesStats.keySet()) {
      var stats = enemiesStats.get(enemy);
      createRectangleText(
          Stats.NAME + ": " + stats.get(Stats.NAME) + "        ",
          X, Y + (posYIter * 160), fill, root);
      createRectangleText(
          Stats.CHARACTER_TYPE +
              ": " + stats.get(Stats.CHARACTER_TYPE) + "         ",
          X + 10, Y + 25 + (posYIter * 160), fill, root);
      createRectangleText(
          Stats.HP + ": " + stats.get(Stats.HP)
              + "/" + stats.get(Stats.MAX_HP) + "   ",
          X + 10, Y + 50 + (posYIter * 160), fill, root);
      createRectangleText(
          Stats.DEF + ": " + stats.get(Stats.DEF) + "   ",
          X + 10, Y + 75 + (posYIter * 160), fill, root);
      createRectangleText(
          Stats.DMG + ": " + stats.get(Stats.DMG) + "   ",
          X + 10, Y + 100 + (posYIter * 160), fill, root);
      createRectangleText(
          Stats.WEIGHT + ": " + stats.get(Stats.WEIGHT) + "   ",
          X + 10, Y + 125 + (posYIter * 160), fill, root);
      posYIter ++;
    }
    mainRoot.getChildren().add(root);
  }

  /**
   * Displays the objective choice box.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param setValues The values in the choice box.
   * @param fill Color of the rectangle.
   * @param mainRoot Main root of the scene.
   */
  private void createObjectiveChoiceBox (
      double X, double Y, ArrayList<String> setValues,
      Color fill, Group mainRoot) {
    var root = new Group();
    ObservableList<String> values = FXCollections.observableArrayList();
    values.addAll(setValues);
    var choice = new ChoiceBox<>(values);

    choice.setLayoutX(X);
    choice.setLayoutY(Y);

    var text = "Objectives: ";
    createRectangleText(
        text, X - (text.length() * 5.4), Y,
        fill, root);
    SingleSelectionModel<String> model =
        choice.getSelectionModel();
    model.selectedItemProperty().addListener(
        (observableValue, s, t1) -> {
          controller.tryToSetObjective(
              controller.findCharacter(t1));
        });

    root.getChildren().add(choice);
    mainRoot.getChildren().add(root);
  }


  /**
   * Displays the objective choice box.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param setValues the values in the choice box.
   * @param fill Color of the rectangle.
   * @param mainRoot Main root of the scene.
   */
  private void createWeaponChoiceBox (
      double X, double Y, ArrayList<String> setValues,
      Color fill, Group mainRoot) {
    var root = new Group();
    ObservableList<String> values = FXCollections.observableArrayList();
    values.addAll(setValues);
    var choice = new ChoiceBox<>(values);

    choice.setLayoutX(X);
    choice.setLayoutY(Y);

    var text = "Weapons:   ";
    createRectangleText(
        text, X - (text.length() * 5.4), Y,
        fill, root);
    SingleSelectionModel<String> model =
        choice.getSelectionModel();
    model.selectedItemProperty().addListener(
        (observableValue, s, t1) -> {
          var character = (IPlayerCharacter) controller.getCharacterPlaying();
          if (character.getEquippedWeapon() == null) {
            weapon = controller.findWeapon(t1);
          }
        });

    root.getChildren().add(choice);
    mainRoot.getChildren().add(root);
  }

  /**
   * Displays the information of the player's party.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param fill Color of the rectangle.
   * @param mainRoot Main root of the scene.
   */
  private void displayPlayerParty(
      double X, double Y,
      Color fill, Group mainRoot){
    var root = new Group();
    var partyStats =
        controller.getPlayerCharactersInfo();
    var posYIter = 0;
    for (var character : partyStats.keySet()){
      var stats = partyStats.get(character);
      createRectangleText(
          Stats.NAME + ": " + stats.get(Stats.NAME) + "   ",
          X, Y + (posYIter * 135), fill, root);
      createRectangleText(
          Stats.CHARACTER_TYPE +
              ": " + stats.get(Stats.CHARACTER_TYPE) + "         ",
          X + 10, Y + 25 + (posYIter * 135), fill, root);
      createRectangleText(
          Stats.HP + ": " + stats.get(Stats.HP) + "/" + stats.get(Stats.MAX_HP) + "   ",
          X + 10, Y + 50 + (posYIter * 135), fill, root);
      createRectangleText(
          Stats.DEF + ": " + stats.get(Stats.DEF) + "   ",
          X + 10, Y + 75 + (posYIter * 135), fill, root);
      createRectangleText(
          Stats.MAX_MP + ": " + stats.get(Stats.MAX_MP) + "   ",
          X + 10, Y + 100 + (posYIter * 135), fill, root);
      posYIter ++;
    }
    mainRoot.getChildren().add(root);
  }

  /**
   * Displays the stats of the hash map.
   * @param stats The stats that will be displayed.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param fill Color of the rectangle.
   * @param mainRoot Main root of the scene.
   */
  private void displayBasicStats(
      HashMap<Stats, Integer> stats,
      double X, double Y, Color fill, Group mainRoot){
    var root = new Group();
    createRectangleText(
        Stats.MAX_HP + ": " + stats.get(Stats.MAX_HP) + "   ",
        X, Y, fill, root);
    createRectangleText(
        Stats.DEF + ": " + stats.get(Stats.DEF) + "  ",
        X + 25, Y + 30, fill, root);
    createRectangleText(
        Stats.MAX_MP + ": " + stats.get(Stats.MAX_MP) + "   ",
        X, Y + 60, fill, root);
    mainRoot.getChildren().add(root);
  }

  /**
   * Displays the characetr class choice box.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param setValues The values in the choice box.
   * @param fill Color of the rectangle.
   * @param mainRoot Main root of the scene.
   */
  private void createClassChoiceBox (
      double X, double Y, ArrayList<String> setValues,
      Color fill, Group mainRoot) {
    var root = new Group();
    ObservableList<String> values = FXCollections.observableArrayList();
    values.addAll(setValues);
    var choice = new ChoiceBox<>(values);

    choice.setLayoutX(X);
    choice.setLayoutY(Y);
    var text = "Character Class: ";
    createRectangleText(
        text, X - (text.length() * 5.4), Y,
        fill, root);
    SingleSelectionModel<String> model =
        choice.getSelectionModel();
    model.selectedItemProperty().addListener(
        (observableValue, s, t1) -> {
          characterClass = CharacterType.valueOf(t1);
        });
    root.getChildren().add(choice);
    mainRoot.getChildren().add(root);
  }

  /**
   * Displays the party size choice box.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param setValues The values in the choice box.
   * @param fill Color of the rectangle.
   * @param mainRoot Main root of the scene.
   */
  private Group createPartyChoiceBox (
      String text,
      double X, double Y, ArrayList<String> setValues,
      Color fill, Group mainRoot) {
    var root = new Group();
    createRectangleText(
        text, X - (text.length() * 5.8), Y,
        fill, root);

    ObservableList<String> values = FXCollections.observableArrayList();
    values.addAll(setValues);
    var partyChoice = new ChoiceBox<>(values);
    partyChoice.setLayoutX(X);
    partyChoice.setLayoutY(Y);
    SingleSelectionModel<String> model =
        partyChoice.getSelectionModel();
    model.selectedItemProperty().addListener(
        (observableValue, s, t1) -> {
          controller.setPartySize(Integer.parseInt(t1));
        });


    root.getChildren().add(partyChoice);
    mainRoot.getChildren().add(root);
    return  root;
  }


  /**
   * Displays a labeled text field.
   * @param text The label of the text field.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param fill Color of the rectangle.
   * @param mainRoot Main root of the scene.
   */
  private Group createLabeledTextField(
      String text, double X, double Y, Color fill, Group mainRoot) {
    var root = new Group();
    createRectangleText(
        text, X - (text.length() * 5.5), Y, fill, root);
    createTextField(X, Y, root);
    mainRoot.getChildren().add(root);
    return root;
  }

  /**
   * Displays a text field.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param root Root of the scene.
   */
  private TextField createTextField (
      double X, double Y, Group root) {
    var field = new TextField();
    field.setLayoutX(X);
    field.setLayoutY(Y);
    root.getChildren().add(field);
    return field;
  }

  /**
   * Scales the node to the given value.
   * @param node The node that will be scaled.
   * @param scale The scale value.
   */
  private void uniformScale (Node node, double scale) {
    node.setScaleX(scale);
    node.setScaleY(scale);
  }

  /**
   * Displays a rectangle with text inside.
   * @param text The label of the text field.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param fill Color of the rectangle.
   * @param mainRoot Main root of the scene.
   */
  private Group createRectangleText(
      String text,
      double X, double Y, Paint fill, Group mainRoot) {
    var root = new Group();
    createRectangle(
        text.length() * 5 + 18, 24, X-10, Y, fill, root);
    createLabel(text, X, Y + 3, root);
    mainRoot.getChildren().add(root);
    return root;
  }


  /**
   * Displays a rectangle with the given sizes and colors.
   * @param width The width of the rectangle.
   * @param height The height of the rectangle.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param fill Color of the rectangle.
   * @param root Root of the scene.
   */
  private Rectangle createRectangle(
      double width, double height,
      double X, double Y, Paint fill, Group root) {
    var rectangle = new Rectangle();
    rectangle.setHeight(height);
    rectangle.setWidth(width);
    rectangle.setX(X);
    rectangle.setY(Y);
    rectangle.setFill(fill);
    root.getChildren().add(rectangle);
    return rectangle;
  }

  /**
   * Reads and displays the given picture.
   * @param fileName The name of the picture to read.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param root Root of the scene.
   */
  private ImageView readImage(
      String fileName, double X,
      double Y, Group root)
      throws FileNotFoundException {
    var img = new ImageView(
        new Image(
            new FileInputStream(
                RESOURCE_PATH + fileName)));
    img.setLayoutX(X);
    img.setLayoutY(Y);
    root.getChildren().add(img);
    return img;
  }

  /**
   * Displays a label.
   * @param text The label of the text field.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param root Root of the scene.
   */
  private Label createLabel (
      String text, double X, double Y, Group root) {
    var label = new Label(text);
    label.setLayoutX(X);
    label.setLayoutY(Y);
    root.getChildren().add(label);
    return label;
  }

  /**
   * Creates a scene
   * @param root Root of the scene.
   */
  private Scene createScene (Stage stage, Group root) {
    var scene = new Scene(root, 1280, 720);
    stage.setScene(scene);
    return scene;
  }

  /**
   * Displays a labeled button.
   * @param text The label of the button.
   * @param X Pos in the X axis.
   * @param Y Pos in the Y axis.
   * @param root Root of the scene.
   */
  private @NotNull Button createButton(
      String text, double X, double Y, Group root) {
    var button = new Button(text);
    button.setLayoutX(X);
    button.setLayoutY(Y);
    button.setFocusTraversable(false);
    root.getChildren().add(button);
    return button;
  }
}